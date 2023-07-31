package com.example.myfplapplication.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfplapplication.Listeners.UserListener;
import com.example.myfplapplication.Model.User;
import com.example.myfplapplication.databinding.ItemContainerUserBinding;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserVIewHolder> {
    private final List<User> users;
    private final UserListener userListener;

    public UserAdapter(List<User> users, UserListener userListener) {
        this.users = users;
        this.userListener = userListener;

    }

    @NonNull
    @Override
    public UserVIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContainerUserBinding itemContainerUserBinding = ItemContainerUserBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new UserVIewHolder(itemContainerUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserVIewHolder holder, int position) {
        holder.setUserData(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserVIewHolder extends RecyclerView.ViewHolder {
        ItemContainerUserBinding binding;

        UserVIewHolder(ItemContainerUserBinding itemContainerUserBinding) {
            super(itemContainerUserBinding.getRoot());
            binding = itemContainerUserBinding;
        }

        void setUserData(User user) {
            binding.tvName.setText(user.name);
            binding.tvEmail.setText(user.email);
            binding.ivProfile.setImageBitmap((getUserImage(user.image)));
            binding.getRoot().setOnClickListener(v -> userListener.onUserClick(user));
        }
    }

    //this funtion for get user image on db after encoded
    private Bitmap getUserImage(String encodedImage) {
        byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
