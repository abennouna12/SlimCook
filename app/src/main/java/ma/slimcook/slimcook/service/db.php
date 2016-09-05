<?php

	$DB_HOST = 'bennouna.fr.mysql';
	$DB_USER = 'bennouna_fr';
	$DB_PASS = 'POO2014';
	$DB_NAME = 'bennouna_fr';

	$connection = mysqli_connect($DB_HOST, $DB_USER, $DB_PASS, $DB_NAME) or die("Error " . mysqli_error($connection));

?>
