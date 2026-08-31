package ru.kireev.moon.back;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class BackClientRunner {
    public static void main(String[] args) throws IOException, InterruptedException {
        try (HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();) {

            HttpRequest request = HttpRequest.newBuilder(URI.create("http://localhost:8080/profile?id=1"))
                    .setHeader("My-Token", "nnnnssss")
                    .GET()
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            Map<String, List<String>> map = httpResponse.headers().map();


            System.out.println(httpResponse.statusCode());
            System.out.println();
            System.out.println(map);
            System.out.println(httpResponse.body());
        }
    }
}