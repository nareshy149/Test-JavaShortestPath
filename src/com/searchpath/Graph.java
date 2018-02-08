package com.searchpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Graph {
	public Node rootNode;
	public Node destinationNode;
	public ArrayList<Node> nodes = new ArrayList<Node>();
	public int[][] adjMatrix;// Edges will be represented as adjacency Matrix
	int size;

	public void setRootNode(Node n) {
		this.rootNode = n;
	}

	public Node getRootNode() {
		return this.rootNode;
	}

	public void setDestinationNode(Node n) {
		this.destinationNode = n;
	}

	public Node getDestinationNode() {
		return this.destinationNode;
	}

	public void addNode(Node n) {
		nodes.add(n);
	}

	// This method will be called to make connect two nodes
	public void connectNode(Node start, Node end) {
		if (adjMatrix == null) {
			size = nodes.size();
			adjMatrix = new int[size][size];
		}

		int startIndex = nodes.indexOf(start);
		int endIndex = nodes.indexOf(end);
		adjMatrix[startIndex][endIndex] = 1;
		adjMatrix[endIndex][startIndex] = 1;
		
		
	}

	private Node getUnvisitedChildNode(Node n) {

		int index = nodes.indexOf(n);
		int j = 0;
		while (j < size) {
			if (adjMatrix[index][j] == 1 && ((Node) nodes.get(j)).visited == false) {
				return (Node) nodes.get(j);
			}
			j++;
		}
		return null;
	}

		public void searchPath2(Node source, Node destination) {

		Queue<Node> q = new LinkedList<Node>();
		Map<Node,Node> prevNodeMap = new HashMap<Node, Node>();
		Node currentNode = source;
		currentNode.visited = true;
		currentNode.label = 'S';
		q.add(currentNode);
		while (!q.isEmpty()) {
		   currentNode = q.remove();
		   if(currentNode == destination){
			   destination.label='E';
			   break;
		   }else{
				Node child = null;
				while ((child = getUnvisitedChildNode(currentNode)) != null) {
					child.visited = true;
					child.label = '"';
					q.add(child);
					prevNodeMap.put(child,currentNode);
				}
			
		   }
			
		}
		if (currentNode != destination) {
			System.out.println("no destination node exist");
		}
		
		List<Node> direction = new LinkedList<Node>();
		Node node = destination;
		while (node!=null) {
			node.label = '*';
			direction.add(node);
			node = prevNodeMap.get(node);			
		}
		Collections.reverse(direction);
		source.label='S';
		destination.label='E';
		// Clear visited property of nodes
		clearNodes();
	}

	
	private void clearNodes() {
		int i = 0;
		while (i < size) {
			Node n = (Node) nodes.get(i);
			n.visited = false;
			i++;
		}
	}

	private void printNode(Node n) {
		System.out.print(n.label + " ");
	}
	
	public void printAdjMatrix(){
		System.out.println("total nodes "+size);
		for (int i = 0; i < adjMatrix.length; i++) {
		    for (int j = 0; j < adjMatrix[i].length; j++) {
		    	System.out.print(" i,j "+i+","+j);
		        System.out.print(" "+adjMatrix[i][j]);
		    }
		    System.out.println();
		}
	}

}
