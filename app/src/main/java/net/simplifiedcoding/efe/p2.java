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

public class p2 extends AppCompatActivity implements View.OnClickListener{

    //Defining views
    private EditText n_cam_fabricar_elite;
    private EditText n_cam_venta_elite;
    private EditText ventau_elite;
    private EditText publicidad_elite;
    private EditText n_cam_fabricar_ejecutivo;
    private EditText n_cam_venta_ejecutivo;
    private EditText ventau_ejecutivo;
    private EditText publicidad_ejecutivo;
    private EditText n_cam_fabricar_combate;
    private EditText n_cam_venta_combate;
    private EditText ventau_combate;
    private EditText publicidad_combate;


    private Button buttonAdd1;
    private Button continuar1;
    private Button buttonView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p2);

        //Initializing views
        n_cam_fabricar_elite = (EditText) findViewById(R.id.n_cam_fabricar_elite);
        n_cam_venta_elite = (EditText) findViewById(R.id.n_cam_venta_elite);
        ventau_elite = (EditText) findViewById(R.id.ventau_elite);
        publicidad_elite = (EditText) findViewById(R.id.publicidad_elite);
        n_cam_fabricar_ejecutivo = (EditText) findViewById(R.id.n_cam_fabricar_ejecutivo);
        n_cam_venta_ejecutivo = (EditText) findViewById(R.id.n_cam_venta_ejecutivo);
        ventau_ejecutivo = (EditText) findViewById(R.id.ventau_ejecutivo);
        publicidad_ejecutivo = (EditText) findViewById(R.id.publicidad_ejecutivo);
        n_cam_fabricar_combate = (EditText) findViewById(R.id.n_cam_fabricar_combate);
        n_cam_venta_combate = (EditText) findViewById(R.id.n_cam_venta_combate);
        ventau_combate = (EditText) findViewById(R.id.ventau_combate);
        publicidad_combate = (EditText) findViewById(R.id.publicidad_combate);




        buttonAdd1 = (Button) findViewById(R.id.buttonAdd1);
        buttonView1 = (Button) findViewById(R.id.buttonView1);
        continuar1 = (Button) findViewById(R.id.continuar1);

        //Setting listeners to button
        buttonAdd1.setOnClickListener(this);
        buttonView1.setOnClickListener(this);
    }

    public void continuar(View v) {
        Intent intent = new Intent(p2.this, p3.class);
        startActivity(intent);
    }

    //Adding an employee
    private void addEmployee(){

        //primero va los campos de la DB     y luego los ids de los campos de texto de la vista

        final String ventaunidadelite = n_cam_fabricar_elite.getText().toString().trim();
        final String ventaunidadejecutivo = n_cam_venta_elite.getText().toString().trim();
        final String ventaunidadcombate = ventau_elite.getText().toString().trim();
        final String publicidadelite = publicidad_elite.getText().toString().trim();
        final String publicidadejecutivo = n_cam_fabricar_ejecutivo.getText().toString().trim();
        final String publicidadcombate = n_cam_venta_ejecutivo.getText().toString().trim();
        final String numerodecamisasafabricarelite = ventau_ejecutivo.getText().toString().trim();
        final String numerodecamisasafabricarejecutivo = publicidad_ejecutivo.getText().toString().trim();
        final String numerodecamisasafabricarcombate = n_cam_fabricar_combate.getText().toString().trim();
        final String numerodecamisasavenderelite = n_cam_venta_combate.getText().toString().trim();
        final String numerodecamisasavenderejecutivo = ventau_combate.getText().toString().trim();
        final String numerodecamisasavendercombate = publicidad_combate.getText().toString().trim();



        class AddEmployee extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(p2.this,"guardando ...","espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(p2.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();

                ////////////////////////////////////// va aca los campos de la DB
                params.put(Config1.KEY_EMP_VENTAUNIDADELITE,ventaunidadelite);
                params.put(Config1.KEY_EMP_VENTAUNIDADEJECUTIVO,ventaunidadejecutivo);
                params.put(Config1.KEY_EMP_VENTAUNIDADCOMBATE,ventaunidadcombate);
                params.put(Config1.KEY_EMP_PUBLICIDADELITE,publicidadelite);
                params.put(Config1.KEY_EMP_PUBLICIDADEJECUTIVO,publicidadejecutivo);
                params.put(Config1.KEY_EMP_PUBLICIDADCOMBATE,publicidadcombate);
                params.put(Config1.KEY_EMP_NUMERODECAMISASAFABRICARELITE,numerodecamisasafabricarelite);
                params.put(Config1.KEY_EMP_NUMERODECAMISASAFABRICAREJECUTIVO,numerodecamisasafabricarejecutivo);
                params.put(Config1.KEY_EMP_NUMERODECAMISASAFABRICARCOMBATE,numerodecamisasafabricarcombate);
                params.put(Config1.KEY_EMP_NUMERODECAMISASAVENDERELITE,numerodecamisasavenderelite);
                params.put(Config1.KEY_EMP_NUMERODECAMISASAVENDEREJECUTIVO,numerodecamisasavenderejecutivo);
                params.put(Config1.KEY_EMP_NUMERODECAMISASAVENDERCOMBATE,numerodecamisasavendercombate);


                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config1.URL_ADD1, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd1){
            addEmployee();
        }

        if(v == buttonView1){
            startActivity(new Intent(this,ViewAllEmployee.class));
        }
    }
}