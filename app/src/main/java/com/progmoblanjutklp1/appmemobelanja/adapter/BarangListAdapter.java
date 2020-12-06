package com.progmoblanjutklp1.appmemobelanja.adapter;

import android.content.Context;
import android.content.DialogInterface;
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

import java.util.ArrayList;

public class BarangListAdapter extends RecyclerView.Adapter<BarangListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Barang> barangArrayList;
    private int position;
    /* trash */
//    BottomSheetDialogFragment editBarang;
//    private FragmentManager fragmentManager;

    View barangDialogView;
    EditText namaBarangInput;
    AlertDialog barangDialog;


    public BarangListAdapter (Context context, ArrayList<Barang> barangArrayList) {
        this.context = context;
        this.barangArrayList = barangArrayList;
//        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public BarangListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.barang_card_list, parent, false);
        return new BarangListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarangListAdapter.ViewHolder viewHolder, int position) {
        viewHolder.namaBarangView.setText(barangArrayList.get(position).getNamaBarang());
    }

    @Override
    public int getItemCount() {
        return barangArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView namaBarangView;
        private Button editBarangButton;
        private Button deleteBarangButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaBarangView = itemView.findViewById(R.id.nama_barang_view);
            editBarangButton = itemView.findViewById(R.id.edit_barang_card_button);
            deleteBarangButton = itemView.findViewById(R.id.delete_barang_card_button);

            editBarangButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    position = getAdapterPosition();
                    Barang barangPosition = barangArrayList.get(position);

                    barangDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_input_barang, null, false);
                    namaBarangInput = barangDialogView.findViewById(R.id.barang_name_text_field);
                    namaBarangInput.setText(barangPosition.getNamaBarang());

                    barangDialog = new MaterialAlertDialogBuilder(context)
                            .setTitle(R.string.edit_barang_title)
                            .setView(barangDialogView)
                            .setPositiveButton(R.string.simpan_button, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    barangArrayList.get(position).setNamaBarang(namaBarangInput.getText().toString());
                                    notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton(R.string.batal_button, new DialogInterface.OnClickListener() {
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
                                    InputMethodManager inputMethodManager= (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
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

                    /* trash */
//                    editBarang = new InputBarangFragment();
//                    Bundle arguments = new Bundle();
//                    arguments.putString("EDITBARANG", barangPosition.getNamaBarang());
//                    editBarang.setArguments(arguments);
//                    editBarang.setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomDialogStyle);
//                    editBarang.show(fragmentManager, "EDITBARANG");

                }
            });


            deleteBarangButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    position = getAdapterPosition();
                    Barang barangPosition = barangArrayList.get(position);

                    new MaterialAlertDialogBuilder(context)
                            .setTitle("Hapus barang dalam list barang")
                            .setMessage(String.format(context.getResources().getString(R.string.delete_barang_dialog_message) , barangPosition.getNamaBarang()))
                            .setPositiveButton(R.string.delete_dialog_positive, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    barangArrayList.remove(position);
                                    notifyItemRemoved(position);
                                    notifyItemRangeChanged(position, barangArrayList.size());
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
