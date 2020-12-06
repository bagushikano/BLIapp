package com.progmoblanjutklp1.appmemobelanja.model;

public class Item {
    private int idBenda;
    private int jumlah;
    private String keterangan;
    private int idBelanjaan;

    public Item(int idBenda, int jumlah, String keterangan, int idBelanjaan) {
        this.idBelanjaan = idBelanjaan;
        this.idBenda = idBenda;
        this.jumlah = jumlah;
        this.keterangan = keterangan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getIdBenda() {
        return idBenda;
    }

    public void setIdBenda(int idBenda) {
        this.idBenda = idBenda;
    }

    public int getIdBelanjaan() {
        return idBelanjaan;
    }

    public void setIdBelanjaan(int idBelanjaan) {
        this.idBelanjaan = idBelanjaan;
    }
}
