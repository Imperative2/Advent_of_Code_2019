package day4;

public class Day4Part1
{
	public static void main(String[] args)
	{
		int count = 0;
		for (int i = 246515; i < 739105; i++)
			{
				boolean flagRepeat = false;
				boolean flagIncrease = true;
				String string = i+"";
				char[] digits = string.toCharArray();
				for(int j=1; j< digits.length; j++)
					{
						if(digits[j-1] == digits[j])
							flagRepeat = true;
						if(digits[j-1]>digits[j])
							flagIncrease = false;
					}
				if(flagRepeat == true && flagIncrease == true)
					{
						count++;
						System.out.println(string);
					}
			}
		
		System.out.println("count: "+count);
	}
}
