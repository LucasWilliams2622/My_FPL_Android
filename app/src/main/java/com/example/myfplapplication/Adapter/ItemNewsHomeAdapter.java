package com.example.myfplapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfplapplication.Model.ItemNewsHome;
import com.example.myfplapplication.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemNewsHomeAdapter extends RecyclerView.Adapter<ItemNewsHomeAdapter.ItemViewHolder> {
    private List<ItemNewsHome> itemList;

    public ItemNewsHomeAdapter(List<ItemNewsHome> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_home, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ItemNewsHome item = itemList.get(position);
        holder.itemTitle.setText(item.getTitle());
        holder.itemContent.setText(item.getContent());
        holder.itemImage.setImageResource(item.getImageResId());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        TextView itemContent;
        ImageView itemImage;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemContent = itemView.findViewById(R.id.item_content);
            itemImage = itemView.findViewById(R.id.item_image);
        }
    }

}
