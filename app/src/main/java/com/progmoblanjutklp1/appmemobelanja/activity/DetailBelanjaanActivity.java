package com.progmoblanjutklp1.appmemobelanja.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.progmoblanjutklp1.appmemobelanja.R;
import com.progmoblanjutklp1.appmemobelanja.adapter.ItemListAdapter;
import com.progmoblanjutklp1.appmemobelanja.model.Belanjaan;
import com.progmoblanjutklp1.appmemobelanja.model.Item;
import com.progmoblanjutklp1.appmemobelanja.model.ItemWithBarang;
import com.progmoblanjutklp1.appmemobelanja.viewmodel.BarangViewModel;
import com.progmoblanjutklp1.appmemobelanja.viewmodel.ItemViewModel;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailBelanjaanActivity extends AppCompatActivity {
    private ArrayList<ItemWithBarang> itemArrayList;
    private ArrayList<Item> items;

    private ItemListAdapter adapterItem;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private ItemViewModel itemViewModel;
    private BarangViewModel barangViewModel;
    private String TAG = "DETAIL_BELANJAA";

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

    private String idItemKey = "iditem";
    private String idBarangKey = "idbarang";
    private String keteranganItemKey = "keteranganitem";
    private String jumlahItemkey = "jumlahitem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_belanjaan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras(); //ini di pake buat nangkep data yang di dapet dari activity yang manggil activity ini
        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        barangViewModel = ViewModelProviders.of(this).get(BarangViewModel.class);

        belanjaanName = extras.getString(namaBelanjaanKey);
        belanjaanDesc = extras.getString(deskripsiBelanjaanKey);
        belanjaanDate = extras.getString(tanggalBelanjaanKey);
        belanjaanId = extras.getInt(idBelanjaanKey);


        listKosong = findViewById(R.id.empty_item_view);
        belanjaanNameView = findViewById(R.id.detail_belanjaan_name);
        belanjaanDescView = findViewById(R.id.detail_belanjaan_desc);;
        belanjaanDateView = findViewById(R.id.detail_belanjaan_tanggal);;
        belanjaanJumlahItemView = findViewById(R.id.detail_belanjaan_jumlah_item);
        recyclerView = findViewById(R.id.item_list);

        //TODO kalo list kosong show tidak ada barang dalam list barang, silahkan tambahkan barang terlebih dahulu
        listKosong.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);


        itemArrayList = new ArrayList<>();
        items = new ArrayList<>();
        // TODO ambil detail belanjaan/item dari database
        //test ui aja
        /*
        itemArrayList.add(new Item(1, 3, "test view item di detail belanjaan", belanjaanId));
        itemArrayList.add(new Item(2, 3, "test view item di detail belanjaan", belanjaanId));
        itemArrayList.add(new Item(3, 3, "test view item di detail belanjaan", belanjaanId));
        itemArrayList.add(new Item(4, 3, "test view item di detail belanjaan", belanjaanId));
        itemArrayList.add(new Item(5, 3, "test view item di detail belanjaan", belanjaanId));
         */

        belanjaanNameView.setText(belanjaanName);
        belanjaanDescView.setText(belanjaanDesc);
        belanjaanDateView.setText(belanjaanDate);
        belanjaanJumlahItemView.setText(String.format(getResources().getString(R.string.jumlah_item_detail_belanjaan_card), itemArrayList.size()));
        adapterItem = new ItemListAdapter(this,itemViewModel);
        adapterItem.notifyDataSetChanged();
        getData();


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
            @Override
            public void onChanged() {
                super.onChanged();
                if (adapterItem.getItemCount() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    listKosong.setVisibility(View.VISIBLE);
                }
                else {
                    recyclerView.setVisibility(View.VISIBLE);
                    listKosong.setVisibility(View.GONE);
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
        if (resultCode == Activity.RESULT_OK) { // pake request code 0 untuk input item baru
            if (requestCode == 0) {
                // TODO tambah item baru
                if(data.hasExtra(idBarangKey) && data.hasExtra(keteranganItemKey) && data.hasExtra(jumlahItemkey)){
                    Item item = new Item(data.getExtras().getInt(jumlahItemkey),data.getExtras().getString(keteranganItemKey),belanjaanId ,data.getExtras().getInt(idBarangKey));
                    adapterItem.notifyDataSetChanged();
                    getData();
                    itemViewModel.insert(item);
                    adapterItem.notifyDataSetChanged();
                }
            }

            else if (requestCode == 1) { //  1 untuk edit item
                // TODO edit item
                if(data.hasExtra(idItemKey) &&data.hasExtra(idBarangKey) && data.hasExtra(keteranganItemKey) && data.hasExtra(jumlahItemkey)){
                    Item item = new Item(data.getExtras().getInt(jumlahItemkey),data.getExtras().getString(keteranganItemKey),belanjaanId ,data.getExtras().getInt(idBarangKey));
                    item.setId(data.getExtras().getInt(idItemKey));
                    adapterItem.notifyDataSetChanged();
                    getData();
                    itemViewModel.update(item);
                    adapterItem.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }

    private void getData(){
//        Log.d(TAG, "getData: "+belanjaanId);

        itemViewModel.getItem(belanjaanId).observe(this, new Observer<List<ItemWithBarang>>() {
            @Override
            public void onChanged(List<ItemWithBarang> itemWithBarangs) {
                itemArrayList.clear();
                itemArrayList.addAll(itemWithBarangs);
//                Log.d(TAG, "getData: "+itemArrayList.get(0).items.getId());
                belanjaanJumlahItemView.setText(String.format("Jumlah item: %d", itemArrayList.size()));
                adapterItem.setItemArrayList(itemArrayList);
            }
        });

//        itemViewModel.getMyItem(belanjaanId).observe(this, new Observer<List<Item>>() {
//            @Override
//            public void onChanged(List<Item> item) {
//                items.clear();
//                items.addAll(item);
////                Log.d(TAG, "getData: "+itemArrayList.get(0).items.getId());
//                adapterItem.setItemList(items);
//            }
//        });
    }
}