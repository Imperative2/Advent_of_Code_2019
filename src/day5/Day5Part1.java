package day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day5Part1
{
	private static List<Integer> opcodesList;
	private static int mainInput = 1;
	private static int index = 0;

	public static void main(String[] args)
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
						opcodeAdd(opcode, opcodesList.get(index+1),opcodesList.get(index+2),opcodesList.get(index+3));
						index+=4;
						break;
					}
				case '2':
					{
						opcodeMull(opcode, opcodesList.get(index+1),opcodesList.get(index+2),opcodesList.get(index+3));
						index+=4;
						break;
					}
				case '3':
					{
						opcodeInput(opcode, opcodesList.get(index+1));
						index+=2;
						break;
					}
				case '4':
					{
						opcodeOutput(opcode, opcodesList.get(index+1));
						index+=2;
						break;
					}
				}
			}

	}

	public static void opcodeAdd(String instruction, int arg1, int arg2, int dest)
	{
		int arg1Mode = 0;
		int arg2Mode = 0;
		
		if(instruction.length()>=3)
			{
				if(instruction.charAt(instruction.length()-3) == '1')
					{
						arg1Mode = 1;
					}
			}
		if(instruction.length()>=4)
			{
				if(instruction.charAt(instruction.length()-4) == '1')
					{
						arg2Mode = 1;
					}
			}
		

		int value1 = arg1Mode == 0? opcodesList.get(arg1): arg1;
		int value2 = arg2Mode == 0? opcodesList.get(arg2): arg2;
		int result = value1+value2;
		
		opcodesList.set(dest, result);
		
	}

	public static void opcodeMull(String instruction, int arg1, int arg2, int dest)
	{
		int arg1Mode = 0;
		int arg2Mode = 0;
		
		if(instruction.length()>=3)
			{
				if(instruction.charAt(instruction.length()-3) == '1')
					{
						arg1Mode = 1;
					}
			}
		if(instruction.length()>=4)
			{
				if(instruction.charAt(instruction.length()-4) == '1')
					{
						arg2Mode = 1;
					}
			}
		

		int value1 = arg1Mode == 0? opcodesList.get(arg1): arg1;
		int value2 = arg2Mode == 0? opcodesList.get(arg2): arg2;
		int result = value1*value2;
		
		opcodesList.set(dest, result);
	}
	
	public static void opcodeInput(String instruction, int dest)
	{
		opcodesList.set(dest, mainInput);
	}
	
	public static void opcodeOutput(String instruction, int arg1)
	{
		int arg1Mode = 0;
		
		if(instruction.length()>=3)
			{
				if(instruction.charAt(instruction.length()-3) == '1')
					{
						arg1Mode = 1;
					}
			}
		
		int value1 = arg1Mode == 0? opcodesList.get(arg1): arg1;
		System.out.println("output: "+value1);
	}

	public static void readInput()
	{
		try
			{
				Scanner sc = new Scanner(new File("C:\\GIT\\Advent_of_Code_2019\\src\\day5\\input_day_5.txt"));
				String input = sc.nextLine();
				input.trim();
				String[] separatedInput = input.split(",");

				opcodesList = Arrays.stream(separatedInput).map(Integer::valueOf).collect(Collectors.toList());
				System.out.println(opcodesList.toString());
			}

		catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
