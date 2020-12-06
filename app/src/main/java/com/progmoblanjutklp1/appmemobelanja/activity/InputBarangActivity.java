package com.progmoblanjutklp1.appmemobelanja.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.progmoblanjutklp1.appmemobelanja.R;

public class InputBarangActivity extends AppCompatActivity {

    /* gak di pake */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_barang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}