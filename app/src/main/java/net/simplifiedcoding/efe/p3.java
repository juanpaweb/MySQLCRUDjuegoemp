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

public class p3 extends AppCompatActivity implements View.OnClickListener{

    //Defining views
    private EditText norma9000_son;
    private EditText licitacioncombate_son;
    private EditText licitacionunitario;
    private EditText seguro_son;
    private EditText incremento_salarios_son;
    private EditText exportacion_elite_son;
    private EditText elite_cantidad_ofertada;

    private Button guardar1;
    private Button ver1;
    //private Button continuar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p3);

        //Initializing views
        norma9000_son = (EditText) findViewById(R.id.norma9000_son);
        licitacioncombate_son = (EditText) findViewById(R.id.licitacioncombate_son);
        licitacionunitario = (EditText) findViewById(R.id.licitacionunitario);
        seguro_son = (EditText) findViewById(R.id.seguro_son);
        incremento_salarios_son = (EditText) findViewById(R.id.incremento_salarios_son);
        exportacion_elite_son = (EditText) findViewById(R.id.exportacion_elite_son);
        elite_cantidad_ofertada = (EditText) findViewById(R.id.elite_cantidad_ofertada);


        guardar1 = (Button) findViewById(R.id.guardar1);
        ver1 = (Button) findViewById(R.id.ver1);

        //Setting listeners to button
        guardar1.setOnClickListener(this);
        ver1.setOnClickListener(this);
    }

    //public void continuar(View v) {
      //  Intent intent = new Intent(p3.this, p3.class);
        //startActivity(intent);
    //}

    //Adding an employee
    private void addEmployee(){

        //primero va los campos de la DB     y luego los ids de los campos de texto de la vista

        final String norma9000 = norma9000_son.getText().toString().trim();
        final String licitacion_camisas_combate = licitacioncombate_son.getText().toString().trim();
        final String preciounitario_licitacion = licitacionunitario.getText().toString().trim();
        final String segurorobo = seguro_son.getText().toString().trim();
        final String incremento_salarios = incremento_salarios_son.getText().toString().trim();
        final String participacion_exp_elite = exportacion_elite_son.getText().toString().trim();
        final String cantidad_ofertada = elite_cantidad_ofertada.getText().toString().trim();


        class AddEmployee extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(p3.this,"Guardando...","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(p3.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config2.KEY_EMP_NORMA9000,norma9000);
                params.put(Config2.KEY_EMP_LICITACIONCOMBATE,licitacion_camisas_combate);
                params.put(Config2.KEY_EMP_LICITACIONUNITARIO,preciounitario_licitacion);
                params.put(Config2.KEY_EMP_SEGUROSON,segurorobo);
                params.put(Config2.KEY_EMP_INCREMENTOSALARIOS,incremento_salarios);
                params.put(Config2.KEY_EMP_EXPORTACIONELITE,participacion_exp_elite);
                params.put(Config2.KEY_EMP_ELITECANTIDADOFERTADA,cantidad_ofertada);


                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config2.URL_ADD2, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == guardar1){
            addEmployee();
        }

        if(v == ver1){
            startActivity(new Intent(this,ViewAllEmployee.class));
        }
    }
}