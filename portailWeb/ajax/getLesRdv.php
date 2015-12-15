<?php

	extract($_POST);

	$file = "../json/rendezVous.json";
	$data = file_get_contents($file);
	$json = json_decode($data, true);
	$lesRdv = [];

	foreach($json as $entry)
	{
		if($entry["medecin"]["id"] == $idMedecin)
		{
			array_push($lesRdv, $entry);
		}
	}

	// Définit le type de contenu
	header('Content-type: application/json; charset=UTF-8');
	// Encode le tableau associatif au format JSON
	echo json_encode($lesRdv);

?>