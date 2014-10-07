package org.sigmaGraph;

import org.sigmaGraph.graphRepresentation.Graph;

import com.vaadin.shared.ui.JavaScriptComponentState;

@SuppressWarnings("serial")
public class SigmaGraphState extends JavaScriptComponentState {
	public Graph theGraph = new Graph();
	
	public String backgroundColour = "#eee";
}
