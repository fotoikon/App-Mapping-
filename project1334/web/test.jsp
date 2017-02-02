<%-- 
    Document   : test
    Created on : Jan 15, 2017, 8:13:33 PM
    Author     : fotini
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add your story</title>
        <link type="text/css" rel="stylesheet" href="styleStory.css">
        
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
        <script type="text/javascript" src="js/jquery.jcarousel.js"></script>
        <!-- Load MixItUup js -->
        <script type="text/javascript" src="js/jquery.mixitup.js"></script>
        <!-- Load js -->
        <script type="text/javascript" src="js/custom.js"></script>
    </head>
    <body >
        <c:set var="longitude" value="0"/>
       
           
            <p style="margin-top: 100px;text-align: center;padding-top: 10px; font-size: 33px">Create your own story.</p>
            
             <%
                Object theuser = "";
                Object storyid = "";
                Object storystep = "";
                Object message = "";
                if (request.getAttribute("message")!=null) {
                    message= request.getAttribute("message");
                }
                if (request.getAttribute("theuser")!=null) {
                    theuser= request.getAttribute("theuser");
                }
                if (request.getAttribute("storyid")!=null) {
                    storyid= request.getAttribute("storyid");
                    storystep= request.getAttribute("storystep");
                }
                pageContext.setAttribute("message", message);
                pageContext.setAttribute("storyid", storyid);
                pageContext.setAttribute("storystep", storystep);
                pageContext.setAttribute("theuser", theuser);
                
            %>

            <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
            <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.js"></script>
            <script  src="https://maps.googleapis.com/maps/api/js"> </script>

          
            <div id="map" style="width:100%;height:500px"></div>

<script>
function myMap() {
  var myCenter = new google.maps.LatLng(39.370264,22.941003);
  var mapCanvas = document.getElementById("map");
  var mapOptions = {center: myCenter, zoom: 10,
        disableDefaultUI: true,
        panControl: true,
        zoomControl: true,
        mapTypeControl: true,
        scaleControl: true,
        streetViewControl: true,
        overviewMapControl: true,
        rotateControl: true};
    
  var map = new google.maps.Map(mapCanvas, mapOptions);
  //var marker = new google.maps.Marker({position:myCenter});
  //marker.setMap(map);
  google.maps.event.addListener(map, 'click', function(event) {
    placeMarker(map, event.latLng);
  });
  
  }
  function placeMarker(map, location) {
  var marker = new google.maps.Marker({
    position: location,
    map: map
  });
  
  var infowindow = new google.maps.InfoWindow({
    content: 'Latitude: ' + location.lat() + '<br>Longitude: ' + location.lng()
  });
  
  /*var infowindow = new google.maps.InfoWindow({
    content: "Hello World!"
  });*/
  infowindow.open(map,marker);
  
  /*var flightPath = new google.maps.Polyline({
    path: [pnt1, pnt2, pnt3],
    strokeColor: "#0000FF",
    strokeOpacity: 0.8,
    strokeWeight: 2
  });
  flightPath.setMap(map);*/

}

</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC4hwLcelVSK2yIsv2mWdG3Q21WF3LqToQ&callback=myMap"></script>

        <div id="map_canvas"></div>
            
        

        <div id="story_window">
            <p style="text-align: center;padding-top: 5px; font-size: 27px; color: black"> Story step ${storystep}</p>
            <hr>
            
            <FORM ACTION="My_first_create_servlet" METHOD=POST enctype="multipart/form-data">
                <center>
                <input type="hidden" name="theuser" value="${theuser}">
                <input type="hidden" name="action" value="story_steps">
                <input type="hidden" name="storyid" value="${storyid}">
                <input type="hidden" name="storystep" value="${storystep}">
                <p style="font-size: 17px">Enter the title:</p>
                <input style="text-align: center;height:30px" size="30" maxlength="25" type="text" name="title" required>
                <p style="font-size: 17px">Enter the header:</p>
                <input style="text-align: center;height:30px; width:70%" size="120" maxlength="80" type="text" name="header" required>
                <p style="font-size: 17px">Enter the text:</p>
                <textarea style="text-align: center;height:150px;width:95%" type="text" name="text_field" ></textarea>
                <input size="20" type="hidden" id="latbox" name="lat">
                <input size="20" type="hidden" id="lngbox" name="lng">
                <br>
                <br>
                <div style="font-size: 15px">file size < 3MB</div> <input type="file" name="file" />
                <br>
               
                <% if (message.equals("not a valid image")) { %> <i style="color:red;">${message}</i><br> <% } %>
                <% if (message.equals("please enter a marker")) { %> <i style="color:red;">${message}</i><br> <% } %>
                 <br>
                <INPUT TYPE=SUBMIT VALUE="  Submit   ">
                </center>
            </form>
           
                <hr>
            <FORM ACTION="StoryController" METHOD=POST>
            <center>
                <input type="hidden" name="theuser" value="${theuser}">
                <INPUT TYPE=SUBMIT VALUE="  Exit Editing   ">
            </center>
            </FORM>
        </div>
    </body>
</html>

