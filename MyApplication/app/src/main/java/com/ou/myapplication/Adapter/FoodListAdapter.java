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
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
//import com.ou.myapplication.Activity.DetailFoodActivity;
import com.ou.myapplication.Activity.DetailFoodActivity;
import com.ou.myapplication.Model.Food;
import com.ou.myapplication.R;

import java.util.ArrayList;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.viewholder>{
    ArrayList<Food> items;
    Context context;

    public FoodListAdapter(ArrayList<Food> items){
        this.items = items;
    }
    @NonNull
    @Override
    public FoodListAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_food,parent,false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodListAdapter.viewholder holder, int position) {
        holder.textViewTitle.setText(items.get(position).getTitle());
        holder.textViewTime.setText(items.get(position).getTimeValue()+"min");
        holder.textViewPrice.setText("$"+items.get(position).getPrice());
        holder.textViewStar.setText(""+items.get(position).getStar());

        Glide.with(context)
                .load(items.get(position).getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.pic);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailFoodActivity.class);
            intent.putExtra("object", items.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewTime;
        private TextView textViewPrice;
        private TextView textViewStar;

        private ImageView pic;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textView_titleListFood);
            textViewTime = itemView.findViewById(R.id.textView_timeListFood);
            textViewPrice = itemView.findViewById(R.id.textView_priceListFood);
            textViewStar = itemView.findViewById(R.id.textView_starListFood);

            pic = itemView.findViewById(R.id.pic_listFood);
        }
    }
}

