package com.progmoblanjutklp1.appmemobelanja.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.progmoblanjutklp1.appmemobelanja.R;
import com.progmoblanjutklp1.appmemobelanja.model.Barang;
import com.progmoblanjutklp1.appmemobelanja.model.Belanjaan;
import com.progmoblanjutklp1.appmemobelanja.viewmodel.BarangViewModel;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class InputItemActivity extends AppCompatActivity {

    private TextInputEditText barangItem;
    private TextInputEditText keteranganItem;
    private TextInputLayout barangItemForm;
    private TextInputLayout keteranganItemForm;
    private TextInputLayout jumlahItemForm;
    private TextInputEditText jumlahItem;
    private Button tambahItemButton;
    private BarangViewModel barangViewModel;
    private String TAG="INPUT_BARANG_BELANJAAN";

    private String idItemKey = "iditem";
    private String idBarangKey = "idbarang";
    private String namaBarang = "namaBarang";
    private String keteranganItemKey = "keteranganitem";
    private String jumlahItemkey = "jumlahitem";
    private String idBelanjaan = "idBelanjaan";
    private int barangID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Intent intent = getIntent();

        barangItem = findViewById(R.id.item_barang_text_field);
        keteranganItem = findViewById(R.id.item_desc_text_field);
        jumlahItem = findViewById(R.id.item_jumlah_text_field);
        tambahItemButton = findViewById(R.id.item_add_button);
        barangItemForm = findViewById(R.id.item_name_form);
        keteranganItemForm = findViewById(R.id.item_desc_form);
        jumlahItemForm = findViewById(R.id.item_jumlah_form);

        barangViewModel = ViewModelProviders.of(this).get(BarangViewModel.class);

        if (intent.hasExtra(idItemKey) && intent.hasExtra(idBarangKey) &&
                intent.hasExtra(keteranganItemKey) && intent.hasExtra(jumlahItemkey)) {

            // TODO ni aku gatau gmn caranya nge set barang ke field nya buat editnya wkwkwkw
            int id = intent.getExtras().getInt(idBarangKey);
            barangViewModel.getOnlyBarang(id).observe(this, new Observer<List<Barang>>() {
                @Override
                public void onChanged(List<Barang> barangs) {
                    barangID = barangs.get(0).getId();
                    barangItem.setText(barangs.get(0).getNamaBarang());
                }
            });

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
                if (barangItem.getText().toString().length() == 0) {
                    barangItemForm.setError("Barang tidak boleh kosong");
                }
                else {
                    barangItemForm.setError(null);
                    if (keteranganItem.getText().toString().length() == 0) {
                        keteranganItemForm.setError("Keterangan tidak boleh kosong");
                    }

                    else {
                        keteranganItemForm.setError(null);
                        if (jumlahItem.getText().toString().length() == 0) {
                            jumlahItemForm.setError("Jumlah tidak boleh kosong");
                        }
                        else {
                            Intent item = new Intent();
                            if (intent.hasExtra(idItemKey)){
                                item.putExtra(idItemKey,intent.getExtras().getInt(idItemKey));
                            }
                            item.putExtra(idBarangKey,barangID);
                            item.putExtra(keteranganItemKey, keteranganItem.getText().toString());
                            item.putExtra(jumlahItemkey, Integer.parseInt(jumlahItem.getText().toString()));
                            setResult(RESULT_OK, item);
                            finish();
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) { // pake request code 0 untuk input belanjaan baru,
            if (requestCode == 0) {
                if (data.hasExtra(idBarangKey)) {
                    //TODO set barangnya disini ke text field nya
                    String namabarang = data.getExtras().getString(namaBarang);
                    barangID = data.getExtras().getInt(idBarangKey);
                    barangItem.setText(namabarang);
                    Log.d(TAG, "idBarang Key: "+idBarangKey);
                }
            }else if(requestCode == 1){//1 untuk edit belanjaan

            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}