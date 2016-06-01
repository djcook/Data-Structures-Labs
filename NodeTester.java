public class NodeTester
{

	public static void main(String[] args)
	{
		Node apple = new Node("apple", null);
		Node banana = new Node("banana", null);
		Node carrot = new Node("carrot", null);

		String result = nodePrint(apple);
		System.out.printf("The node contains: %s\n", result);

		Node first = banana;
		first.setNext(carrot);
		carrot.setNext(apple);

		System.out.printf("We have:  %s\n",nodePrint(first));
	
		Node donut = new Node("donut", null);
		first = insert(first, donut);
		first = insert(first, new Node("eclair", null));
		first = insert(first, new Node("fig", null));
		first = insert(first, new Node("grape", null));
		first = insert(first, new Node("hotdog", null));
		System.out.printf("We have:  %s\n",nodePrint(first));

		//System.out.printf("Search for banana: %b\n", search(first, "banana");

		first = null;
		first = insertInOrder(first, new Node("apple", null));
		first = insertInOrder(first, new Node("hotdog", null));
		first = insertInOrder(first, new Node("fig", null));
		first = insertInOrder(first, new Node("banana", null));
		first = insertInOrder(first, new Node("donut", null));
		first = insertInOrder(first, new Node("tomato", null));
		first = insertInOrder(first, new Node("eclair", null));
		first = insertInOrder(first, new Node("grape", null));
		first = insertInOrder(first, new Node("icecream", null));
		System.out.printf("We have:  %s\n",nodePrint(first));

		System.out.printf("grape at %s\n",find(first, "grape"));
		System.out.printf("tomato at %s\n",find(first, "tomato"));
		System.out.printf("apple at %s\n",find(first, "apple"));
		System.out.printf("banana at  %s\n",find(first, "banana"));
		

		first = insertFront(first, "Kiwi");
		remove(first, "eclair");
		remove(first, "icecream");
		Node copy = copyNodes(first);
		System.out.printf("We have:  %s\n",nodePrint(first));

		while(first != null)
		{
			first = remove(first, first.getData());
			System.out.printf("We have:  %s\n",nodePrint(first));

		}
		System.out.printf("We have:  %s\n",nodePrint(copy));

	}

	public static String nodePrint(Node source)
	{
		if(source == null)
		{
			return "";
		}
		return source.getData() + " " + nodePrint(source.getNext());
	}

	public static int find(Node source, String toFind)
	{
		if(source == null)
		{
			return -1;
		}
		if(source.getData().equals(toFind))
		{
			return 0;
		}
		return 1 + find(source.getNext(), toFind);
	}

	public static boolean search(Node source, String toFind)
	{
		if (source == null)
		{
			return false;
		}
		if(source.getData().equals(toFind))
		{
			return true;
		}
		return search(source.getNext(), toFind);
	}

	public static Node insert(Node at, Node toAdd)
	{
		if(at == null)
		{
			return toAdd;
		}
		Node next = insert(at.getNext(), toAdd);
		at.setNext(next);
		return at;
	}

	public static Node insertInOrder(Node at, Node toAdd)
	{
		if(at == null)
		{
			return toAdd;
		}

		if(at.getData().compareTo(toAdd.getData()) <= 0)
		{
			Node next = insertInOrder(at.getNext(), toAdd);
			at.setNext(next);
			return at;
		}
		else
		{
			toAdd.setNext(at);
			return toAdd;
		}
	}

	public static Node remove(Node at, String toFind)
	{
		return remove(at, null, toFind);
	}

	private static Node remove(Node at, Node prior,  String toFind)
	{
		if(at == null)
		{
			return null;
		}
		if(at.getData().equals(toFind))
		{
			if(prior == null)
			{
				return at.getNext();
			}
			prior.setNext(at.getNext());
			return prior;
		}
		remove(at.getNext(), toFind);
		return at;
	}

	public static Node insertFront(Node at, String data)
	{
		return new Node(data, at);
	}

	public static Node copyNodes(Node at)
	{
		if(at == null)
		{
			return null;
		}
		Node newNode = new Node(at.getData(), null);
		newNode.setNext(copyNodes(at.getNext()));
		return newNode;
	}

}
