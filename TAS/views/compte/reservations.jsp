<%@page import="model.Reservation"%>
<%@page import="java.util.ArrayList"%>
<%
	ArrayList<Reservation> reservation = (ArrayList<Reservation>) request.getAttribute("reservations");
%>
<!doctype html>
<html lang="fr">
<head>
	<meta charset="utf-8">
	<title>Vos Reservation</title>
	<link rel="icon" href="favicon.ico" />
	<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
	<link rel="stylesheet" href="assets/css/bootflat.min.css" />
</head>
<body>
	<jsp:include page="../includes/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h3>Voici vos Reservation</h3>
				<% if(reservation.isEmpty()) { %>
					<p>Vous n'avez pas encore effectué de réservation...</p>
				<% } else { %>
					<table class="table">
						<tr>
							<th>Numéro de la réservation</th>
							<th>Titulaire de la réservation</th>
							<th>Numéro de vol</th>
							<th>Nombre de place(s) réservée(s)</th>
							<th>Ville d'arrivée</th>
							<th>Action</th>
						</tr>
						<% for(Reservation r : reservation) { %>
							<tr>
								<td><%= r.getNumeroReservation() %></td>
								<td><%= r.getCompte().getNom() %></td>
								<td><%= r.getVol().getNumeroVol() %></td>
								<td><%= r.getVol().getCapacite() %></td>
								<td><%= r.getVol().getDestination() %></td>
								<td><a href="reservation?action=annuler&id=<%= r.getNumeroReservation() %>" class="btn btn-sm btn-danger">Annuler le billet</a></td>
							</tr>
						<% } %>
					</table>
				<% } %>
			</div>
		</div>
	</div>
	<jsp:include page="../includes/footer.jsp" />
</body>
</html>