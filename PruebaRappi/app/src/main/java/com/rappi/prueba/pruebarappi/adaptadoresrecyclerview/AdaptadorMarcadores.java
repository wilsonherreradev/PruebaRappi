package com.rappi.prueba.pruebarappi.adaptadoresrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rappi.prueba.pruebarappi.R;
import com.rappi.prueba.pruebarappi.pojos.Marcador;
import com.rappi.prueba.pruebarappi.util.Constantes;

import android.net.Uri;
import java.util.ArrayList;

/**
 * @author wilson herrera
 * @version 1.0
 * Clase para adaptar las celdas del recycleview
 */
public class AdaptadorMarcadores extends RecyclerView.Adapter<AdaptadorMarcadores.ViewHolder>{

    // el contexto de la app
    private Context context;
    //lista de marcadores que seran mostrados
    private ArrayList<Marcador> marcadores;

    /**
     * constructor que asigna la lista y el contexto
     * @param marcadores
     * @param context
     */
    public AdaptadorMarcadores(ArrayList<Marcador> marcadores, Context context){
        super();
        this.marcadores = marcadores;
        this.context = context;
    }


    /**
     * infla el viewholder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.celda_marcadores, null));

    }


    /**
     * asigna los valores a la celda
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Marcador marcador =  marcadores.get(position);
        if(marcador.getImagenIcono().equals("")) {
            marcador.setImagenIcono(Constantes.IMAGEN_DEFAULT);
        }
        holder.txTitulo.setText(marcador.getTitulo());
        holder.txDescripcion.setText(marcador.getDescripcionPublica());
        Glide.with(context).load(marcador.getImagenIcono())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgIcono);
    }


    /**
     * asigna la lista de marcadores y actualiza el recycler
     * @param newlist
     */
    public void updateMarcadorList(ArrayList<Marcador> newlist) {
        marcadores.clear();
        marcadores.addAll(newlist);
        this.notifyDataSetChanged();
    }


    /**
     * retorna el numero de registros de la lista
     * @return
     */
    @Override
    public int getItemCount() {
        return marcadores.size();
    }


    /**
     * clase de apoyo para inicializar el recycler
     */
    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgIcono;
        public TextView txTitulo;
        public TextView txDescripcion;
        public TextView idMarcador;


        public ViewHolder(View itemView) {
            super(itemView);
            imgIcono = (ImageView) itemView.findViewById(R.id.imgMarcador);
            txTitulo = (TextView) itemView.findViewById(R.id.tituloMarcador);
            txDescripcion = (TextView) itemView.findViewById(R.id.descripcionMarcador);
            idMarcador = (TextView) itemView.findViewById(R.id.idMarcador);
        }
    }
}