package com.progmoblanjutklp1.appmemobelanja.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.progmoblanjutklp1.appmemobelanja.R;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.progmoblanjutklp1.appmemobelanja.activity.InputBelanjaanActivity;
import com.progmoblanjutklp1.appmemobelanja.model.Belanjaan;
import com.progmoblanjutklp1.appmemobelanja.activity.DetailBelanjaanActivity;
import com.progmoblanjutklp1.appmemobelanja.viewmodel.BelanjaViewModel;

import java.util.ArrayList;

public class BelanjaanListAdapter extends RecyclerView.Adapter<BelanjaanListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Belanjaan> belanjaanArrayList = new ArrayList<>();
    private int position;
    private BelanjaViewModel belanjaViewModel;
    private String namaBelanjaanKey = "namabelanjaan";
    private String deskripsiBelanjaanKey = "descbelanjaan";
    private String tanggalBelanjaanKey = "tanggalbelanjaan";
    private String idBelanjaanKey = "idbelanjaan";

//    private androidx.fragment.app.Fragment fragment;

    public BelanjaanListAdapter(Context context, BelanjaViewModel belanjaViewModel) {
        this.context = context;
        this.belanjaViewModel = belanjaViewModel;
    }

    public ArrayList<Belanjaan> getBelanjaanArrayList() {
        return belanjaanArrayList;
    }

    public void setBelanjaanArrayList(ArrayList<Belanjaan> belanjaanArrayList) {
        this.belanjaanArrayList.clear();
        this.belanjaanArrayList.addAll(belanjaanArrayList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.belanjaan_card_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.namaBelanjaanView.setText(belanjaanArrayList.get(position).getNamaBelanjaan());
        viewHolder.tanggalBelanjaanView.setText(belanjaanArrayList.get(position).getTanggalBelanjaan().toString());
        viewHolder.deskripsiBelanjaanView.setText(belanjaanArrayList.get(position).getDeskripsiBelanjaan());
    }

    @Override
    public int getItemCount() {
        return belanjaanArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView namaBelanjaanView;
        private AppCompatTextView deskripsiBelanjaanView;
        private AppCompatTextView tanggalBelanjaanView;
        private Button editBelanjaanButton;
        private Button deleteBelanjaanButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaBelanjaanView = itemView.findViewById(R.id.nama_belanjaan_view);
            deskripsiBelanjaanView = itemView.findViewById(R.id.deskripsi_belanjaan_view);
            tanggalBelanjaanView = itemView.findViewById(R.id.tanggal_belanjaan_view);
            editBelanjaanButton = itemView.findViewById(R.id.edit_belanjaan_card_button);
            deleteBelanjaanButton = itemView.findViewById(R.id.delete_belanjaan_card_button);

            editBelanjaanButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    position = getAdapterPosition();
                    Belanjaan belanjaanPosition = belanjaanArrayList.get(position);
                    Intent editBelanjaan = new Intent(context, InputBelanjaanActivity.class);
                    editBelanjaan.putExtra(namaBelanjaanKey, belanjaanPosition.getNamaBelanjaan());
                    editBelanjaan.putExtra(deskripsiBelanjaanKey, belanjaanPosition.getDeskripsiBelanjaan());
                    editBelanjaan.putExtra(tanggalBelanjaanKey, belanjaanPosition.getTanggalBelanjaan());
                    editBelanjaan.putExtra(idBelanjaanKey, belanjaanPosition.getId()); //nanti ganti ini gan
                    ((Activity) context).startActivityForResult(editBelanjaan, 1); // Request code 1 untuk edit data belanjaan

                }
            });

            deleteBelanjaanButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    position = getAdapterPosition();
                    Belanjaan belanjaanPosition = belanjaanArrayList.get(position);

                    //TODO masukkin ntar magic magic db room nya gan


                    new MaterialAlertDialogBuilder(context)
                            .setTitle(R.string.delete_belanjaan_dialog_title)
                            .setMessage(String.format(context.getResources().getString(R.string.delete_belanjaan_dialog_message) , belanjaanPosition.getNamaBelanjaan()))
                            .setPositiveButton(R.string.delete_dialog_positive, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    Belanjaan belanjaanPosition = belanjaanArrayList.get(position);
                                    belanjaViewModel.delete(belanjaanPosition);
                                    notifyItemRemoved(position);
                                    notifyItemRangeChanged(position, belanjaanArrayList.size());
                                }
                            })
                            .setNegativeButton(R.string.delete_dialog_negative, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .show();
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    position = getAdapterPosition();
                    Belanjaan belanjaanPosition = belanjaanArrayList.get(position);
                    Intent detailBelanjaan = new Intent(context, DetailBelanjaanActivity.class);

                    //TODO ini bener ga gini ntar relasi relasinya aku blm pernah buat db yg make relasi,
                    // jadi ini cuman ya ngasi aja id master (belanjaan), ntar dari sana ada detail nya (item)
                    detailBelanjaan.putExtra(namaBelanjaanKey, belanjaanPosition.getNamaBelanjaan());
                    detailBelanjaan.putExtra(deskripsiBelanjaanKey, belanjaanPosition.getDeskripsiBelanjaan());
                    detailBelanjaan.putExtra(tanggalBelanjaanKey, belanjaanPosition.getTanggalBelanjaan());
                    detailBelanjaan.putExtra(idBelanjaanKey, belanjaanPosition.getId()); //nanti ganti ini gan

                    context.startActivity(detailBelanjaan);
                }
            });
        }
    }
}
