package com.rappi.prueba.pruebarappi.activitiy;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rappi.prueba.pruebarappi.R;
import com.rappi.prueba.pruebarappi.adaptadoresrecyclerview.AdaptadorMarcadores;
import com.rappi.prueba.pruebarappi.basededatos.MarcadoresDbHelper;
import com.rappi.prueba.pruebarappi.http.LlamadasTHHP;
import com.rappi.prueba.pruebarappi.listeners.RecyclerItemClickListener;
import com.rappi.prueba.pruebarappi.pojos.Marcador;
import com.rappi.prueba.pruebarappi.util.ConexionesInternet;
import com.rappi.prueba.pruebarappi.util.Constantes;
import com.rappi.prueba.pruebarappi.util.JsonUtil;



import java.util.ArrayList;

/**
 * @author wilson herrera
 * @version 1.0
 * activity con que muestra la lista de los marcadores
 */
public class ListaMarcadoresActivity extends AppCompatActivity {

    //objeto para listar los marcadores
    private RecyclerView recyclerView;
    //Objeto que adapta las celdas de la lista de marcadores
    private AdaptadorMarcadores adaptadorMarcadores;
    //lista de marcadores a mostrar
    private ArrayList<Marcador> marcadores = new ArrayList<>();
    //imagen de carga
    private ProgressBar progressDialog;
    //array para almacenar la respuesta del servicio
    String[] respuestaHTTP;

    /**
     * Metodo llamado cuando se inicia la actividad
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_marcadores_activity);
        progressDialog = (ProgressBar) findViewById(R.id.progressBar1);
        inicializarRecyclreView();
        regargarListaMarcadores();

    }


    /**
     * Dependiendo si el dispositivo cuenta con conexion a internet, carga los datos del webservice o de base de datos
     */
    public void regargarListaMarcadores(){
        progressDialog.setVisibility(View.VISIBLE);
        marcadores.clear();
        if (new ConexionesInternet().isNetDisponible(getApplicationContext())){
            new DownloadTask().execute();
        }else{
            mostrarDatosDB();
            Toast.makeText(getApplicationContext(), getString(R.string.error_conexion), Toast.LENGTH_LONG).show();
        }
    }


    /**
     * Inicializa el RecyclreView y coloca el enento del clic en las celdas
     */
    private void inicializarRecyclreView(){
        recyclerView = (RecyclerView) findViewById(R.id.lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adaptadorMarcadores = new AdaptadorMarcadores(marcadores, getApplicationContext());
        recyclerView.setAdapter(adaptadorMarcadores);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(ListaMarcadoresActivity.this,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent detalle = new Intent(getApplicationContext(), DetalleMarcadorActivity.class);
                        Marcador marcadorSeleccionado = marcadores.get(position);
                        detalle.putExtra(Constantes.INTENT_MARCADOR, marcadorSeleccionado);
                        startActivity(detalle);
                    }
                }));
    }


    /**
     * Realiza la descarga de datos desde el webservice
     */
    public void mostrarDatosHttp(){
        if (respuestaHTTP[0].equals("200")) {
            try {
                marcadores=new JsonUtil().obtenerMarcadorDeJson(respuestaHTTP[1]);
                adaptadorMarcadores.updateMarcadorList(marcadores);
                actializarBaseDatos();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), getString(R.string.error_armando_respuesta), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.error_conexion_servidor), Toast.LENGTH_LONG).show();
        }
        progressDialog.setVisibility(View.INVISIBLE);
    }


    /**
     * borra los datops existentes en la base de datos e inserta los que se descargaron
     */
    public void actializarBaseDatos(){
        MarcadoresDbHelper marcadoresDbHelpernew=new MarcadoresDbHelper(getApplicationContext());
        marcadoresDbHelpernew.borrarMarcadores();
        marcadoresDbHelpernew.guardarMarcadores(marcadores);
    }


    /**
     * Realiza la descarga de datos desde la base de datos
     */
    public void mostrarDatosDB(){
        MarcadoresDbHelper marcadoresDbHelpernew=new MarcadoresDbHelper(getApplicationContext());
        marcadores =marcadoresDbHelpernew.obtenerTodosLosMarcadores();
        if(marcadores.size()==0){
            Toast.makeText(getApplicationContext(), getString(R.string.error_no_datos), Toast.LENGTH_SHORT).show();
        }
        adaptadorMarcadores.updateMarcadorList(marcadores);
        progressDialog.setVisibility(View.INVISIBLE);
    }


    /**
     * Crea el menu para el boton de recargar
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    /**
     * recarga la lista de marcadores
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_recargar:
                regargarListaMarcadores();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * clase para ejecutar llamado de web service en un hilo alterno y ejecutar la respuesta con el hilo principal
     */
    public class DownloadTask extends AsyncTask<String, Void, String> {
        //se ejecuta en un hilo alterno
        @Override
        protected String doInBackground(String... params) {
            try {
                respuestaHTTP=new LlamadasTHHP().ejecutarhttp(Constantes.URL_REDDIT_JSON,null);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return null;
        }

        //Se ejecuta con el hijo principal, luego de que recibe respuesta
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            mostrarDatosHttp();
        }

    }


}
