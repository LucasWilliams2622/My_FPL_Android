package com.example.myfplapplication.Service;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myfplapplication.Model.LoginInfo;

public class UserService {
    private Context context;

    public UserService(Context context) {
        this.context = context;
    }

    public void saveData(LoginInfo loginInfo) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", loginInfo.token);
        editor.putString("id", loginInfo.user.id);
        editor.putString("phone_number", loginInfo.user.phone_number);
        editor.putString("email", loginInfo.user.email);


        editor.apply();
    }

    public String getToken() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        return !token.isEmpty() ? "Bearer " + token : null;
    }

    public String getName() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        return sharedPreferences.getString("name", "");
    }

    public String getId() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        return sharedPreferences.getString("id", "");
    }
    public String getEmail() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        return sharedPreferences.getString("email", "");
    }
    public void clearStorage() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", null);
        editor.putString("name", null);
        editor.apply();
    }
}
