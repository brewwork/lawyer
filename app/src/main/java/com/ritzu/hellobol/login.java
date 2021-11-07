package com.ritzu.hellobol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.internal.$Gson$Preconditions;

public class login extends AppCompatActivity {
    Button signUpButton;
    Button loginButton;
    FirebaseAuth fireAuth;

    private View.OnClickListener mSignpButtonListener = new View.OnClickListener() {
        public void onClick(View v) {
            Log.d("OnClickListener = ", "SignUp Button clicked");
            Intent signUpScreen = new Intent(login.this,signup.class);
            startActivity(signUpScreen);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        try {
            signUpButton = (Button) findViewById(R.id.SignUp);
            signUpButton.setOnClickListener(mSignpButtonListener);
            Log.d("OnClickListener = ", "HelloWorld");


            loginButton = (Button)findViewById(R.id.login);
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText userNameEditText,passwordEditText;
                    userNameEditText = (EditText)findViewById(R.id.email);
                    passwordEditText = (EditText)findViewById(R.id.password);
                    String username,password;
                    username = userNameEditText.getText().toString();
                    password = passwordEditText.getText().toString();

                    if(username == null || password == null){
                        Toast.makeText(login.this,"Please fill both username and Password",Toast.LENGTH_LONG);
                    }else{
                        //connect with Firebase Database
                       /* fireAuth.createUserWithEmailAndPassword(username,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {

                            }
                         */

                        fireAuth.signInWithEmailAndPassword(username,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Log.d("LegalFind","Login results " + authResult);
                                //Toast.makeText(login.this,"Logged in successfully",Toast.LENGTH_LONG);
                                Intent dashBoard = new Intent(login.this,Dashboard.class);
                                startActivity(dashBoard);
                            }

                        });
                    }
                }
            });


        }catch(Exception e){
            //System.out.println("Error"+e);
            Log.d("Error = ", String.valueOf(e));
        }


    }


}