<!DOCTYPE HTML>


<?php
	session_start();
	require_once 'includes/fonctions.php';
	if(isset($_POST["login"]) && isset($_POST["mdp"]) && isset($_POST["type"])){
		extract($_POST);
		
		$idUser = login($login, $mdp, $type);


		if($idUser != -1)
		{
			$_SESSION["id"] = $idUser;
			$_SESSION["login"] = $login;
			$_SESSION["droits"] = $type;

			header("Location: index.php"); 
		}
	}
?>


<html>
<head>
<title>Portail web</title>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

</script>
</head>
<body>
<!-- contact-form -->	
<div class="message warning">
<div class="inset">

		<form action"login.php" method="POST">
			<li>
				<input type="text" name="login" placeholder="login">
			</li>
				<div class="clear"> </div>
			<li>
				<input type="password" name="mdp" placeholder="mot de passe">
			</li>
			<li>
				<input type="radio" name="type" value="patients">Patient
				<input type="radio" name="type" value="medecins">Medecin
			</li>

			<div class="submit">
				<input type="submit" value="Connexion" >

				<div class="clear">  </div>	
			</div>
				
		</form>
		</div>					
	</div>
	</div>
	<div class="clear"> </div>
</div>
</body>
</html>

