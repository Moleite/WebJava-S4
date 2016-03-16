/*contrairement � la version du jeu simple, avec une seule question
un tableau de questions est d�fini au format JSON.
- la fonction question() est modifi�e pour tirer au sort une des questions du tableau
- la fonction reponse() est modifi�e avec d�sormais un param�tre suppl�mentaire, la r�ponse attendue
- 2 fonctions suppl�mentaires permettent de produire le code HTML � partir d'informations issu du tableau
- la fonction() init est �limin� cat on peut mettre son contenu dans onload()
*/

var timer;  //variable r�f�ren�ant un objet temporisateur
var  temps_imparti =  10000;  //temps imparti pour donner la r�ponse (10s)
var q; //r�f�rence � un bloc div pour l'affichage (<di "id=QUEST"></div>)


var repOK = "Bonne r\351ponse !";
var repKO = "'D\351sol\351\n Mauvaise R\351ponse'";
var repNO = "d\351sol\351";

var invite = "Vous avez une minute pour r\351pondre";
	invite += " apr\350s avoir d\351marr\351 le test <br/>";
	invite += "<a href='' onclick='question(temps_imparti); return false'>d\351marrer</a>";

//tableau d�clar� sous forme d'une expression json, repr�sentant un ensemble de questions
//avec pour chaque question, 3 attributs : question (intitu� de la question), diff�rents choix, num�ro de la r�ponse


var tabObject; //tableau des questions

tabObject = [
		{question:"quel est l\'intrus ?",	choix: ["nez","yeux","oreilles"], 	reponse : 0}, 
		{question:"Quel est le fruit ?",	choix: ["patate","figue","carotte"],reponse : 1},
		{question:"Quel musicien ne jouait pas de trompette ?",	choix: ["Miles Davis","Dave Brubeck","Boris Vian"], reponse : 1}
	];  // alternative : initialiser tabObject dans la fonction lancer().


//****************************************************************//
//function init(){
window.onload = function () {
	q =  document.getElementById('QUEST'); //initialisation de q
	prepare_div_event();
	lancer(); //lance le jeu pour la premi�re fois
}

function lancer() {
	q.innerHTML = invite;	//affichage de l'invite
	}


function abandon () {	//message d'alert repNO indiquant l'abandon
	alert(repNO);
	lancer();
}

//****************************************************************//
//fonctions modifiées
var numQuestion; //variable globale, indice de la question

function question (temps_imparti)  {
	numQuestion=Math.floor(Math.random()*tabObject.length);
	q.innerHTML = htmlQuestion(); //affichage de la question dans le bloc Quest
	prepare_div_event();
	timer= setTimeout("abandon()", temps_imparti); //d�finit "timer" pour lancer abandon()
}

//3 fonctions suppl�mentaires
function htmlDivDraggable(iChoix,text){
//construction d'un div pr�sentant un choix
	var choix ="<div class='draggable ui-widget-content' id='";
	choix += iChoix;
	choix += "' ><p>";	
	choix += text;
	choix += "</p></div>";
	return choix;
}

//construire la question 
function htmlQuestion(){
	//tabObject[0]["choix"][1]; identique à tabObject[0].choix[1]; 
	var quest = "<h3 align='center'> QUESTION :  </h3><hr>";
	
	//pr�sentation de l'�nonc� avec ses choix, sous forme de div dites draggable
	quest += tabObject[numQuestion]["question"];
	for(i=0;i<tabObject[numQuestion]["choix"].length;i++){
		quest += htmlDivDraggable(i,tabObject[numQuestion]["choix"][i]); 
	}
	//quest += "<div style='clear:both'>";
	//cr�ation de la div devant recevoir la r�ponse, dites droppable
	quest += "<div id='droppable'"; 
	quest += " style='width: 13em; height: 10em; padding: 0.5em; margin: 10px; float:right'"; 
	quest += " class='ui-widget-header'><p>Faites glisser ici votre réponse</p></div>";

return quest;
}

function reponse(rep, repGood, out)  {
//rep et repGood sont 2 indices, en rapport au tableau des choix de la question
//rep objet $ repr�sentant le paragraphe de la div panier
	//analyse de la r�ponse cliqu�e et suivant le cas
	// A FAIRE
}

function prepare_div_event() { //fonction lanc�e apr�s le chargt des objets-balises en m�moire
	// A FAIRE

};

