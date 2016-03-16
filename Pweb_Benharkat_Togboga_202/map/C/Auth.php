<?php
function connect () {

	$pseudo = isset($_POST['login']);
	$password = isset($_POST['password']);

	require('M/Auth_BD.php');
	require ('V/identif.tpl');
}
?>