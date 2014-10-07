package org.sigmaGraph.graphRepresentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
	public List<Node> nodes = new ArrayList<Node>();
	public List<Edge> edges = new ArrayList<Edge>();
	
	private Map<String, Node> mapOfIDToNode = new HashMap<String, Node>();
	
	
	public Graph() {
	}
	
	public void addNode(Node node){
		nodes.add(node);
		mapOfIDToNode.put(node.id, node);
	}
	
	public void addEdge(Edge edge){
		edges.add(edge);
	}
	
	public Node getNodeFromId(String id){
		return mapOfIDToNode.get(id);
	}
}
