<%@page import="model.Vol"%>
<%@page import="java.util.ArrayList"%>
<%
	ArrayList<Vol> vols = (ArrayList<Vol>) request.getAttribute("vols");
%>
<!doctype html>
<html lang="fr">
<head>
	<meta charset="utf-8">
	<title>Les vols</title>
	<link rel="icon" href="favicon.ico" />
	<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
	<link rel="stylesheet" href="assets/css/bootflat.min.css" />
</head>
<body>
	<jsp:include page="../includes/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h3>Voici les vols</h3>
				<% if(vols.isEmpty()){ %>
					<p>Aucun vol ne correspond à vos critères...</p>
				<% } else { %>
				<form class="form-horizontal" action="reservation" method="GET">
					<input type="hidden" name="action" value="commande" />
					<% for(Vol v : vols) { 
						if (v.getCapacite()==0){ %>
							<input type="radio" name="choix" disabled="disabled">
							<label for="">
								Il n'y a plus de place restante pour un Paris 
								- <%= v.getDestination() %> le <%= v.getDateDep() %>
							</label>
							<% }
								else {%>
							<input type="radio" name="choix" value="<%= v.getNumeroVol() %>" id="v-<%= v.getNumeroVol() %>">
							<label for="v-<%= v.getNumeroVol() %>">
								<%= v.getCapacite() %> places restantes pour un Paris - <%= v.getDestination() %>
								le <%= v.getDateDep() %>
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