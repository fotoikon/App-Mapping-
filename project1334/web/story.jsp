<%-- 
    Document   : story
    Created on : Feb 1, 2017, 6:00:03 PM
    Author     : fotini
--%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>story form</title>
    <link rel="stylesheet" type="text/css" href="login/css/login.css">
</head>                                


<body>
<div class="login">
<div class="heading">
<h2> Title of your Story</h2>
<div class="input-group input-group-lg">
        <span class="input-group-addon"></span>
        <input type="text" class="form-control" name="title" placeholder="Story Title" required>
  
</div>

<!--<div class="input-group input-group-lg">
<span class="input-group-addon"></span>
<input type="text" class="form-control" name="description" placeholder="Description">
</div> 
-->
<form action="StoryServlet" method="post">
    
        <div class="input-group input-group-lg">
        <span class="input-group-addon"></span>
        <input type="text" class="form-control" name="text" placeholder="Description" required>
        </div>
        <input type="hidden" id="lat" name="lat">
        <input type="hidden" id="lon" name="lon">
        <button type="submit" class="float">Submit Story</button>
</form>

   
<div id="floating-panel">
    <input onclick="clearMarkers();" type=button value="Hide Markers">
    <input onclick="showMarkers();" type=button value="Show All Markers">
    <input onclick="deleteMarkers();" type=button value="Delete Markers">
    
</div>
</div>
</div>   
    
    
    
<div id="map" style="width:100%;height:500px;"></div>

<script>
    var map;
    var markersArray = [];
    
    //var info [];
        function initialize() {
            var myLatlng = new google.maps.LatLng(39.35789,22.95125);
            var myOptions = {
                zoom:17,
                center: myLatlng,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            }
            map = new google.maps.Map(document.getElementById("map"), myOptions);
            //add infoware 
            var html = "<table>" +
            "<tr> <td> Place:</td> <td><input type='text' name='place' id='place' required/> </td> </tr>  " +
            "<tr> <td> Description:</td> <td><input type='text' name='description' id='description'required/> </td> </tr> " +
            "<tr> <td> Category:</td> <td><input type='text' name='category' id='category'/> </td> </tr> " +
            "<tr> <td></td> <td> \n\
            <input type='submit' id='submit' value='Save & Close' onclick='saveData()' /> </td> </tr> ";
            
            
            
            infowindow = new google.maps.InfoWindow({
                content: html
            });
            
            // marker refers to a global variable
            marker = new google.maps.Marker({
                position: myLatlng,
                map: map
            });
            
            
            

            google.maps.event.addListener(map, "click", function(event) {
                // get lat/lon of click
                var clickLat = event.latLng.lat();
                var clickLon = event.latLng.lng();
                
                

                // show in input box
                document.getElementById("lat").value = clickLat.toFixed(5);
                document.getElementById("lon").value = clickLon.toFixed(5);

               

                var marker = new google.maps.Marker({
                    position: new google.maps.LatLng(clickLat,clickLon),
                    map: map
                    
                });
                
                infowindow.setContent(html);
                infowindow.open(map, marker);
                
                markersArray.push(marker);
                //info.push(html);

            });
            
            // Opens the InfoWindow when marker is clicked.
            marker.addListener('click', function() {
            infoWindow.open(map, marker);
            });
     
        }   
        
      function saveData(){
        
        var place = escape(document.getElementById("place").value);
        var description = escape(document.getElementById("description").value);
        var category = escape(document.getElementById("category").value);
        var latlng = marker.getPosition();
        
        //var url = "save_json.php?title=" + title + "&description=" + description +
        //    "&category=" + category + "&lat=" + latlng.lat() + "&lng=" + latlng.lng();
        //downloadUrl(url, function(data, responseCode) {
        //if (responseCode == 200 && data.length <= 1) {
        infowindow.close();
        //document.getElementById("message").innerHTML = "Location added.";
        }
      
      
      // Sets the map on all markers in the array.
      function setMapOnAll(map) {
        for (var i = 0; i < markersArray.length; i++) {
          markersArray[i].setMap(map);
        }
        //for (var i = 0; i < info.length; i++) {
        //  info[i].infowindow.open(map, marker);
        //}
      }
      
      // Removes the markers from the map, but keeps them in the array.
      function clearMarkers() {
        setMapOnAll(null);
      }

      // Shows any markers currently in the array.
      function showMarkers() {
        setMapOnAll(map);
      }
      
      // Deletes all markers in the array by removing references to them.
      function deleteMarkers() {
        clearMarkers();
        markersArray = [];
      }

    window.onload = function () { initialize() };
    </script>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC4hwLcelVSK2yIsv2mWdG3Q21WF3LqToQ&callback=inintialize">
        
    </script>

    Lat: <input type="text" id='lat'>
Lon: <input type="text" id='lon'>
tittle: <input type="text" id='title'>
text: <input type="text" id='description'>

</body>
</html>