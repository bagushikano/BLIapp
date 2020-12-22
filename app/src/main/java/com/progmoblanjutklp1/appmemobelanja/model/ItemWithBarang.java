package com.progmoblanjutklp1.appmemobelanja.model;

import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;

public class ItemWithBarang {
    private int id;
    private int jumlah;
    private String keterangan;
    private int idBelanjaan;
    private int idBarang;
    private String namaBarang;

    public ItemWithBarang(int jumlah, String keterangan, int idBelanjaan, int idBarang, String namaBarang) {
        this.jumlah = jumlah;
        this.keterangan = keterangan;
        this.idBelanjaan = idBelanjaan;
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public int getIdBelanjaan() {
        return idBelanjaan;
    }

    public void setIdBelanjaan(int idBelanjaan) {
        this.idBelanjaan = idBelanjaan;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }
}
