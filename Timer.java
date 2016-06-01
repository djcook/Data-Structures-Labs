import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Queue;
import java.util.Scanner;

public class Timer
{
	private long last;

	public Timer()
	{
		last = System.nanoTime();
	}

	public void reset()
	{
		last = System.nanoTime();
	}

	public long get()
	{
		long current = System.nanoTime();
		long retval = current - last;
		last = current;
		return retval;
	}

	public static void main(String[] args)
	{
		ArrayList<String> arrayList = new ArrayList<String>();
		LinkedList<String> linkedList = new LinkedList<String>();
		Set<String> hashSet = new HashSet<String>();
		HashSet<String> linkedHashSet = new LinkedHashSet<String>();
		Queue<String> queue = new LinkedList<String>();
//		Node node = new Node();

		//Sample of using the Timer class to generate time.
		Timer clock = new Timer();
		clock.reset();
		arrayList.add("apple");
		long result = clock.get();
		System.out.printf("Time for arrayList: %d\n", result);
		long aL = clock.get();
		long lL = clock.get();
		long hS = clock.get();
		long lHS = clock.get();
		long q = clock.get();
		
		Scanner file = new Scanner(System.in);
		clock.reset();
		while(file.hasNextLine())
		{
			clock.reset();
			arrayList.add(file.nextLine());
			aL += clock.get();
		
			clock.reset();
			linkedList.add(file.nextLine());
			lL += clock.get();		
		
			clock.reset();
			hashSet.add(file.nextLine());
			hS += clock.get();

			clock.reset();
			linkedHashSet.add(file.nextLine());
			lHS = clock.get();

			clock.reset();
			queue.add(file.nextLine());
			q += clock.get();
		}
		System.out.printf("Time for arrayList: %d\n", aL);
		System.out.printf("Time for linkedList: %d\n", lL);
		System.out.printf("Time for hashSet: %d\n", hS);
		System.out.printf("Time for linkedHashSet: %d\n", lHS);
		System.out.printf("Time for queue: %d\n", q);

//		for(int i = 0; i <arrayList.size(); ++i)
//		{
//			System.out.printf(arrayList.get(i) + "\n");
//		}

	}
}
