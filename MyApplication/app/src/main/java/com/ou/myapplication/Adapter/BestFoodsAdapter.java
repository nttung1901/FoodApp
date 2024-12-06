package com.ou.myapplication.Adapter;

import android.content.Context;
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
import com.ou.myapplication.Model.Food;
import com.ou.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class BestFoodsAdapter extends RecyclerView.Adapter<BestFoodsAdapter.viewHolder> {
    List<Food> items;
    Context context;

    public BestFoodsAdapter(ArrayList<Food> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public BestFoodsAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_bestfood,parent,false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BestFoodsAdapter.viewHolder holder, int position) {
        holder.mTextViewTitle.setText(items.get(position).getTitle());
        holder.mTextViewStar.setText(""+items.get(position).getStar());
        holder.mTextViewTime.setText(items.get(position).getTimeValue()+"min");
        holder.mTextViewPrice.setText("$"+items.get(position).getPrice());

        Glide.with(context)
                .load(items.get(position).getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.mPic);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        private TextView mTextViewTitle;
        private TextView mTextViewStar;
        private TextView mTextViewTime;
        private TextView mTextViewPrice;
        private ImageView mPic;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.textView_title);
            mTextViewStar = itemView.findViewById(R.id.textView_star);
            mTextViewTime = itemView.findViewById(R.id.textView_time);
            mTextViewPrice = itemView.findViewById(R.id.textView_price);
            mPic = itemView.findViewById(R.id.pic);
        }
    }
}
