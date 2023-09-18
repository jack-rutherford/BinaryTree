package binarytree;

/**
 * 
 * Node class that has an ID, and a left, right, and parent node. Uses recursion to
 * search the binary tree that it creates to display the tree, and find other nodes within the tree.
 * This class is a good review of recursion.
 * 
 * @author jackrutherford
 * @date 4/5/22
 * @class CSCI 235
 *
 */
public class Node implements INode{

	private String id;
	private INode left;
	private INode right;
	private INode parent;

	public Node(String ID) { // Used to create the root node (which has no parent)
		id = ID;
		left = right = null;
	}

	public Node(String newNodeID, Node parent) { // Used to create all other nodes.
		id = newNodeID;
		this.parent = parent;
		left = right = null;
	}

	

	/**
	 * This method checks to see if a node with the parentID exists in the tree.
	 * If not, it returns false after printing an appropriate message. If the
	 * node exists, the method checks to see if it already has two children. If
	 * it does, the method returns false. Otherwise, it either adds the new node
	 * as the parent node’s left child (if the parent has no children) or as the
	 * right child (if the parent already has one child). A recursive approach is
	 * used.
	 *
	 * @param ID The ID of the new node to be added to the tree.
	 * @param parentID The ID of the parent of the new node.
	 * @return true if new node was added successfully, false otherwise.
	 */
	@Override
	public boolean addChild(String ID, String parentID) {

		INode parent = find(parentID);

		if(parent == null) {
			System.out.println("Node " + parentID + " not found");
			return false;
		}
		
		if(id.equals(parentID)) {
			if(left != null && right != null) {
				System.out.println("Parent " + this.getId() + " already has two children");
				return false;
			}
			if(left == null) {
				left = new Node(ID, this);
				
				return true;
			}
			else {
				right = new Node(ID, this);
				return true;
			}
		}
		else {
			return parent.addChild(ID, parentID);
		}
	}
	/**
	 * This method looks within the tree to find if “value” (the ID of the node
	 * to be found) is contained in that subtree. As the search progresses down
	 * the tree different nodes will be calling find().
	 *
	 * @param value a string (ID of a node) to be found in the tree
	 * @return the node, if found; null otherwise.
	 */
	@Override
	public INode find(String value) {
		INode search = null;

		if(this.id.equals(value)) {
			search = this;
		}
		else if(left != null){
			search = left.find(value);
			if(right != null && search == null) {
				search = right.find(value);
			}
		}

		return search;
	}
	/**
	 * Returns the parent of this node.

	 *
	 * @return the parent of this node.
	 */
	@Override
	public INode getParent() {
		return parent;
	}
	/**
	 * Returns the size of the tree or subtree whose root calls this ethod.
	 *
	 * @return the size of the tree (or subtree) starting from this node
	 * all the way down to the leaf nodes.
	 */
	@Override
	public int size() {
		int size = 1;
		if(left != null) {
			size += left.size();
			if(right != null) {
				size += right.size();
			}
		}
		return size;
	}
	/**
	 * Returns a string with the IDs of this node and its immediate children (if
	 * any) all on one line.
	 *
	 * @return a string with the IDs of this node and its immediate children (if
	 * any).
	 */
	public String toString() {
		String temp = "";
		temp += this.getId() + " ";
		if(left != null) {
			temp += left.getId() + " ";
			if(right != null) {
				temp += right.getId() + " ";
			}
		}
		return temp;
	}
	/**
	 * Returns the ID of this node.
	 *
	 * @return the String ID of this node.
	 */
	@Override
	public String getId() {
		return id;
	}
	/**
	 * Uses recursion (and the toString() method) to print (on a line) each
	 * node's ID and those of any children it has.
	 */
	@Override
	public void printTree() {
		System.out.println(toString());
		if(left != null) {
			left.printTree();
			if(right != null) {
				right.printTree();
			}
		}
	}
}
