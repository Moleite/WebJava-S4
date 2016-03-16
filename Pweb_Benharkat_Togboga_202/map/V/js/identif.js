var q;
var point;
var pointList= new Array();
var polyline;
var fin;
var connexion="<H2>Help Eddy</H2>";
connexion+="Veuillez vous connecter";
connexion+="<br />";
connexion+="<br />";
connexion+="<div id='login-box-name' style='margin-top:20px;'>Pseudo:</div>";
connexion+="<div id='login-box-field' style='margin-top:20px;'> <input name='q'"; 
connexion+="id='pseudo' class='form-login' title='Username' value='' size='30' maxlength='2048' /></div>";
connexion+="<div id='login-box-name'>Password:</div><div id='login-box-field'>";
connexion+="<input name='q' type='password' id='passwd' class='form-login' title='Password' value='' size='30' maxlength='2048' />";
connexion+="</div> <br />";
connexion+="<br />";
connexion+="<a href='#' onclick='idgood()'; return false' class='myButton1'>Connexion</a></a>";
var timer;  //variable référenéant un objet temporisateur
var  temps_imparti =  10000;  //temps imparti pour donner la réponse (10s)
var p; //référence au bloc div d'affichage (<div "id=QUEST"></div>)

var lvl=0;

var repOK = "Bonne r\351ponse !";
var repKO = "'D\351sol\351\n Mauvaise Réponse'";
var repNO = "d\351sol\351";

var invite = "<p>Eddy voyage en France, il ne connait pas bien le pays, il planifie donc ses itin\351raires à l'aide d'une map. Aidez le à trouver les endroits à visiter ! mais pour cela es-tu assez cultivé ? Pour répondre déplacer la réponse de votre choix </br>sur la map </p> ";
	invite += "<a href='#' onclick='preparerjeu()'; return false' class='myButton1'>Commencer</a>";
 var popup = L.popup();
 var map;
 var objet=L;
//tableau au format JSON représentant un ensemble de questions
//avec pour chaque question, 3 attributs : question (intitué de la question), différents choix indicés, indice de la bonne réponse
var tabObject;
var tabCible;
var cptCible=0;
window.onload = function () {
	p =  document.getElementById('jeu'); 
	tabObject = [
		{question:"Quelle stade a accueilli l'arrivée du tour de France de 1968 à 1974 ?",			choix: ["Parc des Princes, Paris", "Vélodrome de Saint-Quentin-en-Yvelines", "La Cipale, Vélodrome de Vincennes"], reponse : "draggable2", rep:"2", Lat:48.82337, Lng:2.430078, type:"marker", image:"images/lacipale.png"} ,
		{question:"Quelle cathédrale est la plus grande de France ?",				choix: ["Notre-Dame d'Amiens","Notre-Dame de Paris","Notre-Dame de Chartres"], 				reponse : "draggable0", rep:"0", Lat:49.895, Lng:2.3022, type:"marker", image:"images/amiens.png"}, 
		{question:"L'armistice de la seconde Guerre Mondiale a eu lieu à Reims, à quelle date ?",	choix: ["7 mai 1945","8 mai 1945","9 mai 1945"], reponse : "draggable1", rep:"1", Lat:49.258329, Lng:4.031696, type:"circle", image:"images/reims.png"},
		{question:"Dans quelle ville peut-on se balader sur la Promenade des Anglais ?",				choix: ["Marseille","Toulon","Nice"], 				reponse : "draggable2", rep:"2", Lat:43.710173, Lng:7.261953, type:"circle", image:"images/nice.png"},
		{question:"Dans quelle ville a lieu chaque année un des plus grands festival cinématographique ?",				choix: ["Cannes","Bordeaux","Toulouse"], 				reponse : "draggable0", rep:"0", Lat:43.552847, Lng : 7.017369, type:"circle", image:"images/cannes.png"},
		{question:"Quelle est le chef-lieu des Charentes-Maritimes?",				choix: ["La Rochelle","Rochefort","Royan"], 	reponse : "draggable0", rep:"0", Lat:46.160329, Lng: -1.151139, type:"circle", image:"images/larochelle.png"},
		{question:"Quelle monument a-été construit pour l'exposition universelle de 1889 ?",				choix: ["L'Arc d Triomphe","La Tour Eiffel","La Tour Montparnasse"], 				reponse : "draggable1", rep:"1", Lat:48.85837, Lng : 2.294481, type:"marker", image:"images/eiffel.png"}
	];  // alternative : initialiser tabObject dans la fonction lancer().
	
}

function preparerjeu() {
	if (lvl!=0) { 
var i=lvl; lvl=0;
while (i!=lvl) { 
gain(); lvl=lvl+1;
}
 }
 lancerjeu();
}
function go() {
	p.innerHTML = invite;	 //affichage de l'invite
	}


function abandon () {	//message d'alert repNO indiquant l'abandon
	alert(repNO);
	lancerjeu();
}
function connecter() {
q=document.getElementById('login-box');
q.innerHTML=connexion;
}
function idgood(){
var adr = document.getElementById("pseudo").value; 
	var pass = document.getElementById("passwd").value; 
	if ((adr=="boolbi") && (pass="omega")) {
		connexion="";
		var z=document.getElementById('login-box').innerhtml=connexion;
		var elmt = document.getElementById("map");
		elmt.style.display = "block";
		go(); lancer();
	}
	else { 
	alert(" Pseudo ou password incorrect"); }
}

