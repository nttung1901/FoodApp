package com.ou.myapplication.Adapter;

import android.content.Context;
import android.content.pm.LabeledIntent;
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
//import com.ou.myapplication.ChangeNumberItemsListener;
//import com.ou.myapplication.Helper.ManagmentCart;
import com.ou.myapplication.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.viewholder> {
    ArrayList<Food> list;
//    private ManagmentCart managmentCart;
//    ChangeNumberItemsListener changeNumberItemsListener;

//    public CartAdapter(ArrayList<Foods> list, Context context, ChangeNumberItemsListener changeNumberItemsListener){
//        this.list = list;
//        managmentCart = new ManagmentCart(context);
//        this.changeNumberItemsListener = changeNumberItemsListener;
//    }

    @NonNull
    @Override
    public CartAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cart,parent,false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.viewholder holder, int position) {
        holder.textViewTitle.setText(list.get(position).getTitle());
        holder.textViewFeeEachItem.setText("$"+list.get(position).getPrice());
        holder.textViewTotalEachItem.setText("$"+list.get(position).getNumberInCart()*list.get(position).getPrice());
        holder.textViewNum.setText(list.get(position).getNumberInCart()+"");

        Glide.with(holder.itemView.getContext())
                .load(list.get(position).getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.mPicFoodCart);

//        holder.buttonPlus.setOnClickListener(v -> managmentCart.plusNumberItem(list, position, () -> {
//            notifyDataSetChanged();
//            changeNumberItemsListener.change();
//        }));
//
//        holder.buttonMinus.setOnClickListener(v -> {
//            managmentCart.minusNumberItem(list, position, () -> {
//                notifyDataSetChanged();
//                changeNumberItemsListener.change();
//            });
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView textViewTitle;
        TextView textViewTotalEachItem;
        TextView textViewNum;
        TextView buttonPlus;
        TextView buttonMinus;
        TextView textViewFeeEachItem;
        ImageView mPicFoodCart;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textView_tileFoodCart);
            textViewTotalEachItem = itemView.findViewById(R.id.textView_totalEachFoods);
            textViewNum = itemView.findViewById(R.id.textView_numberEachFoods);
            buttonPlus = itemView.findViewById(R.id.button_plusCart);
            buttonMinus = itemView.findViewById(R.id.button_minusCart);
            textViewFeeEachItem = itemView.findViewById(R.id.textView_feeEachItem);
            mPicFoodCart = itemView.findViewById(R.id.pic_FoodCart);
        }
    }
}