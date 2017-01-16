package com.rappi.prueba.pruebarappi.util;


import com.rappi.prueba.pruebarappi.pojos.Marcador;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


/**
 * @author wilson herrera
 * @version 1.0
 * Clase utilidad con metodos de ayuda JSON
 */

public class JsonUtil {

    /**
     * retorna una lista de marcadores de un JSON especifico
     * @param respuesta
     * @return
     */
    public ArrayList<Marcador> obtenerMarcadorDeJson(String respuesta){
        ArrayList<Marcador> marcadores=new ArrayList<Marcador>();
        try {
            JSONObject jsonRespuesta = new JSONObject(respuesta);
            JSONObject jsonData= new JSONObject(jsonRespuesta.getString("data"));
            JSONArray jsonChildren= jsonData.getJSONArray("children");
            for (int i = 0; i < jsonChildren.length(); i++){
                JSONObject jsonMarcador=jsonChildren.getJSONObject(i);
                JSONObject jsonDataChildren= new JSONObject(jsonMarcador.getString("data"));
                Marcador marcador=new Marcador();
                marcador.setImagenIcono(jsonDataChildren.getString("icon_img"));
                marcador.setTitulo(quitarCaracteres(jsonDataChildren.getString("title")));
                marcador.setDescripcion(quitarCaracteres(jsonDataChildren.getString("description")));
                marcador.setDescripcionPublica(quitarCaracteres(jsonDataChildren.getString("public_description")));
                marcador.setUrl(jsonDataChildren.getString("url"));
                marcador.setNombre(jsonDataChildren.getString("display_name"));
                marcador.setSuscriptores(jsonDataChildren.getString("subscribers"));
                marcador.setImagenBanner(jsonDataChildren.getString("banner_img"));
                marcadores.add(marcador);
            }
            return marcadores;
        } catch (JSONException e) {
                e.printStackTrace();
        return null;
        }
    }

    /**
     * quita los /r y # del texto pasado
     * @param texto
     * @return
     */
    public String quitarCaracteres(String texto){
        return texto.replaceAll("#","").replaceAll("/r/","").replaceAll("r/","");
    }
}
