package com.progmoblanjutklp1.appmemobelanja.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="barang")
public class Barang {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String namaBarang;

    public Barang( String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }
}
