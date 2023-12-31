package com.example.campuslink;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import com.example.campuslink.Models.User;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText textEmail,textPassword,textName;
    ProgressBar progressBar;
    DatabaseReference reference;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textEmail = (TextInputEditText)findViewById(R.id.email_ed_register);
        textPassword = (TextInputEditText)findViewById(R.id.password_ed_register);
        progressBar = (ProgressBar)findViewById(R.id.progressBarRegister);
        textName = (TextInputEditText)findViewById(R.id.name_ed_register);
        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Users");

    }

    public void RegisterUser(View v){
 progressBar.setVisibility(View.VISIBLE);
 final String email = textEmail.getText().toString();
 final String password = textPassword.getText().toString();
 final String name =  textName.getText().toString();

 if (!email.equals("") && !password.equals("") && password.length()>6){
     auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
         @Override
         public void onComplete(@NonNull Task<AuthResult> task){
             if(task.isSuccessful()){
                 // insert values in database
                 FirebaseUser firebaseUser = auth.getCurrentUser();
                 User u = new User();
                 u.setName(name);
                 u.getEmail(email);
                 reference.child(firebaseUser.getUid()).setValue(u).addOnCompleteListener(new OnCompleteListener<Void>{
                     @Override
                             public void onComplete(@NonNull Task <Void>task){
                     if(task.isSuccessful()){
                         Toast.makeText(getApplicationContext(),"User Registered Successfully", Toast.LENGTH_SHORT).show();
                         progressBar.setVisibility(View.GONE);
                         finish();
                         Intent i = new Intent(RegisterActivity.this,GroupChatActivity.class);
                         startActivity(i);
                     }
                     else{
                         progressBar.setVisibility(View.GONE);
                         Toast.makeText(getApplicationContext(), "User could not be created", Toast.LENGTH_SHORT).show();
                     }
                     }
                 });
             }
         }
     });
 }
    }

    public void gotoLogin(View v){
        Intent i = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(i);
    }
}