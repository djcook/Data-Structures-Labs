public class Node
{
	private Node left, right, parent;
	private Integer data;

	public Node()
	{
		left = right = parent = null;
		data = 0;
	}

	public Node getLeft()
	{
		return left;
	}

	public Node getRight()
	{
		return right;
	}
	public Node getParent()
	{
		return parent;
	}

	public Integer getData()
	{
		return data;
	}

	public Node setLeft(Node left)
	{
		return this.left = left;
	}

	public Node setRight(Node right)
	{
		return this.right = right;
	}
	public Node setParent(Node parent)
	{
		return this.parent = parent;
	}

	public Integer setData(Integer data)
	{
		return this.data = data;
	}

	public String toString()
	{
		return " " + data;
	}
}
