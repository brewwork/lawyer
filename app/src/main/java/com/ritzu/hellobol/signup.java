package com.ritzu.hellobol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class signup extends AppCompatActivity {
    FirebaseAuth fireAuth;
    FirebaseFirestore database;
    Button signUp;
    EditText name,emailId,passwordId,phoneNumberId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        fireAuth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();
        name = (EditText)findViewById(R.id.nameTextbox);
        emailId = (EditText)findViewById(R.id.email2);
        passwordId = (EditText)findViewById(R.id.password2);
        phoneNumberId = (EditText)findViewById(R.id.phonenumber);

        signUp = (Button)findViewById(R.id.SignUpButton);

        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
        try {
            String email, username, password, phoneNumber;
            email = emailId.getText().toString();
            password = passwordId.getText().toString();
            username = name.getText().toString();
            phoneNumber = phoneNumberId.getText().toString();
            user userobj = new user();
            userobj.setEmail(email);
            userobj.setName(username);
            userobj.setPhonenumber(phoneNumber);
            userobj.setPassword(password);

            Log.d("LegalFind", "onClick for signup button");
            fireAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Log.d("LegalFind", "onComplete Auth"+task);
                    if (task.isSuccessful()) {
                        Log.d("LegalFind", "onComplete Auth"+task);
                        database.collection("UsersData")
                                .document("legal").set(userobj).addOnSuccessListener(
                                new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        startActivity(new Intent(signup.this, login.class));
                                    }
                                }
                        );
                        Log.d("LegalFind", "SignUp Success");
                        Toast.makeText(signup.this, "SignedUp Successfully", Toast.LENGTH_LONG);
                    } else {
                        Log.d("LegalFind", "SignUp failed");
                        Toast.makeText(signup.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG);
                    }
                }
            });
        }catch(Exception e){
            Toast.makeText(signup.this, e.getMessage()  , Toast.LENGTH_LONG);
        }

            }
        });

    }
}