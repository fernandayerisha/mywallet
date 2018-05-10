package com.example.rhp.remiderkuliah;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class PenjualanPlaceholder extends AppCompatActivity {

    private FirebaseAuth mAuth;

    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjualan);

        mAuth = FirebaseAuth.getInstance();

        Button buttonLogout = (Button) findViewById(R.id.buttonLogout);

        buttonLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mAuth.signOut();

                finish();
                startActivity(new Intent(PenjualanPlaceholder.this, LoginActivity.class));
            }
        });
    }

}
