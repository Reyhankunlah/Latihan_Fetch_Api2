package com.example.mobiledev;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpClientHelper {

    private final OkHttpClient client = new OkHttpClient();
    private final String BASE_URL = "https://wanting-motors-barely-national.trycloudflare.com/";

    // Method untuk Login
    public void login(String username, String password, Callback callback) {
        String url = BASE_URL + "/login"; // Endpoint login
        String json = "{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }";

        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(callback);
    }

    // Method untuk Signup
    public void signup(String username, String password, String email, Callback callback) {
        String url = BASE_URL; // Endpoint signup
        String json = "{ \"username\": \"" + username + "\", \"password\": \"" + password + "\", \"email\": \"" + email + "\" }";

        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(callback);
    }
}
