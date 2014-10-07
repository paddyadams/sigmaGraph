package org.sigmaGraph;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.sigmaGraph.graphRepresentation.Edge;
import org.sigmaGraph.graphRepresentation.Graph;
import org.sigmaGraph.graphRepresentation.Node;
import org.sigmaGraph.selectionMethods.SelectionMethod;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;

@SuppressWarnings("serial")
@JavaScript({"sigma.min.js","SigmaGraphConnector.js","sigma.layout.forceAtlas2.min.js","sigma.plugins.dragNodes.min.js"})
public class SigmaGraph extends AbstractJavaScriptComponent {
	
	private List<ClickNodeListener> clickNodeListeners = new ArrayList<ClickNodeListener>();
	private SelectionMethod selectionMethod;

	public SigmaGraph() {
		super();
		this.setHeight("300px");
		setupEventHandlers();
	}


	private void setupEventHandlers() {
		this.addFunction("clickNodeH", new JavaScriptFunction() {
			public void call(JSONArray arguments) throws JSONException {
				String id =arguments.getString(0);
				Node node = getState().theGraph.getNodeFromId(id);
				
				for (ClickNodeListener clickNodeListener : clickNodeListeners) {
					clickNodeListener.nodeClicked(node);
				}
			}
		});		
	}

	@Override
	protected SigmaGraphState getState() {
		return (SigmaGraphState)super.getState();
	}
	
	public void addNode(Node node){
		getState().theGraph.nodes.add(node);
	}
	
	public void addEdge(Edge edge){
		getState().theGraph.edges.add(edge);
	}
	
	public void setGraph(Graph graph){
		getState().theGraph = graph;
	}
	
	public void startForceLayout(){
		callFunction("startForce");
	}
	
	public void stopForceLayout(){
		callFunction("stopForce");
	}
	
	public void attachClickNodeListener(ClickNodeListener listener){
		clickNodeListeners.add(listener);
	}
	
	public void setSelectionMethod(SelectionMethod selectionMethod){
		this.selectionMethod = selectionMethod;
		callFunction("setSelectImpl", selectionMethod.getImplementationName());
	}


	public SelectionMethod getSelectionMethod() {
		return selectionMethod;
	}
	
}
