package com.progmoblanjutklp1.appmemobelanja.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.progmoblanjutklp1.appmemobelanja.R;
import com.progmoblanjutklp1.appmemobelanja.model.Item;

import java.util.ArrayList;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Item> itemArrayList;
    private int position;

    public ItemListAdapter(Context context, ArrayList<Item> itemArrayList) {
        this.context = context;
        this.itemArrayList = itemArrayList;
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
        viewHolder.namaItemView.setText(String.valueOf(itemArrayList.get(position).getIdBenda())); // TODO ini gimana sih ntar nyari nama bendanya?, sementara ta tampilin id beda aja
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
                }
            });


            deleteItemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO masukkin magic magic room nya gan
                    // TODO ini gimana sih ntar nyari nama bendanya?, sementara ta tampilin id beda aja
                    position = getAdapterPosition();
                    Item itemPosition = itemArrayList.get(position);
                    new MaterialAlertDialogBuilder(context)
                            .setTitle(R.string.delete_item_dialog_title)
                            .setMessage(String.format(context.getResources().getString(R.string.delete_item_dialog_message) , String.valueOf(itemPosition.getIdBenda())))
                            .setPositiveButton(R.string.delete_dialog_positive, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    itemArrayList.remove(position);
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

