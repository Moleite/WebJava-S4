<%
	Integer commande = (Integer) session.getAttribute("commande");
	Boolean erreur = (Boolean) request.getAttribute("erreur");
%>
<!doctype html>
<html lang="fr">
	<head>
		<meta charset="utf-8">
		<title>Ajout de vols</title>
		<link rel="icon" href="favicon.ico" />
		<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="assets/css/bootflat.min.css" />
	</head>
<body>
	<jsp:include page="../includes/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-lg-4">
				<h3>Ajout de Vols</h3>
				<% if(erreur != null && erreur) { %>
					<div class="alert alert-danger">
						<p>Ce vol existe déjà !</p>
					</div>
				<% } %>
				<% if(commande != null) { %>
					<div class="alert alert-warning">
						<p>Votre commande sera validée une fois connecté. Vous possédez déjà un compte ? <a href="login">Connectez-vous ici.</a></p>
					</div>
				<% } %>
				<form method="POST" action="compte?action=ajouter" class="form-horizontal">
					<label for="">Destination</label>
					<input type="text" id="destination" name="destination" placeholder="Destination" class="form-control" required="required"/><br />
					
					<label for="">Date du voyage</label>
					<div class="datepicker ll-skin-nigran hasDatepicker">
					<input class="form-control" type="text" placeholder="14/03/2016" name="DateDebut" id="DateDebut" required="required"/>
					</div><br />
					
					<label for="">Nombre de places</label>
					<input class="form-control" type="text" placeholder="1" name="NbPlaces" id="NbPlaces" required="required"/><br />
					
					<label for="">Prix</label>
					<input class="form-control" type="text" placeholder="1" name="Prix" id="Prix" required="required"/><br />
									
					
					<input type="submit" value="Ajouter" class="btn btn-primary" />
				</form>
			</div>
		</div>
	</body>
</html>