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

public class ViewEmployee3 extends AppCompatActivity implements View.OnClickListener {

    private EditText norma9000_son;
    private EditText licitacioncombate_son;
    private EditText licitacionunitario;
    private EditText seguro_son;
    private EditText incremento_salarios_son;
    private EditText exportacion_elite_son;
    private EditText elite_cantidad_ofertada;
    private EditText editTextId2;

    private Button buttonUpdate2;
    private Button buttonDelete2;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee2);

        Intent intent = getIntent();

        id = intent.getStringExtra(Config2.EMP_ID2);

        norma9000_son = (EditText) findViewById(R.id.norma9000_son);
        licitacioncombate_son = (EditText) findViewById(R.id.licitacioncombate_son);
        licitacionunitario = (EditText) findViewById(R.id.licitacionunitario);
        seguro_son = (EditText) findViewById(R.id.seguro_son);
        incremento_salarios_son = (EditText) findViewById(R.id.incremento_salarios_son);
        exportacion_elite_son = (EditText) findViewById(R.id.exportacion_elite_son);
        elite_cantidad_ofertada = (EditText) findViewById(R.id.elite_cantidad_ofertada);




        buttonUpdate2 = (Button) findViewById(R.id.buttonUpdate2);
        buttonDelete2 = (Button) findViewById(R.id.buttonDelete2);

        buttonUpdate2.setOnClickListener(this);
        buttonDelete2.setOnClickListener(this);

        editTextId2.setText(id);

        getEmployee();
    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewEmployee3.this,"Obteniendo datos...","Espere...",false,false);
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
                String s = rh.sendGetRequestParam(Config3.URL_GET_EMP3,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result2 = jsonObject.getJSONArray(Config2.TAG_JSON_ARRAY2);
            JSONObject c = result2.getJSONObject(0);
            String norma9000 = c.getString(Config2.TAG_NORMA9000);
            String licitacion_camisas_combate = c.getString(Config2.TAG_LICITACIONCOMBATE);
            String preciounitario_licitacion = c.getString(Config2.TAG_LICITACIONUNITARIO);
            String segurorobo = c.getString(Config2.TAG_SEGUROSON);
            String incremento_salarios = c.getString(Config2.TAG_INCREMENTOSALARIOS);
            String participacion_exp_elite = c.getString(Config2.TAG_EXPORTACIONELITE);
            String cantidad_ofertada = c.getString(Config2.TAG_ELITECANTIDADOFERTADA);



            //campos de la DB
            norma9000_son.setText(norma9000);
            licitacioncombate_son.setText(licitacion_camisas_combate);
            licitacionunitario.setText(preciounitario_licitacion);
            seguro_son.setText(segurorobo);
            incremento_salarios_son.setText(incremento_salarios);
            exportacion_elite_son.setText(participacion_exp_elite);
            elite_cantidad_ofertada.setText(cantidad_ofertada);



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateEmployee(){
        //campos de la DB
        final String norma9000 = norma9000_son.getText().toString().trim();
        final String licitacion_camisas_combate = licitacioncombate_son.getText().toString().trim();
        final String preciounitario_licitacion = licitacionunitario.getText().toString().trim();
        final String segurorobo = seguro_son.getText().toString().trim();
        final String incremento_salarios = incremento_salarios_son.getText().toString().trim();
        final String participacion_exp_elite = exportacion_elite_son.getText().toString().trim();
        final String cantidad_ofertada = elite_cantidad_ofertada.getText().toString().trim();




        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewEmployee3.this,"Actualizando...","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewEmployee3.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Config2.KEY_EMP_ID2,id);

                // campos de DB
                hashMap.put(Config2.KEY_EMP_NORMA9000,norma9000);
                hashMap.put(Config2.KEY_EMP_LICITACIONCOMBATE,licitacion_camisas_combate);
                hashMap.put(Config2.KEY_EMP_LICITACIONUNITARIO,preciounitario_licitacion);
                hashMap.put(Config2.KEY_EMP_SEGUROSON,segurorobo);
                hashMap.put(Config2.KEY_EMP_INCREMENTOSALARIOS,incremento_salarios);
                hashMap.put(Config2.KEY_EMP_EXPORTACIONELITE,participacion_exp_elite);
                hashMap.put(Config2.KEY_EMP_ELITECANTIDADOFERTADA,cantidad_ofertada);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config2.URL_UPDATE_EMP2,hashMap);

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
                loading = ProgressDialog.show(ViewEmployee3.this, "Actualizando...", "Espere...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewEmployee3.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config2.URL_DELETE_EMP2, id);
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
                        startActivity(new Intent(ViewEmployee3.this,view_all_employee2.class));
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
        if(v == buttonUpdate2){
            updateEmployee();
        }

        if(v == buttonDelete2){
            confirmDeleteEmployee();
        }


    }
}
