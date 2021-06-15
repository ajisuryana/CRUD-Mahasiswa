package com.example.crudmahasiswa;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    //sesuaikan dengan IP pada Komputer Anda,
    //disini saya menggunakan ngrok agar dapat di run pada handphone
    public static final String URL = " http://530e057330aa.ngrok.io/";
    private EditText editTextnim;
    private EditText editTextnama;
    private EditText editTexttelepon;
    private EditText editTextprodi;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextnim = (EditText) findViewById(R.id.editTextnim);
        editTextnama = (EditText) findViewById(R.id.editTextnama);
        editTexttelepon = (EditText) findViewById(R.id.editTexttelepon);
        editTextprodi = (EditText) findViewById(R.id.editTextprodi);
        btnAdd = findViewById(R.id.buttonAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nim = editTextnim.getText().toString();
                String nama = editTextnama.getText().toString();
                String tlp = editTexttelepon.getText().toString();
                String prodi = editTextprodi.getText().toString();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                RegisterAPI api = retrofit.create(RegisterAPI.class);
                Call<Value> call = api.daftar(nim, nama, tlp, prodi);
                call.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
                        String value = response.body().getValue();
                        String message = response.body().getMessage();
                        if (value.equals("1")) {
                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Jaringan Error!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}