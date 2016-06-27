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

public class p4 extends AppCompatActivity implements View.OnClickListener{

    //Defining views
    private EditText kit_maquinariaU;
    private EditText kitmaquinaria_son;
    private EditText tela_nal_mts;
    private EditText tela_nacional_son;
    private EditText telaimportada_mts;
    private EditText tela_importada_son;
    private EditText otros_insumos_com_U;
    private EditText otros_insumos_combate_son;
    private EditText otros_insumos_elite_U;
    private EditText otros_insumos_elite_son;

    private Button guardar4;
    private Button ver4;
    //private Button continuar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p4);

        //Initializing views
        kit_maquinariaU = (EditText) findViewById(R.id.kit_maquinariaU);
        kitmaquinaria_son = (EditText) findViewById(R.id.kitmaquinaria_son);
        tela_nal_mts = (EditText) findViewById(R.id.tela_nal_mts);
        tela_nacional_son = (EditText) findViewById(R.id.tela_nacional_son);
        telaimportada_mts = (EditText) findViewById(R.id.telaimportada_mts);
        tela_importada_son = (EditText) findViewById(R.id.tela_importada_son);
        otros_insumos_com_U = (EditText) findViewById(R.id.otros_insumos_com_U);
        otros_insumos_combate_son = (EditText) findViewById(R.id.otros_insumos_combate_son);
        otros_insumos_elite_U = (EditText) findViewById(R.id.otros_insumos_elite_U);
        otros_insumos_elite_son = (EditText) findViewById(R.id.otros_insumos_elite_son);



        guardar4 = (Button) findViewById(R.id.guardar4);
        ver4 = (Button) findViewById(R.id.ver4);

        //Setting listeners to button
        guardar4.setOnClickListener(this);
        ver4.setOnClickListener(this);
    }

    //public void continuar(View v) {
    //  Intent intent = new Intent(p3.this, p3.class);
    //startActivity(intent);
    //}

    //Adding an employee
    private void addEmployee(){

        //primero va los campos de la DB     y luego los ids de los campos de texto de la vista

        final String kitdemaquinariaunidades = kit_maquinariaU.getText().toString().trim();
        final String telanacionalenmts = kitmaquinaria_son.getText().toString().trim();
        final String telaimportadamts = tela_nal_mts.getText().toString().trim();
        final String otrosinsumoscombateunidades = tela_nacional_son.getText().toString().trim();
        final String otrosinsumoseliteunidad = telaimportada_mts.getText().toString().trim();
        final String kitmaquinariasiono = tela_importada_son.getText().toString().trim();
        final String telanacionalsiono = otros_insumos_com_U.getText().toString().trim();
        final String telaimportadasiono = otros_insumos_combate_son.getText().toString().trim();
        final String otrosinsumoscombatesiono = otros_insumos_elite_U.getText().toString().trim();
        final String otrosinsumoselitesiono = otros_insumos_elite_son.getText().toString().trim();


        class AddEmployee extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(p4.this,"Guardando...","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(p4.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config3.KEY_EMP_KITDEMAQUINARIAUNIDADES,kitdemaquinariaunidades);
                params.put(Config3.KEY_EMP_TELANACIONALENMTS,telanacionalenmts);
                params.put(Config3.KEY_EMP_TELAIMPORTADAENMTS,telaimportadamts);
                params.put(Config3.KEY_EMP_OTROSINSUMOSCOMBATEUNIDADES,otrosinsumoscombateunidades);
                params.put(Config3.KEY_EMP_OTROSINSUMOSELITEUNIDADES,otrosinsumoseliteunidad);
                params.put(Config3.KEY_EMP_KITMAQUINARIASIONO,kitmaquinariasiono);
                params.put(Config3.KEY_EMP_TELANACIONALSIONO,telanacionalsiono);
                params.put(Config3.KEY_EMP_TELAIMPORTADASIONO,telaimportadasiono);
                params.put(Config3.KEY_EMP_OTROSINSUMOSCOMBATESIONO,otrosinsumoscombatesiono);
                params.put(Config3.KEY_EMP_OTROSINSUMOSELITESIONO,otrosinsumoselitesiono);


                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config3.URL_ADD3, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == guardar4){
            addEmployee();
        }

        if(v == ver4){
            startActivity(new Intent(this,ViewAllEmployee.class));
        }
    }
}