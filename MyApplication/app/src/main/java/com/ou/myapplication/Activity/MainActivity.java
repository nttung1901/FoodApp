package com.ou.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.ou.myapplication.Common.Common;
import com.ou.myapplication.Fragment.AccountFragment;
import com.ou.myapplication.Fragment.MenuFragment;
import com.ou.myapplication.Fragment.OrderFragment;
import com.ou.myapplication.R;
import com.ou.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new MenuFragment());
        getVariable();
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
    }

    private void getVariable(){
        binding.navMenu.setOnClickListener(v -> replaceFragment(new MenuFragment()));

        binding.navOrder.setOnClickListener(v -> replaceFragment(new OrderFragment()));

        binding.navAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Common.currentUser!=null){
                    replaceFragment(new AccountFragment());
                }
                else{
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                }
            }
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .commit();
    }
}