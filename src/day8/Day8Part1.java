package day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day8Part1
{
	private static List<int[][]> layersList = new ArrayList<>();
	
	public static void main(String[] args)
	{
		readInput();
		int layerZeroCount = 0;
		int bestLayer = 0;
		int bestLayerZeroCount = Integer.MAX_VALUE;
		for(int layerCount =0; layerCount < layersList.size(); layerCount++)
			{
				System.out.println();
				int[][] layer = layersList.get(layerCount);
				layerZeroCount = 0;
				for(int i=0; i<6; i++)
					{
						for(int j=0; j<25; j++)
							{
								System.out.print(layer[i][j]);
								if(layer[i][j] == 0)
									{
										layerZeroCount++;
									}
							}
						System.out.println();
					}
			//	System.out.println("layer zeros: "+layerZeroCount);

				if(layerZeroCount < bestLayerZeroCount)
					{
						bestLayerZeroCount = layerZeroCount;
						bestLayer = layerCount;
					}
			}
		System.out.println(bestLayer);
		
		int onesCount = 0;
		int twosCount = 0;
		for(int i=0; i<6; i++)
			{
				for(int j=0; j<25; j++)
					{
						if(layersList.get(bestLayer)[i][j] == 1)
							{
								onesCount++;
							}
						else if(layersList.get(bestLayer)[i][j] == 2)
							{
								twosCount++;
							}
					}
			}
		System.out.println(onesCount*twosCount);
		
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
