public class LinkedList<T extends Comparable<T>>
{
	protected Node<T> first;
	protected int size;

	public LinkedList()
	{
	first = null;
	size = 0;
	}

	public static void main(String[] args)
	{
		LinkedList<String> myList = new LinkedList<String>();
		myList.insert("apple");
		myList.insert("banana");
		myList.insert("carrot");
		System.out.printf("The list contains %s\n", myList);
		System.out.printf("Location of banana is %d\n", myList.find("banana"));
		System.out.printf("Location of danish is %d\n", myList.find("danish"));
		System.out.printf("Is banana in list %b\n", myList.search("banana"));
		System.out.printf("Is danish in list %b\n", myList.search("danish"));
		if(!myList.remove("danish"))
		{
		System.out.printf("Failed remove, there is no danish\n");
		}
		LinkedList<String> copyList = new LinkedList<String>(myList);
		myList.remove("apple");
		System.out.printf("The list contains %s\n", myList);
		myList.remove("carrot");
		System.out.printf("The list contains %s\n", myList);
		System.out.printf("The list contains %s\n", copyList);
		myList.insertInOrder("zebracake");
		myList.insertInOrder("yellow");
		myList.insertInOrder("turnip");
		myList.insertInOrder("moocow");
		myList.insertInOrder("banana");
		myList.insertInOrder("apple");
		System.out.printf("The list contains %s\n", myList);
		myList.insertFront("cabbage");
		System.out.printf("The list contains %s\n", myList);

	}

	public LinkedList<T> subset(int begin, int end)
	{
		LinkedList<T> retval = new LinkedList<T>();
		Node<T> start = first;
		int counter = 0;
		while(start != null)
		{
			if((counter >= begin) && (counter < end))
			{
				retval.insert(start.getData());
			}
			if(counter >= end)
			{
				return retval;
			}
			counter++;
			start = start.getNext();
		}
		return null;
	}

	public LinkedList<T> reverse()
	{
		LinkedList<T> retval = new LinkedList<T>();
		Node<T> start = first;
		while(start != null)
		{
			retval.insertFront(start.getData());
			start = start.getNext();
		}
		return retval;
	}

	public void merge(LinkedList<T> append)
	{
		size = size + append.size;
		if(first == null)
		{
			first = append.first;
		}
		Node<T> at = first;
		while(at.getNext() != null)
		{
			at = at.getNext();
		}
		at.setNext(append.first);
	}

	public String toString()
	{
		String retval = "";
		Node<T> at = first;
		while(at != null)
		{
			retval += at.getData() + " ";
			at = at.getNext();
		}
		return retval.trim();
	}

	public int find(T toFind)
	{
		int retval =  find(first, toFind);
		if(retval == size)
		{
			return -1;
		}
		return retval;
	}
	private int find(Node<T> source, T toFind)
	{
		if(source == null)
		{
			return 0;
		}
		if(source.getData().equals(toFind))
		{
			return 0;
		}
		return 1 + find(source.getNext(), toFind);
	}

	public boolean search(T toFind)
	{
		return search(first, toFind);
	}

	private boolean search(Node<T> source, T toFind)
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

	public boolean insert(T data)
	{
		Node<T> toInsert = new Node<T>(data, null);
		first = insert(first, toInsert);
		return true;
	}

	private Node<T> insert(Node<T> at, Node<T> toAdd)
	{
		if(at == null)
		{
			size++;
			return toAdd;
		}
		Node<T> next = insert(at.getNext(), toAdd);
		at.setNext(next);
		return at;
	}

	public boolean insertInOrder(T data)
	{
		Node<T> toInsert = new Node<T>(data, null);
		first = insertInOrder(first, toInsert);
		size++;
		return true;
	}

	private Node<T> insertInOrder(Node<T> at, Node<T> toAdd)
	{
		if(at == null)
		{
			return toAdd;
		}

		if(at.getData().compareTo(toAdd.getData()) <= 0)
		{
			Node<T> next = insertInOrder(at.getNext(), toAdd);
			at.setNext(next);
			return at;
		}
		else
		{
			toAdd.setNext(at);
			return toAdd;
		}
	}

	public boolean remove(T toFind)
	{
		return remove(first, null, toFind);
	}

	private boolean remove(Node<T> at, Node<T> prior, T toFind)
	{
		if(at == null)
		{
			return false;
		}
		if(at.getData().equals(toFind))
		{
			size--;
			if(prior == null)
			{
				first = at.getNext();
				return true;
			}
			prior.setNext(at.getNext());
			return true;
		}
		return remove(at.getNext(), at, toFind);
	}

	public boolean insertFront(T data)
	{
		size++;
		first = new Node<T>(data, first);
		return true;
	}

	public LinkedList(LinkedList<T> source)
	{
		Node<T> at = source.first;
		while(at != null)
		{
			insert(at.getData());
			at = at.getNext();
		} 
	}

}
