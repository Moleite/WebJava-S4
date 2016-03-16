//alert ("Bonjour la France!");  

window.onload =function() {
		var vues = document.getElementById ("vue").childNodes;
		alert ("bonjour \n" + vues[3].tagName);
		for (var unevue in vues) {
			if ((typeof(vues[unevue].tagName)!="undefined")) {
				alert ("onglet cliqué : " + vues[unevue].tagName);
			}
		}

}