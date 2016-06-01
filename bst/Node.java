public class Node
{
	private Node left, right;
	private Integer data;

	public Node()
	{
		left = right = null;
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

	public Integer setData(Integer data)
	{
		return this.data = data;
	}

	public String toString()
	{
		return " " + data;
	}
}
