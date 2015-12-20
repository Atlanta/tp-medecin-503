<?php
header("Access-Control-Allow-Origin: *");

if(!isset($_COOKIE["idClient"]) || $_COOKIE["idClient"] < 0) {
	header("Location: index.php");
}

$mois = array("Janvier", "Février", "Mars","Avril", "Mai", "Juin","Juillet", "Août", "Septembre","Octobre","Novembre","Décembre");
?>

<!DOCTYPE html>
<html lang="fr">
	<head>
	  <meta charset="utf-8">
	  <title>Gestion de rendez-vous</title>
	  <meta name="description" content="">
	  <meta name="author" content="Julien Hubert">
	  <meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	  <link rel="stylesheet" href="css/normalize.css">
	  <link rel="stylesheet" href="css/skeleton.css">
	  <link rel="icon" type="image/png" href="images/favicon.png">
	</head>
	<body>
	  <div class="container">
	    <div class="row">
	      <div class="one-half column" style="margin-top: 10%">
					<h1></h1>
          <h4>Rendez-vous fixés :</h4>
					<div id="rdv"></div>
	      </div>
				<div class="one-half column" style="margin-top: 10%">
          <h4>Prendre un rendez-vous :</h4>
					<form>
						<label>Médecin :</label>
						<select id="medecin" name="medecin"></select><br>
						<label>Date :</label>
						<input type="text" id="datepicker" />
						<br>
						<label>Heure :</label>
						<select id="heure" name="heure">
						  <?php
						  for($i=8;$i<18;$i++)
						  {
						    if($i<10) echo "<option>0".$i."h</option>";
						    else echo "<option>".$i."h</option>";
						  }
						  ?>
						</select>
						<select id="minutes" name="minutes">
						  <?php
						  for($i=0;$i<60;$i+=15)
						  {
						    if($i<10) echo "<option>0".$i."</option>";
						    else echo "<option>".$i."</option>";
						  }
						  ?>
						</select>
						<br>
						<input type="button" id="add" value="Ajouter un rendez-vous" />
						<br><br>
						<input type="button" id="disconnect" value="Se déconnecter" />
					</form>
	      </div>
	    </div>
	  </div>
	</body>

	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery.cookie.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script type="text/javascript" src="js/gestion.js"></script>
</html>
