var f1;    /*�quivalent : f1=document.getElementById('f1'); �quivalent fait au chargt de la page $(function)*/

//�quivalent : window.onlaod=functio(){}
$(
	function() {
		f1 = $('#f1'); // f1=document.getElementById('f1');
		f1.submit(onSubmit); // pour impl�menter la soumission via une requ�te ajax*/
		$('#cnx').click(onClick); // $('#cnx').one('click', onClick) // pour une nique fois, cnx.off() pour
		
	}
);
	



	
function onClick() {
	if (f1.css('display')=='block') {
		f1.css('display','none');
	} else {
		f1.css('display','block');
	}
	return false;
}



function onSubmit() {	
	$.ajax({
		url : "index.php?control=user&action=ajax_connect",
		type : f1.attr('method'), // method="POST"
		data : f1.serialize(), // param�trage de la requ�te : s�rialisation des valeurs saisies dans le formulaire
		dataType : 'html', // type de retour html ou simplement texte
		success : marquerOK, // r�f�rence � la fonction marquerOK
		error : marquerErr 
	});
	return false; // pour �viter la soumission automatique faite par le navigateur puisque fait en ajax
}

function marquerOK(data) {
	var login = f1.find('input[name=login]').attr('value'); // valeur saisie du input name=login
	var h5 = $('#connect').find('h5').empty(); //r�f�rencement et nettoyage de la balise h(
	onClick(); // cacher le formulaire
	if (data.indexOf('OK',0)!=1) {
		h5.append (login);
	} else {
		h5.append ('erreur : recommencez svp.');
	}
	//$('#presentation').empty().html(data);
}

function marquerErr() {
	$('#connect').find('h5').empty().append("erreur : " . err);
}