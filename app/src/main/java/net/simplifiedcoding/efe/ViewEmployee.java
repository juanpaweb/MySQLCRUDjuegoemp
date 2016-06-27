package net.simplifiedcoding.efe;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ViewEmployee extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextId;
    private EditText etg;
    private EditText etm;
    private EditText ettacdt;
    private EditText etpmcc;
    private EditText etcdt;
    private EditText etscb;
    private EditText etscbm;
    private EditText etpcb;
    private EditText etpc;

    private Button buttonUpdate;
    private Button buttonDelete;


    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee);

        Intent intent = getIntent();

        id = intent.getStringExtra(Config.EMP_ID);

        editTextId = (EditText) findViewById(R.id.editTextId);
        ettacdt = (EditText) findViewById(R.id.ettacdt);
        etg = (EditText) findViewById(R.id.etg);
        etm = (EditText) findViewById(R.id.etm);
        etpmcc = (EditText) findViewById(R.id.etpmcc);
        etcdt = (EditText) findViewById(R.id.etcdt);
        etscb = (EditText) findViewById(R.id.etscb);
        etscbm = (EditText) findViewById(R.id.etscbm);
        etpcb = (EditText) findViewById(R.id.etpcb);
        etpc = (EditText) findViewById(R.id.etpc);


        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);


        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);


        editTextId.setText(id);

        getEmployee();
    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewEmployee.this,"Obteniendo datos...","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_EMP,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String grupo = c.getString(Config.TAG_GRUPO);
            String decision_mes = c.getString(Config.TAG_DECISION_MES);
            String trasladocc_acdt = c.getString(Config.TAG_TRASLADOCC_ACDT);
            String trasladocdt_acc = c.getString(Config.TAG_TRASLADOCDT_ACC);
            String sol_cred_bancario = c.getString(Config.TAG_SOL_CRED_BANCARIO);
            String pag_cred_bancario = c.getString(Config.TAG_PAG_CRED_BANCARIO);
            String pag_ctas_por_pagar = c.getString(Config.TAG_PAG_CTAS_POR_PAGAR);
            String plazo_meses_cdt = c.getString(Config.TAG_PLAZO_MESES_CDT);
            String plazo_meses_bancario = c.getString(Config.TAG_PAG_CRED_BANCARIO);

            etg.setText(grupo);
            etm.setText(decision_mes);
            ettacdt.setText(trasladocc_acdt);
            etpmcc.setText(trasladocdt_acc);
            etcdt.setText(sol_cred_bancario);
            etscb.setText(pag_cred_bancario);
            etscbm.setText(pag_ctas_por_pagar);
            etpcb.setText(plazo_meses_cdt);
            etpc.setText(plazo_meses_bancario);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateEmployee(){
        final String grupo = etg.getText().toString().trim();
        final String decision_mes = etm.getText().toString().trim();
        final String trasladocc_acdt = ettacdt.getText().toString().trim();
        final String trasladocdt_acc = etcdt.getText().toString().trim();
        final String sol_cred_bancario = etscb.getText().toString().trim();
        final String pag_cred_bancario = etpcb.getText().toString().trim();
        final String pag_ctas_por_pagar = etpc.getText().toString().trim();
        final String plazo_meses_cdt = etscbm.getText().toString().trim();
        final String plazo_meses_bancario = etpmcc.getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewEmployee.this,"Actualizando...","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewEmployee.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_EMP_ID,id);
                hashMap.put(Config.KEY_EMP_GRUPO,grupo);
                hashMap.put(Config.KEY_EMP_DECISION_MES,decision_mes);
                hashMap.put(Config.KEY_EMP_TRASLADOCC_ACDT,trasladocc_acdt);
                hashMap.put(Config.KEY_EMP_TRASLADOCDT_ACC,trasladocdt_acc);
                hashMap.put(Config.KEY_EMP_SOL_CRED_BANCARIO,sol_cred_bancario);
                hashMap.put(Config.KEY_EMP_PAG_CRED_BANCARIO,pag_cred_bancario);
                hashMap.put(Config.KEY_EMP_PAG_CTAS_POR_PAGAR,pag_ctas_por_pagar);
                hashMap.put(Config.KEY_EMP_PLAZO_MESES_CDT,plazo_meses_cdt);
                hashMap.put(Config.KEY_EMP_PLAZO_MESES_BANCARIO,plazo_meses_bancario);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_UPDATE_EMP,hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    private void deleteEmployee(){
        class DeleteEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewEmployee.this, "Actualizando...", "Espere...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewEmployee.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_DELETE_EMP, id);
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void confirmDeleteEmployee(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Esta seguro de borrar este grupo?");

        alertDialogBuilder.setPositiveButton("Si",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteEmployee();
                        startActivity(new Intent(ViewEmployee.this,ViewAllEmployee.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateEmployee();
        }

        if(v == buttonDelete){
            confirmDeleteEmployee();
        }


    }
}
