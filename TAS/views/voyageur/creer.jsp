<%
	Integer commande = (Integer) session.getAttribute("commande");
	Boolean erreur = (Boolean) request.getAttribute("erreur");
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
				<h3>Inscription sur Voyages-SNGF</h3>
				<% if(erreur != null && erreur) { %>
					<div class="alert alert-danger">
						<p>Ce compte existe déjà !</p>
					</div>
				<% } %>
				<% if(commande != null) { %>
					<div class="alert alert-warning">
						<p>Votre commande sera validée une fois connecté. Vous possédez déjà un compte ? <a href="login">Connectez-vous ici.</a></p>
					</div>
				<% } %>
				<form method="POST" action="voyageur?action=creer" class="form-horizontal">
					<label for="LoginVoyageur">Login</label>
					<input type="text" id="LoginVoyageur" name="LoginVoyageur" placeholder="Login" class="form-control" required="required"/><br />
					
					<label for="PassVoyageur">Mot de passe</label>
					<input type="password" id="PassVoyageur" name="PassVoyageur" placeholder="Mot de passe" class="form-control" required="required"/><br />
					
					<label for="NomVoyageur">Nom</label>
					<input type="text" id="NomVoyageur" name="NomVoyageur" placeholder="Nom" class="form-control" required="required"/><br />
					
					<label for="AdresseVoyageur">Adresse</label>
					<input type="text" id="AdresseVoyageur" name="AdresseVoyageur" placeholder="Adresse" class="form-control" required="required"/><br />
					
					<input type="submit" value="Valider" class="btn btn-primary" />
				</form>
			</div>
		</div>
	</body>
</html>