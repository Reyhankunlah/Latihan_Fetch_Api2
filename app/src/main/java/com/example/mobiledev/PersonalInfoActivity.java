package com.example.mobiledev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PersonalInfoActivity extends AppCompatActivity {

    private EditText edtNama, edtEmail, edtNoHP;
    private RadioButton rbMale, rbFemale;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        // Pairing XML <--> Java
        edtNama = findViewById(R.id.edtNama);
        edtEmail = findViewById(R.id.edtEmail);
        edtNoHP = findViewById(R.id.edtNoHP);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = edtNama.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String noHP = edtNoHP.getText().toString().trim();
                String gender = rbMale.isChecked() ? "Laki-Laki" :
                        rbFemale.isChecked() ? "Perempuan" : null;

                // Validasi input
                if (!rbMale.isChecked() && !rbFemale.isChecked()) {
                    Toast.makeText(PersonalInfoActivity.this, "Pilih salah satu jenis kelamin", Toast.LENGTH_SHORT).show();
                } else if (nama.isEmpty()) {
                    edtNama.setError("Nama Harus Diisi");
                } else if (email.isEmpty()) {
                    edtEmail.setError("Email Harus Diisi");
                } else if (!email.contains("@")) {
                    edtEmail.setError("Format Email Tidak Valid");
                } else if (noHP.isEmpty()) {
                    edtNoHP.setError("Nomor HP Harus Diisi");
                } else {
                    // Ambil data dari Intent
                    Intent cache = getIntent();
                    String username = cache.getStringExtra("username");
                    String password = cache.getStringExtra("password");

                    // Kirim data ke server menggunakan OkHttp
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("username", username)
                            .add("password", password)
                            .add("email", email)
                            .build();

                    Request request = new Request.Builder()
                            .url("https://wanting-motors-barely-national.trycloudflare.com/api/users")
                            .post(requestBody)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            runOnUiThread(() -> Toast.makeText(PersonalInfoActivity.this, "Failed to Sign Up: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseBody = response.body() != null ? response.body().string() : "No Response";
                            runOnUiThread(() -> {
                                Toast.makeText(PersonalInfoActivity.this, response + responseBody, Toast.LENGTH_SHORT).show();
                                if (response.isSuccessful()) {
                                    Toast.makeText(PersonalInfoActivity.this, "Sign Up Successful: " + responseBody, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(PersonalInfoActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(PersonalInfoActivity.this, "Sign Up Failed: " + responseBody, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            }
        });
    }
}
