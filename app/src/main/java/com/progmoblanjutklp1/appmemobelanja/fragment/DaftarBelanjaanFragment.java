package com.progmoblanjutklp1.appmemobelanja.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.progmoblanjutklp1.appmemobelanja.R;
import com.progmoblanjutklp1.appmemobelanja.activity.InputBelanjaanActivity;
import com.progmoblanjutklp1.appmemobelanja.adapter.BelanjaanListAdapter;
import com.progmoblanjutklp1.appmemobelanja.model.Belanjaan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DaftarBelanjaanFragment extends Fragment {

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
    //  cmn sekarang biar bisa test ui saja

    ExtendedFloatingActionButton fab;
    View v;

    public DaftarBelanjaanFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) { // pake request code 0 untuk input belanjaan baru, 1 untuk edit belanjaan
            if (requestCode == 0) {
                if (data.hasExtra(namaBelanjaanKey) && data.hasExtra(deskripsiBelanjaanKey) && data.hasExtra(tanggalBelanjaanKey)) {
                    try {
                        date = new Date(String.valueOf(simpleFormat.parse(data.getExtras().getString(tanggalBelanjaanKey))));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    belanjaanArrayList.add(new Belanjaan(data.getExtras().getString(namaBelanjaanKey), data.getExtras().getString(deskripsiBelanjaanKey), date, date, countId++));
                    adapterBelanjaan.notifyDataSetChanged();
                    Toast.makeText(getContext(), String.format(getString(R.string.belanjaan_insert_toast), data.getExtras().getString(namaBelanjaanKey)), Toast.LENGTH_SHORT).show();

                    // TODO tambahin magic magic room databasenya gan
                    // TODO aduh date ini bikin pala pusing aja, gatau itu udh bener / blm cara buat datenya wkwkwkwkw, formatyg ta pake mm//dd/yyyy
                }
            }

            else if (requestCode == 1) { // pake request code 0 untuk input belanjaan baru, 1 untuk edit belanjaan
                if (data.hasExtra(namaBelanjaanKey) && data.hasExtra(deskripsiBelanjaanKey) && data.hasExtra(tanggalBelanjaanKey)) {
                    try {
                        date = new Date(String.valueOf(simpleFormat.parse(data.getExtras().getString(tanggalBelanjaanKey))));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    belanjaanArrayList.get(data.getExtras().getInt(idBelanjaanKey)).setNamaBelanjaan(data.getExtras().getString(namaBelanjaanKey));
                    belanjaanArrayList.get(data.getExtras().getInt(idBelanjaanKey)).setDeskripsiBelanjaan(data.getExtras().getString(deskripsiBelanjaanKey));
                    belanjaanArrayList.get(data.getExtras().getInt(idBelanjaanKey)).setTanggalBelanjaan(date);
                    adapterBelanjaan.notifyDataSetChanged();
                    Toast.makeText(getContext(), String.format(getString(R.string.belanjaan_edit_toast), data.getExtras().getString(namaBelanjaanKey)), Toast.LENGTH_SHORT).show();

                    // TODO tambahin magic magic room databasenya gan
                    // TODO aduh date ini bikin pala pusing aja, gatau itu udh bener / blm cara buat datenya wkwkwkwkw, formatyg ta pake mm//dd/yyyy
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_daftar_belanjaan, container, false);

        listKosong = v.findViewById(R.id.empty_view);
        listKosong.setVisibility(View.VISIBLE);
        recyclerView = v.findViewById(R.id.belanjaan_list);

        belanjaanArrayList = new ArrayList<>();

        adapterBelanjaan = new BelanjaanListAdapter(getActivity(), belanjaanArrayList);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterBelanjaan);

        adapterBelanjaan.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                recyclerView.setVisibility(View.VISIBLE);
                listKosong.setVisibility(View.GONE);
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) { // ganti pake nge cek ke db nya?
                super.onItemRangeRemoved(positionStart, itemCount);
                if (adapterBelanjaan.getItemCount() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    listKosong.setVisibility(View.VISIBLE);
                }
            }
        });

        ExtendedFloatingActionButton fab = v.findViewById(R.id.add_belanjaan_fab);
        fab.setOnClickListener(new View.OnClickListener() { //listener untuk fab buttonnya tiap di klik
            @Override
            public void onClick(View view) {
                Intent addBelanjaan = new Intent(getActivity(), InputBelanjaanActivity.class);
                startActivityForResult(addBelanjaan, 0); //pake 0 untuk tambahin, 1 untuk edit
            }
        });
        return v;
    }
}