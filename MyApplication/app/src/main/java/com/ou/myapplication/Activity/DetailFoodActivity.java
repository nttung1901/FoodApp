package com.ou.myapplication.Activity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.ou.myapplication.Model.Food;
//import com.ou.myapplication.Helper.ManagmentCart;
import com.ou.myapplication.databinding.ActivityDetailFoodBinding;

public class DetailFoodActivity extends BaseActivity {
    ActivityDetailFoodBinding binding;
    private Food object;
    private int num =1;
//    private ManagmentCart managmentCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        setVariable();
    }

    private void setVariable(){
//        managmentCart=new ManagmentCart(this);

        binding.buttonBackDetailFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Glide.with(DetailFoodActivity.this)
                .load(object.getImagePath())
                .into(binding.picDetailFood);

        binding.textViewPriceDetailFood.setText("$"+object.getPrice());
        binding.textViewStarDetailFood.setText(object.getStar()+"Rating");
        binding.textViewTimeDetailFood.setText(object.getTimeValue()+" min");
        binding.textViewDescription.setText(object.getDescription());
        binding.ratingBar.setRating((float) object.getStar());
        binding.textViewTitleDetailFood.setText(object.getTitle());
        binding.textViewNumber.setText(num+"");
        binding.textViewPriceTotal.setText("$"+num*object.getPrice());

        binding.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                binding.textViewNumber.setText(num+" ");
                binding.textViewPriceTotal.setText("$"+(num*object.getPrice()));
            }
        });
        binding.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num>1){
                    num--;
                    binding.textViewNumber.setText(num+"");
                    binding.textViewPriceTotal.setText("$"+num*object.getPrice());
                }
            }
        });

//        binding.buttonAddCartDetailFood.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                object.setNumberInCart(num);
//                managmentCart.insertFood(object);
//            }
//        });
    }

    private void getIntentExtra(){
        object = (Food) getIntent().getSerializableExtra("object");
    }
}