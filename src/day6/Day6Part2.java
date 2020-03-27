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

public class Day6Part2
{
	public static Map<String, Orbit2> orbitsMap = new HashMap<>();

	public static void main(String[] args)
	{
		readInput();

		System.out.println(orbitsMap.toString());

		int orbitsCount = 0;

		Map<String, Orbit2> orbitsPool = new HashMap<>();

		Orbit2 orbitSAN = orbitsMap.get("SAN");
		Orbit2 orbitYOU = orbitsMap.get("YOU");

		orbitSAN.getAllParents(orbitsPool);
		Orbit2 firstMatch = orbitYOU.getFirstMatch(orbitsPool);
		if (firstMatch == null)
			{
				System.out.println("error no match");

			} else
			{
				System.out.println(firstMatch.getName());
			}
		System.out.println(orbitsPool.toString());
		
		System.out.println("distance YOU "+orbitYOU.distanceToPoint(firstMatch));
		System.out.println("distance SAN "+orbitSAN.distanceToPoint(firstMatch));

	}

	public static void readInput()
	{
		try
			{
				Scanner sc = new Scanner(new File("C:\\GIT\\Advent_of_Code_2019\\src\\day6\\input_day_6.txt"));

				while (sc.hasNextLine() == true)
					{
						String input = sc.nextLine();
						// System.out.println(input);
						input.trim();
						String[] separatedInput = input.split("\\)");

						String planet1 = separatedInput[0];
						String planet2 = separatedInput[1];

						Orbit2 orb1 = null;
						Orbit2 orb2 = null;

						if (orbitsMap.containsKey(planet1) == false && orbitsMap.containsKey(planet2) == false)
							{
								orb1 = new Orbit2(planet1);
								orb2 = new Orbit2(planet2);
								orb1.addNewChildrenOrbit(orb2);
								orb2.addNewParentOrbit(orb1);
							} else if (orbitsMap.containsKey(planet1) == true && orbitsMap.containsKey(planet2) == true)
							{
								orb1 = orbitsMap.get(planet1);
								orb2 = orbitsMap.get(planet2);
								orb1.addNewChildrenOrbit(orb2);
								orb2.addNewParentOrbit(orb1);
							} else if (orbitsMap.containsKey(planet1) == true
									&& orbitsMap.containsKey(planet2) == false)
							{
								orb1 = orbitsMap.get(planet1);
								orb2 = new Orbit2(planet2);
								orb1.addNewChildrenOrbit(orb2);
								orb2.addNewParentOrbit(orb1);
							} else if (orbitsMap.containsKey(planet1) == false
									&& orbitsMap.containsKey(planet2) == true)
							{
								orb1 = new Orbit2(planet1);
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

class Orbit2
{
	String name;
	List<Orbit2> listParentOrbits = new ArrayList<>();
	List<Orbit2> listChildrenOrbits = new ArrayList<>();

	public Orbit2(String name)
	{
		this.name = name;
	}

	public List<Orbit2> getParentOrbits()
	{
		return listParentOrbits;
	}

	public List<Orbit2> getChildrenOrbits()
	{
		return listChildrenOrbits;
	}

	public void addNewParentOrbit(Orbit2 parentOrbit)
	{
		if (listParentOrbits.contains(parentOrbit) == false)
			listParentOrbits.add(parentOrbit);
	}

	public void addNewChildrenOrbit(Orbit2 childrenOrbit)
	{
		if (listChildrenOrbits.contains(childrenOrbit) == false)
			listChildrenOrbits.add(childrenOrbit);
	}

	public int countOrbits()
	{

		int count = 0;
		for (Orbit2 parentOrbit : listParentOrbits)
			{
				if (parentOrbit.getName().equals("COM"))
					{
						return count + 1;
					} else
					{
						count++;
						count += parentOrbit.countOrbits();
					}
			}
		return count;
	}

	public void getAllParents(Map<String, Orbit2> orbitsPool)
	{
		for (Orbit2 parentOrbit : listParentOrbits)
			{
				if (parentOrbit.getName().equals("COM"))
					{
						break;
					} else
					{
						orbitsPool.put(parentOrbit.getName(), parentOrbit);
						parentOrbit.getAllParents(orbitsPool);
					}
			}
	}

	public Orbit2 getFirstMatch(Map<String, Orbit2> orbitsPool)
	{
		for (Orbit2 parentOrbit : listParentOrbits)
			{
				if (parentOrbit.getName().equals("COM"))
					{
						break;
					} else
					{
						if (orbitsPool.containsValue(parentOrbit))
							{
								return parentOrbit;
							} else
							{
								return parentOrbit.getFirstMatch(orbitsPool);
							}

					}
			}
		return null;
	}
	
	public int distanceToPoint(Orbit2 destinationOrbit)
	{
		int distance = 0;
		for (Orbit2 parentOrbit : listParentOrbits)
			{
				if (parentOrbit.getName().equals("COM"))
					{
						break;
					} else
					{
						if (parentOrbit.getName().equals(destinationOrbit.getName()) == true)
							{
								distance++;
								return distance;
							} else
							{
								distance++;
								return distance+parentOrbit.distanceToPoint(destinationOrbit);
							}

					}
			}
		return 0;
	}

	public String getName()
	{
		return name;
	}
}
