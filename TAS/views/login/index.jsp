﻿<%
	Integer commande = (Integer) session.getAttribute("commande");
	String erreur = request.getParameter("erreur");
%>
<!doctype html>
<html lang="fr">
	<head>
		<meta charset="utf-8">
		<title>Inscription voyageur</title>
		<link rel="icon" href="favicon.ico" />
		<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="assets/css/bootflat.min.css" />
	</head>
<body>
	<jsp:include page="../includes/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-lg-4">
				<h3>Merci de confirmer vos identifiants</h3>
				<% if(erreur != null) { %>
					<div class="alert alert-danger">
						<p>Erreur d'identifiants !</p>
					</div>
				<% } %>
				<% if(commande != null) { %>
					<div class="alert alert-warning">
						<p>Votre commande sera validée une fois connecté. Pas encore de compte ? <a href="voyageur?action=creer">Inscrivez-vous ici.</a></p>
					</div>
				<% } %>
				<form class="form-horizontal" action="login" method="POST">
					<label for="LoginVoyageur">Login</label>
					<input id="LoginVoyageur" class="form-control" type="text" placeholder="Login" name="LoginVoyageur" required="required"/>
					<br />
					<label for="PassVoyageur">Mot de passe</label>
					<input id="PassVoyageur" class="form-control" type="password" placeholder="Mot de passe" name="PassVoyageur" required="required"/>
					<br />
					<input type="submit" value="Se connecter" class="btn btn-primary" />
				</form>
			</div>
			<div class="col-lg-4">
				<h3>Pas encore de compte ?</h3>
				<p><a href="voyageur?action=creer">Cliquez ici pour vous inscrire.</a>
			</div>
		</div>
	</body>
</html>