<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Help Eddy</title>
 <link rel="stylesheet" href="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.css" />
 <link rel="stylesheet"  type="text/css" href="css/index.css" />
 <link href="css/login-box.css" rel="stylesheet" type="text/css" />
 <script src="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.js"></script>
 <link href="http://leafletjs.com/atom.xml" type="application/atom+xml" rel="alternate" title="Leaflet Dev Blog Atom Feed">
 <script src="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.js"></script>
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
 <script type="text/javascript" src="js/ScriptJS/tp3qcm.js"  ></script>
 <script type="text/javascript" src="js/identif.js"  ></script>
 <script>
		MB_ATTR = 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, ' +
				'<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
				'Imagery © <a href="http://mapbox.com">Mapbox</a>';

		MB_URL = 'http://{s}.tiles.mapbox.com/v3/{id}/{z}/{x}/{y}.png';

		OSM_URL = 'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
		OSM_ATTRIB = '&copy; <a href="http://openstreetmap.org/copyright">OpenStreetMap</a> contributors';
	</script>
 
</head>

<body onLoad='connecter()'>
<div id="left"> <div id="login-box"> 
 </div>
 <div id="jeu"> </div>
 </div>
 <div id="map" class="map leaflet-container leaflet-fade-anim" style="height: 500px; position: relative;" tabindex="0"><div class="leaflet-map-pane" style="transform: translate3d(0px, 0px, 0px);"><div class="leaflet-tile-pane"><div class="leaflet-layer"><div class="leaflet-tile-container"></div><div class="leaflet-tile-container leaflet-zoom-animated"><img class="leaflet-tile leaflet-tile-loaded" src="http://b.tiles.mapbox.com/v3/examples.map-i875mjb7/13/4093/2724.png" style="height: 256px; width: 256px; left: 150px; top: 55px;">
           <img class="leaflet-tile leaflet-tile-loaded" src="http://a.tiles.mapbox.com/v3/examples.map-i875mjb7/13/4093/2723.png" style="height: 256px; width: 256px; left: 150px; top: -201px;"><img class="leaflet-tile leaflet-tile-loaded" src="http://b.tiles.mapbox.com/v3/examples.map-i875mjb7/13/4094/2723.png" style="height: 256px; width: 256px; left: 406px; top: -201px;"><img class="leaflet-tile leaflet-tile-loaded" src="http://c.tiles.mapbox.com/v3/examples.map-i875mjb7/13/4094/2724.png" style="height: 256px; width: 256px; left: 435px; top: 59px;">
  <img class="leaflet-tile leaflet-tile-loaded" src="http://a.tiles.mapbox.com/v3/examples.map-i875mjb7/13/4093/2725.png" style="height: 256px; width: 256px; left: 150px; top: 311px;">
 <img class="leaflet-tile leaflet-tile-loaded" src="http://a.tiles.mapbox.com/v3/examples.map-i875mjb7/13/4094/2725.png" style="height: 256px; width: 256px; left: 406px; top: 311px;">
 <img class="leaflet-tile leaflet-tile-loaded" src="http://c.tiles.mapbox.com/v3/examples.map-i875mjb7/13/4092/2723.png" style="height: 256px; width: 256px; left: -106px; top: -201px;">
 <img class="leaflet-tile leaflet-tile-loaded" src="http://c.tiles.mapbox.com/v3/examples.map-i875mjb7/13/4095/2723.png" style="height: 256px; width: 256px; left: 662px; top: -201px;">
 <img class="leaflet-tile leaflet-tile-loaded" src="http://a.tiles.mapbox.com/v3/examples.map-i875mjb7/13/4092/2724.png" style="height: 256px; width: 256px; left: -106px; top: 55px;">
 <img class="leaflet-tile leaflet-tile-loaded" src="http://a.tiles.mapbox.com/v3/examples.map-i875mjb7/13/4095/2724.png" style="height: 256px; width: 256px; left: 662px; top: 55px;">
  <img class="leaflet-tile leaflet-tile-loaded" src="http://a.tiles.mapbox.com/v3/examples.map-i875mjb7/13/4092/2725.png" style="height: 256px; width: 256px; left: -106px; top: 311px;">
 <img class="leaflet-tile leaflet-tile-loaded" src="http://a.tiles.mapbox.com/v3/examples.map-i875mjb7/13/4095/2725.png" style="height: 256px; width: 256px; left: 662px; top: 311px;">
 </div></div></div><div class="leaflet-objects-pane"><div class="leaflet-shadow-pane"></div><div class="leaflet-overlay-pane"></div><div class="leaflet-marker-pane"></div><div class="leaflet-popup-pane"></div></div></div><div class="leaflet-control-container"><div class="leaflet-top leaflet-left"><div class="leaflet-control-zoom leaflet-bar leaflet-control"><a class="leaflet-control-zoom-in" href="#" title="Zoom in" target="_self">+</a><a class="leaflet-control-zoom-out" href="#" title="Zoom out" target="_self">-</a></div></div><div class="leaflet-top leaflet-right"></div><div class="leaflet-bottom leaflet-left"></div><div class="leaflet-bottom leaflet-right"><div class="leaflet-control-attribution leaflet-control"><a href="http://leafletjs.com" title="A JS library for interactive maps" target="_self">Leaflet</a> | Map data © <a href="http://openstreetmap.org" target="_self">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/" target="_self">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com" target="_self">Mapbox</a></div></div></div>
 
 
 
 </div>
 <div id="nominatim"> <div id="contenu"> </div> </div>
 
</body>
</html>
