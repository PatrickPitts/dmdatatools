<!DOCTYPE HTML>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/mapmover.css" rel="stylesheet">
    <script src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script src="webjars/jquery/3.5.1/jquery.min.js"></script>
    <script src="webjars/jquery-ui/1.12.0/jquery-ui.min.js"></script>
</head>
<body>
<div id="mapDiv">
    <img src="/localimages/toh_5e_cropped.jpg" id="map" />
</div>

<div id="nodeOriginSpace">

</div>
<button onclick="saveNodes()">Alert location of all nodes</button>

<input type="hidden" th:value="${configurationSettingsMap}" id="configurationSettingsMap">
<input th:if="${mapNodeTokenMap} != null" type="hidden" th:value="${mapNodeTokenMap}" id = "mapNodeTokenMap">
</body>


</html>


<script type="text/javascript">
    let NUM_NODE_TOKENS = 0;
    let configurationSettingsMap = {};
    let mapNodeTokenMap = {};
    $(function(){

        let configurationSettingsMapElement = $('#configurationSettingsMap');
        configurationSettingsMap = JSON.parse(configurationSettingsMapElement.val());

        if(configurationSettingsMap["HAS_STARTING_NODES"]){

            mapNodeTokenMap = JSON.parse($('#mapNodeTokenMap').val());
            populateGivenNodes(mapNodeTokenMap);
        } else {
            populateStartingNodes(configurationSettingsMap["NUM_STARTING_TOKENS"]);
        }
        makeDraggable();
    });

    function populateGivenNodes(nodeMap){

        let mapLocation = $("#map").position();
        for(const [nodeID, locationTuple] of Object.entries(nodeMap)){
            NUM_NODE_TOKENS += 1;
            let leftPix = "" + (locationTuple[0] + mapLocation.left) + "px";
            let topPix = "" + (locationTuple[1] + mapLocation.top) + "px";
            let newNode = document.createElement("div");
            newNode.id  = nodeID;
            newNode.classList.add('mapNodeBase', 'type1', 'draggable');
            newNode.innerText = nodeID.split("_")[1];
            //$(newNode.id).css({"position":"absolute", "left": leftPix, "top": topPix});
            newNode.style.top = topPix; newNode.style.left = leftPix;
            newNode.style.position = "absolute";
            $("#mapDiv").append(newNode);
        }
    }

    function populateStartingNodes(numStartingNodes) {

        let nodeOriginDiv = document.getElementById("nodeOriginSpace");

        for (let i = 0; i < numStartingNodes; i++) {
            NUM_NODE_TOKENS += 1;
            let newNode = document.createElement("div");
            let nodeId = "mapNodeToken_" + NUM_NODE_TOKENS;
            newNode.innerText = NUM_NODE_TOKENS;
            newNode.id = nodeId;
            newNode.classList.add('mapNodeBase', 'type1', 'draggable');
            nodeOriginDiv.appendChild(newNode);
        }
    }
    function makeDraggable(){
        $(".draggable").draggable(
            {containment: 'parent'}
        );
    }



    function getAllNodes() {

        var mapPos = $("#map").position();

        $(".mapNodeBase").each(function (index) {
            console.log($(this).attr("id"));
            console.log($(this).position().left);
            console.log($(this).position().top);
        });
    }

    function saveNodes(){
        let mapNodeData = {};
        mapNodeData["gameMapNodeLocations"] = {};
        $(".mapNodeBase").each(function(){
            mapNodeData["gameMapNodeLocations"][$(this).attr("id")] = [$(this).position().left, $(this).position().top];
        });
        //TODO update name get based on input data or passed data
        mapNodeData['gameMapName'] = 'Tomb Of Horrors';

        console.log(mapNodeData);
        $.ajax({
            url:'/uploadMapData',
            contentType: 'application/json; charset=utf-8',
            dataType:'json',
            type:'post',
            data:JSON.stringify(mapNodeData)
        });
    }


</script>