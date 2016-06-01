import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

public class Graph
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);

		int nodes = input.nextInt();
		Integer matrix[][] = new Integer[nodes][nodes];
		for(int i = 0; i < nodes; i++)
		{
			for(int j = 0; j < nodes; j++)
			{
				matrix[i][j] = input.nextInt();
			}
		}

		for(int i = 0; i < nodes; i++)
		{
			for(int j = 0; j < nodes; j++)
			{
				System.out.printf("%d ", matrix[i][j]);
			}
			System.out.printf("\n");
		}
		
		System.out.printf("Find 2 from 4 %b\n", bread(matrix, 4, 2));
		System.out.printf("Find 1 from 5 %b\n", bread(matrix, 5, 1));
		System.out.printf("Find 4 from 0 %b\n", bread(matrix, 0, 4));

		System.out.printf("Find 2 from 4 %b\n", depth(matrix, 4, 2));
		System.out.printf("Find 1 from 5 %b\n", depth(matrix, 5, 1));
		System.out.printf("Find 4 from 0 %b\n", depth(matrix, 0, 4));
	}

	public static boolean depth(Integer[][] matrix, int start, int find)
	{
		return depth(matrix, start, find, new ArrayList<Integer>());
	}
	private static boolean depth(Integer[][] matrix, int start, int find, ArrayList<Integer> beenTo)
	{
		if ((start < 0) || (start >= matrix.length))
		{
			return false;
		}
		if(beenTo.contains(start))
		{
			return false;
		}
		beenTo.add(start);
		System.err.printf("We are at node %d\n", start);

		if(start == find)
		{
			return true;
		}
		for(int i = 0; i < matrix[start].length; i++)
		{
			if(matrix[start][i] > 0)
			{
				if(depth(matrix, i, find , beenTo))
				{
					return true;
				}
			}
		}
		return false;
	}

	public static boolean bread(Integer[][] matrix, int start, int find)
	{
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);

		ArrayList<Integer> been = new ArrayList<Integer>();
		
		while(q.size() != 0)
		{
			Integer at = q.poll();
			if((at < 0) || (at >= matrix.length))
			{
				continue;
			}
			if(been.contains(at))
			{
				continue;
			}
			System.err.printf("We are at node %d\n", at);
			been.add(at);

			for(int i = 0; i < matrix[at].length; i++)
			{
				if(matrix[at][i] > 0)
				{
					q.add(i);
				}
			}
		}
		
		if(been.contains(find))
		{
			return true;
		}
		return false;
	}

}
