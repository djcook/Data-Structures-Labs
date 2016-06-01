import java.util.ArrayList;
import java.util.Scanner;

public class Bst
{
	private Node root;
	private int size;

	public static void main(String[] args)
	{
		Bst myTree = new Bst();
		for(int i=0; i<args.length; i++)
		{
			myTree.insert(Integer.parseInt(args[i]));
		}
		System.out.printf("%s\n", myTree.bread());
		System.out.printf("%s\n", myTree.preorder());
		System.out.printf("%s\n", myTree.inorder());
		System.out.printf("%s\n", myTree.postorder());

		Scanner input = new Scanner(System.in);
		while(true)
		{
			Integer toRemove = input.nextInt();
			myTree.remove(toRemove);
			System.out.printf("%s\n", myTree.bread());
		}
	}

	public Bst()
	{
		root = null;
		size = 0;
	}

	public boolean insert(Integer data)
	{
		if(root == null)
		{
			root = new Node();
			root.setData(data);
			root.setLeft(null);
			root.setRight(null);
			size = 1;
			return true;
		}
		if(data < root.getData())
		{
			root.setLeft(insert(data, root.getLeft()));
		}
		else
		{
			root.setRight(insert(data, root.getRight()));
		}
		return true;
	}

	private Node insert(Integer data, Node at)
	{
		if(at != null)
		{
			if(data < at.getData())
			{
				at.setLeft(insert(data, at.getLeft()));
			}
			else
			{
				at.setRight(insert(data, at.getRight()));
			}
			return at;
		}
		else
		{
			at = new Node();
			at.setData(data);
			at.setLeft(null);
			at.setRight(null);
			size++;
			return at;
		}
	}

	public boolean remove(Integer data)
	{
		if(root == null)
		{
			return false;
		}
		return remove(data, root, null);
	}

	private boolean remove(Integer data, Node at, Node parent)
	{
		if(at == null)
		{
			return false;
		}
		if(at.getData().equals(data))
		{
			if((at.getLeft() == null) && (at.getRight() == null))
			{
				size--;
				if(parent == null)
				{
					root = null;
					return true;
				}
				if(parent.getLeft() == at)
				{
					parent.setLeft(null);
					return true;
				}
				parent.setRight(null);
				return true;
			}
			else if((at.getLeft() == null) ^ (at.getRight() == null))
			{
				size--;
				Node ourChild = at.getLeft();
				if(ourChild == null)
				{
					ourChild = at.getRight();
				}
				if(parent == null)
				{
					root = ourChild;
					return true;
				}
				if(parent.getLeft() == at)
				{
					parent.setLeft(ourChild);
					return true;
				}
				parent.setRight(ourChild);
				return true;

			}
			else
			{
				Integer smallest = getSmallest(at.getRight());
				at.setData(smallest);
				return remove(smallest, at.getRight(), at);
			}
		}
		if(at.getData().compareTo(data) > 0)
		{
			return remove(data, at.getLeft(), at);
		}
		return remove(data, at.getRight(), at);
	}

	private Integer getSmallest(Node at)
	{
		if(at == null)
		{
			return null;
		}
		if(at.getLeft() != null)
		{
			return getSmallest(at.getLeft());
		}
		return at.getData();
	}

	public String bread()
	{
		String retval = "";
		ArrayList<Node> beenTo = new ArrayList<Node>();
		ArrayList<Node> queue = new ArrayList<Node>();
		ArrayList<Integer> level = new ArrayList<Integer>();

		if(root != null)
		{
			queue.add(root);
			level.add(0);
		}

		while(queue.size() != 0)
		{
			Node at = queue.get(0);
			int curLevel = level.get(0);
			queue.remove(0);
			level.remove(0);

			if(beenTo.contains(at))
			{
				continue;
			}
			beenTo.add(at);

			retval += at.getData() + "(L:" + curLevel + ") ";
			if(at.getLeft() != null)
			{
				queue.add(at.getLeft());
				level.add(curLevel + 1);
			}
			if(at.getRight() != null)
			{
				queue.add(at.getRight());
				level.add(curLevel + 1);
			}
		}
		return retval;
	}

	public String preorder()
	{
		return preorder(root);
	}

	public String inorder()
	{
		return inorder(root);
	}

	public String postorder()
	{
		return postorder(root);
	}

	private String preorder(Node at)
	{
		if(at == null)
		{
			return "";
		}
		String retval = "";
		retval = at.getData() + " ";
		retval = retval + preorder(at.getLeft());
		retval = retval + preorder(at.getRight());
		return retval;
	}

	private String inorder(Node at)
	{
		if(at == null)
		{
			return "";
		}
		String retval = "";
		retval = retval + preorder(at.getLeft()); //L
		retval += at.getData() + " "; //N
		retval = retval + preorder(at.getRight()); //R
		return retval;

	}

	private String postorder(Node at)
	{
		if(at == null)
		{
			return "";
		}
		String retval = "";
		retval = retval + preorder(at.getLeft()); //L
		retval = retval + preorder(at.getRight()); //R
		retval += at.getData() + " "; //N
		return retval;

	}
}
