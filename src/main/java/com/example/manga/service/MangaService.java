package com.example.manga.service;

import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class MangaService {
    public String getManga(String description) {
        String apiUrl = "https://api.openai.com/v1/chat/completions";
        String payload = String.format("{\"model\":\"gpt-3.5-turbo\",\"messages\":[{\"role\":\"user\",\"content\":\"Write a manga poem based on the description of the following character: %s\"}]}", description);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS) // Set the connection timeout
                .readTimeout(30, TimeUnit.SECONDS) // Set the read timeout
                .build();

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, payload);

        Request request = new Request.Builder()
                .url(apiUrl)
                .post(body)
                .addHeader("Content-Type", "application/json; utf-8")
                .addHeader("Authorization", "Bearer API_KEY")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"error\": \"Something went wrong\"}";
        }
    }
}
