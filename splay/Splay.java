import java.util.ArrayList;
import java.util.Scanner;

public class Splay
{
	private Node root;
	private int size;
	private Node toSplay;

	public static void main(String[] args)
	{
		Splay myTree = new Splay();
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

	public Splay()
	{
		root = toSplay = null;
		size = 0;
	}

	private Node splay(Node at)
	{
		if(at == null)
		{
			return null;
		}
		Node parent = at.getParent();
		if(parent == null)
		{
			assert(at == null) : "We best be being root";
			return null;
		}
		Node grandParent = parent.getParent();
		if(grandParent == null)
		{
			assert(parent == root) : "Parent is somehow not root";
			//zig Transformation

			if(parent.getRight() == at)
			{
				parent.setRight(at.getLeft());
				at.setLeft(parent);
			}
			else
			{
				parent.setLeft(at.getRight());
				at.setRight(parent);
			}
			parent.setParent(at);
			root = at;
			root.setParent(null);
			return root;
		}
		else
		{
			boolean isParentLeft = (grandParent.getLeft() == parent);
			boolean isAtLeft = (parent.getLeft() == at);
			if(isParentLeft == isAtLeft)
			{
				//both the same either right or left
				if(isParentLeft)
				{
					parent.setLeft(at.getRight());
					at.setRight(parent);
					grandParent.setLeft(parent.getRight());
					parent.setRight(grandParent);
					//left left case
				}
				else
				{
					parent.setRight(at.getLeft());
					at.setLeft(parent);
					grandParent.setRight(parent.getLeft());
					parent.setLeft(grandParent);
					//right right case
				}
				Node great GrandParent = grandParent.getParent();
				grandParent.setParent(parent);
				parent.setParent(at);
				at.setParent(greatGrandParent);
			}
			else
			{
				//both different
				if(!isAtLeft)
				{
					parent.setRight(at.getLeft());
					grandParent.setLeft(at.getRight());
					at.setLeft(parent);
					at.setRight(grandParent);
				}
				else
				{
					parent.setLeft(at.getRight());
					grandParent.setRight(at.getLeft());
					at.setRight(parent);
					at.setLeft(grandParent);
				}
				Node great GrandParent = grandParent.getParent();
				at.setParent(greatGrandParent);
				parent.setParent(at);
				grandParent.setParent(at);
			}
			if(at.getParent() == root)
			{
				root = at;
			}
			if(at != root)
			{
				splay(at);
			}
			return at;
		}
	}

	public boolean insert(Integer data)
	{
		if(root == null)
		{
			root = new Node();
			root.setData(data);
			root.setLeft(null);
			root.setRight(null);
			root.setParent(null);
			size = 1;
			return true;
		}
		toSplay = null;
		if(data < root.getData())
		{
			root.setLeft(insert(data, root.getLeft()));
			root.getLeft().setParent(root);
		}
		else
		{
			root.setRight(insert(data, root.getRight()));
			root.getRight().setParent(root);
		}
		splay(toSplay);
		return true;
	}

	private Node insert(Integer data, Node at)
	{
		if(at != null)
		{
			if(data < at.getData())
			{
				at.setLeft(insert(data, at.getLeft()));
				at.getLeft().setParent(at);
			}
			else
			{
				at.setRight(insert(data, at.getRight()));
				at.getRight().setParent(at);
			}
			return at;
		}
		else
		{
			at = new Node();
			at.setData(data);
			at.setLeft(null);
			at.setRight(null);
			at.setParent(null);
			size++;
			toSplay = at;
			return at;
		}
	}

	public boolean remove(Integer data)
	{
		if(root == null)
		{
			return false;
		}
		if(remove(data, root, null))
		{
			assert(root.getData().equals(data)) : "Failed to splay";
			size --;
			Node left = root.getLeft();
			Node right = root.getRight();

			root = right;
			right = splaySmallest(right);
			if(root == null)
			{
				root = left;
			}
			else
			{
				root.setLeft(left);
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean remove(Integer data, Node at, Node parent)
	{
		if(at == null)
		{
			return false;
		}
		if(at.getData().equals(data))
		{
			splay(at);
			return true;
/*			if((at.getLeft() == null) && (at.getRight() == null))
			{
				size--;
				splay(at);
/*				if(parent == null)
				{
					root = null;
					return true;
				}*/
/*				if(parent.getLeft() == at)
				{
					parent.setLeft(null);
					return true;
				}
				parent.setRight(null);*/
			//	return true;
/*			}
			else if((at.getLeft() == null) ^ (at.getRight() == null))
			{
				size--;
				Node ourChild = at.getLeft();
				if(ourChild == null)
				{
					ourChild = at.getRight();
				}
				ourchild.setParent(at.getParent());
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
		}*/
		if(at.getData().compareTo(data) > 0)
		{
			return remove(data, at.getLeft(), at);
		}
		return remove(data, at.getRight(), at);
	}

	private Node splaySmallest(Node at)
	{
		if(at == null)
		{
			return null;
		}
		if(at.getLeft() != null)
		{
			return splaySmallest(at.getLeft());
		}
		splay(at);
		return at;
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
