package binarytree;

import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * User interface class, handles interacting with the user and passing data to the node class
 * 
 * @author jackrutherford
 * @date 4/5/22
 * @class CSCI 235
 *
 */
public class UserInterface {

	private Node root;
	private Scanner sc;
	
	public UserInterface() {
		sc = new Scanner(System.in);
	}
	
	/**
	 * Handles checking user input to call different methods depending on the user input.
	 * Runs until the user chooses to quit.
	 */
	public void manipulateTree() {
		root = new Node("A");
		createTree();
		optionMenu();
		boolean flag = false;
		while(!flag) {
			try {
				System.out.print("->");
				String str = sc.nextLine();
				if(str.trim().equals("")){ //checks if the value is an empty string first
					throw new EmptyStringException();
				}
				int num = Integer.parseInt(str);
				if(num == 1) {
					addNode();
				}
				else if(num == 2) {
					treeSize();
				}
				else if(num == 3) {
					findNode();
				}
				else if(num == 0) {
					flag = true;
					break;
				}
				else { //doesn't match an answer, so value is outside of desired range
					throw new InvalidInputException();
				}
				optionMenu();
			}catch(InvalidInputException e) {
				System.out.println("That's not a valid number! Try again");
				optionMenu();
			}catch(EmptyStringException e) {		
				System.out.println(e.getMessage());			
			}catch(NumberFormatException e) {
				System.out.println("Please enter an integer");
			}
		}
		
	}
	
	/**
	 * This method takes user input to find a node, and handles any errors that may pop up
	 */
	public void findNode() {
		System.out.println("Please enter the ID of the node you would like to find:");
		System.out.print("->");
		boolean flag = false;
		while(!flag){
			String value = "";
			try {
				value = sc.nextLine();
				if(value.trim().equals("")) {
					throw new EmptyStringException();
				}
				INode newNode = root.find(value);
				if(newNode == null) {
					throw new IOException();
				}
				System.out.println("Node " + newNode.getId() + " found\n" + newNode.getId());
				flag = true;
			}catch(EmptyStringException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println("Node " + value + " does not exist");
				break;
			}
		}
		
		
	}
	
	/**
	 * Takes user input to return the tree starting at a specific node
	 */
	public void treeSize() { //change this to look like hers
		System.out.println("Please input the root node->");
		boolean flag = false;
		INode newNode = null;
		String value = "";
		while(!flag) {
			try {
				value = sc.nextLine();
				if(value.trim().equals("")) {
					throw new EmptyStringException();
				}
				newNode = root.find(value);
				if(newNode == null) {
					throw new IOException();
				}
				newNode.printTree();
				flag = true;
			}catch(EmptyStringException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println("Node " + value + " does not exist");
				optionMenu();
				return;
			}
		}
		System.out.println("There are " + newNode.size() + " nodes in this tree.\n");
	}
	
	/**
	 * Adds a node that the user can choose it's name and choose the parent node.
	 * Handles any input errors or if the parent node has 2 children already
	 */
	public void addNode() {
		boolean flag = false;
		while(!flag) {
			try {
				System.out.println("Please enter the ID of the new node:");
				System.out.print("> ");
				String newID = sc.nextLine();
				if(newID.trim().equals("")) {
					throw new EmptyStringException(); 
				}
				System.out.println("Please enter the ID for parent node of " + newID + ":");
				System.out.print("> ");
				String parentID = sc.nextLine();
				if(newID.trim().equals("")) {
					throw new EmptyStringException(); 
				}
				boolean val = root.addChild(newID, parentID);
				if(val) {
					System.out.println("Node successfully added!");
					root.printTree();
					System.out.println(); //spacing line
				}
				flag = true;
				
			}catch(EmptyStringException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Standard option menu that displays the users choices
	 */
	public void optionMenu() {
		System.out.println(
			"Please select one of the following options: "+
			"\n1. Add Node"+
			"\n2. Tree Size"+
			"\n3. Find Node"+
			"\n0. Exit");
	}
	
	/**
	 * Adds nodes to the tree to start
	 */
	public void createTree() {
		root.addChild("B", "A");
		root.addChild("C", "A");
		root.addChild("D", "B");
		root.addChild("E", "B");
		root.addChild("F", "C");
		root.addChild("G", "C");
		root.addChild("H", "D");
		root.addChild("I", "D");

		root.addChild("J", "E");
		root.addChild("K", "E");
		root.addChild("L", "F");
		root.printTree();
		System.out.println("There are " + root.size() + " nodes in this tree.\n");
		}

}
