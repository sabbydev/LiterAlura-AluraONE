package com.example.literalura.servicio;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteHTTP {
    private static final HttpClient client = HttpClient.newHttpClient();

    public static String obtenerRespuesta(String url) {
        HttpRequest solicitud = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> respuesta;
        try {
            respuesta = client.send(solicitud, HttpResponse.BodyHandlers.ofString());
            return respuesta.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
