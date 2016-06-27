<?php 
	$id = $_GET['id'];
	
	require_once('dbConnect.php');
	
	$sql = "DELETE FROM pantalla1 WHERE id=$id;";
	
	if(mysqli_query($con,$sql)){
		echo 'grupo borrado!';
	}else{
		echo 'no se pudo borrar el grupo, intente de nuevo';
	}
	
	mysqli_close($con);
