<%@page import="model.Compte"%>
<%
	Compte user = (Compte) request.getSession().getAttribute("user");
%>
<!doctype html>
<html lang="fr">
<head>
	<meta charset="utf-8">
	<title>OKLM AIR</title>
	<link rel="icon" href="favicon.ico" />
	<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
	<link rel="stylesheet" href="assets/css/bootflat.min.css" />
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.3/themes/smoothness/jquery-ui.css">
	<script>
		$(function() {
			$( "#DateDebut" ).datepicker({ dateFormat: 'dd/mm/yy' });
			$( "#DateFin" ).datepicker({ dateFormat: 'dd/mm/yy' });
		});
	</script>
</head>
<body>
	<jsp:include page="../includes/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h2>Bienvenue sur OKLM Air</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="jumbotron">
					<div class="jumbotron-photo">
						<img src="assets/img/home/caraïbes.jpg">
					</div>
					<div class="jumbotron-contents">
						<h1>Espace Clients</h1>
						<% if(user != null) { %>
							<p>Bonjour <%= user.getNom() %> !</p>
							<ul>
								<li><a href="compte">Votre espace client</a></li>
								<li><a href="compte?action=reservations">Vos réservations</a></li>
							</ul>
							<p><a href="compte?action=deconnexion">Se déconnecter</a></p>
						<% } else { %>
						<h2>Inscription</h2>
						<p>Pour vous inscrire sur OKLM Air, <a href="compte?action=creer">cliquez ici</a>.</p>
						<h2>Connexion</h2>
						<form class="form-horizontal" action="login" method="POST">
							<label for="login">Login</label>
							<input id="login" class="form-control" type="text" placeholder="Login" name="login" required="required"/>
							<br />
							<label for="mdp">Mot de passe</label>
							<input id="mdp" class="form-control" type="password" placeholder="Mot de passe" name="mdp" required="required"/>
							<br />
							<input type="submit" value="Se connecter" class="btn btn-primary" />
						</form>
						<% } %>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="jumbotron">
					<div class="jumbotron-photo">
						<img src="assets/img/home/avion.jpg">
					</div>
					<div class="jumbotron-contents" id="nouveauVoyage">
						<h1>Faire un nouveau voyage</h1>
						<form class="form-horizontal" action="reservation?action=lister" method="POST">
							<label for="">Ville de départ</label>
							<input id="" class="form-control" type="text" value="Paris" name="VilleDepart" disabled="disabled"/>
							<br />
							<label for="">Ville de destination</label>
							<input id="" class="form-control" type="text" placeholder="Londres" name="VilleDestination" required="required"/>
							<br />
							<label for="">Date du voyage</label>
							<div class="datepicker ll-skin-nigran hasDatepicker">
							<input class="form-control" type="text" placeholder="14/03/2016" name="DateDebut" id="DateDebut" required="required"/>
							</div>
							<br />
							<label for="">Nombre de places</label>
							<input class="form-control" type="text" placeholder="1" name="NbPlaces" id="NbPlaces" required="required"/>
							<br />
							<input type="submit" value="Réserver" class="btn btn-primary" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../includes/footer.jsp" />
</body>
</html>