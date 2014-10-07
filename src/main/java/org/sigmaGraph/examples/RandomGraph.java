package org.sigmaGraph.examples;

import java.util.Random;

import org.sigmaGraph.graphRepresentation.Edge;
import org.sigmaGraph.graphRepresentation.Graph;
import org.sigmaGraph.graphRepresentation.Node;

public class RandomGraph {
	private static Random random = new Random();
	
	public static Graph generateRandomGraph(int numberOfNodes,int numberOfEdges){
		Graph myRandomGraph = new Graph();
		
		addNodes(numberOfNodes, myRandomGraph);		
		addEdges(numberOfNodes, numberOfEdges, myRandomGraph);
		
		return myRandomGraph;
	}

	private static void addEdges(int numberOfNodes, int numberOfEdges,
			Graph myRandomGraph) {
		for (int i = 0; i < numberOfEdges; i++) {
			Node target = myRandomGraph.nodes.get(random.nextInt(numberOfNodes));
			Node source = myRandomGraph.nodes.get(random.nextInt(numberOfNodes));
			
			Edge thisEdge = new Edge("E"+i, source, target);
			myRandomGraph.edges.add(thisEdge);
		}
	}

	private static void addNodes(int numberOfNodes, Graph myRandomGraph) {
		for (int i = 0; i < numberOfNodes; i++) {
			Node thisNode = new Node("N"+i, "node " + i);
			myRandomGraph.addNode(thisNode);
		}
	}
}
