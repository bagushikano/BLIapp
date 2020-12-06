package com.progmoblanjutklp1.appmemobelanja.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.progmoblanjutklp1.appmemobelanja.R;
import com.progmoblanjutklp1.appmemobelanja.adapter.BarangListAdapter;
import com.progmoblanjutklp1.appmemobelanja.adapter.BarangSelectionAdapter;
import com.progmoblanjutklp1.appmemobelanja.model.Barang;

import java.util.ArrayList;

public class BarangSelectionActivity extends AppCompatActivity {
    private ArrayList<Barang> barangArrayList;

    private BarangSelectionAdapter adapterBarang;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private TextView listKosong;

    int idBarang;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang_selection);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //TODO ambil list barang dari database

        barangArrayList = new ArrayList<>();

        /* test ui */

        barangArrayList.add(new Barang("apaje 1", 1));
        barangArrayList.add(new Barang("apaje 2", 2));
        barangArrayList.add(new Barang("apaje 3", 3));
        barangArrayList.add(new Barang("apaje 4", 4));
        barangArrayList.add(new Barang("apaje 5", 5));


        listKosong = findViewById(R.id.empty_barang_selection_view);

        recyclerView = findViewById(R.id.barang_list_view);

        adapterBarang = new BarangSelectionAdapter(this, barangArrayList);
        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterBarang);

        //TODO kalo list kosong show tidak ada barang dalam list barang, silahkan tambahkan barang terlebih dahulu
        // listKosong.setVisibility(View.VISIBLE);

        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }


}