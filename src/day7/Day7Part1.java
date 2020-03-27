package day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day7Part1
{
	public static void main(String[] args)
	{
		int maxValue = Integer.MIN_VALUE;
		

		for (int arg1 = 0; arg1 < 5; arg1++)
			{
				List<Integer> permutationsList = new ArrayList<>();
				permutationsList.add(0);
				permutationsList.add(1);
				permutationsList.add(2);
				permutationsList.add(3);
				permutationsList.add(4);
				int a1 = permutationsList.remove(arg1);

				for (int arg2 = 0; arg2 < 4; arg2++)
					{
						List<Integer> permutationsListCopy1 = new ArrayList<>(permutationsList);
						int a2 = permutationsListCopy1.remove(arg2).intValue();

						for (int arg3 = 0; arg3 < 3; arg3++)
							{
								List<Integer> permutationsListCopy2 = new ArrayList<>(permutationsListCopy1);
								int a3 = permutationsListCopy2.remove(arg3).intValue();

								for (int arg4 = 0; arg4 < 2; arg4++)
									{
										List<Integer> permutationsListCopy3 = new ArrayList<>(permutationsListCopy2);
										int a4 = permutationsListCopy3.remove(arg4).intValue();

										for (int arg5 = 0; arg5 < 1; arg5++)
											{
												List<Integer> permutationsListCopy4 = new ArrayList<>(permutationsListCopy3);
												int a5 = permutationsListCopy4.remove(arg5).intValue();
												System.out.println("settings: " + a1 + " " + a2 + " " + a3 + " "
														+ a4 + " " + a5);
												OpcodeMachine opcodeMachine1 = new OpcodeMachine(a1, 0);
												opcodeMachine1.calculate();
												int output = opcodeMachine1.getOutput();
												OpcodeMachine opcodeMachine2 = new OpcodeMachine(a2, output);
												opcodeMachine2.calculate();
												output = opcodeMachine2.getOutput();
												OpcodeMachine opcodeMachine3 = new OpcodeMachine(a3, output);
												opcodeMachine3.calculate();
												output = opcodeMachine3.getOutput();
												OpcodeMachine opcodeMachine4 = new OpcodeMachine(a4, output);
												opcodeMachine4.calculate();
												output = opcodeMachine4.getOutput();
												OpcodeMachine opcodeMachine5 = new OpcodeMachine(a5, output);
												opcodeMachine5.calculate();
												output = opcodeMachine5.getOutput();
												if (output > maxValue)
													{
														maxValue = output;
													}
											}
									}
							}
					}
			}

		System.out.println("max value: " + maxValue);
	}
}

class OpcodeMachine
{
	private List<Integer> opcodesList;
	private int phaseSettings = 0;
	private int ampInput = 0;
	private int index = 0;
	private boolean firstInputUsed = false;
	private int output = 0;

	public OpcodeMachine(int phaseSettings, int ampInput)
	{
		this.phaseSettings = phaseSettings;
		this.ampInput = ampInput;
	}

	public void calculate()
	{

		readInput();

		while (opcodesList.get(index) != 99)
			{
//				if(index%100 == 0 )
//					{
//						System.out.println("index: "+index);
//					}
				String opcode = opcodesList.get(index).toString();
				char instruction = opcode.charAt(opcode.length() - 1);
				switch (instruction)
				{
				case '1':
					{
						opcodeAdd(opcode, opcodesList.get(index + 1), opcodesList.get(index + 2),
								opcodesList.get(index + 3));
						index += 4;
						break;
					}
				case '2':
					{
						opcodeMull(opcode, opcodesList.get(index + 1), opcodesList.get(index + 2),
								opcodesList.get(index + 3));
						index += 4;
						break;
					}
				case '3':
					{
						opcodeInput(opcode, opcodesList.get(index + 1));
						index += 2;
						break;
					}
				case '4':
					{
						opcodeOutput(opcode, opcodesList.get(index + 1));
						index += 2;
						break;
					}
				case '5':
					{
						boolean jumped = opcodeJumpIfTrue(opcode, opcodesList.get(index + 1),
								opcodesList.get(index + 2));
						if (jumped == false)
							{
								index += 3;
							}
						break;
					}
				case '6':
					{
						boolean jumped = opcodeJumpIfFalse(opcode, opcodesList.get(index + 1),
								opcodesList.get(index + 2));
						if (jumped == false)
							{
								index += 3;
							}
						break;
					}
				case '7':
					{
						opcodeLessThan(opcode, opcodesList.get(index + 1), opcodesList.get(index + 2),
								opcodesList.get(index + 3));
						index += 4;
						break;
					}
				case '8':
					{
						opcodeEquals(opcode, opcodesList.get(index + 1), opcodesList.get(index + 2),
								opcodesList.get(index + 3));
						index += 4;
						break;
					}
				}
			}

	}

	public void readInput()
	{
		try
			{
				Scanner sc = new Scanner(new File("C:\\GIT\\Advent_of_Code_2019\\src\\day7\\input_day_7.txt"));
				String input = sc.nextLine();
				input.trim();
				String[] separatedInput = input.split(",");

				opcodesList = Arrays.stream(separatedInput).map(Integer::valueOf).collect(Collectors.toList());
			}

		catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
	}

