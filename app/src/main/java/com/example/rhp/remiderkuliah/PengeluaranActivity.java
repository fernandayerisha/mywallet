package com.example.rhp.remiderkuliah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rhp.remiderkuliah.Adapter.PengeluaranAdapter;
import com.example.rhp.remiderkuliah.Model.PengeluaranModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PengeluaranActivity extends AppCompatActivity {

    //firebase auth object
    private FirebaseAuth firebaseAuth;
    //firebase database reference object
    DatabaseReference databasePengeluaran;

    private EditText nama_barang, jml_barang, lokasi_barang, et_harga, id_barang;
    private Button add_pengeluaran;
    private RecyclerView recyclerView;

    private PengeluaranAdapter pengeluaranAdapter;
    private ArrayList<PengeluaranModel> pengeluaranList = new ArrayList<PengeluaranModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengeluaran);

        firebaseAuth = FirebaseAuth.getInstance();

        //getting the reference of Dosen node
        databasePengeluaran = FirebaseDatabase.getInstance().getReference("pengeluaran");

        //if the user is not logged in
        //that means current user will return null
        if(firebaseAuth.getCurrentUser() == null){
            startActivity(new Intent(this, LoginActivity.class));
            finish();

        }


        nama_barang = (EditText)findViewById(R.id.et_nama_barang);
        jml_barang = (EditText)findViewById(R.id.et_jml_barang);
        et_harga = (EditText)findViewById(R.id.et_harga);
        id_barang = (EditText)findViewById(R.id.et_id_barang);
        lokasi_barang = (EditText)findViewById(R.id.et_lokasi);
        add_pengeluaran = findViewById(R.id.btn_add_pengeluaran);

        recyclerView = (RecyclerView) findViewById(R.id.rv_pengeluaran);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        pengeluaranAdapter = new PengeluaranAdapter(this);

        add_pengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPengeluaran();
            }
        });

        databasePengeluaran.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous dosen list
                pengeluaranList.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting dosen
                    PengeluaranModel pengeluaran = postSnapshot.getValue(PengeluaranModel.class);
                    //adding dosen to the list
                    pengeluaranList.add(pengeluaran);
                }

                //creating adapter
                pengeluaranAdapter.addItem(pengeluaranList);

                //attaching adapter to the recyclerview
                recyclerView.setAdapter(pengeluaranAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void addPengeluaran() {
        //getting the values to save
        String id = id_barang.getText().toString();
        String nama = nama_barang.getText().toString();
        String harga = et_harga.getText().toString();
        String jumlah = jml_barang.getText().toString();
        String lokasi = lokasi_barang.getText().toString();

        //checking if the value is provided
        if (!TextUtils.isEmpty(nama)&&!TextUtils.isEmpty(id)&&!TextUtils.isEmpty(harga)&&!TextUtils.isEmpty(jumlah)) {

            PengeluaranModel pengeluaran = new PengeluaranModel(id, nama, jumlah, harga, lokasi);

            databasePengeluaran.child(id).setValue(pengeluaran);

            //setting edittext to blank again
            id_barang.setText("");
            nama_barang.setText("");
            et_harga.setText("");
            jml_barang.setText("");
            lokasi_barang.setText("");


            //displaying a success toast
            Toast.makeText(this, "Data berhasil ditambahkan", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Mohon semua field diisi!", Toast.LENGTH_LONG).show();
        }
    }

}
