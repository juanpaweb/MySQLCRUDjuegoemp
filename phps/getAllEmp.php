<?php 
	//Importing Database Script 
	require_once('dbConnect.php');
	
	//Creating sql query
	$sql = "SELECT * FROM pantalla1";
	
	//getting result 
	$r = mysqli_query($con,$sql);
	
	//creating a blank array 
	$result = array();
	
	//looping through all the records fetched
	while($row = mysqli_fetch_array($r)){
		
		//Pushing name and id in the blank array created 
		array_push($result,array(
			"id"=>$row['id'],
			"grupo"=>$row['grupo'],
			"decision_mes"=>$row['decision_mes'],
			"trasladocc_acdt"=>$row['trasladocc_acdt'],
			"trasladocdt_acc"=>$row['trasladocdt_acc'],
			"sol_cred_bancario"=>$row['sol_cred_bancario'],
			"pag_cred_bancario"=>$row['pag_cred_bancario'],
			"pag_ctas_por_pagar"=>$row['pag_ctas_por_pagar'],
			"plazo_meses_cdt"=>$row['plazo_meses_cdt'],
			"plazo_meses_bancario"=>$row['plazo_meses_bancario']
		));
	}
	
	//Displaying the array in json format 
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);