<%@page import="model.Depart"%>
<%@page import="java.util.ArrayList"%>
<%
	ArrayList<Depart> departs = (ArrayList<Depart>) request.getAttribute("departs");
%>
<!doctype html>
<html lang="fr">
<head>
	<meta charset="utf-8">
	<title>Les départs</title>
	<link rel="icon" href="favicon.ico" />
	<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
	<link rel="stylesheet" href="assets/css/bootflat.min.css" />
</head>
<body>
	<jsp:include page="../includes/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h3>Voici les départs</h3>
				<% if(departs.isEmpty()){ %>
					<p>Aucun départ ne correspond à vos critères...</p>
				<% } else { %>
				<form class="form-horizontal" action="billet" method="GET">
					<input type="hidden" name="action" value="commande" />
					<% for(Depart d : departs) { 
						if (d.getCapacite()==0){ %>
							<input type="radio" name="choix" disabled="disabled">
							<label for="">
								Il n'y a plus de place restante pour un
								<%= d.getLigne().getVilleDepart() %> - <%= d.getLigne().getVilleDestination() %> le <%= d.getDateDep() %>
							</label>
							<% }
								else {%>
							<input type="radio" name="choix" value="<%= d.getNumeroDepart() %>" id="d-<%= d.getNumeroDepart() %>">
							<label for="d-<%= d.getNumeroDepart() %>">
								<%= d.getCapacite() %> places restantes pour un <%= d.getLigne().getVilleDepart() %> - <%= d.getLigne().getVilleDestination() %>
								le <%= d.getDateDep() %>
							</label>
						<% } %><br />
					<% } %>
						</label><br />
						<p><input type="submit" value="Valider le voyage" class="btn btn-primary" /></p>
				<% } %>
			</div>
		</div>
	</div>
	<jsp:include page="../includes/footer.jsp" />
</body>
</html>