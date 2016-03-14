<%@page import="model.Compte"%>
<%
	Compte user = (Compte) request.getSession().getAttribute("user");
%>
<!doctype html>
<html lang="fr">
<head>
	<meta charset="utf-8">
	<title>Mon compte</title>
	<link rel="icon" href="favicon.ico" />
	<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
	<link rel="stylesheet" href="assets/css/bootflat.min.css" />
</head>
<body>
	<jsp:include page="../includes/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h3>Bienvenue, <%= user.getNom() %> !</h3>
			</div>
			<div class="col-md-3">
				<div class="pricing">
					<ul>
						<li class="unit price-primary">
							<div class="price-title">
								<h3>Réservations</h3>
							</div>
							<div class="price-body">
								<p>Gérez vos réservations</p>
							</div>
							<div class="price-foot"><a href="compte?action=reservations" class="btn btn-primary">Consulter</a></div>
						</li>
					</ul>
				</div>
			</div>
			<div class="col-md-3">
				<div class="pricing">
					<ul>
						<li class="unit price-success">
							<div class="price-title"><h3>Voyages</h3></div>
							<div class="price-body">
								<p>Faites une nouvelle réservation</p>
							</div>
							<div class="price-foot"><a href="home#nouveauVoyage" class="btn btn-success">Commander</a></div>
						</li>
					</ul>
				</div>
			</div>
			<div class="col-md-6">
				<p>Vous êtes <%= user.getNom() %> aka. <%= user.getLogin() %>.
				<p>Vous avez terminé ? <a href="compte?action=deconnexion">Se déconnecter</a></p>
			</div>
			<div class="col-lg-12">
				<p>&nbsp;</p>
				<p>&nbsp;</p>
			</div>
		</div>
	</div>
	<jsp:include page="../includes/footer.jsp" />
</body>
</html>