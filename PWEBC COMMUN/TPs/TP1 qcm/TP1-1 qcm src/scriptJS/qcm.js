var q; //référence à  un bloc div pour l'affichage (<di "id=QUEST"></div>)
var timer;  //variable référençant un objet temporisateur
var  temps_imparti =  10000;  //temps imparti pour donner la réponse (10s)

//var eaigu = '\351';
//var egrave = '\350';
var quest  =   "<h3 align='center'> QUESTION :  </h3><hr>";
	quest  +=   "Quel nom faut il \351carter ? ";
	quest  +=	"<ul style='list-style-type:none'>";
	quest  +=   "<li><input  type='radio' name='rd' value='r1' onclick='reponse(this.value)' /> Oeil</li>";
	quest  +=   "<li><input  type='radio' name='rd' value='r4' onclick='reponse(this.value)' /> Nez</li>";
	quest  +=   "<li><input  type='radio' name='rd' value='r6' onclick='reponse(this.value)' /> Sourcil</li>";
	quest  +=     "</ul>";

var rep4 = "r4";
var repOK = "Bonne r\351ponse !";
var repKO = "'D\351sol\351, la r\351ponse est NEZ. \n Les autres vont par deux'";
var repNO = "d\351sol\351";

var invite = "Vous avez une minute pour r\351pondre";
	invite += "apr\350s avoir d\351marrer le test <br/>";
	invite += "<a href='' onclick='question(temps_imparti); return false;'>d\351marrer</a>";

function init(){
	//initialisation de q
	
	//lance le jeu pour la première fois
	
}

function lancer() {
	//affichage de l'invite
	
	}

function question (temps_imparti)  {
	//affichage de la question dans le bloc QUEST
	
	
	timer= setTimeout("abandon()", temps_imparti);  //définit "timer" pour lancer abandon() après le temps imparti	
}

function reponse(rep)  {
	alert("rep : "+ rep);
	clearTimeout(timer); 	//stoppe le timer
	//analyse de la réponse cliquée et suivant le cas
	//message d'alert repKO ou repOK.
	//Relancer le jeu
}

function abandon () {	
	//message d'alert repNO indiquant l'abandon
	//Relancer le jeu
}

