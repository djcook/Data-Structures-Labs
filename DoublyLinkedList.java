import java.util.Iterator;

public class DoublyLinkedList<T extends Comparable<T>> extends LinkedList<T> implements Iterable<T>
{
	public DoublyLinkedList()
	{
		super();
	}

	//@Override
	public DoublyLinkedList(DoublyLinkedList<T> source)
	{
		Node<T> at = source.first;
		while(at != null)
		{
			insert(at.getData());
			at = at.getNext();
		}
	}

	public DoublyLinkedList(LinkedList<T> source)
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
		public iterator()
		{
			pos = first;
			prev = null;
		}
		public boolean hasNext()
		{
			return (pos != null);
		}
		public T next()
		{
			Node<T> temp = pos;
			prev = pos;
			pos = pos.getNext();
			return temp.getData();
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

	public static void main(String[] args)
        {
                DoublyLinkedList<String> myList = new DoublyLinkedList<String>();
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

		for(String word : myList)
		{
			System.out.printf("%s\n", word); //dont ever use it
		}

                DoublyLinkedList<String> copyList = new DoublyLinkedList<String>(myList);
                myList.remove("apple");
                System.out.printf("The list contains %s\n", myList);
                myList.remove("carrot");
                System.out.printf("The list contains %s\n", myList);
                System.out.printf("The list contains %s\n", copyList);
                myList.insert("zebracake");
                myList.insert("yellow");
                myList.insert("turnip");
                myList.insert("moocow");
                myList.insert("banana");
                myList.insert("apple");
                System.out.printf("The list contains %s\n", myList);
                myList.insert("cabbage");
                System.out.printf("The list contains %s\n", myList);

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
		if(at.getData().equals(toFind))
		{
			//Node<T> prev = at.getPrev();
			//Node<T> next = at.getNext();
			size--;
			if(at.getPrev() == null)
			{
				first = at.getNext();
				if(first != null)
				{
					first.setPrev(null);
				}
				return true;
			}
			//prev.setNext(next);
			//next.setPrev(prev);
			at.getPrev().setNext(at.getNext());
			if(at.getNext() != null)
			{
			at.getNext().setPrev(at.getPrev());
			}
		}
		return remove(at.getNext(), toFind);
	}

	@Override
	public boolean insert(T toInsert)
	{
		first = insert(first, toInsert);
		return true;
	}

	private Node<T> insert(Node<T> at, T toInsert)
	{
		if(at == null)
		{
			Node<T> newNode = new Node<T>(toInsert, null, null);
			size++;
			return newNode;
		}
		at.setNext(insert(at.getNext(), toInsert));
		Node<T> next = at.getNext();
		next.setPrev(at);
		return at;
	}
}
