package com.progmoblanjutklp1.appmemobelanja.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.progmoblanjutklp1.appmemobelanja.R;
import com.progmoblanjutklp1.appmemobelanja.activity.InputItemActivity;
import com.progmoblanjutklp1.appmemobelanja.model.Item;
import com.progmoblanjutklp1.appmemobelanja.model.ItemWithBarang;
import com.progmoblanjutklp1.appmemobelanja.viewmodel.BarangViewModel;
import com.progmoblanjutklp1.appmemobelanja.viewmodel.ItemViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ItemWithBarang> itemArrayList = new ArrayList<>();
    private ArrayList<Item> itemList = new ArrayList<>();
    private int position;
    private ItemViewModel itemViewModel;
    private BarangViewModel barangViewModel;
    private String TAG = "DETAIL_BELANJAA";
    private String idItemKey = "iditem";
    private String idBarangKey = "idbarang";
    private String idBelanjaan = "idBelanjaan";
    private String keteranganItemKey = "keteranganitem";
    private String jumlahItemkey = "jumlahitem";

    public ItemListAdapter(Context context, ItemViewModel itemViewModel) {
        this.context = context;
        this.itemViewModel = itemViewModel;
    }

    public void setItemArrayList(ArrayList<ItemWithBarang> itemArrayList) {
        this.itemArrayList.clear();
        this.itemArrayList.addAll(itemArrayList);
        notifyDataSetChanged();
    }

    public void setItemList(ArrayList<Item> itemList) {
        this.itemList.clear();
        this.itemList.addAll(itemList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_card_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.namaItemView.setText(String.valueOf(itemArrayList.get(position).getNamaBarang())); // TODO ini gimana sih ntar nyari nama bendanya?, sementara ta tampilin id beda aja
        viewHolder.jumlahItemView.setText(String.format(viewHolder.itemView.getResources().getString(R.string.jumlah_item_card), itemArrayList.get(position).getJumlah()));
        viewHolder.keteranganItemView.setText(itemArrayList.get(position).getKeterangan());
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView namaItemView;
        private AppCompatTextView jumlahItemView;
        private AppCompatTextView keteranganItemView;
        private Button editItemButton;
        private Button deleteItemButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaItemView = itemView.findViewById(R.id.nama_item_view);
            jumlahItemView = itemView.findViewById(R.id.jumlah_item_view);
            keteranganItemView = itemView.findViewById(R.id.keterangan_item_view);
            editItemButton = itemView.findViewById(R.id.edit_item_card_button);
            deleteItemButton = itemView.findViewById(R.id.delete_item_card_button);

            editItemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO edit item nya belum bro
                    position = getAdapterPosition();
                    ItemWithBarang item = itemArrayList.get(position);
                    Intent editIntent = new Intent(context, InputItemActivity.class);
                    editIntent.putExtra(idItemKey, item.getId());
                    editIntent.putExtra(keteranganItemKey, item.getKeterangan());
                    editIntent.putExtra(jumlahItemkey,item.getJumlah());
                    editIntent.putExtra(idBarangKey, item.getIdBarang());
                    editIntent.putExtra(idBelanjaan, item.getIdBelanjaan());
                    ((Activity)context).startActivityForResult(editIntent,1);
                }
            });


            deleteItemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO masukkin magic magic room nya gan
                    // TODO ini gimana sih ntar nyari nama bendanya?, sementara ta tampilin id beda aja
                    position = getAdapterPosition();
                    ItemWithBarang itemWithBarang = itemArrayList.get(position);
                    final Item itemPosition = new Item(itemWithBarang.getJumlah(),itemWithBarang.getKeterangan(),itemWithBarang.getIdBelanjaan(),itemWithBarang.getIdBarang());
                    itemPosition.setId(itemWithBarang.getId());
                    new MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme)
                            .setTitle(R.string.delete_item_dialog_title)
                            .setMessage("Yakin ingin menghapus item dari list?")
                            .setPositiveButton(R.string.delete_dialog_positive, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    itemViewModel.delete(itemPosition);
                                    notifyItemRemoved(position);
                                    notifyItemRangeChanged(position, itemArrayList.size());
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
        }
    }
}

