public class Sentinel
{
	private int size;
	private Node<String> head;
	private Node<String> tail;

	public static void main(String[] args)
	{
		Sentinel myList = new Sentinel();
		for (int i=0, i<args.length, i++)
		{
			myList.insert(args[i]);
		}
		System.out.printf("List: %s\n", myList);
	}

	public Sentinel()
	{
		size = 0;
		head = new Node<String>("Head", null);
		tail = new Node<String>("Tail", null);
		head.setNext(tail);
	}

	public boolean insert(String data)
	{
		size = size + 1;
		Node<String> newNode = new Node<String>(data, null);
		
		Node<String> at = head;
		while(at.getNext != tail)
		{
			at = at.getNext();
		}
		at.setNext(newNode);
		newNode.setNext(tail);
		return true;
	}

	public boolean insertFront(String data)
	{
		size = size + 1;
		Node<String> newNode = new Node<String>(data, head.getNext);
		head.setNext(newNode);
		return true;
	}

	public boolean remove(String data)
	{
		return remove(head.getNext(), head, data);
	}

	private boolean remove(Node<String> at, Node<String> prev, String data)
	{
		if(at == tail)
		{
			return false;
		}
		
		if(at.getData().equals(data))
		{
			size = size - 1;
			prev.setNext(at.getNext());
			return true;
		}
		return remove(at.getNext(), at, data);
	}

	public boolean find(String data)
	{
		Node<String> at = head.getNext();
		while(at != tail)
		{
			if(at.getData().equals(data))
			{
				return true;
			}
			at = at.getNext();
		}
		return false;
	}

	public String toString()
	{
		String retval = "[";
		Node<String> at = head.getNext();
		while(at != tail)
		{
			retval = retval + at.getData();
			at = at.getNext();
			if(at != tail)
			{
				retval = retval + ", ";
			}
		}
		return retval + "]";
	}
}
