package net.simplifiedcoding.efe;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Defining views
    private EditText etg;
    private EditText etm;
    private EditText ettacdt;
    private EditText etpmcc;
    private EditText etcdt;
    private EditText etscb;
    private EditText etscbm;
    private EditText etpcb;
    private EditText etpc;

    private Button buttonAdd;
    private Button buttonView;
    //private Button continuar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing views
        etg = (EditText) findViewById(R.id.etg);
        etm = (EditText) findViewById(R.id.etm);
        ettacdt = (EditText) findViewById(R.id.ettacdt);
        etpmcc = (EditText) findViewById(R.id.etpmcc);
        etcdt = (EditText) findViewById(R.id.etcdt);
        etscb = (EditText) findViewById(R.id.etscb);
        etscbm = (EditText) findViewById(R.id.etscbm);
        etpcb = (EditText) findViewById(R.id.etpcb);
        etpc = (EditText) findViewById(R.id.etpc);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }

    public void continuar(View v) {
        Intent intent = new Intent(MainActivity.this, p2.class);
        startActivity(intent);
    }

    //Adding an employee
    private void addEmployee(){

        final String grupo = etg.getText().toString().trim();
        final String decision_mes = etm.getText().toString().trim();
        final String trasladocc_acdt = ettacdt.getText().toString().trim();
        final String trasladocdt_acc = etpmcc.getText().toString().trim();
        final String sol_cred_bancario = etcdt.getText().toString().trim();
        final String pag_cred_bancario = etscb.getText().toString().trim();
        final String pag_ctas_por_pagar = etscbm.getText().toString().trim();
        final String plazo_meses_cdt = etpcb.getText().toString().trim();
        final String plazo_meses_bancario = etpc.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Guardando datos...","espere por favor...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_EMP_GRUPO,grupo);
                params.put(Config.KEY_EMP_DECISION_MES,decision_mes);
                params.put(Config.KEY_EMP_TRASLADOCC_ACDT,trasladocc_acdt);
                params.put(Config.KEY_EMP_TRASLADOCDT_ACC,trasladocdt_acc);
                params.put(Config.KEY_EMP_SOL_CRED_BANCARIO,sol_cred_bancario);
                params.put(Config.KEY_EMP_PAG_CRED_BANCARIO,pag_cred_bancario);
                params.put(Config.KEY_EMP_PAG_CTAS_POR_PAGAR,pag_ctas_por_pagar);
                params.put(Config.KEY_EMP_PLAZO_MESES_CDT,plazo_meses_cdt);
                params.put(Config.KEY_EMP_PLAZO_MESES_BANCARIO,plazo_meses_bancario);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addEmployee();
        }

        if(v == buttonView){
            startActivity(new Intent(this,ViewAllEmployee.class));
        }
    }
}