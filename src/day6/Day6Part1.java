package day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day6Part1
{
	public static Map<String, Orbit> orbitsMap = new HashMap<>();

	public static void main(String[] args)
	{
		readInput();

		System.out.println(orbitsMap.toString());
		
		int orbitsCount = 0;

		for(Orbit orbit : orbitsMap.values())
			{
				orbitsCount +=orbit.countOrbits();
			}
		System.out.println(orbitsCount);
	}

	public static void readInput()
	{
		try
			{
				Scanner sc = new Scanner(new File("C:\\GIT\\Advent_of_Code_2019\\src\\day6\\input_day_6.txt"));
				
				while(sc.hasNextLine() == true)
					{
						String input = sc.nextLine();
						System.out.println(input);
						input.trim();
						String[] separatedInput = input.split("\\)");

						String planet1 = separatedInput[0];
						String planet2 = separatedInput[1];
						
						Orbit orb1 = null;
						Orbit orb2 = null;

						if (orbitsMap.containsKey(planet1) == false && orbitsMap.containsKey(planet2) == false)
							{
								orb1 = new Orbit(planet1);
								orb2 = new Orbit(planet2);
								orb1.addNewChildrenOrbit(orb2);
								orb2.addNewParentOrbit(orb1);
							} else if (orbitsMap.containsKey(planet1) == true && orbitsMap.containsKey(planet2) == true)
							{
								orb1 = orbitsMap.get(planet1);
								orb2 = orbitsMap.get(planet2);
								orb1.addNewChildrenOrbit(orb2);
								orb2.addNewParentOrbit(orb1);
							} else if (orbitsMap.containsKey(planet1) == true && orbitsMap.containsKey(planet2) == false)
							{
								orb1 = orbitsMap.get(planet1);
								orb2 = new Orbit(planet2);
								orb1.addNewChildrenOrbit(orb2);
								orb2.addNewParentOrbit(orb1);
							} else if (orbitsMap.containsKey(planet1) == false && orbitsMap.containsKey(planet2) == true)
							{
								orb1 = new Orbit(planet1);
								orb2 = orbitsMap.get(planet2);
								orb1.addNewChildrenOrbit(orb2);
								orb2.addNewParentOrbit(orb1);
							}
						
						orbitsMap.putIfAbsent(planet1, orb1);
						orbitsMap.putIfAbsent(planet2, orb2);
					}
			}

		catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}

class Orbit
{
	String name;
	List<Orbit> listParentOrbits = new ArrayList<>();
	List<Orbit> listChildrenOrbits = new ArrayList<>();

	public Orbit(String name)
	{
		this.name = name;
	}

	public List<Orbit> getParentOrbits()
	{
		return listParentOrbits;
	}

	public List<Orbit> getChildrenOrbits()
	{
		return listChildrenOrbits;
	}

	public void addNewParentOrbit(Orbit parentOrbit)
	{
		if (listParentOrbits.contains(parentOrbit) == false)
			listParentOrbits.add(parentOrbit);
	}

	public void addNewChildrenOrbit(Orbit childrenOrbit)
	{
		if (listChildrenOrbits.contains(childrenOrbit) == false)
			listChildrenOrbits.add(childrenOrbit);
	}
	public int countOrbits()
	{
		
		int count = 0;
		for(Orbit parentOrbit : listParentOrbits)
			{
				if(parentOrbit.getName().equals("COM"))
					{
						return count+1;
					}
				else
					{
					count++;
					count += parentOrbit.countOrbits();
					}
			}
		return count;
	}
	
	public String getName()
	{
		return name;
	}
}
