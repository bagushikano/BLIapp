package com.progmoblanjutklp1.appmemobelanja.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName="Belanjaan")
public class Belanjaan {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String namaBelanjaan;
    private String deskripsiBelanjaan;
    private String tanggalBelanjaan; //tanggal belanjaan ini maksudnya untuk tanggal berapa belanjaannya itu harusnya di belanjakan gitu
    private String tanggalDitambahkan; //ini tanggal kapan belanjaannya itu di tambah


    public Belanjaan( String namaBelanjaan, String deskripsiBelanjaan, String tanggalBelanjaan, String tanggalDitambahkan) {

        this.namaBelanjaan = namaBelanjaan;
        this.deskripsiBelanjaan = deskripsiBelanjaan;
        this.tanggalBelanjaan = tanggalBelanjaan;
        this.tanggalDitambahkan = tanggalDitambahkan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaBelanjaan() {
        return namaBelanjaan;
    }

    public void setNamaBelanjaan(String namaBelanjaan) {
        this.namaBelanjaan = namaBelanjaan;
    }

    public String getDeskripsiBelanjaan() {
        return deskripsiBelanjaan;
    }

    public void setDeskripsiBelanjaan(String deskripsiBelanjaan) {
        this.deskripsiBelanjaan = deskripsiBelanjaan;
    }

    public String getTanggalBelanjaan() {
        return tanggalBelanjaan;
    }

    public void setTanggalBelanjaan(String tanggalBelanjaan) {
        this.tanggalBelanjaan = tanggalBelanjaan;
    }

    public String getTanggalDitambahkan() {
        return tanggalDitambahkan;
    }

    public void setTanggalDitambahkan(String tanggalDitambahkan) {
        this.tanggalDitambahkan = tanggalDitambahkan;
    }
}