<?php

function login($login, $mdp, $type)
{
	$file = "json/". $type . ".json";
	$data = file_get_contents($file);
	$json = json_decode($data, true);
	

	$idUser = -1;

	foreach($json as $entry)
	{
		if($entry["login"] == $login && $entry["mdp"] = $mdp)
			$idUser = $entry["id"];
	}

	return $idUser;


}

function getData($nom)
{
	$file = "json/". $nom . ".json";
	$data = file_get_contents($file);
	$json = json_decode($data, true);
	return $json;
}

function reserver($idPatient, $idMedecin, $date, $heure)
{
	$json = getData("rendezVous");
	$newEntry = [];
	$patient = [];
	$medecin = [];


	$patient["id"] = $idPatient;
	$medecin["id"] = $idMedecin;


	$newEntry["date"] = $date;
	$newEntry["heure"] = $heure;
	$newEntry["patient"] = $patient;
	$newEntry["id"] = 0;
	$newEntry["medecin"] = $medecin;



	
	
	//array_push($patient, $idPatient);
	//array_push($medecin, $idMedecin);

	//array_push($newEntry, $date, $heure, $patient, 0, $medecin);
	array_push($json, $newEntry);


	$newEntryJson = json_encode($json);

	file_put_contents("json/rendezVous.json", $newEntryJson);

}

?>