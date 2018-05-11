package com.example.rhp.remiderkuliah.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rhp.remiderkuliah.Model.PengeluaranModel;
import com.example.rhp.remiderkuliah.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PengeluaranAdapter extends RecyclerView.Adapter<PengeluaranAdapter.CustomViewHolder> {

    private LayoutInflater mInflater;
    private ArrayList<PengeluaranModel> pengeluaran;
    private Context context;

    public PengeluaranAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public PengeluaranAdapter.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                                  int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                viewGroup.getContext());
        View v =
                inflater.inflate(R.layout.list_view, viewGroup, false);
        // set the view's size, margins, paddings and layout parameters
        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final PengeluaranAdapter.CustomViewHolder holder, final int position) {
        final String nama_barang = pengeluaran.get(position).getNama_barang();
        final String jml_barang = pengeluaran.get(position).getJml_barang();
        final String harga = pengeluaran.get(position).getHarga();
        final String id_barang = pengeluaran.get(position).getId_barang();
        final String lokasi_barang = pengeluaran.get(position).getLokasi();
        holder.nama_barang.setText(nama_barang);
        holder.jml_barang.setText(jml_barang);
        holder.harga.setText(harga);
        holder.id_barang.setText(id_barang);
        holder.lokasi_barang.setText(lokasi_barang);

        holder.btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pengeluaran.get(position).getId_barang()==holder.id_barang.getText().toString())
                {   pengeluaran.get(position).setNama_barang(holder.nama_barang.getText().toString());
                    pengeluaran.get(position).setId_barang(holder.id_barang.getText().toString());
                    pengeluaran.get(position).setJml_barang(holder.jml_barang.getText().toString());
                    pengeluaran.get(position).setHarga(holder.harga.getText().toString());
                    pengeluaran.get(position).setLokasi(holder.lokasi_barang.getText().toString());
                    updateitem(pengeluaran.get(position));
                }
                else{

                    deleteitem(pengeluaran.get(position).getId_barang());
                    pengeluaran.get(position).setNama_barang(holder.nama_barang.getText().toString());
                    pengeluaran.get(position).setId_barang(holder.id_barang.getText().toString());
                    pengeluaran.get(position).setJml_barang(holder.jml_barang.getText().toString());
                    pengeluaran.get(position).setHarga(holder.harga.getText().toString());
                    pengeluaran.get(position).setLokasi(holder.lokasi_barang.getText().toString());
                    updateitem(pengeluaran.get(position));

                }
                notifyDataSetChanged();
            }
        });
        holder.btnhapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteitem(pengeluaran.get(position).getId_barang());

                notifyDataSetChanged();
            }
        });

        holder.cv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                // do something here !!
                return false;
            }
        });

    }

    private void updateitem(PengeluaranModel pengeluaran) {
        //getting the specified dosen reference

        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("pengeluaran").child(pengeluaran.getId_barang());


        dR.setValue(pengeluaran);


        Toast.makeText(context, "Data telah diperbarui", Toast.LENGTH_SHORT).show();

    }

    private void deleteitem(String id) {
        //getting the specified dosen reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("pengeluaran").child(id);

        //removing data
        dR.removeValue();


        Toast.makeText(context, "Data telah dihapus", Toast.LENGTH_SHORT).show();

    }

    @Override
    public int getItemCount() {
        return pengeluaran.size();
    }

    public void addItem(ArrayList<PengeluaranModel> mData) {
        this.pengeluaran = mData;
        notifyDataSetChanged();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private EditText nama_barang, jml_barang,lokasi_barang, harga, id_barang;
        private Button btnedit, btnhapus;
        private CardView cv;

        public CustomViewHolder(View itemView) {
            super(itemView);

            nama_barang = (EditText) itemView.findViewById(R.id.nama_barang);
            jml_barang = (EditText) itemView.findViewById(R.id.jml_barang);
            harga = (EditText) itemView.findViewById(R.id.harga_barang);
            lokasi_barang = (EditText) itemView.findViewById(R.id.lokasi_barang);
            id_barang = (EditText) itemView.findViewById(R.id.id_barang);
            btnedit = (Button) itemView.findViewById(R.id.btn_edit);
            btnhapus = (Button) itemView.findViewById(R.id.btn_hapus);
            cv = (CardView) itemView.findViewById(R.id.cv);


        }

    }

}
