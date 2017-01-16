package com.rappi.prueba.pruebarappi.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author wilson herrera
 * @version 1.0
 * Clase con utilidades de conexiones de internet
 */
public class ConexionesInternet {

    /**
     * verifica si el dispositivo cuenta con conexion a internet
     * @param context contexto de la aplicacion
     * @return true si hay conexion, false en caso contrario
     */
    public  boolean isNetDisponible(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();
        return (actNetInfo != null && actNetInfo.isConnected());
    }
}
