<!doctype html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>connexion en jquery</title>

	<link rel="stylesheet" type="text/css"  href="V/css/connect.css " /> 

		<!-- <style>  #f1 {display:none;border:1px green solid;width:4em; height:7em;}   </style>-->
	<script type="text/javascript" src="V/js/jquery.js"></script> 
	<script type="text/javascript" src="V/js/connect.js"></script>
	
</head>
<body>

<header>

<div id="connect">
	<a id="cnx" href="" title="connexion">connexion</a> 
	<form id="f1" method="post" action="index.php?control=user&action=connect">
		<label for="login">login :</label>
		<input name="login" value="<?php echo $login ?>" /> <br/>
		<label for="pass">pass :</label>
		<input type="password" name="pass" value="<?php echo $pass ?>" /> <br/>
		<input type="submit" value="connecter" /> 
	</form>
	<h5></h5> <!-- pour affichage login si connect�-->
	</div> <!-- fin connect-->
</header> <!-- fin header -->

<div id='presentation'>
	<?php
	echo $presentation;
	?>
</div>

</body>
</html>
