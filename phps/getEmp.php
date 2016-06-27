<?php 
	$id = $_GET['id'];
	
	require_once('dbConnect.php');
	
	$sql = "SELECT * FROM pantalla1 WHERE id=$id";
	$r = mysqli_query($con,$sql);
	
	$result = array();
	
	$row = mysqli_fetch_array($r);
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

	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);