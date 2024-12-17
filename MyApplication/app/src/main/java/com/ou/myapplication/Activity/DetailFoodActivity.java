package com.ou.myapplication.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ou.myapplication.Database.Database;
import com.ou.myapplication.Model.Food;
//import com.ou.myapplication.Helper.ManagmentCart;
import com.ou.myapplication.Model.Order;
import com.ou.myapplication.databinding.ActivityDetailFoodBinding;

public class DetailFoodActivity extends BaseActivity {
    ActivityDetailFoodBinding binding;
    private Food currentFood;
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
                .load(currentFood.getImagePath())
                .into(binding.picDetailFood);

        binding.textViewPriceDetailFood.setText("$"+currentFood.getPrice());
        binding.textViewStarDetailFood.setText(currentFood.getStar()+"Rating");
        binding.textViewTimeDetailFood.setText(currentFood.getTimeValue()+" min");
        binding.textViewDescription.setText(currentFood.getDescription());
        binding.ratingBar.setRating((float) currentFood.getStar());
        binding.textViewTitleDetailFood.setText(currentFood.getTitle());
        binding.textViewNumber.setText(num+"");
        binding.textViewPriceTotal.setText("$"+num*currentFood.getPrice());

        binding.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                binding.textViewNumber.setText(num+" ");
                binding.textViewPriceTotal.setText("$"+(num*currentFood.getPrice()));
            }
        });
        binding.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num>1){
                    num--;
                    binding.textViewNumber.setText(num+"");
                    binding.textViewPriceTotal.setText("$"+num*currentFood.getPrice());
                }
            }
        });

        binding.buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToCart(new Order(
                       currentFood.getId(),
                        currentFood.getTitle(),
                        num,
                        currentFood.getPrice(),
                        currentFood.getDiscount(),
                        currentFood.getImagePath()
                ));
                Toast.makeText(DetailFoodActivity.this, "Added to Cart", Toast.LENGTH_SHORT).show();
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
        currentFood = (Food) getIntent().getSerializableExtra("object");
    }
}