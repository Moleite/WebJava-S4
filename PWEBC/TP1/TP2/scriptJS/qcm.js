
alert ("c'est parti \n PCWEB");

var q; //r�f�rence � un bloc div pour l'affichage (<di "id=QUEST"></div>)
var timer;  //variable r�f�ren�ant un objet temporisateur
var  temps_imparti =  2000;  //temps imparti pour donner la r�ponse (10s)

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
	invite += "<a href='' onclick='question(temps_imparti); return false'>d\351marrer</a>";
	
 var tabObject =[
					{question:"Quel est l'intrus ?",choix:{"nez","yeux","oreilles"},reponse:0},
					{question:"Quel est le fruit ?",choix:{"patate, figue, carotte"},reponse:1},
					{question:"Quel musicien ne jouait pas de trompette ?",choix:{"Miles Davis","Dave Brubeck","Boris Vian"},reponse:1},
				];

function init(){
	//initialisation de q
	//lance le jeu pour la premi�re fois
	console.log('init');
	q = document.getElementById('QUEST');
	lancer();
	}

function lancer() {
	//affichage de l'invite
	q.innerHTML = invite;
	}

function question (temps_imparti)  {
	var questionNum=Math.floor(Math.random()*tabObject.length);
	q.innerHTML = htmlQuestion(questionNum);//affichage de la question dans le bloc QUEST
	timer= setTimeout("abandon()", temps_imparti);  //d�finit "timer" pour lancer abandon() apr�s le temps imparti	
}

function reponse(iChoix, repGood)  {
	clearTimeout(timer); 	//stoppe le timer
	// A FAIRE : Test de l'indice de la r�ponse cliqu�e (iChoix) avec celui de la bonne r�ponse (repGood)
	// A FAIRE : Affichage d'un message d'alerte repKO ou repOK, suivant la r�ponse cliqu�e
	if (iChoix != repGood){
		alert (repKO);
	} else {
		alert (OK);
	}
	
	lancer();
}

function abandon () {	//message d'alerte repNO indiquant l'abandon
	alert(repNO);
	lancer();
}

function htmlQuestion(numQ) {
	var quest;	
	quest = tabObject(numQ),question;
	
	quest += "<ul>";
	for (var i in tabObject) {
		quest += tableObject(numQ).choix[i];
	}
	quest += "</ul>";
	
	return quest;
}

function htmlLiInput(numQ,iChoix,Choix) {
	var prop; 
	// A FAIRE : initialiser prop avec une chaine de caract�res html, 
		// correspondant � un des choix affich�s sous forme d'un �l�ment de liste <li>
		// 3 param�tres  : l'indice de la question (numQ), l'indice d'une r�ponse (iChoix) et le texte de cette r�ponse (Choix).	
		// L'�l�ment de liste � afficher contient un champ de type bouton radio, 
		// Son attribut value sera affect� � l'indice de la r�ponse trait�e (iChoix). 
		// L'affichage du bouton radio devra �tre suivi du texte de la r�ponse trait�e (Choix). 
		// La fonction r�ponse() devra �tre lanc�e par un clic sur le bouton radio.
	return prop;
}

/*
function abandonAncien () {
	alert ("Faut que tu stop Plus500 Sobina");
	//message d'alert repNO indiquant l'abandon
	//Relancer le jeu
}

function questionAncienne (temps_imparti)  {
	//affichage de la question dans le bloc QUEST
	q.innerHTML = quest;
	timer= setTimeout("abandon()", temps_imparti);  //d�finit "timer" pour lancer abandon() apr�s le temps imparti	
}

function reponseAncienne(rep)  {
	clearTimeout(timer); 	//stoppe le timer
	//analyse de la r�ponse cliqu�e et suivant le cas
	if (rep == rep4) {
		alert(repOK);
	} else {
		alert(repKO);
	}
	lancer();
	//message d'alert repKO ou repOK.
	//Relancer le jeu
}
*/
