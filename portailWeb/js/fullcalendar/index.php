<?php
    if(empty($_SESSION)){
        header("Location: ../../login.php");
    }
    else{
        header("Location: ../../agenda.php");
    }
?>