<%-- 
    Document   : home
    Created on : Jan 14, 2017, 4:43:47 PM
    Author     : fotini
--%>

<%@page import="com.project1334.util.User"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Home Page</title>
</head>
<body>
    <%User user = (User) session.getAttribute("User"); %>   
    <h3>Hi <%=user.getName() %></h3>
    <strong>Your Email</strong>: <%=user.getEmail() %><br>

<br>
<form action="Logout" method="post">
    <input type="submit" value="Logout" >
</form>
<br>

<div id="map" style="width:100%;height:500px"></div>

<script>
    <%
        Object message = "";
        if (request.getAttribute("message")!=null) {
            message= request.getAttribute("message");
        }
        
        pageContext.setAttribute("message", message);

    %>
function myMap() {
  var myCenter = new google.maps.LatLng(39.35783584,22.95059502);
  var mapCanvas = document.getElementById("map");
  var mapOptions = {center: myCenter, zoom: 16,
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
 
    
    //google.maps.event.addDomListener(window, 'load', initialize);
                    
  
  var infowindow = new google.maps.InfoWindow({
    content: String
    //content: 'Latitude: ' + location.lat() + '<br>Longitude: ' + location.lng()     
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


</body>
</html>
