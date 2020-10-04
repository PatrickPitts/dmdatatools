var NUM_NODE_TOKENS = 0;

function getAllNodes() {
    let allNodes = $(document).find(".mapNodeBase");
    for (i = 0; i < allNodes.length; i++) {
        let thisNode = allNodes[i];
        console.log(thisNode.offset());
        //let nodeBounds = allNodes[i].position();
        //console.log(nodeBounds);
    }

}

$(document).ready((function () {
    $(".draggable").draggable(
    );
}));

function populateStartingNodes(numStartingNodes) {

    let nodeOriginDiv = document.getElementById("node-origin-space");

    for (let i = 0; i < numStartingNodes; i++) {
        NUM_NODE_TOKENS += 1;
        let newNode = document.createElement("div");
        let nodeId = "map-node-token_"+NUM_NODE_TOKENS;
        newNode.innerText = NUM_NODE_TOKENS;
        newNode.id = nodeId;
        newNode.classList.add('circleBase', 'type1', 'draggable');
        nodeOriginDiv.appendChild(newNode);
    }
}


