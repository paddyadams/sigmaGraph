package org.sigmaGraph.graphRepresentation;

public class GraphObject {

	public String id;
	public Float size;
	public String color;
	
	public GraphObject(String id) {
		this.id = id;
		size = 0.1f;
		color = "#ec5148";
	}

}