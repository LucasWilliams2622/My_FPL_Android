package com.example.myfplapplication.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myfplapplication.Model.LoginInfo;
import com.example.myfplapplication.R;
import com.example.myfplapplication.Service.APIService;
import com.example.myfplapplication.Service.UserService;
import com.example.myfplapplication.Utilities.Constants;
import com.example.myfplapplication.Utilities.PreferenceManager;
import com.example.myfplapplication.databinding.ActivityLoginBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners() {
        binding.loginBtn.setOnClickListener(v ->
        {
            if (isValidSignUpDetails()) {
                signIn();
            }
        });


//        binding.loginBtn.setOnClickListener(v ->addDataToFireStore());
    }

    private void signIn() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.base_link)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);

        Call<LoginInfo> response = apiService.Login(binding.email.getText().toString(), binding.password.getText().toString());
        response.enqueue(new Callback<LoginInfo>() {
            @Override
            public void onResponse(Call<LoginInfo> call, Response<LoginInfo> response) {
                LoginInfo loginInfo = response.body();
                UserService userService = new UserService(LoginActivity.this);
                userService.saveData(loginInfo);
                Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
            @Override
            public void onFailure(Call<LoginInfo> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }
        });

////        loading(true);
//        FirebaseFirestore databse = FirebaseFirestore.getInstance();
//        databse.collection(Constants.KEY_COLLECTION_USERS)
//                .whereEqualTo(Constants.KEY_EMAIL, binding.email.getText().toString().trim())
//                .whereEqualTo(Constants.KEY_PASSWORD, binding.password.getText().toString().trim())
//                .get()
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful() && task.getResult() != null && task.getResult().getDocuments().size() > 0) {
//                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
//                        preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true);
//                        preferenceManager.putString(Constants.KEY_USER_ID, documentSnapshot.getId());
//                        preferenceManager.putString(Constants.KEY_PHONE_NUMBER, documentSnapshot.getString(Constants.KEY_PHONE_NUMBER));
//
//                        preferenceManager.putString(Constants.KEY_EMAIL, documentSnapshot.getString(Constants.KEY_EMAIL));
//                        preferenceManager.putString(Constants.KEY_NAME, documentSnapshot.getString(Constants.KEY_NAME));
//                        preferenceManager.putString(Constants.KEY_IMAGE, documentSnapshot.getString(Constants.KEY_IMAGE));
//
//                        showToast("Đăng nhập thành công!");
//                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(intent);
//
//                    } else {
////                        loading(false);
//                        showToast("Please check your email and password");
//                    }
//                });


    }

    private void showToast(String message) {
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }

    private Boolean isValidSignUpDetails() {
        if (binding.email.getText().toString().trim().isEmpty()) {
            showToast("Hãy nhập email của bạn!");
            return false;
        }
//        else if (!Patterns.EMAIL_ADDRESS.matcher(binding.email.getText().toString()).matches()) {
//
//            showToast("Hãy nhập email đúng định dạng!");
//            return false;
//        }
        else if (binding.password.getText().toString().trim().isEmpty()) {
            showToast("Hãy nhập password!");
            return false;
        } else {
            return true;
        }
    }
//    private void addDataToFireStore() {
//        FirebaseFirestore database = FirebaseFirestore.getInstance();
//        HashMap<String, Object> data = new HashMap<>();
//
//        data.put("first_name", "Lucas");
//        data.put("last_name", "Williams");
//        database.collection("users")
//                .add(data)
//                .addOnSuccessListener(documentReference -> {
//                    Toast.makeText(this, "Data inserted", Toast.LENGTH_SHORT).show();
//                })
//                .addOnFailureListener(exception -> {
//                    Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
//                });
//    }
}