<?php
    require_once "includes/fonctions.php";
    session_start();

    if(empty($_SESSION["login"]))
    {
        header("Location: login.php"); 
    }

    $jsonData = getData("medecins");

    if(isset($_POST["submit"])){
        extract($_POST);
        extract($_SESSION);
        
        reserver($id, $medecin, $date, $heure);
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
    <link rel="stylesheet" href="css/jquery-ui.css">
    

    <!-- Favicon
    –––––––––––––––––––––––––––––––––––––––––––––––––– -->
    <link rel="icon" type="image/png" href="images/favicon.png">

</head>
<body>

  <!-- Primary Page Layout
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
    <div class="container">
        <div class="row">
            <div class="one-half column">
                <h3>Bienvenue sur le portail web</h3>
        
            </div>
            
        </div>

        <div class="six columns">
            <h4>Prendre un rendez vous</h4>
        </div>

        <div class="twelve columns">


            <form id="formRdv" action="index.php" method="post">
            
            <select id="selectMedecins" name="medecin">
            <?php
                echo '<option value="0" selected>Choisir un médecin</option>';
                foreach($jsonData as $entry){
                    echo "<option value=".$entry["id"].">".$entry["nom"] . " ". $entry["prenom"] . " - " . $entry["specialisation"] ."</option>";
                }
            ?>
            </select>
            <br>
            <br>


            
                <fieldset id="fieldRdv">
                    <p>Date: <input type="text" id="datepicker" name="date"></p>

                    <p>Heure :

                    <select id="selectHeure" name="heure">
                        <option value="08:00">08:00</option>
                        <option value="08:15">08:15</option>
                        <option value="08:30">08:30</option>
                        <option value="08:45">08:45</option>
                        <option value="09:00">09:00</option>
                        <option value="09:15">09:15</option>
                        <option value="09:30">09:30</option>
                        <option value="09:45">09:45</option>
                        <option value="10:00">10:00</option>
                        <option value="10:15">10:15</option>
                        <option value="10:30">10:30</option>
                        <option value="10:45">10:45</option>
                        <option value="11:00">11:00</option>
                        <option value="11:15">11:15</option>
                        <option value="11:30">11:30</option>
                        <option value="11:45">11:45</option>
                        <option value="12:00">12:00</option>
                        <option value="12:15">12:15</option>
                        <option value="12:30">12:30</option>
                        <option value="12:45">12:45</option>
                        <option value="13:00">13:00</option>
                        <option value="13:15">13:15</option>
                        <option value="13:30">13:30</option>
                        <option value="13:45">13:45</option>
                        <option value="14:00">14:00</option>
                        <option value="14:15">14:15</option>
                        <option value="14:30">14:30</option>
                        <option value="14:45">14:45</option>
                        <option value="15:00">15:00</option>
                        <option value="15:15">15:15</option>
                        <option value="15:30">15:30</option>
                        <option value="15:45">15:45</option>
                        <option value="16:00">16:00</option>
                        <option value="16:15">16:15</option>
                        <option value="16:30">16:30</option>
                        <option value="16:45">16:45</option>
                        <option value="17:00">17:00</option>

                    </select></p>


                    <input type="submit" name="submit" value="Reserver">

                </fieldset>





            </form>
        </div> 
        
    </div>

<!-- End Document
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
</body>

<script src="js/jquery.js"></script>
<script src="js/jquery-ui.min.js"></script>

<script type="text/javascript">

    $(document).ready(function(){

        $("#fieldRdv").hide();
        $("#datepicker").datepicker({dateFormat: 'dd-mm-yy'});

        $('#selectMedecins').change(function(){



            $("#selectHeure option").each(function(){


                var isDisabled = $(this).is(':disabled');
                if (isDisabled) {
                    $(this).prop('disabled', false);
                }

                
            })
            if($("#selectMedecins option:selected").val() != "0"){
                $("#fieldRdv").show();
            }else{
                $("#fieldRdv").hide();
            }
        })

        $('#datepicker').bind("change",function(){
            console.log("datepicker : " + $("#datepicker").val() )
            if($("#datepicker").val() != "") {

                var idMedecin = $("#selectMedecins option:selected").val();
                var date = $("#datepicker").val();
                console.log("date : " + date);
                var requete = $.post('ajax/getLesRdvJour.php', {idMedecin:idMedecin, date:date}, 'json');
                requete.done(function(data){
                    $.each(data, function(index, value){

                        console.log(data[0].heure);
                        $("#selectHeure option").each(function(){
                            if($(this).text() == data[index].heure){
                                $(this).attr('disabled', 'disabled');
                            }
                        })
                    })
                })
            }
        })
    });

    
</script>


</html>
