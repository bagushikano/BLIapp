package com.progmoblanjutklp1.appmemobelanja.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.progmoblanjutklp1.appmemobelanja.R;
import com.progmoblanjutklp1.appmemobelanja.model.Belanjaan;

import java.text.ParseException;
import java.util.Date;

public class InputItemActivity extends AppCompatActivity {

    private TextInputEditText barangItem;
    private TextInputEditText keteranganItem;
    private TextInputEditText jumlahItem;
    private Button tambahItemButton;

    private String idItemKey = "iditem";
    private String idBarangKey = "idbarang";
    private String keteranganItemKey = "keteranganitem";
    private String jumlahItemkey = "jumlahitem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();

        barangItem = findViewById(R.id.item_barang_text_field);
        keteranganItem = findViewById(R.id.item_desc_text_field);
        jumlahItem = findViewById(R.id.item_jumlah_text_field);
        tambahItemButton = findViewById(R.id.item_add_button);

        if (intent.hasExtra(idItemKey) && intent.hasExtra(idBarangKey) &&
                intent.hasExtra(keteranganItemKey) && intent.hasExtra(jumlahItemkey)) {

            // TODO ni aku gatau gmn caranya nge set barang ke field nya buat editnya wkwkwkw

            keteranganItem.setText(intent.getExtras().getString(keteranganItemKey));
            jumlahItem.setText(intent.getExtras().getString(jumlahItemkey));
            tambahItemButton.setText(R.string.simpan_button);
            setTitle("Edit item");
        }

        barangItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent item = new Intent(getApplicationContext(), BarangSelectionActivity.class);
                startActivityForResult(item, 0); // 0 untuk add barang baru
            }
        });

        barangItem.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    Intent item = new Intent(getApplicationContext(), BarangSelectionActivity.class);
                    startActivityForResult(item, 0); // 0 untuk add barang baru
                }
            }
        });

        tambahItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent item = new Intent();
                item.putExtra(keteranganItemKey, keteranganItem.getText().toString());
                item.putExtra(jumlahItemkey, jumlahItem.getText());
                setResult(RESULT_OK, item);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) { // pake request code 0 untuk input belanjaan baru, 1 untuk edit belanjaan
            if (requestCode == 0) {
                if (data.hasExtra(idBarangKey)) {
                    //TODO set barangnya disini ke text field nya
                }
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}