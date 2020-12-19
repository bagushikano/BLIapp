package com.progmoblanjutklp1.appmemobelanja.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.progmoblanjutklp1.appmemobelanja.R;
import com.progmoblanjutklp1.appmemobelanja.adapter.BarangListAdapter;
import com.progmoblanjutklp1.appmemobelanja.adapter.BarangSelectionAdapter;
import com.progmoblanjutklp1.appmemobelanja.model.Barang;
import com.progmoblanjutklp1.appmemobelanja.viewmodel.BarangViewModel;

import java.util.ArrayList;
import java.util.List;

public class BarangSelectionActivity extends AppCompatActivity {
    private ArrayList<Barang> barangArrayList;
    private BarangViewModel barangViewModel;

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
        adapterBarang = new BarangSelectionAdapter(this);
        /* test ui */

//        barangArrayList.add(new Barang("Bambu"));
//        barangArrayList.add(new Barang("Runcing"));
        barangViewModel =  ViewModelProviders.of(this).get(BarangViewModel.class);
        barangViewModel.getBarangs().observe(this, new Observer<List<Barang>>() {
            @Override
            public void onChanged(List<Barang> barangs) {
                barangArrayList.clear();
                barangArrayList.addAll(barangs);
                adapterBarang.setBarangArrayList(barangArrayList);
            }
        });

        listKosong = findViewById(R.id.empty_barang_selection_view);
        recyclerView = findViewById(R.id.barang_list_view);


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