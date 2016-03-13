<%@page import="model.Billet"%>
<%@page import="java.util.ArrayList"%>
<%
	ArrayList<Billet> billets = (ArrayList<Billet>) request.getAttribute("billets");
%>
<!doctype html>
<html lang="fr">
<head>
	<meta charset="utf-8">
	<title>Vos billets</title>
	<link rel="icon" href="favicon.ico" />
	<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
	<link rel="stylesheet" href="assets/css/bootflat.min.css" />
</head>
<body>
	<jsp:include page="../includes/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h3>Voici vos billets</h3>
				<% if(billets.isEmpty()) { %>
					<p>Vous n'avez pas encore commandé de billet...</p>
				<% } else { %>
					<table class="table">
						<tr>
							<th>Numéro du billet</th>
							<th>Numéro du départ</th>
							<th>Date du voyage</th>
							<th>Ville de départ</th>
							<th>Ville d'arrivée</th>
							<th>Action</th>
						</tr>
						<% for(Billet b : billets) { %>
							<tr>
								<td><%= b.getNumeroBillet() %></td>
								<td><%= b.getDepart().getNumeroDepart() %></td>
								<td><%= b.getDepart().getDateDep() %></td>
								<td><%= b.getDepart().getLigne().getVilleDepart() %></td>
								<td><%= b.getDepart().getLigne().getVilleDestination() %></td>
								<td><a href="billet?action=annuler&id=<%= b.getNumeroBillet() %>" class="btn btn-sm btn-danger">Annuler le billet</a></td>
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