package com.example.mobiledev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignupActivity extends AppCompatActivity {

    // Deklarasi elemen UI
    private EditText edtUsername, edtPassword, edtCPassword;
    private Button btnPersonalInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        // Pairing XML <--> Java
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtCPassword = findViewById(R.id.edtCPassword);

        btnPersonalInfo = findViewById(R.id.btnPersonalInfo);

        btnPersonalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String confirmPassword = edtCPassword.getText().toString().trim();

                boolean isValid = true;

                // Empty username
                if (username.isEmpty()) {
                    edtUsername.setError("Username Harus Diisi");
                    isValid = false;
                }

                // Empty password
                if (password.isEmpty()) {
                    edtPassword.setError("Password Harus Diisi");
                    isValid = false;
                }

                // Empty confirm password
                if (confirmPassword.isEmpty()) {
                    edtCPassword.setError("Confirm Password Harus Diisi");
                    isValid = false;
                }

                // Password != Confirm Password
                if (!password.equals(confirmPassword) && !password.isEmpty() && !confirmPassword.isEmpty()) {
                    edtCPassword.setError("Passwords do not match");
                    Toast.makeText(SignupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    isValid = false;
                }

                if (isValid) {

                    Intent intent = new Intent(SignupActivity.this, PersonalInfoActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    startActivity(intent);
                }
            }
        });

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
