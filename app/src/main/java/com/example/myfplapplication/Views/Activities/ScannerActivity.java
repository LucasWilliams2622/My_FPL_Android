package com.example.myfplapplication.Views.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfplapplication.Model.DiemDanh;
import com.example.myfplapplication.Model.LoginInfo;
import com.example.myfplapplication.R;
import com.example.myfplapplication.Service.APIService;
import com.example.myfplapplication.Service.UserService;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScannerActivity extends AppCompatActivity {

    Button scan_btn, btn_back;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        scan_btn = findViewById(R.id.scanner);
        textView = findViewById(R.id.textScanner);
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScannerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(ScannerActivity.this);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setPrompt("Scan  a QR Code");
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            String contents = intentResult.getContents();
            if (contents != null) {
                Log.d("TAGDiemdanh", "Diem danh " + contents);
                String idLichHoc = contents.substring(contents.lastIndexOf("/") + 1);
                Log.d("TAGDiemdanh", "Diem danh " + idLichHoc);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(APIService.base_link)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                UserService userService = new UserService(getBaseContext());
                APIService apiService = retrofit.create(APIService.class);
                Log.d("TAGDiemdanh", "Diem danh 1232131" + apiService);

                Call<DiemDanh> response = apiService.DiemDanh(Integer.parseInt(idLichHoc), userService.getToken());
                Log.d("TAGDiemdanh", "Diem danh " + response);

                Toast.makeText(this, "response", Toast.LENGTH_SHORT).show();
                response.enqueue(new Callback<DiemDanh>() {
                    @Override
                    public void onResponse(Call<DiemDanh> call, Response<DiemDanh> response) {
                        Log.d("TAGDiemdanh", "Diem danh thanh cong");
                        Toast.makeText(ScannerActivity.this, "Điểm danh thành công", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<DiemDanh> call, Throwable t) {
                        Log.d("TAGDiemdanh", "Diem danh that bai");
                        Toast.makeText(getApplicationContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}