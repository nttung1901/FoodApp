package com.ou.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
//import com.ou.myapplication.Activity.ListFoodActivity;
import com.ou.myapplication.Activity.ListFoodActivity;
import com.ou.myapplication.Model.Category;
import com.ou.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewHolder> {
    List<Category> items;
    Context context;

    public CategoryAdapter(ArrayList<Category> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoryAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_category,parent,false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.viewHolder holder, int position) {
        holder.mTextViewName.setText(items.get(position).getName());

        switch (position){
            case 0:
                holder.mPic.setBackgroundResource(R.drawable.category_0_background);
                break;
            case 1:
                holder.mPic.setBackgroundResource(R.drawable.category_1_background);
                break;
            case 2:
                holder.mPic.setBackgroundResource(R.drawable.category_2_background);
                break;
            case 3:
                holder.mPic.setBackgroundResource(R.drawable.category_3_background);
                break;
            case 4:
                holder.mPic.setBackgroundResource(R.drawable.category_4_background);
                break;
            case 5:
                holder.mPic.setBackgroundResource(R.drawable.category_5_background);
                break;
            case 6:
                holder.mPic.setBackgroundResource(R.drawable.category_6_background);
                break;
            case 7:
                holder.mPic.setBackgroundResource(R.drawable.category_7_background);
                break;

        }
        int drawableResourceId = context.getResources().getIdentifier(items.get(position).getImagePath()
                ,"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.mPic);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ListFoodActivity.class);
            intent.putExtra("CategoryId",items.get(position).getId());
            intent.putExtra("CategoryName",items.get(position).getName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        private ImageView mPic;
        private TextView mTextViewName;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            mPic = itemView.findViewById(R.id.image_category);
            mTextViewName= itemView.findViewById(R.id.textView_nameCategory);
        }
    }
}
