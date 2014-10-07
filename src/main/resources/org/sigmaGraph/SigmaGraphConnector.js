/**
 * The connector
 */
var debug;

org_sigmaGraph_SigmaGraph = function() {
    // 	Instantiate sigma:
    sigmaG = new sigma({
    		graph: this.getState().theGraph,
    		container: this.getElement()
    		});
    
    var connector = this;
    
    sigmaG.bind("clickNode",function(e){connector.clickNodeH(e.data.node.id);});
    
    this.startForce = function(){sigmaG.startForceAtlas2({worker: true});};
    this.stopForce = function(){sigmaG.stopForceAtlas2();};

    setupSelections(connector,sigmaG);
    /*
    this.onStateChange = function() {
    }*/
};

setupSelections = function(connector,sigmaG){
	connector.noSelect = function(id){};
    
	connector.select = function(e){connector.selectImpl(e.data.node.id);};
	connector.selectImpl = connector.noSelect;
	connector.setSelectImpl = function(newImpl){connector.selectImpl = eval(newImpl);};
    
    sigmaG.bind("clickNode",function(e){connector.select(e);});
    sigmaG.bind("clickStage",function(e)
    		{
    			sigmaG.graph.nodes().forEach(function(n){n.color = n.originalColor;})
    			sigmaG.graph.edges().forEach(function(e){e.color = e.originalColor;})
    			sigmaG.refresh();
    		});
    
    storeOriginalColours(sigmaG);
    
    connector.selectOneHop = function(id){
    	toKeep = sigmaG.graph.neighbours(id);
    	toKeep[id] = true;
    	
    	colourGraph(sigmaG,toKeep);
    }
    
    connector.selectNodeOnly = function(id){
    	toKeep = {};
    	toKeep[id] = true;
    	colourGraph(sigmaG,toKeep);
    }
    
    connector.selectComponent = function(id){
    	alert("this.selectComponent");
    }

};

colourGraph = function(sigmaG,toKeep){
	sigmaG.graph.nodes().forEach(function(n) {
		if(toKeep[n.id])
			n.color = n.originalColor;
		else
          n.color = '#eee';
      });
	
	sigmaG.graph.edges().forEach(function(e) {
        if (toKeep[e.source] || toKeep[e.target])
          e.color = e.originalColor;
        else
          e.color = '#eee';
      });
	sigmaG.refresh();
}

storeOriginalColours = function(sigmaF){
	sigmaG.graph.nodes().forEach(function(n) {
        n.originalColor = n.color;
      });
    sigmaG.graph.edges().forEach(function(e) {
        e.originalColor = e.color;
      });
};

sigma.classes.graph.addMethod('neighbours', function(nodeId) {
    var k,
        neighbours = {},
        index = this.allNeighboursIndex[nodeId] || {};

    for (k in index)
      neighbours[k] = this.nodesIndex[k];

    return neighbours;
 });