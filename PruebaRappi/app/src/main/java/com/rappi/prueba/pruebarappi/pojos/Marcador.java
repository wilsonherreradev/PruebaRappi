package com.rappi.prueba.pruebarappi.pojos;

import android.content.ContentValues;

import com.rappi.prueba.pruebarappi.basededatos.EsquemaBaseDatos;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author wilson herrera
 * @version 1.0
 * Clase con los datos del marcador
 */

public class Marcador implements Serializable {

    private String id;
    private String imagenIcono;
    private String imagenBanner;
    private String titulo;
    private String tituloCabecera;
    private String descripcion;
    private String descripcionPublica;
    private String url;
    private String nombre;
    private String suscriptores;

    /**
     * constructor vacio
     */
    public Marcador(){}

    /**
     * constructor que inicializa los datos
     * @param imagenCabecera
     * @param imagenIcono
     * @param imagenBanner
     * @param titulo
     * @param tituloCabecera
     * @param descripcion
     * @param descripcionPublica
     * @param url
     * @param nombre
     * @param suscriptores
     */
    public Marcador(String imagenCabecera, String imagenIcono, String imagenBanner, String titulo, String tituloCabecera, String descripcion, String descripcionPublica, String url, String nombre, String suscriptores) {
        this.id = UUID.randomUUID().toString();
        this.imagenIcono = imagenIcono;
        this.imagenBanner = imagenBanner;
        this.titulo = titulo;
        this.tituloCabecera = tituloCabecera;
        this.descripcion = descripcion;
        this.descripcionPublica = descripcionPublica;
        this.url = url;
        this.nombre = nombre;
        this.suscriptores = suscriptores;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagenIcono() {
        return imagenIcono;
    }

    public void setImagenIcono(String imagenIcono) {
        this.imagenIcono = imagenIcono;
    }

    public String getImagenBanner() {
        return imagenBanner;
    }

    public void setImagenBanner(String imagenBanner) {
        this.imagenBanner = imagenBanner;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTituloCabecera() {
        return tituloCabecera;
    }

    public void setTituloCabecera(String tituloCabecera) {
        this.tituloCabecera = tituloCabecera;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionPublica() {
        return descripcionPublica;
    }

    public void setDescripcionPublica(String descripcionPublica) {
        this.descripcionPublica = descripcionPublica;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSuscriptores() {
        return suscriptores;
    }

    public void setSuscriptores(String suscriptores) {
        this.suscriptores = suscriptores;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(  EsquemaBaseDatos.MarcadoresDB.ID , id);
        values.put(EsquemaBaseDatos.MarcadoresDB.IMAGEN_BANNER, imagenBanner);
        values.put(EsquemaBaseDatos.MarcadoresDB.IMAGEN_ICONO ,imagenIcono );
        values.put(EsquemaBaseDatos.MarcadoresDB.TITULO ,titulo );
        values.put(EsquemaBaseDatos.MarcadoresDB.TITULO_CABECERA ,tituloCabecera );
        values.put(EsquemaBaseDatos.MarcadoresDB.DESCRIPCION ,descripcion );
        values.put(EsquemaBaseDatos.MarcadoresDB.DESCRIPCION_PUBLICA ,descripcionPublica );
        values.put(EsquemaBaseDatos.MarcadoresDB.URL ,url );
        values.put(EsquemaBaseDatos.MarcadoresDB.NOMBRE ,nombre );
        values.put(EsquemaBaseDatos.MarcadoresDB.SUSCRIPTORES ,suscriptores );
        return values;
    }
}
