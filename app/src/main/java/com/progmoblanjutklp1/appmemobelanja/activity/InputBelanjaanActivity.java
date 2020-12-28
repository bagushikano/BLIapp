package com.progmoblanjutklp1.appmemobelanja.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.progmoblanjutklp1.appmemobelanja.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class InputBelanjaanActivity extends AppCompatActivity {

    private TextInputEditText namaBelanjaan;
    private TextInputEditText deskripsiBelanjaan;
    private TextInputEditText tanggalBelanjaan;
    private TextInputLayout namaBelanjaanForm;
    private TextInputLayout deskripsiBelanjaanForm;
    private TextInputLayout tanggalBelanjaanForm;
    private Button tambahBelanjaanButton;
    private String TAG = "DB_DF_BELANJAA";
    private String namaBelanjaanKey = "namabelanjaan";
    private String deskripsiBelanjaanKey = "descbelanjaan";
    private String tanggalBelanjaanKey = "tanggalbelanjaan";
    private String idBelanjaanKey = "idbelanjaan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_belanjaan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Intent intent = getIntent();

        namaBelanjaan = findViewById(R.id.belanjaan_name_text_field);
        deskripsiBelanjaan = findViewById(R.id.belanjaan_deskripsi_text_field);
        tanggalBelanjaan = findViewById(R.id.belanjaan_tanggal_text_field);
        namaBelanjaanForm = findViewById(R.id.belanjaan_name_form);
        deskripsiBelanjaanForm = findViewById(R.id.belanjaan_deskripsi_form);
        tanggalBelanjaanForm = findViewById(R.id.belanjaan_tanggal_form);
        tambahBelanjaanButton = findViewById(R.id.belanjaan_add);
        int idBelanja;

        if (intent.hasExtra(namaBelanjaanKey) && intent.hasExtra(deskripsiBelanjaanKey) &&
                intent.hasExtra(tanggalBelanjaanKey) && intent.hasExtra(idBelanjaanKey)) {

            namaBelanjaan.setText(intent.getExtras().getString(namaBelanjaanKey));
            deskripsiBelanjaan.setText(intent.getExtras().getString(deskripsiBelanjaanKey));
            tanggalBelanjaan.setText(intent.getExtras().getString(tanggalBelanjaanKey));
            tambahBelanjaanButton.setText(R.string.simpan_button);
            setTitle(getString(R.string.update_belanjaan_title));

            // TODO ini tanggal nya ga tau gmn caranya biar otomatis ke isi form nya
            //  ini nanti jg ngambil data dari db ga sih? jadi kan bisa di bundle extrasnya kasi id aja
        }

        // date picker builder
        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText("Pilih tanggal belanjaan");
        final MaterialDatePicker materialDatePicker = materialDateBuilder.build();

        // listener untuk form tanggal belanjaannya, nanti dia nge buka date pickernya itu
        tanggalBelanjaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
            }
        });

        tanggalBelanjaan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
                }
            }
        });


        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selectedDate) {
                // link: https://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext
                // user has selected a date
                // format the date and set the text of the input box to be the selected date
                // right now this format is hard-coded, this will change
                ;
                // Get the offset from our timezone and UTC.
                TimeZone timeZoneUTC = TimeZone.getDefault();
                // It will be negative, so that's the -1
                int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime()) * -1;

                // Create a date format, then a date object with our offset
                SimpleDateFormat simpleFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                Date date = new Date(selectedDate + offsetFromUTC);

                tanggalBelanjaan.setText(simpleFormat.format(date));
            }
        });

        tambahBelanjaanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (namaBelanjaan.getText().toString().length() == 0) {
                   namaBelanjaanForm.setError("Nama belanjaan tidak boleh kosong");
                }
                else {
                    namaBelanjaanForm.setError(null);
                    if (deskripsiBelanjaan.getText().toString().length() == 0) {
                        deskripsiBelanjaanForm.setError("Deskripsi belanjaan tidak boleh kosong");
                    }
                    else {
                        deskripsiBelanjaanForm.setError(null);
                        if (tanggalBelanjaan.getText().toString().length() == 0) {
                            tanggalBelanjaanForm.setError("Tanggal belanjaan tidak boleh kosong");
                        }
                        else {
                            tanggalBelanjaanForm.setError(null);
                            Intent belanjaan = new Intent();
                            if (intent.hasExtra(idBelanjaanKey)){
                                final int idBelanja = intent.getExtras().getInt(idBelanjaanKey);
                                belanjaan.putExtra(idBelanjaanKey,idBelanja);
                            }
                            belanjaan.putExtra(namaBelanjaanKey, namaBelanjaan.getText().toString());
                            belanjaan.putExtra(deskripsiBelanjaanKey, deskripsiBelanjaan.getText().toString());
                            belanjaan.putExtra(tanggalBelanjaanKey, tanggalBelanjaan.getText().toString());
                            setResult(RESULT_OK, belanjaan);
                            finish();
                        }
                    }
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}