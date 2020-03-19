package day3;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day3Part1
{
	private static String[][] grid = new String[100][100];
	private static ArrayList<List<String>> linesList = new ArrayList<List<String>>();
	
	
	public static void main(String[] args)
	{	
		readInput();
		
		for(int i = 0; i< grid.length; i++)
			{
				for(int j = 0; j<grid[0].length; j++)
					{
						grid[i][j] = ".";
					}
			}
		Point startingPoint = new Point(grid.length/2, grid[0].length/2);
		grid[startingPoint.y][startingPoint.x] = "Y";
		
		for(int i=0; i<linesList.size(); i++)
			{
				Point lineStart = new Point(startingPoint.y, startingPoint.x);
				List<String> line = linesList.get(i);
				
				for(int j=0; j<line.size(); j++)
					{
						String section = line.get(j);
						int length = Integer.parseInt(section.substring(1));
						switch(section.charAt(0)) {
							case 'D':
							{
								for(int s= lineStart.y; s <=lineStart.y+length; s++ )
									{
										if(grid[s][lineStart.x].equals("Y") == true) continue;
										else if(grid[s][lineStart.x].equals(".") == true)  grid[s][lineStart.x] = i+"";
										else if(grid[s][lineStart.x].equals("X") == true) continue;
										else if(grid[s][lineStart.x].equals(i+"") == false) grid[s][lineStart.x] = "X";
									}
								lineStart.y = lineStart.y+length;
								break;
							}
							
							case 'R':
							{
								for(int s= lineStart.x; s <=lineStart.x+length; s++ )
									{
										if(grid[lineStart.y][s].equals("Y") == true) continue;
										else if(grid[lineStart.y][s].equals(".") == true)  grid[lineStart.y][s] = i+"";
										else if(grid[lineStart.y][s].equals("X") == true) continue;
										else if(grid[lineStart.y][s].equals(i+"") == false) grid[lineStart.y][s] = "X";
									}
								lineStart.x = lineStart.x+length;
								break;
							}
							case 'L':
							{
								for(int s= lineStart.x-length; s >= lineStart.x; s++ )
									{
										if(grid[lineStart.y][s].equals("Y") == true) continue;
										else if(grid[lineStart.y][s].equals(".") == true)  grid[lineStart.y][s] = i+"";
										else if(grid[lineStart.y][s].equals("X") == true) continue;
										else if(grid[lineStart.y][s].equals(i+"") == false) grid[lineStart.y][s] = "X";
									}
								lineStart.x = lineStart.x-length;
								break;
							}
							case 'U':
							{
								for(int s= lineStart.y-length; s >=lineStart.y; s++ )
									{
										if(grid[s][lineStart.x].equals("Y") == true) continue;
										else if(grid[s][lineStart.x].equals(".") == true)  grid[s][lineStart.x] = i+"";
										else if(grid[s][lineStart.x].equals("X") == true) continue;
										else if(grid[s][lineStart.x].equals(i+"") == false) grid[s][lineStart.x] = "X";
									}
								lineStart.y = lineStart.y-length;
								break;
							}
						}
						
					}
			}
		
		
		for(int i = 0; i< grid.length; i++)
			{
				for(int j = 0; j<grid[0].length; j++)
					{
						System.out.print(grid[i][j]);
					}
				System.out.println();
			}
	}
	
	
	public static void readInput()
	{
		try
			{
				Scanner sc = new Scanner(new File("C:\\GIT\\Advent_of_Code_2019\\src\\day3\\input_day_3.txt"));
				
				while(sc.hasNextLine()) {
					String inputLine = sc.nextLine();
					String[] separatedLineInstructions = inputLine.split(",");
					List<String> line = Arrays.asList(separatedLineInstructions);
					linesList.add(line);
					System.out.println(line);
				}


			}

		catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
