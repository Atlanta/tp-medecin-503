<?php
header("Access-Control-Allow-Origin: *");

if(isset($_COOKIE["idClient"]) && $_COOKIE["idClient"] >= 0) {
	header("Location: gestion.php");
}
?>

<!DOCTYPE html>
<html lang="fr">
	<head>
	  <meta charset="utf-8">
	  <title>Connexion</title>
	  <meta name="description" content="">
	  <meta name="author" content="Julien Hubert">
	  <meta name="viewport" content="width=device-width, initial-scale=1">
	  <link rel="stylesheet" href="css/normalize.css">
	  <link rel="stylesheet" href="css/skeleton.css">
	  <link rel="icon" type="image/png" href="images/favicon.png">
	</head>
	<body>
	  <div class="container">
	    <div class="row">
	      <div class="one-half column" style="margin-top: 10%">
          <h4>Connexion</h4>
					<span id="loginFalse"></span>
	        <form>
            <label>Identifiant :</label>
            <input type="text" id="login"><br>
            <label>Mot de passe : </label>
            <input type="password" id="password"><br>
            <input type="button" id="send" value="Connexion">
          </form>
	      </div>
				<div class="one-half column" style="margin-top: 10%">
          <h4>Créer un compte</h4>
					<span id="registerStatut"></span>
	        <form>
            <label>Prénom :</label>
            <input type="text" id="registerFirstName">
            <label>Nom : </label>
						<input type="text" id="registerLastName"><br>
						<label>Téléphone : </label>
						<input type="text" id="registerPhoneNumber"><br>
						<label>Mot de passe : </label>
            <input type="password" id="registerPassword"><br>
            <input type="button" id="register" value="S'enregistrer">
          </form>
	      </div>
	    </div>
	  </div>
	</body>

	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery.cookie.js"></script>
	<script type="text/javascript" src="js/latinize.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
</html>
