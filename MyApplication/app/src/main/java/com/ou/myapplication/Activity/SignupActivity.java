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
import com.ou.myapplication.Model.User;
import com.ou.myapplication.databinding.ActivitySignupBinding;

public class SignupActivity extends BaseActivity {
    ActivitySignupBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();
    }

    private void setVariable(){
        DatabaseReference myRef = database.getReference("User");
        binding.signupButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String email = binding.signupEditTextEmail.getText().toString();
//                String passWord = binding.signupEditTextPassword.getText().toString();
//                if(passWord.length()<6){
//                    Toast.makeText(SignupActivity.this, "your password must be6 6 characters", Toast.LENGTH_SHORT).show();
//                }else {
//                    mAuth.createUserWithEmailAndPassword(email, passWord).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                startActivity(new Intent(SignupActivity.this, MainActivity.class));
//                            } else {
//                                Toast.makeText(SignupActivity.this, "Authentication fail", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                }
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(binding.signupEditTextPhoneNumber.getText().toString()).exists()){
                            Toast.makeText(SignupActivity.this, "Phone Number already register", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            User user = new User(binding.signupEditTextUsername.getText().toString()
                                    ,binding.signupEditTextPassword.getText().toString()
                                    ,binding.signupEditTextPhoneNumber.getText().toString());
                            myRef.child(user.getPhone()).setValue(user);
                            Toast.makeText(SignupActivity.this, "Sign up successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        binding.signupButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });
    }
}