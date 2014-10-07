package org.sigmaGraph.graphRepresentation;

import java.util.Random;



public class Node extends GraphObject {
	
	public String label;
	public Float x;
	public Float y;
	
	public Node(String id, String label) {
		super(id);
		this.label = label;
		
		Random random = new Random();
		
		x = random.nextFloat();
		y = random.nextFloat();
	}
}
