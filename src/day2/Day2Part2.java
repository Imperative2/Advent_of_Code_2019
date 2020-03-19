package day2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day2Part2
{
	private static List<Integer> opcodesList;


	public static void main(String[] args)
	{
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				readInput();
				
				opcodesList.set(1, i);
				opcodesList.set(2, j);
				
				int index = 0;
				while(opcodesList.get(index) != 99)
					{
					//	System.out.println("index: "+index+"  element "+opcodesList.get(index)+" "+opcodesList.toString());
						if(opcodesList.get(index) == 1) 
						{
							add(index);
							index+=4;
						}
						else if(opcodesList.get(index) == 2)
						{
							mull(index);
							index+=4;
						}
					}
				if(opcodesList.get(0) == 19690720) {
					System.out.println("i: "+i+" j: "+j);
				}

			}
		}
	}

	public static void add(int index) {
		int arg1Addr = opcodesList.get(index+1);
		int arg2Addr = opcodesList.get(index+2);
		int destAddr = opcodesList.get(index+3);
		
		int sum = opcodesList.get(arg1Addr) + opcodesList.get(arg2Addr);
		opcodesList.set(destAddr, sum);

	}
	
	public static void mull(int index) {
		int arg1Addr = opcodesList.get(index+1);
		int arg2Addr = opcodesList.get(index+2);
		int destAddr = opcodesList.get(index+3);
		
		int product = opcodesList.get(arg1Addr) * opcodesList.get(arg2Addr);
		opcodesList.set(destAddr, product);

	}

	public static void readInput()
	{
		try
			{
				Scanner sc = new Scanner(new File("C:\\GIT\\Advent_of_Code_2019\\src\\day2\\input_day_2.txt"));
				String input = sc.nextLine();
				input.trim();
				String[] separatedInput = input.split(",");

				opcodesList = Arrays.stream(separatedInput).map(Integer::valueOf).collect(Collectors.toList());
//				System.out.println(opcodesList.toString());
			}

		catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}

