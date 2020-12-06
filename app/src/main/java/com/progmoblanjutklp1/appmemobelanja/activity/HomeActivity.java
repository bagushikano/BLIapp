package com.progmoblanjutklp1.appmemobelanja.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.progmoblanjutklp1.appmemobelanja.R;
import com.progmoblanjutklp1.appmemobelanja.adapter.HomeFragmentAdapter;
import com.progmoblanjutklp1.appmemobelanja.fragment.DaftarBarangFragment;
import com.progmoblanjutklp1.appmemobelanja.fragment.DaftarBelanjaanFragment;


public class HomeActivity extends AppCompatActivity {

    ViewPager homeViewPager;
    Fragment fragment1;
    Fragment fragment2;

    private int[] tabIcons = {
            R.drawable.ic_baseline_shopping_bag_24,
            R.drawable.ic_baseline_category_24
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TabLayout homeTabLayout = findViewById(R.id.home_tab_bar);
        TabItem belanjaanTab = findViewById(R.id.belanjaan_tab);
        TabItem barangTab = findViewById(R.id.barang_tab);
        ViewPager homeViewPager = findViewById(R.id.home_view_pager);


        homeTabLayout.setupWithViewPager(homeViewPager);

        HomeFragmentAdapter homeViewPagerAdapter = new HomeFragmentAdapter(getSupportFragmentManager());
        homeViewPagerAdapter.addFrag(new DaftarBelanjaanFragment(), "Daftar Belanjaan");
        homeViewPagerAdapter.addFrag(new DaftarBarangFragment(), "Datar Barang");
        homeViewPager.setAdapter(homeViewPagerAdapter);

        homeTabLayout.getTabAt(0).setIcon(tabIcons[0]);
        homeTabLayout.getTabAt(1).setIcon(tabIcons[1]);

        fragment1 = homeViewPagerAdapter.getItem(0);
        fragment2 = homeViewPagerAdapter.getItem(1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fragment1.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            Intent about = new Intent(this, AboutActivity.class);
            startActivity(about);
            return true;
        }
        else if (id == R.id.action_exit) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}