import java.util.ArrayList;

public class Quicksort<T extends Comparable<T>>
{

	public static void main(String[] args)
	{
		ArrayList<String> toSort = new ArrayList<String>();
		
		for(int i=0; i<args.length; i++)
		{
			toSort.add(args[i]);

		}
		Quicksort<String> sorter = new Quicksort<String>();
		toSort = sorter.sort(toSort);

		for(int i=0; i<toSort.size(); i++)
		{
			System.out.printf("%s ", toSort.get(i));
		}
		System.out.printf("\n");
	}

	public ArrayList<T> msort(ArrayList<T> toSort)
	{
		if(toSort.size() <= 1)
		{
			return toSort;
		}

		int middle = toSort.size()/2;
		ArrayList<T> left = new ArrayList<T>();
		ArrayList<T> right = new ArrayList<T>();

		for(int i=0; i<middle; i++)
		{
			left.add(toSort.get(i));
		}
		for(int i=middle; i<toSort.size(); i++)
		{
			right.add(toSort.get(i));
		}

		left = msort(left);
		right = msort(right);

		ArrayList<T> merged = new ArrayList<T>();
		while((left.size() != 0) || (right.size() != 0))
		{
			if(left.size() == 0)
			{
				merged.add(right.get(0));
				right.remove(0);
			}
			else if(right.size() == 0)
			{
				merged.add(left.get(0));
				left.remove(0);
				
			}
			else
			{
				if(left.get(0).compareTo(right.get(0)) < 0)
				{
				merged.add(left.get(0));
				left.remove(0);
				}
				else
				{
				merged.add(right.get(0));
				right.remove(0);
				}
			}
		}

		return merged;
	}

	public ArrayList<T> sort(ArrayList<T> toSort)
	{
		if(toSort.size() <= 1)
		{
			return toSort;
		}
		
		ArrayList<T> lessThan = new ArrayList<T>();
		ArrayList<T> pivot = new ArrayList<T>();
		ArrayList<T> greaterThan = new ArrayList<T>();

		T pivotValue = toSort.get(0);

		pivot.add(pivotValue);

		for(int i = 1; i < toSort.size(); i++)
		{
			if(toSort.get(i).compareTo(pivotValue) < 0)
			{
				lessThan.add(toSort.get(i));
			}
			else
			{
				greaterThan.add(toSort.get(i));
			}
		}

		lessThan = sort(lessThan);
		greaterThan = sort(greaterThan);

		lessThan.addAll(pivot);
		lessThan.addAll(greaterThan);
		return lessThan;
	}
}
