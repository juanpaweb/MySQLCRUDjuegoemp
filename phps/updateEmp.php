<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		$id = $_POST['id'];
		$grupo = $_POST['grupo'];
		$decision_mes = $_POST['decision_mes'];
		$trasladocc_acdt = $_POST['trasladocc_acdt'];
		$trasladocdt_acc = $_POST['trasladocdt_acc'];
		$sol_cred_bancario = $_POST['sol_cred_bancario'];
		$pag_cred_bancario = $_POST['pag_cred_bancario'];
		$pag_ctas_por_pagar = $_POST['pag_ctas_por_pagar'];
		$plazo_meses_cdt = $_POST['plazo_meses_cdt'];
		$plazo_meses_bancario = $_POST['plazo_meses_bancario'];
		
		require_once('dbConnect.php');
		
		$sql = "UPDATE pantalla1 SET grupo = '$grupo', decision_mes = '$decision_mes', trasladocc_acdt = '$trasladocc_acdt',
		trasladocdt_acc = '$trasladocdt_acc',sol_cred_bancario = '$sol_cred_bancario',pag_cred_bancario = '$pag_cred_bancario',
		pag_ctas_por_pagar = '$pag_ctas_por_pagar',plazo_meses_cdt = '$plazo_meses_cdt',plazo_meses_bancario = '$plazo_meses_bancario', WHERE id =$id;";
		
		if(mysqli_query($con,$sql)){
			echo 'grupo actualizado correctamente';
		}else{
			echo 'no se pudo actualizar el grupo intente de nuevo gggggggggg';
		}
		
		mysqli_close($con);
	}