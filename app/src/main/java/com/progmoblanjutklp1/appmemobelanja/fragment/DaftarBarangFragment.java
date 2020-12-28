package com.progmoblanjutklp1.appmemobelanja.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.progmoblanjutklp1.appmemobelanja.R;
import com.progmoblanjutklp1.appmemobelanja.adapter.BarangListAdapter;
import com.progmoblanjutklp1.appmemobelanja.model.Barang;
import com.progmoblanjutklp1.appmemobelanja.viewmodel.BarangViewModel;

import java.util.ArrayList;
import java.util.List;


public class DaftarBarangFragment extends Fragment {

    private ArrayList<Barang> barangArrayList;

    private BarangListAdapter adapterBarang;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private BarangViewModel viewModel;

    private TextView listKosong;

    ExtendedFloatingActionButton fab;
    View v;
    View barangDialogView;
    AlertDialog barangDialog;
    EditText namaBarangInput;

    int idBarang;

    public DaftarBarangFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(BarangViewModel.class);
        barangArrayList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        v = inflater.inflate(R.layout.fragment_daftar_barang, container, false);
        listKosong = v.findViewById(R.id.empty_barang_view);
        listKosong.setVisibility(View.VISIBLE);
        recyclerView = v.findViewById(R.id.barang_list_view);


        adapterBarang = new BarangListAdapter(this.getActivity(), viewModel);
        adapterBarang.notifyDataSetChanged();
        getData();
        linearLayoutManager = new LinearLayoutManager(this.getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterBarang);


        adapterBarang.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) { // ganti pake nge cek ke db nya?
                super.onItemRangeRemoved(positionStart, itemCount);
                if (adapterBarang.getItemCount() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    listKosong.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onChanged() {
                super.onChanged();
                if (adapterBarang.getItemCount() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    listKosong.setVisibility(View.VISIBLE);
                }
                else {
                    recyclerView.setVisibility(View.VISIBLE);
                    listKosong.setVisibility(View.GONE);
                }
            }
        });

        fab = v.findViewById(R.id.add_barang_fab);
        fab.setOnClickListener(new View.OnClickListener() { //listener untuk fab buttonnya tiap di klik
            @Override
            public void onClick(View view) {
                barangDialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_input_barang, null, false);
                namaBarangInput = barangDialogView.findViewById(R.id.barang_name_text_field);
                barangDialog = new MaterialAlertDialogBuilder(getContext(), android.R.style.Theme_DeviceDefault_Dialog_NoActionBar)
                        .setTitle("Barang baru")
                        .setView(barangDialogView)
                        .setPositiveButton("Tambahkan barang", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (namaBarangInput.getText().toString().length() == 0) {
                                    Toast.makeText(getActivity(), "Nama barang tidak boleh kosong", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    dialogInterface.dismiss();
                                    viewModel.insert(new Barang( namaBarangInput.getText().toString()));
                                    getData();
                                    adapterBarang.notifyDataSetChanged();
                                }
                            }
                        })
                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .show();

                namaBarangInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        namaBarangInput.post(new Runnable() {
                            @Override
                            public void run() {
                                InputMethodManager inputMethodManager= (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                inputMethodManager.showSoftInput(namaBarangInput, InputMethodManager.SHOW_IMPLICIT);
                            }
                        });
                    }
                });
                namaBarangInput.requestFocus();

                namaBarangInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_DONE) {
                            barangDialog.getButton(AlertDialog.BUTTON_POSITIVE).callOnClick();
                            return true;
                        }
                        return false;
                    }
                });
                /* ini sampah, dont use */
//                addBarang = new InputBarangFragment();
//                addBarang.show(getChildFragmentManager(), "INPUTBARANG");
            }
        });

        return v;
    }

    private void getData(){
        viewModel.getBarangs().observe(getViewLifecycleOwner(), new Observer<List<Barang>>() {
            @Override
            public void onChanged(List<Barang> barangs) {
                if (barangs != null){
                    barangArrayList.clear();
                    barangArrayList.addAll(barangs);
                    adapterBarang.setBarangArrayList(barangArrayList);
                    adapterBarang.notifyDataSetChanged();
                }
            }
        });
    }
}