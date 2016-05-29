import java.io.*;
import java.util.Scanner;

public class HuffmanCoding 
{
	
	public static class ListBinNode
	{ 
		public int prob;
		public String chaStr;
		public ListBinNode next;
		public ListBinNode left;
		public ListBinNode right;
		 
		public ListBinNode()
		{
			next = null;
			left = null;
			right = null;
		}
		
		public ListBinNode(int item)
		{
			prob = item;
			next = null;
			left = null;
			right = null;
		}
		
		public ListBinNode(int item, String str)
		{
			chaStr = str;
			prob = item;
			next = null;
			left = null;
			right = null;
			
		}
		
		public void printNode(ListBinNode T, PrintWriter outFile3)
		{
			outFile3.print(T.chaStr + " " + T.prob + " ");
			if(T.next == null)
				outFile3.print("NULL");
			else
				outFile3.print(T.next.chaStr);
			if(T.left == null)
				outFile3.print(" NULL");
			else
				outFile3.print(" " + T.left.chaStr);
			if(T.right == null)
				outFile3.print(" NULL");
			else
				outFile3.print(" " + T.right.chaStr);
			
				
			outFile3.println();
		}
	}

	public static class HuffmanLinkedList
	{ 
		ListBinNode listHead;
		ListBinNode oldListHead;
		HuffmanLinkedList()
		{
			listHead = new ListBinNode(-9999, "dummy");
			oldListHead = new ListBinNode(-9999, "dummy");
			oldListHead.next = listHead.next;
		}
		
		ListBinNode findSpot(int item)
		{
			ListBinNode spot = listHead;
			while(spot.next != null && spot.next.prob < item)
			{
				spot = spot.next;
			}
			return spot;
		}
		
		void listInsert(ListBinNode node)
		{
			ListBinNode spot;	
			spot = findSpot(node.prob);
			node.next = spot.next;
			spot.next = node;
		}
		
		void printList(int data, PrintWriter outFile)
		{
				outFile.print("Inserting " + data + ": ");
				outFile.print("listHead->" + "\"(dummy,0,"); 
				ListBinNode current;
				current = listHead;
				while(current.next != null)
				{
					outFile.print(current.next.chaStr + ")->(" + current.next.chaStr + "," + current.next.prob + ","); 
					current = current.next;
				}
				outFile.print("NULL)->NULL");
				outFile.println();
		}
		
		boolean isEmpty()
		{
			if(listHead.next == null)
				return true; 
			else
				return false;
		}
	}

	public static class HuffmanBinaryTree
	{
		public String chaStr;
		public int prob;
		public String code;
		ListBinNode root;
		HuffmanBinaryTree(ListBinNode rootNode)
		{
			root = rootNode;
		}	
		
		//recursive
		void constructCharCode(ListBinNode T, String code, PrintWriter outFile1)
		{
			if(T == null)
				return;
			else if(T.left == null && T.right == null)
			{
				outFile1.println(T.chaStr + " " + code ); 
			}
			else
				constructCharCode(T.left, code + "0", outFile1);
				constructCharCode(T.right, code + "1", outFile1);
					
		}
		
		void preOrder(ListBinNode T, PrintWriter outFile2)
		{
			if(T == null)
			return;
			else
			{
				ListBinNode temp = new ListBinNode();
				temp.printNode(T, outFile2);
				preOrder(T.left, outFile2);
				preOrder(T.right, outFile2);
			}	
		}
	}


	public static void main(String[] argv) throws IOException 
	{		
		Scanner myFile = new Scanner(new FileReader(argv[0]));
		PrintWriter outFile1 = new PrintWriter(new FileWriter(argv[1]));
		PrintWriter outFile2 = new PrintWriter(new FileWriter(argv[2]));
		PrintWriter outFile3 = new PrintWriter(new FileWriter(argv[3]));
		int probability;
		String chaString;
		HuffmanLinkedList myList = new HuffmanLinkedList();
		
		while (myFile.hasNext())
		{
			chaString = myFile.next();
			probability = myFile.nextInt();
			ListBinNode newNode = new ListBinNode(probability, chaString);
			myList.listInsert(newNode);
		}

		myFile.close();
		
		ListBinNode Root = new ListBinNode();
		while(myList.listHead.next.next != null)
		{
			ListBinNode newNode = new ListBinNode();
			newNode.prob = myList.listHead.next.prob + myList.listHead.next.next.prob;
			newNode.chaStr = myList.listHead.next.chaStr + myList.listHead.next.next.chaStr;
			newNode.left = myList.listHead.next;
			newNode.right = myList.listHead.next.next;
			myList.listHead.next = myList.listHead.next.next.next;
			myList.listHead.printNode(newNode, outFile3);
			myList.listInsert(newNode);
			myList.printList(newNode.prob, outFile3);
			Root = newNode;
		}
		HuffmanBinaryTree myTree = new HuffmanBinaryTree(Root);
		myTree.constructCharCode(Root, "", outFile1);
		myTree.preOrder(Root, outFile2);
		outFile1.close();
		outFile2.close();
		outFile3.close();
	}

}
