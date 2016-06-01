public class MultiplyLinkedList
{
	private Node firstName, firstType, firstWeight, firstHp;
	private int size;
	public static void main(String[] args)
	{
		MultiplyLinkedList myList = new MultiplyLinkedList();
		myList.insert("Zubat", "Grass", "17", "40");
		myList.insert("Onix", "Fighting", "463", "90");
		myList.insert("Bellsprout", "Grass", "9", "40");
		myList.insert("Bulbasaur", "Grass", "15", "40");

		System.out.printf("By name: %s\n", myList.printNodes("name"));
		System.out.printf("By type: %s\n", myList.printNodes("type"));
		System.out.printf("By weight: %s\n", myList.printNodes("weight"));
		System.out.printf("By hp: %s\n", myList.printNodes("hp"));
	}

	public MultiplyLinkedList()
	{
		firstName= firstType = firstWeight= firstHp= null;
		size = 0;
	}

	public boolean insert(String name, String type, String weight, String hp)
	{
		Node newNode = new Node();
		newNode.name = name;
		newNode.type = type;
		newNode.weight = weight;
		newNode.hp = hp;

		if(size == 0)
		{
			size++;
			firstName = newNode;
			firstType = newNode;
			firstWeight = newNode;
			firstHp = newNode;
			return true;
		}
		
		size = size + 1;
		Node at = firstName;
		Node prev = null;
		while((at != null) && (name.compareTo(at.name) > 0))
		{
			prev = at;
			at = at.nextName;
		}
		if(prev == null)
		{
			newNode.nextName = firstName;
			firstName = newNode;
		}
		else if(at == null)
		{
			prev.nextName = newNode;
			newNode.nextName = null;
		}
		else
		{
			newNode.nextName = prev.nextName;
			prev.nextName = newNode;
		}

		at = firstType;
		prev = null;
		while((at != null) && (type.compareTo(at.type) > 0))
		{
			prev = at;
			at = at.nextType;
		}
		if(prev == null)
		{
			newNode.nextType = firstType;
			firstType = newNode;
		}
		else if(at == null)
		{
			prev.nextType = newNode;
			newNode.nextType = null;
		}
		else
		{
			newNode.nextType = prev.nextType;
			prev.nextType = newNode;
		}

		at = firstWeight;
		prev = null;
		while((at != null) && (weight.compareTo(at.weight) > 0))
		{
			prev = at;
			at = at.nextWeight;
		}
		if(prev == null)
		{
			newNode.nextWeight = firstWeight;
			firstWeight = newNode;
		}
		else if(at == null)
		{
			prev.nextWeight = newNode;
			newNode.nextWeight = null;
		}
		else
		{
			newNode.nextWeight = prev.nextWeight;
			prev.nextWeight = newNode;
		}

		at = firstHp;
		prev = null;
		while((at != null) && (hp.compareTo(at.hp) > 0))
		{
			prev = at;
			at = at.nextHp;
		}
		if(prev == null)
		{
			newNode.nextHp = firstHp;
			firstHp = newNode;
		}
		else if(at == null)
		{
			prev.nextHp = newNode;
			newNode.nextHp = null;
		}
		else
		{
			newNode.nextHp = prev.nextHp;
			prev.nextHp = newNode;
		}
		return true;
	}

	public String printNodes(String cond)
	{
		String retval = "";
		Node at;
		if(cond.equals("name"))
		{
			at = firstName;
		}
		else if(cond.equals("type"))
		{
			at = firstType;
		}
		else if(cond.equals("weight"))
		{
			at = firstWeight;
		}
		else if(cond.equals("hp"))
		{
			at = firstHp;
		}

		while(at != null)
		{
			if(cond.equals("name"))
			{
				retval = retval + at.name;
				at = firstName;
			}
			else if(cond.equals("type"))
			{
				retval = retval + at.type;
				at = firstType;
			}
			else if(cond.equals("weight"))
			{
				retval = retval + at.weight;
				at = firstWeight;
			}
			else if(cond.equals("hp"))
			{
				retval = retval + at.hp;
				at = firstHp;
			}
			if(at != null)
			{
				retval = retval + ", ";
			}
		}

		return retval;
	}
}
