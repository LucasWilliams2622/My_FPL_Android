package com.example.myfplapplication.Views.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


import com.example.myfplapplication.Utilities.Constants;
import com.example.myfplapplication.Utilities.PreferenceManager;
import com.example.myfplapplication.databinding.ActivitySignUpBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    private String encodedImage;
    private PreferenceManager preferenceManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferenceManager = new PreferenceManager(getApplicationContext());
        setListeners();
    }

    private String encodeImage(Bitmap bitmap) {
        int previewWith = 150;
        int previewHeight = bitmap.getHeight() * previewWith / bitmap.getWidth();
        Bitmap previewBitmap = Bitmap.createScaledBitmap(bitmap, previewWith, previewHeight, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private void setListeners() {
        binding.tvGoSingIn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), LoginActivity.class)));

        binding.btnSignUp.setOnClickListener(v ->
        {
            if (isValidSignUpDetails()) {
                signUp();
            }
        });
        binding.layoutImage.setOnClickListener(v ->
        {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            pickImage.launch(intent);
        });

    }

    private void showToast(String message) {
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }

    private void signUp() {
        loading(true);
        FirebaseFirestore databse = FirebaseFirestore.getInstance();
        HashMap<String, Object> user = new HashMap<>();
        user.put(Constants.KEY_NAME, binding.edtName.getText().toString());
        user.put(Constants.KEY_EMAIL, binding.edtEmail.getText().toString());
        user.put(Constants.KEY_PASSWORD, binding.edtPassword.getText().toString());
        user.put(Constants.KEY_PHONE_NUMBER, "");
        user.put(Constants.KEY_IMAGE, encodedImage);
        databse.collection(Constants.KEY_COLLECTION_USERS)
                .add(user)
                .addOnSuccessListener(documentReference -> {
                    loading(false);
                    preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true);
                    preferenceManager.putString(Constants.KEY_USER_ID, documentReference.getId());
                    preferenceManager.putString(Constants.KEY_PHONE_NUMBER, "");
                    preferenceManager.putString(Constants.KEY_EMAIL, binding.edtEmail.getText().toString().trim());
                    preferenceManager.putString(Constants.KEY_NAME, binding.edtName.getText().toString().trim());
                    preferenceManager.putString(Constants.KEY_IMAGE, encodedImage);

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                })
                .addOnFailureListener(exception -> {
                    loading(false);
                    showToast(exception.getMessage());
                });

    }

    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result ->
            {
                if (result.getResultCode() == RESULT_OK) {
                    if (result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        try {
                            InputStream inputStream = getContentResolver().openInputStream(imageUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            binding.tvAddImage.setVisibility(View.GONE);
                            encodedImage = encodeImage(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    private Boolean isValidSignUpDetails() {

       if (binding.edtName.getText().toString().trim().isEmpty()) {

            showToast("Please enter username");
            return false;
        } else if (binding.edtEmail.getText().toString().trim().isEmpty()) {
            showToast("Please enter username");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.edtEmail.getText().toString()).matches()) {

            showToast("Please enter valid email");
            return false;
        } else if (binding.edtPassword.getText().toString().trim().isEmpty()) {
            showToast("Please enter password");
            return false;
        } else if (binding.edtConfirmPassword.getText().toString().trim().isEmpty()) {
            showToast("Please confirm your password");
            return false;
        } else if (!binding.edtPassword.getText().toString().trim().equals(binding.edtConfirmPassword.getText().toString().trim())) {
            showToast("Password and confirm password must be same");
            return false;
        } else {
            return true;
        }
    }

    private void loading(Boolean isLoading) {
        if (isLoading) {
            binding.processBarSignUp.setVisibility(View.VISIBLE);
            binding.btnSignUp.setVisibility(View.INVISIBLE);

        } else {
            binding.processBarSignUp.setVisibility(View.INVISIBLE);
            binding.btnSignUp.setVisibility(View.VISIBLE);
        }
    }


}