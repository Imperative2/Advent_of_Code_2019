package day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day8Part2
{
	private static List<int[][]> layersList = new ArrayList<>();
	private static int[][] finalLayer = new int[6][25];
	
	public static void main(String[] args)
	{
		readInput();
		
		for(int i=0; i<6; i++)
			{
				for(int j=0; j<25; j++)
					{
						finalLayer[i][j] = 2;
					}
			}

		for(int[][] inputLayer: layersList)
			{
				for(int i=0; i<6; i++)
					{
						for(int j=0; j<25; j++)
							{
								if(finalLayer[i][j] == 2)
									finalLayer[i][j] = inputLayer[i][j];
							}
					}
			}
		
		for(int i=0; i<6; i++)
			{
				for(int j=0; j<25; j++)
					{
						if(finalLayer[i][j] == 1)
							System.out.print("#");
						else if(finalLayer[i][j] == 0)
							System.out.print(" ");

					}
				System.out.println();
			}
		
	}

	public static void readInput()
	{
		try
			{
				int[][] layer = new int[6][25];
				Scanner sc = new Scanner(new File("C:\\GIT\\Advent_of_Code_2019\\src\\day8\\input_day_8.txt"));
				String input = sc.nextLine();
				System.out.println(input.length()/150);
				for(int i=0; i<input.length(); i++)
					{
						if(i!= 0 && i%150==0)
							{
								layersList.add(layer);
								layer = new int[6][25];
							}
						int value = Integer.parseInt(input.charAt(i)+"");
						int a = i%150;
						int row = a/25;
						
						int column = a%25;
						System.out.println("row: "+row+" column: "+column+" value: "+value+" i: "+i);
						layer[row][column] = value;
					}
				layersList.add(layer);
				System.out.println(layersList.size());
			}

		catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
