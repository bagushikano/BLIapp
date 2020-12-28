package com.progmoblanjutklp1.appmemobelanja.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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
import com.progmoblanjutklp1.appmemobelanja.model.BelanjaanWithBarang;
import com.progmoblanjutklp1.appmemobelanja.viewmodel.BelanjaViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DaftarBelanjaanFragment extends Fragment {

    private ArrayList<Belanjaan> belanjaanArrayList;
    private String TAG = "DB_DF_BELANJAA";
    private BelanjaanListAdapter adapterBelanjaan;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private BelanjaViewModel belanjaViewModel;
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
        belanjaViewModel = ViewModelProviders.of(this).get(BelanjaViewModel.class);
        belanjaanArrayList = new ArrayList<>();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) { // pake request code 0 untuk input belanjaan baru, 1 untuk edit belanjaan
            if (requestCode == 0) {
                if (data.hasExtra(namaBelanjaanKey) && data.hasExtra(deskripsiBelanjaanKey) && data.hasExtra(tanggalBelanjaanKey)) {

                    adapterBelanjaan.notifyDataSetChanged();
                    Toast.makeText(getContext(), String.format(getString(R.string.belanjaan_insert_toast), data.getExtras().getString(namaBelanjaanKey)), Toast.LENGTH_SHORT).show();
                    getData();
                    belanjaViewModel.insert(new Belanjaan(data.getExtras().getString(namaBelanjaanKey), data.getExtras().getString(deskripsiBelanjaanKey), data.getExtras().getString(tanggalBelanjaanKey), data.getExtras().getString(tanggalBelanjaanKey)));
                    adapterBelanjaan.notifyDataSetChanged();
                }
            }

            else if (requestCode == 1) { // pake request code 0 untuk input belanjaan baru, 1 untuk edit belanjaan
                if (data.hasExtra(namaBelanjaanKey) && data.hasExtra(deskripsiBelanjaanKey) && data.hasExtra(tanggalBelanjaanKey)) {
                    try {
                        date = new Date(String.valueOf(simpleFormat.parse(data.getExtras().getString(tanggalBelanjaanKey))));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Belanjaan belanjaan = new Belanjaan(data.getExtras().getString(namaBelanjaanKey), data.getExtras().getString(deskripsiBelanjaanKey), data.getExtras().getString(tanggalBelanjaanKey), data.getExtras().getString(tanggalBelanjaanKey));
                    belanjaan.setId(data.getExtras().getInt(idBelanjaanKey));
                    Log.d(TAG, "onActivityResult: "+data.getExtras().getInt(idBelanjaanKey));
                    belanjaViewModel.update(belanjaan);
                    getData();
                    adapterBelanjaan.notifyDataSetChanged();
                    Toast.makeText(getContext(), String.format(getString(R.string.belanjaan_edit_toast), data.getExtras().getString(namaBelanjaanKey)), Toast.LENGTH_SHORT).show();
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
        adapterBelanjaan = new BelanjaanListAdapter(getActivity(),belanjaViewModel);
        adapterBelanjaan.notifyDataSetChanged();
        getData();

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterBelanjaan);



        adapterBelanjaan.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if (adapterBelanjaan.getItemCount() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    listKosong.setVisibility(View.VISIBLE);
                }
                else {
                    recyclerView.setVisibility(View.VISIBLE);
                    listKosong.setVisibility(View.GONE);
                }
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

    private void getData(){
        belanjaViewModel.getBelanjas().observe(getViewLifecycleOwner(), new Observer<List<Belanjaan>>() {
            @Override
            public void onChanged(List<Belanjaan> belanjaans) {
                if(belanjaans != null){
                    belanjaanArrayList.clear();
                    belanjaanArrayList.addAll(belanjaans);
                    adapterBelanjaan.setBelanjaanArrayList(belanjaanArrayList);
                }

            }
        });
    }
}