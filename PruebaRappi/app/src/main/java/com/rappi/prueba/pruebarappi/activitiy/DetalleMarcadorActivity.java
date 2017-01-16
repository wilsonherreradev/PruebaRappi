package com.rappi.prueba.pruebarappi.activitiy;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rappi.prueba.pruebarappi.R;
import com.rappi.prueba.pruebarappi.pojos.Marcador;
import com.rappi.prueba.pruebarappi.util.Constantes;

/**
 * @author wilson herrera
 * @version 1.0
 * activity con que muestra el detalle del marcador seleccionado
 */
public class DetalleMarcadorActivity extends AppCompatActivity {

    //titulo y descripcion del marcador
    TextView titulo, descripcionNormal;
    //imagen del amrcador
    ImageButton imgMarcador;
    //Button para enviar a la pagina
    Button abrirUrl;
    //Objeto marcador que se recibira de listamarcadoresactivity
    Marcador marcador;


    /**
     * Metodo llamado cuando se inicia la actividad
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_marcador_activity);
        marcador= (Marcador)getIntent().getSerializableExtra(Constantes.INTENT_MARCADOR);
        inicializarComponentesGraficos();
        asignarValoresVista();
    }


    /**
     * Se inicializan los componentes de la parte grafica
     */
    public void inicializarComponentesGraficos(){
        titulo = (TextView) findViewById(R.id.titulo);
        descripcionNormal = (TextView) findViewById(R.id.txDescripcionNormal);
        imgMarcador = (ImageButton) findViewById(R.id.imgMarcador);
        abrirUrl = (Button) findViewById(R.id.enlace);
        abrirUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirUrl();
            }
        });
    }


    /**
     * asigna los valores a la parte grafica, tomando los valores del objeto marcador, pasado por intent
     */
    public void asignarValoresVista(){
        titulo.setText(marcador.getTitulo());
        descripcionNormal.setText(marcador.getDescripcionPublica());
        Glide.with(getApplicationContext()).load(marcador.getImagenIcono())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgMarcador);
    }


    /**
     * abre la url del marcador conmpleto en un navegador
     */
    public void abrirUrl(){
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constantes.URL_REDDIT+marcador.getUrl()));
            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(browserIntent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), getString(R.string.error_abiri_url), Toast.LENGTH_SHORT).show();
        }
    }



}
