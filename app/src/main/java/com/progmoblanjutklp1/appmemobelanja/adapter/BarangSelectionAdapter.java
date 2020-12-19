package com.progmoblanjutklp1.appmemobelanja.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.progmoblanjutklp1.appmemobelanja.R;
import com.progmoblanjutklp1.appmemobelanja.model.Barang;
import com.progmoblanjutklp1.appmemobelanja.viewmodel.ItemViewModel;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class BarangSelectionAdapter extends RecyclerView.Adapter<BarangSelectionAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Barang> barangArrayList = new ArrayList<>();
    private int position;


    private String idBarangKey = "idbarang";


    public BarangSelectionAdapter (Context context) {
        this.context = context;
    }

    public ArrayList<Barang> getBarangArrayList() {
        return barangArrayList;
    }

    public void setBarangArrayList(ArrayList<Barang> barangArrayList) {
        this.barangArrayList.clear();
        this.barangArrayList.addAll(barangArrayList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BarangSelectionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.barang_selection_card_list, parent, false);
        return new BarangSelectionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarangSelectionAdapter.ViewHolder viewHolder, int position) {
        viewHolder.namaBarangView.setText(barangArrayList.get(position).getNamaBarang());
    }

    @Override
    public int getItemCount() {
        return barangArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView namaBarangView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaBarangView = itemView.findViewById(R.id.nama_barang_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    position = getAdapterPosition();
                    Intent barang = new Intent();
                    barang.putExtra(idBarangKey, barangArrayList.get(position).getId());
                    barang.putExtra("namaBarang",barangArrayList.get(position).getNamaBarang());
                    ((Activity) context).setResult(RESULT_OK, barang);
                    ((Activity) context).finish();
                }
            });
        }
    }
}
