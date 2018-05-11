package com.example.rhp.remiderkuliah;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignup;
    private TextView textViewSignin;
    private ProgressDialog progressDialog;


    private String email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignup = (Button) findViewById(R.id.buttonSignup);
        textViewSignin = (TextView) findViewById(R.id.textViewSignin);

        progressDialog = new ProgressDialog(RegisterActivity.this);

        //initializing firebase auth object
        mAuth = FirebaseAuth.getInstance();

        //if getCurrentUser does not returns null
        if (mAuth.getCurrentUser() != null) {
            // user is already logged in
            // open profile activity
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            //close this activity
            finish();
        }

        //attaching listener to button
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = editTextEmail.getText().toString();
                password = editTextPassword.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(RegisterActivity.this, "Please enter Email", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "Please enter password", Toast.LENGTH_LONG).show();
                } else if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(email, password);
                }
            }
        });

        textViewSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

    }

    private void registerUser(String email, String password) {

        //getting email and password from edit texts
        // String email = editTextEmail.getText().toString().trim();
        // String password  = editTextPassword.getText().toString().trim();

        //checking if email and passwords are empty

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //creating a new user
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //checking if success
                if (task.isSuccessful()) {
                    //display some message here
                    Toast.makeText(RegisterActivity.this, "Successfully registered", Toast.LENGTH_LONG).show();
                } else {
                    //display some message here
                    Toast.makeText(RegisterActivity.this, "Registration Error", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }



}