function reverseGeocoding(lat,lng, callback){
    var url = 'http://open.mapquestapi.com/nominatim/v1/reverse?format=json&lat=' + lat + '&lon=' +lng+' &zoom=18&addressdetails=1';
    var status = true
    $.ajax({
        url: url,                       
        crossDomain:true,
		error: function(xhr, status, error) {
alert("ERROR "+error);
},
        success: function(response){
                status = callback(response);
        }
    });
    return status
}

 function lancer() {
	 map = objet.map('map').setView([48.8358, 2.35657], 6);
 L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
maxZoom: 18
}).addTo(map);

map.on('click', onMapClick);

 }
 function lancerjeu(){
	
	p.innerHTML = htmlQuestion();
	
	$( "#draggable0" ).draggable();
	$( "#draggable1" ).draggable();
	$( "#draggable2" ).draggable();
	
$("#map").droppable({
drop: function( event, ui ) {
$( this )
.addClass( "ui-state-highlight" )
.find( "p" )
.html(reponse(ui.draggable.attr("id"), tabObject[lvl].reponse));
}
});

 }
 function onMapClick(e) {
	 var elmt = document.getElementById("nominatim");
	 elmt.innerHTML="<div id='contenu'> </div>";
   reverseGeocoding(e.latlng.lat, e.latlng.lng, function(response){

    $("#contenu").html(response.display_name);

});

elmt.style.zIndex = "4";
elmt.style.backgroundColor="white";
elmt.style.border="1px solid blue";

elmt.innerHTML+="<div> <p></br> lat : "+ e.latlng.lat +"</br> lng : "+ e.latlng.lng + " </p></div><a href='#' onclick='retour()';  return false' class='myButton2'>ok</a></a>";
 //  alert(e.latlng);
}
function retour() { 
var elmt = document.getElementById("nominatim");
elmt.style.zIndex = "1";
elmt.style.backgroundColor="transparent";
elmt.style.border="1px solid white";
elmt.innerHTML="";
}
function htmlQuestion()
{		//quest sour forme d'une liste à puces (sans les puces) de 3 champs input de type radio
		//htmlQuestion() appelle la fonction secondaire htmlLiInput() pour construire les <li>
	var quest  = "<h3> QUESTION :  </h3><hr>";
	quest += "<p> "+tabObject[lvl].question + "</p>";
	quest += htmlDraggable(lvl);
	return quest;
}

function htmlDraggable()
{		//i est à la fois l'indice de la boucle
		//et l'indice du choix sélectionné dans le tableau des choix de la question
	var prop="";
	var i=0;
	prop+="<div class='orgadrag'>";
	while(i<3) {
		prop+="<div id='draggable" + i + "' class='ui-widget-content'> </div>";
        prop+="<p>" + tabObject[lvl].choix[i] + "</p> ";
		i=i+1;
	}
	prop+="</div> ";
	return prop;
}


function reponse(iChoix, repGood)  {
	 
	if (iChoix != repGood)   {	//message d'alerte repKO ou repOK, suivant la réponse cliquée
		alert (repKO);}
	else {
		alert (repOK); 
		gain();
		lvl=lvl+1;
	}
	if (lvl!=7)
	lancerjeu();
	else { 
	var p1= new L.LatLng(47.12995, -15.33691);
	var p2= new L.LatLng(45.36758, -12.91992);
	var p3= new L.LatLng(48.4292, -9.18457);
	var tabVic = [p1, p2, p3];
	fin = new L.Polyline(tabVic, {
color: 'green',
weight: 12,
opacity: 0.8,
smoothFactor: 1

}); 
fin.addTo(map); map.setView([47.27923, -4.1748], 5);
//map.panTo(new L.LatLng(47.10004, 2.41699));
	p.innerHTML="<p>F\351licitation ! Vous avez fort bien aid\351 Eddy, il peut à présent commencer son parcours en toute s\351r\351nit\351 !</p><br/><br/>";
	}
}

function gain(){ 
	if(tabObject[lvl].type=="marker"){ 
	var marker = objet.marker([tabObject[lvl].Lat, tabObject[lvl].Lng]).addTo(map);
	map.panTo(new L.LatLng(tabObject[lvl].Lat, tabObject[lvl].Lng));
	marker.bindPopup("<b>" + tabObject[lvl].choix[tabObject[lvl].rep] + "</b><br/><br/><img src='"+ tabObject[lvl].image + "' width='250px' height='170px'  />").openPopup();

	cptCible=cptCible+1;
	} else if (tabObject[lvl].type=="circle") {
   var circle =objet.circle([tabObject[lvl].Lat, tabObject[lvl].Lng], 15000, {
    color: 'red',
    fillColor: '#f03',
    fillOpacity: 0.5
}).addTo(map);
map.panTo(new L.LatLng(tabObject[lvl].Lat, tabObject[lvl].Lng));
circle.bindPopup( "<b>" +tabObject[lvl].choix[tabObject[lvl].rep] + "</b>"+  "</b><br/><br/><img src='"+ tabObject[lvl].image + "' width='250px' height='170px'  />").openPopup();
	}
	point = new L.LatLng(tabObject[lvl].Lat, tabObject[lvl].Lng);
pointList[lvl] = point; 
	if(lvl!=0) { 
if(lvl==1) {
polyline = new L.Polyline(pointList, {
color: 'blue',
weight: 5,
opacity: 0.5,
smoothFactor: 1

}); 
polyline.addTo(map); } else { map.removeLayer(polyline);
polyline = new L.Polyline(pointList, {
color: 'blue',
weight: 5,
opacity: 0.5,
smoothFactor: 1

}); 
polyline.addTo(map); }
		} 
}