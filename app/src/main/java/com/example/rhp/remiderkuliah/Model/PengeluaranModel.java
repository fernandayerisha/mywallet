package com.example.rhp.remiderkuliah.Model;

public class PengeluaranModel {
    private String nama_barang;
    private String id_barang;
    private String jml_barang;
    private String harga;

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    private String lokasi;


    public String getId_barang() {
        return id_barang;
    }

    public void setId_barang(String id_barang) {
        this.id_barang = id_barang;
    }

    public PengeluaranModel(String id_barang,String nama_barang, String jml_barang, String harga, String lokasi) {
        this.id_barang = id_barang;
        this.nama_barang = nama_barang;
        this.jml_barang = jml_barang;
        this.harga = harga;
        this.lokasi = lokasi;
    }

    public PengeluaranModel() {

    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getJml_barang() {
        return jml_barang;
    }

    public void setJml_barang(String jml_barang) {
        this.jml_barang = jml_barang;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
