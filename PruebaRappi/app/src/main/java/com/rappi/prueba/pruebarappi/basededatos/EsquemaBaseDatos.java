package com.rappi.prueba.pruebarappi.basededatos;

import android.provider.BaseColumns;

/**
 * @author wilson herrera
 * @version 1.0
 * Clase base para el esquema de base de datos
 */

public class EsquemaBaseDatos {

    public static abstract class MarcadoresDB implements BaseColumns {
        public static final String NOMBRE_TABLA ="marcadores";
        public static final String ID = "id";
        public static final String IMAGEN_ICONO="imagenIcono";
        public static final String IMAGEN_BANNER="imagenBanner";
        public static final String TITULO="titulo";
        public static final String TITULO_CABECERA="tituloCabecera";
        public static final String DESCRIPCION="descripcion";
        public static final String DESCRIPCION_PUBLICA="descripcionPublica";
        public static final String URL="url";
        public static final String NOMBRE="nombre";
        public static final String SUSCRIPTORES="suscriptores";

    }
}
