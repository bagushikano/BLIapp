package com.progmoblanjutklp1.appmemobelanja.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Barang.class,
parentColumns = "id",
childColumns = "idBarang"))

public class Item {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "jumlah")
    private int jumlah;

    @ColumnInfo(name = "keterangan")
    private String keterangan;

    @ColumnInfo(name = "idBelanjaan")
    private int idBelanjaan;

    @ColumnInfo(name = "idBarang")
    private int idBarang;

    public Item( int jumlah, String keterangan, int idBelanjaan, int idBarang) {
        this.idBelanjaan = idBelanjaan;
        this.jumlah = jumlah;
        this.keterangan = keterangan;
        this.idBarang = idBarang;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
