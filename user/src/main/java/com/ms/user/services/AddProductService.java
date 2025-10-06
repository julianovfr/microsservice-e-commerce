package com.ms.user.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.user.dtos.CreateProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class AddProductService {

    public ResponseEntity pedidoAddProduct(CreateProductDto createProductDto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonBody = objectMapper.writeValueAsString(createProductDto);
            System.out.println("*********** " + jsonBody);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://warehouse-java:8082/add-product"))
                    .header("Content-Type", "application/json") // 🔥 OBRIGATÓRIO!
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return ResponseEntity.ok()
                    .header("Content-Type", "application/json")
                    .body(response.body());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
