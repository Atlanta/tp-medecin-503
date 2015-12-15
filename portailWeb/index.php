<<<<<<< HEAD
<?php

    session_start();

    if(empty($_SESSION["login"]))
    {
        header("Location: login.php"); 
    }



?>


<!DOCTYPE html>
<html lang="en">
<head>

  <!-- Basic Page Needs
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <meta charset="utf-8">
  <title>Portail Web - index</title>
  <meta name="description" content="">
  <meta name="author" content="">

  <!-- Mobile Specific Metas
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- FONT
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <link href="//fonts.googleapis.com/css?family=Raleway:400,300,600" rel="stylesheet" type="text/css">

  <!-- CSS
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <link rel="stylesheet" href="css/normalize.css">
  <link rel="stylesheet" href="css/skeleton.css">

  <!-- Favicon
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <link rel="icon" type="image/png" href="images/favicon.png">

</head>
<body>

  <!-- Primary Page Layout
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
    <div class="container">
        <div class="row">
            <div class="one-half column" style="margin-top: 25%">
                <h4>Bienvenue</h4>
        
            </div>

            <div class="ten columns">
                <p>Bienvenue sur le portail web</p>
            </div>
        </div>
    </div>

<!-- End Document
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
</body>
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
	        <h4>Gestion des rendez-vous médicaux</h4>
	        <p></p>
	      </div>
	    </div>
	  </div>
	</body>
	
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/datatables.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			console.log("Hello");
		});
	</script>
	    
>>>>>>> refs/remotes/Atlanta/master
</html>
