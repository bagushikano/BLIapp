package com.progmoblanjutklp1.appmemobelanja.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.progmoblanjutklp1.appmemobelanja.R;


public class InputBarangFragment extends BottomSheetDialogFragment {

    /* gak di pake bre */

    View v;
    EditText namaBarang;
    String desired_string;
    Button simpanBarang;

    public InputBarangFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_input_barang, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        namaBarang = v.findViewById(R.id.item_name_text_field);
        simpanBarang = v.findViewById(R.id.item_add_button);

        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey("EDITBARANG")) {
            desired_string = arguments.getString("EDITBARANG");
            namaBarang.setText(desired_string);
            simpanBarang.setText("Simpan");
        }

        namaBarang.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(namaBarang, InputMethodManager.SHOW_IMPLICIT);

        simpanBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return v;
    }
}