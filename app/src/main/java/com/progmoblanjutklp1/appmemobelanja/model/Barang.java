package com.progmoblanjutklp1.appmemobelanja.model;

public class Barang {
    private String namaBarang;
    private int idBarang;

    public Barang(String namaBarang, int idBarang) {
        this.namaBarang = namaBarang;
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }
}
