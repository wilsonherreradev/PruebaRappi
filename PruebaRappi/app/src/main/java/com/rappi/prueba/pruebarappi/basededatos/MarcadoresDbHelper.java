package com.rappi.prueba.pruebarappi.basededatos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rappi.prueba.pruebarappi.pojos.Marcador;
import com.rappi.prueba.pruebarappi.util.Constantes;

import java.util.ArrayList;


/**
 * @author wilson herrera
 * @version 1.0
 * Clase para crear la base de datos y con metodos para accesarla
 */

public class MarcadoresDbHelper extends SQLiteOpenHelper {

    //version de la DB
    public static final int DATABASE_VERSION = 1;
    //Nombre de la DB
    public static final String DATABASE_NAME = "marcadores.db";


    /**
     * constructor de la clase
     * @param context
     */
    public MarcadoresDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * actualiza la base de datos de version
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    /**
     * Crea la base de datos
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + EsquemaBaseDatos.MarcadoresDB.NOMBRE_TABLA + " ("
                + EsquemaBaseDatos.MarcadoresDB._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + EsquemaBaseDatos.MarcadoresDB.ID + " TEXT,"
                + EsquemaBaseDatos.MarcadoresDB.IMAGEN_ICONO + " TEXT,"
                + EsquemaBaseDatos.MarcadoresDB.IMAGEN_BANNER + " TEXT,"
                + EsquemaBaseDatos.MarcadoresDB.TITULO + " TEXT,"
                + EsquemaBaseDatos.MarcadoresDB.TITULO_CABECERA + " TEXT,"
                + EsquemaBaseDatos.MarcadoresDB.DESCRIPCION + " TEXT,"
                + EsquemaBaseDatos.MarcadoresDB.DESCRIPCION_PUBLICA + " TEXT,"
                + EsquemaBaseDatos.MarcadoresDB.URL + " TEXT,"
                + EsquemaBaseDatos.MarcadoresDB.NOMBRE + " TEXT,"
                + EsquemaBaseDatos.MarcadoresDB.SUSCRIPTORES + " TEXT,"
                + "UNIQUE (" + EsquemaBaseDatos.MarcadoresDB.ID + "))");
    }


    /**
     * guarda un objeto marcador en la DB
     * @param marcador
     * @return
     */
    public long guardarMarcador(Marcador marcador) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(
                EsquemaBaseDatos.MarcadoresDB.NOMBRE_TABLA,
                null,
                marcador.toContentValues());

    }


    /**
     * retorna todos los registros de la DB como una lista de Marcador
     * @return
     */
    public ArrayList<Marcador> obtenerTodosLosMarcadores() {
        ArrayList<Marcador> marcadores=new ArrayList<Marcador>();
        Cursor c= getReadableDatabase()
                .query(EsquemaBaseDatos.MarcadoresDB.NOMBRE_TABLA, null, null,null,null, null, null);
        while(c.moveToNext()){
            Marcador marcador=new Marcador();
            marcador.setImagenIcono(Constantes.IMAGEN_DEFAULT);
            marcador.setTitulo(c.getString(c.getColumnIndex(EsquemaBaseDatos.MarcadoresDB.TITULO)));
            marcador.setDescripcion(c.getString(c.getColumnIndex(EsquemaBaseDatos.MarcadoresDB.DESCRIPCION)));
            marcador.setDescripcionPublica(c.getString(c.getColumnIndex(EsquemaBaseDatos.MarcadoresDB.DESCRIPCION_PUBLICA)));
            marcador.setUrl(c.getString(c.getColumnIndex(EsquemaBaseDatos.MarcadoresDB.URL)));
            marcador.setNombre(c.getString(c.getColumnIndex(EsquemaBaseDatos.MarcadoresDB.NOMBRE)));
            marcador.setSuscriptores(c.getString(c.getColumnIndex(EsquemaBaseDatos.MarcadoresDB.SUSCRIPTORES)));
            marcador.setImagenBanner(c.getString(c.getColumnIndex(EsquemaBaseDatos.MarcadoresDB.IMAGEN_BANNER)));
            marcadores.add(marcador);
        }
        return marcadores;
    }


    /**
     * borra todos los registros en la DB
     * @return
     */
    public long borrarMarcadores(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete( EsquemaBaseDatos.MarcadoresDB.NOMBRE_TABLA,null,null);
    }


    /**
     * guarda en la DB la lista pasada por parametro
     * @param marcadores
     */
    public void  guardarMarcadores(ArrayList<Marcador> marcadores){
        for (Marcador marcador:marcadores) {
            guardarMarcador(marcador);
        }
    }



}
