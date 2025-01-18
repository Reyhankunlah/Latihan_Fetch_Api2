package com.example.mobiledev;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class KalkulatorActivity extends AppCompatActivity {

    private TextInputEditText edtA1, edtA2;
    private AppCompatButton btnPlus, btnMinus, btnKali, btnBagi;
    private TextView txtResult;
    private Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_kalkulator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Deklarasi elemen
        edtA1 = findViewById(R.id.edtA1);
        edtA2 = findViewById(R.id.edtA2);

        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnKali = findViewById(R.id.btnKali);
        btnBagi = findViewById(R.id.btnBagi);

        txtResult = findViewById(R.id.txtResult);
        btnReset = findViewById(R.id.btnReset);

        int grey = ContextCompat.getColor(this, R.color.grey);

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInputs()) {
                    int a1 = Integer.parseInt(edtA1.getText().toString());
                    int a2 = Integer.parseInt(edtA2.getText().toString());
                    double result = a1 + a2;

                    txtResult.setText(String.valueOf(result));
                    txtResult.setTextColor(Color.BLACK);
                }
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInputs()) {
                    int a1 = Integer.parseInt(edtA1.getText().toString());
                    int a2 = Integer.parseInt(edtA2.getText().toString());
                    double result = a1 - a2;

                    txtResult.setText(String.valueOf(result));
                    txtResult.setTextColor(Color.BLACK);
                }
            }
        });

        btnKali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInputs()) {
                    int a1 = Integer.parseInt(edtA1.getText().toString());
                    int a2 = Integer.parseInt(edtA2.getText().toString());
                    double result = a1 * a2;

                    txtResult.setText(String.valueOf(result));
                    txtResult.setTextColor(Color.BLACK);
                }
            }
        });

        btnBagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInputs()) {
                    int a1 = Integer.parseInt(edtA1.getText().toString());
                    int a2 = Integer.parseInt(edtA2.getText().toString());

                    if (a2 == 0) {
                        Toast.makeText(KalkulatorActivity.this, "Tidak bisa membagi dengan nol!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    double result = a1 / a2;

                    txtResult.setText(String.valueOf(result));
                    txtResult.setTextColor(Color.BLACK);
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtA1.setText("");
                edtA2.setText("");
                txtResult.setText("Result");
                txtResult.setTextColor(grey);
            }
        });
    }
    
    // Fungsi untuk validasi input
    private boolean validateInputs() {
        String inputA1 = edtA1.getText().toString().trim();
        String inputA2 = edtA2.getText().toString().trim();

        if (inputA1.isEmpty() || inputA2.isEmpty()) {
            Toast.makeText(this, "Input tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            Integer.parseInt(inputA1);
            Integer.parseInt(inputA2);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Input harus berupa angka!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
