package com.ou.myapplication.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.ou.myapplication.Fragment.AccountFragment;
import com.ou.myapplication.Fragment.MenuFragment;
import com.ou.myapplication.Fragment.OrderFragment;
import com.ou.myapplication.R;
import com.ou.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new MenuFragment());
        getVariable();
    }

    private void getVariable(){
        binding.buttonMenu.setOnClickListener(v -> replaceFragment(new MenuFragment()));

        binding.buttonOrder.setOnClickListener(v -> replaceFragment(new OrderFragment()));

        binding.buttonAccount.setOnClickListener(v -> replaceFragment(new AccountFragment()));
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .commit();
    }
}