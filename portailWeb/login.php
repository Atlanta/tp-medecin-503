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
