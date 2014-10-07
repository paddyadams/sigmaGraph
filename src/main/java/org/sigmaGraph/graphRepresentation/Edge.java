package org.sigmaGraph.graphRepresentation;

public class Edge extends GraphObject{
	public String source;
	public String target;
	
	public Edge(String id, Node source, Node target) {
		super(id);
		this.source = source.id;
		this.target = target.id;
	}
	
}
