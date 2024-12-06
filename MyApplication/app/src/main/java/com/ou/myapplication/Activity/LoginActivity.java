package com.ou.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.ou.myapplication.Common.Common;
import com.ou.myapplication.Model.User;
import com.ou.myapplication.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity {
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();
    }

    private void setVariable(){
        DatabaseReference myRef = database.getReference("User");
        binding.loginButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String email = binding.loginEdittextEmail.getText().toString();
//                String password = binding.loginEditextPassword.getText().toString();
//                if(!email.isEmpty() && !password.isEmpty()){
//                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if(task.isSuccessful()){
//                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
//                                finish();
//                            }else{
//                                Toast.makeText(LoginActivity.this, "Authentication fail", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                }else{
//                    Toast.makeText(LoginActivity.this, "Please fill username and password", Toast.LENGTH_SHORT).show();
//                }
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(binding.loginEdittextPhoneNumber.getText().toString()).exists()){
                            User user = snapshot.child(binding.loginEdittextPhoneNumber.getText().toString()).getValue(User.class);
                            if(user.getPassword().equals(binding.loginEditextPassword.getText().toString())){
                                Toast.makeText(LoginActivity.this, "Sign in succesfully!", Toast.LENGTH_SHORT).show();
                                Common.currentUser = user;
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }else{
                                Toast.makeText(LoginActivity.this, "Authentication fail", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(LoginActivity.this, "User not exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        binding.loginButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });

    }
}