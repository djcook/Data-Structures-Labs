import java.util.Iterator;

public class DoublyCircularList<T extends Comparable<T>> extends LinkedList<T> implements Iterable<T>
{
	public DoublyCircularList()
	{
		super();
	}
	
	public DoublyCircularList(DoublyCircularList<T> source)
	{
		Node<T> at = source.first;
		int index = 0;
		while(index < size)
		{
			insert(at.getData());
			at = at.getNext();
			index++;
		}
	}

	public DoublyCircularList(LinkedList<T> source)
	{
		Node<T> at = source.first;
		while(at != null)
		{
			insert(at.getData());
			at = at.getNext();
		}
	}

	public class iterator implements Iterator<T>
        {
                private Node<T> pos;
                private Node<T> prev;
		int index = 0;
                public iterator()
                {
                        pos = first;
                        prev = null;
                }
                public boolean hasNext()
                {
                        return ((pos != null) && (index < size));
                }
                public T next()
                {
			index++;
                        Node<T> temp = pos;
                        prev = pos;
                        pos = pos.getNext();
                        return temp.getData();
                }
		public T prev()
		{	Node<T> temp = pos;
			pos = pos.getPrev();
			return temp.getData();
		}
		public T peek()
		{
			return pos.getNext().getData();
		}
		public int indexOf()
		{
			return index;
		}
                public void remove() throws IllegalStateException
                {
                        if(pos == first)
                        {
                                throw new IllegalStateException();
                        }
                        if(pos == null)
                        {
                                if(this.prev == first)
                                {
                                        first = null;
                                        size = 0;
                                        return;
                                }
                                Node<T> superPrev = prev.getPrev();
                                superPrev.setNext(null);
                                size--;
                                this.prev = superPrev;
                                return;
                        }
                        Node<T> prev = pos.getPrev();
                        if(prev == null)
                        {
                                throw new IllegalStateException();
                        }
                        size--;
                        if(prev == first)
                        {
                                first = pos;
                        }
                        Node<T> superPrev = prev.getPrev();
                        pos.setPrev(superPrev);
                        if(superPrev != null)
                        {
                                superPrev.setNext(pos);
                        }
                        this.prev = pos.getPrev();
                }
        }

	public Iterator<T> iterator()
        {
                return new iterator();
        }

	@Override
	public boolean insert(T toInsert)
	{
		first = insert(first, toInsert);
		return true;
	}

	private Node<T> insert(Node<T> at, T toInsert)
	{
		size++;
		if(at == null)
		{
			Node<T> newNode = new Node<T>(toInsert, null, null);
			newNode.setNext(newNode);
			newNode.setPrev(newNode);
			return newNode;
		}
		Node<T> newNode = new Node<T>(toInsert, first, first.getPrev());
		first.getPrev().setNext(newNode);
		first.setPrev(newNode);
		return at;
	}

	@Override
	public boolean remove(T toFind)
	{	
		return remove(first, toFind);
	}

	private boolean remove(Node<T> at, T toFind)
	{
		if(at == null)
		{
			return false;
		}
		int i = 0;
		while(i < size)
		{
			if(at.getData().equals(toFind))
			{
				Node<T> next = at.getNext();
				Node<T> prev = at.getPrev();
				if(at == first && size == 1)
				{
					first = null;
					size = 0;
					return true;
				}
				else if(at == first)
				{
					size--;
					first = at.getNext();
					prev.setNext(first);
					first.setPrev(prev);
					return true;
				}
				next.setPrev(prev);
				prev.setNext(next);
				size--;
				return true;
			}
			at = at.getNext();
			i++;
		}
		return false;
	}
	
	@Override
	public boolean insertFront(T data)
	{
		size++;
		if(first == null)
		{
			Node<T> newNode = new Node<T>(data, null, null);
			newNode.setPrev(newNode);
			newNode.setNext(newNode);
			first = newNode;
			return true;
		}
		Node<T> newNode = new Node<T>(data, first, first.getPrev());
		first.getPrev().setNext(newNode);
		first.setPrev(newNode);
		first = newNode;
		return true; 
	}
	
	@Override
	public boolean insertInOrder(T data)
	{
		Node<T> toInsert = new Node<T>(data, null, null);
		first = insertInOrder(first, toInsert);
		size++;
		return true;
	}

	private Node<T> insertInOrder(Node<T> at, Node<T> toAdd)
	{
		if(at == null)
		{
			toAdd.setNext(toAdd);
			toAdd.setPrev(toAdd);
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
			Node<T> prev = at.getPrev();
			prev.setNext(toAdd);
			toAdd.setPrev(prev);
			toAdd.setNext(at);
			at.setPrev(toAdd);
			return toAdd;
		}
	}

	@Override
	public int find(T toFind)
	{
		int index = find(first, toFind);
		if(index == size)
		{
			return -1;
		}
		return index;
	}

	private int find(Node<T> source, T toFind)
	{
		int index = 0;
		while(index < size)
		{
			if(source == null)
			{
				return 0;
			}
			if(source.getData().equals(toFind))
			{
				return index;
			}
			source = source.getNext();
			index++;
		}
		return index;
	}

	@Override
	public DoublyCircularList<T> subset(int begin, int end)
	{
		DoublyCircularList<T> retval = new DoublyCircularList<T>();
		Node<T> start = first;
		int index = 0;
		int wrap = 0;
		while(start != null)
		{
			if((index >= begin) && (index < end) && (wrap < size))
			{
				wrap++;
				retval.insert(start.getData());
			}
			if(index >= end)
			{
				return retval;
			}
			index++;
			start = start.getNext();
		}
		return retval;
	}

	public String toString()
	{
		String retval = "";
		Node<T> at = first;
		int index = 0;
		while((at != null) && (index < size))
		{
			retval += at.getData() + " ";
			at = at.getNext();
			index++;
		}
		return retval.trim();
	}
}
