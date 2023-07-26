package com.example.myfplapplication.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myfplapplication.Model.LoginInfo;
import com.example.myfplapplication.R;
import com.example.myfplapplication.Service.APIService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private LinearLayout loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(APIService.base_link)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                APIService apiService = retrofit.create(APIService.class);


                Call<LoginInfo> response = apiService.Login(username.getText().toString(), password.getText().toString());;
                response.enqueue(new Callback<LoginInfo>() {
                    @Override
                    public void onResponse(Call<LoginInfo> call, Response<LoginInfo> response) {
                        LoginInfo loginInfo = response.body();


                        Toast.makeText(LoginActivity.this, "" + loginInfo.token, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<LoginInfo> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Không thành công", Toast.LENGTH_SHORT).show();
                    }
                });
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
            }
        });

    }
}