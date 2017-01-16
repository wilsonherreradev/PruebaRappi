package com.rappi.prueba.pruebarappi.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * @author wilson herrera
 * @version 1.0
 * Clase para llamados HTTP y HTTPs
 */
public class LlamadasTHHP {


    /**
     * ejecuta el llamado del webservice THHPs, los parametros son la url y el json ya armado
     * @param urlText
     * @param json
     * @return
     */
    public String[] ejecutarhttp(String urlText, String json) {
        String[] respuesta=new String[2];
        HttpsURLConnection connection;
        DataOutputStream dStream;
        try {
            URL url = new URL(urlText);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + connection.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));
            final StringBuilder output = new StringBuilder("");
            respuesta[0]= String.valueOf(connection.getResponseCode());
            String line = "";
            while ((line = br.readLine()) != null) {
                output.append(line);
            }
            br.close();
            respuesta[1]=output.toString();
            return respuesta;
        } catch (Exception e) {
            respuesta[0]="700";
            respuesta[1]=e.getMessage();
            return respuesta;
        }
    }
}
