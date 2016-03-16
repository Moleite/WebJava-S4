

<?php
$T = array();
$q['question'] = "quel est l\'intrus ?";
$q['choix'] = array('nez', 'yeux', 'oneilles' );
$q['reponse'] = 0;
$T[]=$q;

$q['question'] = "Quel est le fruit ?";
$q['choix'] = array('patate', 'figue', 'carotte');
$q['reponse'] = 1;
$T[]=$q;

$q['question'] = "Quel musicien ne jouait pas de trompette ?";
$q['choix'] = array( 'Miles Davis', 'Dave Brubeck', 'Boris Vian');
$q['reponse'] = 1;
$T[]=$q;

  //--------------------------------------------------------------------------
  // 3) echo result $T au format json 
  //--------------------------------------------------------------------------

$E = json_encode($T);
echo $E;

?>





<?php
/*
  $host = "---";
  $user = "---";
  $pass = "---";

  $databaseName = "---";
  $tableName = "variables";

  $con = mysql_connect($host,$user,$pass);
  $dbs = mysql_select_db($databaseName, $con);
  $result = mysql_query("SELECT * FROM $tableName");          //query
  $T = mysql_fetch_row($result);                          //un enregistrement du résultat    
*/
?>

