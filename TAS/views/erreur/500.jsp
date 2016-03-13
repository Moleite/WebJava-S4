<%
	String erreur = (String) request.getAttribute("erreur");
%>
<!doctype html>
<html lang="fr">
<head>
	<meta charset="utf-8">
	<title>Erreur 500</title>
	<link rel="icon" href="favicon.ico" />
	<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
	<link rel="stylesheet" href="assets/css/bootflat.min.css" />
</head>
<body>
	<jsp:include page="../includes/header.jsp" />
	<div class="container">
		<div class="row">	
			<div class="col-lg-12">
				<h3>Erreur 500</h3>
				<p>Le serveur a rencontré une erreur !</p>
				<% if(erreur != null) { %>
				<p>Le serveur indique : <%= erreur %></p>
				<% } %>
				<p>&nbsp;</p>
				<p>&nbsp;</p>
				<p>&nbsp;</p>
			</div>
		</div>
	</div>
	<jsp:include page="../includes/footer.jsp" />
</body>
</html>