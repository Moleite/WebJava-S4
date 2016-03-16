<?php

$hote='localhost';   		
$login='root';  	
$pass=''; 		
$bd='projetweb'; 
$log=0;
$pw=0;



$link = mysqli_connect($hote, $login, $pass) 
		or die ("erreur de connexion :" . mysql_error()); 
mysqli_select_db($link, $bd) 
		or die (htmlentities("erreur d'accès à la base :") . $bd);

$reponse = $link->query('SELECT login FROM user');
$reponse2 = $link->query('SELECT password FROM user');

    while ($donnees = $reponse->fetch())
    {
            if ($donnees['login'] == $pseudo){
                return $log;
            }
            else {
            	$log=1;
            	return $log;
            }


    }
    while ($donnees = $reponse2->fetch()){

     		if ($donnees['password'] == $password){
     			return $pw;
            }
            else {
            	$pw=1;
            	return $log;
            }
    }
?>