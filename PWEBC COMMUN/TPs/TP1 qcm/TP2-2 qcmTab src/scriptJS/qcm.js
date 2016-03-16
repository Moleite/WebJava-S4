/*contrairement à la version du jeu simple, avec une seule question
un tableau de questions est défini au format JSON.
- la fonction question() est modifiée pour tirer au sort une des questions du tableau
- la fonction reponse() est modifiée avec désormais un paramètre supplémentaire, la réponse attendue
- 2 fonctions supplémentaires permettent de produire le code HTML à partir d'informations issu du tableau
- la fonction() init est éliminé cat on peut mettre son contenu dans onload()
*/

var timer;  //variable référençant un objet temporisateur
var  temps_imparti =  10000;  //temps imparti pour donner la réponse (10s)
var q; //référence au bloc div d'affichage (<div "id=QUEST"></div>)


var repOK = "Bonne r\351ponse !";
var repKO = "'D\351sol\351\n Mauvaise Réponse'";
var repNO = "d\351sol\351";

var invite = "Vous avez une minute pour r\351pondre";
	invite += " apr\350s avoir d\351marr\351 le test <br/>";
	invite += "<a href='' onclick='question(temps_imparti); return false'>d\351marrer</a>";

//tableau au format JSON représentant un ensemble de questions
//avec pour chaque question, 3 attributs : question (intitué de la question), différents choix, numéro de la réponse
/*
var myJSONtext; // A REMPLIR avec la description de toutes les questions

var tabObject = eval('('+myJSONtext+')');  //Exploiter myJSONtext pour faire le tableau javascript
*/
//window.onload=alert(tabObject[1].choix[tabObject[1].reponse]);



//****************************************************************//

//A FAIRE : à la place de la déclaration de la fonction init() et de <body onload="init()">, 
//  récriture  de la fonction onload() de window
//	pour initialisation de q comme référence de la div QUEST
//	et invoquer lancer() pour lancer le jeu 


//****************************************************************//
function lancer() {
	q.innerHTML = invite;	//affichage de l'invite
	}


function abandon () {	//message d'alerte repNO indiquant l'abandon
	alert(repNO);
	lancer();
}

function question (temps_imparti)  {
	var questionNum=Math.floor(Math.random()*tabObject.length);
	q.innerHTML = htmlQuestion(questionNum);//affichage de la question dans le bloc QUEST
	timer= setTimeout("abandon()", temps_imparti);  //définit "timer" pour lancer abandon() après le temps imparti	
}

function reponse(iChoix, repGood)  {
	clearTimeout(timer); 	//stoppe le timer
	// A FAIRE : Test de l'indice de la réponse cliquée (iChoix) avec celui de la bonne réponse (repGood)
	// A FAIRE : Affichage d'un message d'alerte repKO ou repOK, suivant la réponse cliquée
	lancer();
}

function htmlQuestion(numQ) {
	var quest;
	//	A FAIRE : initialiser quest avec une chaine de caractères html, 
	//	   correspondant aux différents choix de la question d'indice numQ.
	// 	   Afficher par une boucle indicée, les différents choix possibles 
	//	   sous forme d'un liste à puces <ul>.
	//		IMPORTANT  : L'indice de boucle servira à repérer ce choix.
	// 	   Optionnellement, s'aider de la fonction htmlLiInput() - à écrire -, 
	//		pouvant servir à l'affichage d'un des éléments de liste, suivant 3 paramètres  : 
	//		  - l'indice de la question (numQ), 
	//		  - l'indice d'une des réponses à la question (iChoix) et 
	//		  - le texte de cette réponse (Choix).
	return quest;
}

function htmlLiInput(numQ,iChoix,Choix) {
	var prop; 
	// A FAIRE : initialiser prop avec une chaine de caractères html, 
		// correspondant à un des choix affichés sous forme d'un élément de liste <li>
		// 3 paramètres  : l'indice de la question (numQ), l'indice d'une réponse (iChoix) et le texte de cette réponse (Choix).	
		// L'élément de liste à afficher contient un champ de type bouton radio, 
		// Son attribut value sera affecté à l'indice de la réponse traitée (iChoix). 
		// L'affichage du bouton radio devra être suivi du texte de la réponse traitée (Choix). 
		// La fonction réponse() devra être lancée par un clic sur le bouton radio.
	return prop;
}




