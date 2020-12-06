package com.progmoblanjutklp1.appmemobelanja.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.progmoblanjutklp1.appmemobelanja.R;
import com.progmoblanjutklp1.appmemobelanja.adapter.BelanjaanListAdapter;
import com.progmoblanjutklp1.appmemobelanja.model.Belanjaan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Belanjaan> belanjaanArrayList;

    private BelanjaanListAdapter adapterBelanjaan;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private TextView listKosong;

    private String namaBelanjaanKey = "namabelanjaan";
    private String deskripsiBelanjaanKey = "descbelanjaan";
    private String tanggalBelanjaanKey = "tanggalbelanjaan";
    private String idBelanjaanKey = "idbelanjaan";

    private SimpleDateFormat simpleFormat = new SimpleDateFormat("MM/dd/yyyy");
    private Date date;


    private int countId = 0;
    // TODO klo dh ada db nya hapus aja ini, di model belanjaan nya kan jg gaperlu id,
    //  cmn buat test ui aja ini


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listKosong = findViewById(R.id.empty_view);
        listKosong.setVisibility(View.VISIBLE);

        belanjaanArrayList = new ArrayList<>();

        recyclerView = findViewById(R.id.belanjaan_list);

//        adapterBelanjaan = new BelanjaanListAdapter(this, belanjaanArrayList);
//        linearLayoutManager = new LinearLayoutManager(this);

//        listKosong.setVisibility(View.VISIBLE);

        adapterBelanjaan.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) { // ganti pake nge cek ke db nya?
                super.onItemRangeRemoved(positionStart, itemCount);
                if (adapterBelanjaan.getItemCount() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    listKosong.setVisibility(View.VISIBLE);
                }
            }
        });

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterBelanjaan);
        adapterBelanjaan.notifyDataSetChanged();

        ExtendedFloatingActionButton fab = findViewById(R.id.add_belanjaan_fab); //fab button utk nambah lokasi
        fab.setOnClickListener(new View.OnClickListener() { //listener untuk fab buttonnya tiap di klik
            @Override
            public void onClick(View view) { //aksi ketika fab buttonnya di klik
                Intent addBelanjaan = new Intent(getApplicationContext(), InputBelanjaanActivity.class); //pembuatan intent, intent ini gunanya buat jembatan antar activity
                startActivityForResult(addBelanjaan, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 0) { // pake request code 0 untuk input belanjaan baru, 1 untuk edit belanjaan
            if (data.hasExtra(namaBelanjaanKey) && data.hasExtra(deskripsiBelanjaanKey) && data.hasExtra(tanggalBelanjaanKey)) {

                try {
                    date = new Date(String.valueOf(simpleFormat.parse(data.getExtras().getString(tanggalBelanjaanKey))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                belanjaanArrayList.add(new Belanjaan(data.getExtras().getString(namaBelanjaanKey), data.getExtras().getString(deskripsiBelanjaanKey), date, date, countId++));
                adapterBelanjaan.notifyDataSetChanged();
                recyclerView.setVisibility(View.VISIBLE);
                listKosong.setVisibility(View.GONE);

                // TODO tambahin magic magic room databasenya gan
                // TODO aduh date ini bikin pala pusing aja, gatau itu udh bener / blm cara buat datenya wkwkwkwkw, formatyg ta pake mm//dd/yyyy
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            Intent about = new Intent(this, AboutActivity.class);
            startActivity(about);
            return true;
        }
        else if (id == R.id.action_exit) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}