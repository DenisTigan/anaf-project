package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

@Service
public class AnafApiService {

    private static final String API_URL = "https://webservicesp.anaf.ro/api/PlatitorTvaRest/v9/tva";

    public String getCompanyInfo(Long cui) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String today = LocalDate.now().toString();
            String requestBody = String.format("""
                [
                  {
                    "cui": %s,
                    "data": "%s"
                  }
                ]
                """, cui, today);


            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
