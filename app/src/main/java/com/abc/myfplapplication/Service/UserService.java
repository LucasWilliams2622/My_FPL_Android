package com.abc.myfplapplication.Service;

import android.content.Context;
import android.content.SharedPreferences;

import com.abc.myfplapplication.Model.LoginInfo;

public class UserService {
    private Context context;

    public UserService(Context context) {
        this.context = context;
    }

    public void saveData(LoginInfo loginInfo) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", loginInfo.token);
        editor.putString("name", loginInfo.user.name);
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

    public void clearStorage() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", null);
        editor.putString("name", null);
        editor.apply();
    }
}
