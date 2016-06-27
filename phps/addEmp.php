<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		//Getting values
		$grupo = $_POST['grupo'];
		$decision_mes = $_POST['decision_mes'];
		$trasladocc_acdt = $_POST['trasladocc_acdt'];
		$trasladocdt_acc = $_POST['trasladocdt_acc'];
		$sol_cred_bancario = $_POST['sol_cred_bancario'];
		$pag_cred_bancario = $_POST['pag_cred_bancario'];
		$pag_ctas_por_pagar = $_POST['pag_ctas_por_pagar'];
		$plazo_meses_cdt = $_POST['plazo_meses_cdt'];
		$plazo_meses_bancario = $_POST['plazo_meses_bancario'];
		
		//Creating an sql query
		$sql = "INSERT INTO pantalla1 (grupo,decision_mes,trasladocc_acdt,trasladocdt_acc,
		sol_cred_bancario,pag_cred_bancario,pag_ctas_por_pagar,plazo_meses_cdt,plazo_meses_bancario)
		VALUES ('$grupo','$decision_mes','$trasladocc_acdt','$trasladocdt_acc','$sol_cred_bancario',
		'$pag_cred_bancario','$pag_ctas_por_pagar','$plazo_meses_cdt','$plazo_meses_bancario')";
		
		//Importing our db connection script
		require_once('dbConnect.php');
		
		//Executing query to database
		if(mysqli_query($con,$sql)){
			echo 'datos de grupo guardados con exito';
		}else{
			echo 'no se pudo adicionar el grupo';
		}
		
		//Closing the database 
		mysqli_close($con);
	}