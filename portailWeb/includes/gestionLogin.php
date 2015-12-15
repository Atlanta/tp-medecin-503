<?php

function login($login, $mdp, $type)
{
	$file = "json/". $type . "s.json";
	$data = file_get_contents($file);
	$json = json_decode($data, true);
	

	$found = false;

	foreach($json as $entry)
	{
		if($entry["login"] == $login && $entry["mdp"] = $mdp)
			$found = true;
	}

	return $found;


}

?>