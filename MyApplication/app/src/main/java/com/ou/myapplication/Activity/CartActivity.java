package com.ou.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ou.myapplication.Adapter.CartAdapter;
//import com.ou.myapplication.Helper.ChangeNumberItemsListener;
//import com.ou.myapplication.Helper.ManagmentCart;
import com.ou.myapplication.Database.Database;
import com.ou.myapplication.Model.Order;
import com.ou.myapplication.R;
import com.ou.myapplication.databinding.ActivityCartBinding;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends BaseActivity {
    private ActivityCartBinding binding;
    private RecyclerView.Adapter adapter;
    //private ManagmentCart managmentCart;
    private double tax;
    private Database databaseSqlite;
    List<Order> cart = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //managmentCart = new ManagmentCart(this);
        databaseSqlite = new Database(this);

        setVariable();
        //calculateCart();
        initList();
    }

    private void initList(){

        cart = databaseSqlite.getCarts();
        if(cart.isEmpty()){
            binding.textViewEmpty.setVisibility(View.VISIBLE);
            binding.scrollViewCart.setVisibility(View.GONE);
        }else{
            binding.textViewEmpty.setVisibility(View.GONE);
            binding.scrollViewCart.setVisibility(View.VISIBLE);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        binding.cartView.setLayoutManager(linearLayoutManager);
        adapter= new CartAdapter(databaseSqlite.getCarts()) ;
//        {
//            @Override
//            public void change() {
//                calculateCart();
//            }
//        };
        binding.cartView.setAdapter(adapter);
    }

//    private void calculateCart(){
//        double percentTax = 0.02;
//        double delivery =10;
//
//        tax = Math.round(managmentCart.getTotalFee()*percentTax*100.0)/100;
//
//        double total = Math.round((managmentCart.getTotalFee()+tax+delivery)*100)/100;
//        double itemTotal = Math.round(managmentCart.getTotalFee()*100)/100;
//
//        binding.textViewSubtotal.setText("$"+itemTotal);
//        binding.textViewTotalTax.setText("$"+tax);
//        binding.textViewDeliveryPrice.setText("$"+delivery);
//        binding.textViewTotalCart.setText("$"+total);
//    }

    private void setVariable(){
        binding.buttonBackCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}