<<<<<<< HEAD
<!DOCTYPE HTML>


<?php
	session_start();
	require_once 'includes/gestionLogin.php';
	if(isset($_POST["login"]) && isset($_POST["mdp"]) && isset($_POST["type"])){
		extract($_POST);
		
		$connected = login($login, $mdp, $type);


		if($connected)
		{
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
				<input type="radio" name="type" value="patient">Patient
				<input type="radio" name="type" value="medecin">Medecin
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
=======
<!DOCTYPE html>
<html lang="en">
	<head>
	  <meta charset="utf-8">
	  <title>Your page title here :)</title>
	  <meta name="description" content="">
	  <meta name="author" content="">
	  <meta name="viewport" content="width=device-width, initial-scale=1">
	  <link rel="stylesheet" href="css/normalize.css">
	  <link rel="stylesheet" href="css/skeleton.css">
	  <link rel="stylesheet" href="css/datatables.min.css">
	  <link rel="icon" type="image/png" href="images/favicon.png">
	</head>
	<body>
	  <div class="container">
	    <div class="row">
	      <div class="one-half column" style="margin-top: 25%">
	        <h4>Login</h4>
	        <form action="/traitement.php:8080" method="POST">
            <label>Login :</label>
            <input type="text" name="login">
            <label>Pass : </label>
            <input type="text" name="pass"><br>
            <input type="submit" value="Envoyer">
          </form>
	      </div>
	    </div>
	  </div>
	</body>

	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/datatables.min.js"></script>
	<script type="text/javascript">
  function sayHello() {
    console.log("Hello");
  };
		$(sayHello());
	</script>

</html>
>>>>>>> refs/remotes/Atlanta/master
