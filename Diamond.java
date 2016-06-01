import java.util.Scanner;

public class Diamond 
{
	public static void main(String[] args)
	{
		int odd;

		Scanner input = new Scanner (System.in);
		System.out.printf("Enter an odd number.\n");
		odd = input.nextInt();

		diamondPrinter(odd);
	}
 
	public static void diamondPrinter(int odd)
	{
		String[] space = new String[odd];
		for(int i = 0; i < space.length; i++)
		{
			System.out.printf(" ");
		} 
		int start = odd+1/2;
		space[start] = Integer.toString(odd);

		int right = start;
		int left = start;

		for(int i=1; i<=odd/2; i++)
		{
			for(int t = 0; t < start; t++)
			{
				System.out.printf(" ");
			}
	
			for(int k=0; k < start; k++)
			{
				System.out.printf("%d", odd);
			}
		System.out.printf("\n");
		left--;
		right++;
		} 

    
	}

}
