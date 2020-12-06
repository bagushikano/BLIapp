package com.progmoblanjutklp1.appmemobelanja.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.progmoblanjutklp1.appmemobelanja.R;
import com.progmoblanjutklp1.appmemobelanja.adapter.ItemListAdapter;
import com.progmoblanjutklp1.appmemobelanja.model.Item;

import java.util.ArrayList;

public class DetailBelanjaanActivity extends AppCompatActivity {
    private ArrayList<Item> itemArrayList;

    private ItemListAdapter adapterItem;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private TextView listKosong;
    private TextView belanjaanNameView;
    private TextView belanjaanDescView;
    private TextView belanjaanDateView;
    private TextView belanjaanJumlahItemView;

    private String belanjaanName;
    private String belanjaanDate;
    private String belanjaanDesc;
    private int belanjaanId;

    private String namaBelanjaanKey = "namabelanjaan";
    private String deskripsiBelanjaanKey = "descbelanjaan";
    private String tanggalBelanjaanKey = "tanggalbelanjaan";
    private String idBelanjaanKey = "idbelanjaan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_belanjaan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras(); //ini di pake buat nangkep data yang di dapet dari activity yang manggil activity ini

        belanjaanName = extras.getString(namaBelanjaanKey);
        belanjaanDesc = extras.getString(deskripsiBelanjaanKey);
        belanjaanDate = extras.getString(tanggalBelanjaanKey);
        belanjaanId = extras.getInt(idBelanjaanKey);


        listKosong = findViewById(R.id.empty_item_view);
        belanjaanNameView = findViewById(R.id.detail_belanjaan_name);
        belanjaanDescView = findViewById(R.id.detail_belanjaan_desc);;
        belanjaanDateView = findViewById(R.id.detail_belanjaan_tanggal);;
        belanjaanJumlahItemView = findViewById(R.id.detail_belanjaan_jumlah_item);

        itemArrayList = new ArrayList<>();

        //test ui aja
        itemArrayList.add(new Item(1, 3, "test view item di detail belanjaan", belanjaanId));
        itemArrayList.add(new Item(2, 3, "test view item di detail belanjaan", belanjaanId));
        itemArrayList.add(new Item(3, 3, "test view item di detail belanjaan", belanjaanId));
        itemArrayList.add(new Item(4, 3, "test view item di detail belanjaan", belanjaanId));
        itemArrayList.add(new Item(5, 3, "test view item di detail belanjaan", belanjaanId));


        belanjaanNameView.setText(belanjaanName);
        belanjaanDescView.setText(belanjaanDesc);
        belanjaanDateView.setText(belanjaanDate);
        belanjaanJumlahItemView.setText(String.format(getResources().getString(R.string.jumlah_item_detail_belanjaan_card), itemArrayList.size()));


        recyclerView = findViewById(R.id.item_list);

        adapterItem = new ItemListAdapter(this, itemArrayList);
        linearLayoutManager = new LinearLayoutManager(this);
        adapterItem.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                super.onItemRangeRemoved(positionStart, itemCount);
                if (adapterItem.getItemCount() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    listKosong.setVisibility(View.VISIBLE);
                }
            }
        });
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterItem);
        recyclerView.setVisibility(View.VISIBLE);

        ExtendedFloatingActionButton fab = findViewById(R.id.add_item_fab);
        fab.setOnClickListener(new View.OnClickListener() { //listener untuk fab buttonnya tiap di klik
            @Override
            public void onClick(View view) {
                Intent addItem = new Intent(getApplicationContext(), InputItemActivity.class);
                startActivityForResult(addItem, 0); // 0 untuk add item baru, 1 untuk edit item
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}