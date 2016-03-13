	<nav class="navbar navbar-static-top navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="home">
					<img src="assets/img/global/logo.png" height="30" />
				</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="home">Accueil</a></li>
				<li><a href="voyageur">Espace client</a></li>
				<% if(request.getSession().getAttribute("user") != null) { %>
					<li><a href="voyageur?action=deconnexion">Se déconnecter</a></li>
				<% } %>
			</ul>
		</div>
	</nav>