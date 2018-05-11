package com.example.rhp.remiderkuliah;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends Activity {

    ImageView menu_pemasukan, menu_pengeluaran, menu_akun, menu_hitung;
    Button btn_logout;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        menu_pemasukan = (ImageView) findViewById(R.id.menu_pemasukan);
        menu_pengeluaran = (ImageView) findViewById(R.id.menu_pengeluaran);
        menu_akun = (ImageView) findViewById(R.id.menu_akun);
        menu_hitung = (ImageView) findViewById(R.id.menu_hitung);
//        btn_logout = (Button)findViewById(R.id.btn_logout);

        menu_pengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PengeluaranActivity.class);
                startActivity(intent);
            }
        });
        menu_akun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, PenjualanPlaceholder.class);
                startActivity(intent2);
            }
        });
//        btn_logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mAuth.signOut();
//
//                finish();
//                startActivity(new Intent(MainActivity.this, LoginActivity.class));
//            }
//        });
    }
}
