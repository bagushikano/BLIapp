package com.progmoblanjutklp1.appmemobelanja.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.progmoblanjutklp1.appmemobelanja.BuildConfig;
import com.progmoblanjutklp1.appmemobelanja.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView about;
        String versionName = BuildConfig.VERSION_NAME;
        about = findViewById(R.id.version_number);
        about.setText(String.format(getResources().getString(R.string.version_string), versionName));
    }
}