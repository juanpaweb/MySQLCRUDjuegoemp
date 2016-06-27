package net.simplifiedcoding.efe;


public class Config {

    //Address of our scripts of the CRUD
    public static final String URL_ADD = "http://oroconsultores.com/juego_empresarial/p1/addEmp.php";
    public static final String URL_GET_ALL = "http://oroconsultores.com/juego_empresarial/p1/getAllEmp.php";
    public static final String URL_GET_EMP = "http://oroconsultores.com/juego_empresarial/p1/getEmp.php?id=";
    public static final String URL_UPDATE_EMP = "http://oroconsultores.com/juego_empresarial/p1/updateEmp.php";
    public static final String URL_DELETE_EMP = "http://oroconsultores.com/juego_empresarial/p1/deleteEmp.php?id=";


    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_GRUPO = "grupo";
    public static final String KEY_EMP_DECISION_MES = "decision_mes";
    public static final String KEY_EMP_TRASLADOCC_ACDT = "trasladocc_acdt";
    public static final String KEY_EMP_TRASLADOCDT_ACC = "trasladocdt_acc";
    public static final String KEY_EMP_SOL_CRED_BANCARIO = "sol_cred_bancario";
    public static final String KEY_EMP_PAG_CRED_BANCARIO = "pag_cred_bancario";
    public static final String KEY_EMP_PAG_CTAS_POR_PAGAR = "pag_ctas_por_pagar";
    public static final String KEY_EMP_PLAZO_MESES_CDT = "plazo_meses_cdt";
    public static final String KEY_EMP_PLAZO_MESES_BANCARIO = "plazo_meses_bancario";


    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_GRUPO = "grupo";
    public static final String TAG_DECISION_MES = "decision_mes";
    public static final String TAG_TRASLADOCC_ACDT = "trasladocc_acdt";
    public static final String TAG_TRASLADOCDT_ACC = "trasladocdt_acc";
    public static final String TAG_SOL_CRED_BANCARIO = "sol_cred_bancario";
    public static final String TAG_PAG_CRED_BANCARIO = "pag_cred_bancario";
    public static final String TAG_PAG_CTAS_POR_PAGAR = "pag_ctas_por_pagar";
    public static final String TAG_PLAZO_MESES_CDT = "plazo_meses_cdt";
    public static final String TAG_PLAZO_MESES_BANCARIO = "plazo_meses_bancario";



    //employee id to pass with intent
    public static final String EMP_ID = "emp_id";
}
