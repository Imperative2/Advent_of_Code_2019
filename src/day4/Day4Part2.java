package day4;

import java.util.ArrayList;
import java.util.List;

public class Day4Part2
{
	public static void main(String[] args)
	{
		List<Integer> numbersWithPairsList = new ArrayList<>();

		int count = 0;
		for (int i = 246515; i < 739105; i++)
			{
				boolean flagRepeat = false;
				boolean flagIncrease = true;
				String string = i + "";
				char[] digits = string.toCharArray();
				for (int j = 1; j < digits.length; j++)
					{
						if (digits[j - 1] == digits[j])
							flagRepeat = true;
						if (digits[j - 1] > digits[j])
							flagIncrease = false;
					}
				if (flagRepeat == true && flagIncrease == true)
					{
						count++;
						System.out.println(string);
						numbersWithPairsList.add(i);
					}
			}

		System.out.println("count: " + count);

		count = 0;

		for (Integer number : numbersWithPairsList)
			{
				String numString = number.toString();
				
				for (int i = 1; i < numString.length(); i++)
					{
						if (i < numString.length() - 1)
							{
								if (numString.charAt(i - 1) == numString.charAt(i)
										&& numString.charAt(i + 1) != numString.charAt(i))
									{
										count++;
										System.out.println(numString);
										break;
									}
								else if(numString.charAt(i - 1) == numString.charAt(i)) {
									while( numString.charAt(i) == numString.charAt(i+1) ) {
										i++;
										if(i==numString.length()-1) {
											break;
										}
									}
								}
							}
						else if(numString.charAt(i) == numString.charAt(i-1)) {
							System.out.println(numString);
							count++;
						}

					}
			}

		System.out.println("count: " + count);
	}
}