	public void opcodeAdd(String instruction, int arg1, int arg2, int dest)
	{
		int arg1Mode = 0;
		int arg2Mode = 0;

		if (instruction.length() >= 3)
			{
				if (instruction.charAt(instruction.length() - 3) == '1')
					{
						arg1Mode = 1;
					}
			}
		if (instruction.length() >= 4)
			{
				if (instruction.charAt(instruction.length() - 4) == '1')
					{
						arg2Mode = 1;
					}
			}

		int value1 = arg1Mode == 0 ? opcodesList.get(arg1) : arg1;
		int value2 = arg2Mode == 0 ? opcodesList.get(arg2) : arg2;
		int result = value1 + value2;

		opcodesList.set(dest, result);

	}

	public void opcodeMull(String instruction, int arg1, int arg2, int dest)
	{
		int arg1Mode = 0;
		int arg2Mode = 0;

		if (instruction.length() >= 3)
			{
				if (instruction.charAt(instruction.length() - 3) == '1')
					{
						arg1Mode = 1;
					}
			}
		if (instruction.length() >= 4)
			{
				if (instruction.charAt(instruction.length() - 4) == '1')
					{
						arg2Mode = 1;
					}
			}

		int value1 = arg1Mode == 0 ? opcodesList.get(arg1) : arg1;
		int value2 = arg2Mode == 0 ? opcodesList.get(arg2) : arg2;
		int result = value1 * value2;

		opcodesList.set(dest, result);
	}

	public void opcodeInput(String instruction, int dest)
	{
		if (firstInputUsed == false)
			{
				opcodesList.set(dest, phaseSettings);
				firstInputUsed = true;
			}
		else
			{
				opcodesList.set(dest, ampInput);
			}

	}

	public void opcodeOutput(String instruction, int arg1)
	{
		int arg1Mode = 0;

		if (instruction.length() >= 3)
			{
				if (instruction.charAt(instruction.length() - 3) == '1')
					{
						arg1Mode = 1;
					}
			}

		int value1 = arg1Mode == 0 ? opcodesList.get(arg1) : arg1;
		System.out.println("output: " + value1);
		this.output = value1;
	}

	public boolean opcodeJumpIfTrue(String instruction, int arg1, int arg2)
	{
		int arg1Mode = 0;
		int arg2Mode = 0;

		if (instruction.length() >= 3)
			{
				if (instruction.charAt(instruction.length() - 3) == '1')
					{
						arg1Mode = 1;
					}
			}
		if (instruction.length() >= 4)
			{
				if (instruction.charAt(instruction.length() - 4) == '1')
					{
						arg2Mode = 1;
					}
			}

		int value1 = arg1Mode == 0 ? opcodesList.get(arg1) : arg1;
		int value2 = arg2Mode == 0 ? opcodesList.get(arg2) : arg2;
		boolean jump = value1 != 0 ? true : false;
		if (jump == true)
			{
				index = value2;
				return true;
			} else
			return false;
	}

	public boolean opcodeJumpIfFalse(String instruction, int arg1, int arg2)
	{
		int arg1Mode = 0;
		int arg2Mode = 0;

		if (instruction.length() >= 3)
			{
				if (instruction.charAt(instruction.length() - 3) == '1')
					{
						arg1Mode = 1;
					}
			}
		if (instruction.length() >= 4)
			{
				if (instruction.charAt(instruction.length() - 4) == '1')
					{
						arg2Mode = 1;
					}
			}

		int value1 = arg1Mode == 0 ? opcodesList.get(arg1) : arg1;
		int value2 = arg2Mode == 0 ? opcodesList.get(arg2) : arg2;
		boolean jump = value1 == 0 ? true : false;
		if (jump == true)
			{
				index = value2;
				return true;
			} else
				return false;
	}

	public void opcodeLessThan(String instruction, int arg1, int arg2, int dest)
	{
		int arg1Mode = 0;
		int arg2Mode = 0;

		if (instruction.length() >= 3)
			{
				if (instruction.charAt(instruction.length() - 3) == '1')
					{
						arg1Mode = 1;
					}
			}
		if (instruction.length() >= 4)
			{
				if (instruction.charAt(instruction.length() - 4) == '1')
					{
						arg2Mode = 1;
					}
			}

		int value1 = arg1Mode == 0 ? opcodesList.get(arg1) : arg1;
		int value2 = arg2Mode == 0 ? opcodesList.get(arg2) : arg2;
		int result = value1 < value2 ? 1 : 0;

		opcodesList.set(dest, result);
	}

	public void opcodeEquals(String instruction, int arg1, int arg2, int dest)
	{
		int arg1Mode = 0;
		int arg2Mode = 0;

		if (instruction.length() >= 3)
			{
				if (instruction.charAt(instruction.length() - 3) == '1')
					{
						arg1Mode = 1;
					}
			}
		if (instruction.length() >= 4)
			{
				if (instruction.charAt(instruction.length() - 4) == '1')
					{
						arg2Mode = 1;
					}
			}

		int value1 = arg1Mode == 0 ? opcodesList.get(arg1) : arg1;
		int value2 = arg2Mode == 0 ? opcodesList.get(arg2) : arg2;
		int result = value1 == value2 ? 1 : 0;

		opcodesList.set(dest, result);
	}

	public int getOutput()
	{
		return output;
	}

}
