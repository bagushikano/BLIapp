package com.progmoblanjutklp1.appmemobelanja.model;

import java.util.Date;

public class Belanjaan {
    private String namaBelanjaan;
    private String deskripsiBelanjaan;
    private Date tanggalBelanjaan; //tanggal belanjaan ini maksudnya untuk tanggal berapa belanjaannya itu harusnya di belanjakan gitu
    private Date tanggalDitambahkan; //ini tanggal kapan belanjaannya itu di tambah
    private int idBelanjaan;

    public Belanjaan(String namaBelanjaan, String deskripsiBelanjaan, Date tanggalBelanjaan, Date tanggalDitambahkan, int idBelanjaan) {
        this.namaBelanjaan = namaBelanjaan;
        this.deskripsiBelanjaan = deskripsiBelanjaan;
        this.tanggalBelanjaan = tanggalBelanjaan;
        this.tanggalDitambahkan = tanggalDitambahkan;
        this.idBelanjaan = idBelanjaan;
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

    public Date getTanggalBelanjaan() {
        return tanggalBelanjaan;
    }

    public void setTanggalBelanjaan(Date tanggalBelanjaan) {
        this.tanggalBelanjaan = tanggalBelanjaan;
    }

    public Date getTanggalDitambahkan() {
        return tanggalDitambahkan;
    }

    public void setTanggalDitambahkan(Date tanggalDitambahkan) {
        this.tanggalDitambahkan = tanggalDitambahkan;
    }

    public int getIdBelanjaan() {
        return idBelanjaan;
    }

    public void setIdBelanjaan(int idBelanjaan) {
        this.idBelanjaan = idBelanjaan;
    }
}