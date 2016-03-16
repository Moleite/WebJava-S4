/*contrairement � la version du jeu simple, avec une seule question
un tableau de questions est d�fini au format JSON.
- la fonction question() est modifi�e pour tirer au sort une des questions du tableau
- la fonction reponse() est modifi�e avec d�sormais un param�tre suppl�mentaire, la r�ponse attendue
- 2 fonctions suppl�mentaires permettent de produire le code HTML � partir d'informations issu du tableau
- la fonction() init est �limin� cat on peut mettre son contenu dans onload()
*/

var timer;  //variable r�f�ren�ant un objet temporisateur
var  temps_imparti =  10000;  //temps imparti pour donner la r�ponse (10s)
var q; //r�f�rence au bloc div d'affichage (<div "id=QUEST"></div>)


var repOK = "Bonne r\351ponse !";
var repKO = "'D\351sol\351\n Mauvaise R�ponse'";
var repNO = "d\351sol\351";

var invite = "Vous avez une minute pour r\351pondre";
	invite += " apr\350s avoir d\351marr\351 le test <br/>";
	invite += "<a href='' onclick='question(temps_imparti); return false'>d\351marrer</a>";

//tableau au format JSON repr�sentant un ensemble de questions
//avec pour chaque question, 3 attributs : question (intitu� de la question), diff�rents choix, num�ro de la r�ponse
/*
var myJSONtext; // A REMPLIR avec la description de toutes les questions

var tabObject = eval('('+myJSONtext+')');  //Exploiter myJSONtext pour faire le tableau javascript
*/
//window.onload=alert(tabObject[1].choix[tabObject[1].reponse]);



//****************************************************************//

//A FAIRE : � la place de la d�claration de la fonction init() et de <body onload="init()">, 
//  r�criture  de la fonction onload() de window
//	pour initialisation de q comme r�f�rence de la div QUEST
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
	timer= setTimeout("abandon()", temps_imparti);  //d�finit "timer" pour lancer abandon() apr�s le temps imparti	
}

function reponse(iChoix, repGood)  {
	clearTimeout(timer); 	//stoppe le timer
	// A FAIRE : Test de l'indice de la r�ponse cliqu�e (iChoix) avec celui de la bonne r�ponse (repGood)
	// A FAIRE : Affichage d'un message d'alerte repKO ou repOK, suivant la r�ponse cliqu�e
	lancer();
}

function htmlQuestion(numQ) {
	var quest;
	//	A FAIRE : initialiser quest avec une chaine de caract�res html, 
	//	   correspondant aux diff�rents choix de la question d'indice numQ.
	// 	   Afficher par une boucle indic�e, les diff�rents choix possibles 
	//	   sous forme d'un liste � puces <ul>.
	//		IMPORTANT  : L'indice de boucle servira � rep�rer ce choix.
	// 	   Optionnellement, s'aider de la fonction htmlLiInput() - � �crire -, 
	//		pouvant servir � l'affichage d'un des �l�ments de liste, suivant 3 param�tres  : 
	//		  - l'indice de la question (numQ), 
	//		  - l'indice d'une des r�ponses � la question (iChoix) et 
	//		  - le texte de cette r�ponse (Choix).
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




