package com.progmoblanjutklp1.appmemobelanja.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.progmoblanjutklp1.appmemobelanja.R;
import com.progmoblanjutklp1.appmemobelanja.adapter.BelanjaanListAdapter;
import com.progmoblanjutklp1.appmemobelanja.adapter.HomeFragmentAdapter;
import com.progmoblanjutklp1.appmemobelanja.fragment.DaftarBarangFragment;
import com.progmoblanjutklp1.appmemobelanja.fragment.DaftarBelanjaanFragment;
import com.progmoblanjutklp1.appmemobelanja.model.Barang;
import com.progmoblanjutklp1.appmemobelanja.model.Belanjaan;
import com.progmoblanjutklp1.appmemobelanja.model.BelanjaanWithBarang;
import com.progmoblanjutklp1.appmemobelanja.model.Item;
import com.progmoblanjutklp1.appmemobelanja.model.ItemWithBarang;
import com.progmoblanjutklp1.appmemobelanja.viewmodel.BarangViewModel;
import com.progmoblanjutklp1.appmemobelanja.viewmodel.BelanjaViewModel;
import com.progmoblanjutklp1.appmemobelanja.viewmodel.ItemViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager homeViewPager;
    Fragment fragment1;
    Fragment fragment2;

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE =1;
    public static final int EDIT_WORD_ACTIVITY_REQUEST_CODE = 2;

    private BelanjaViewModel belanjaViewModel;
    private ItemViewModel itemViewModel;
    private BarangViewModel barangViewModel;

    private String TAG = "DB_BELANJAA";

    private int[] tabIcons = {
            R.drawable.ic_baseline_shopping_bag_24,
            R.drawable.ic_baseline_category_24
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout homeTabLayout = findViewById(R.id.home_tab_bar);
        TabItem belanjaanTab = findViewById(R.id.belanjaan_tab);
        TabItem barangTab = findViewById(R.id.barang_tab);
        ViewPager homeViewPager = findViewById(R.id.home_view_pager);

        belanjaViewModel = ViewModelProviders.of(this).get(BelanjaViewModel.class);
        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        barangViewModel = ViewModelProviders.of(this).get(BarangViewModel.class);


        homeTabLayout.setupWithViewPager(homeViewPager);

        HomeFragmentAdapter homeViewPagerAdapter = new HomeFragmentAdapter(getSupportFragmentManager());
        homeViewPagerAdapter.addFrag(new DaftarBelanjaanFragment(), "Daftar Belanjaan");
        homeViewPagerAdapter.addFrag(new DaftarBarangFragment(), "Datar Barang");
        homeViewPager.setAdapter(homeViewPagerAdapter);

        homeTabLayout.getTabAt(0).setIcon(tabIcons[0]);
        homeTabLayout.getTabAt(1).setIcon(tabIcons[1]);

        fragment1 = homeViewPagerAdapter.getItem(0);
        fragment2 = homeViewPagerAdapter.getItem(1);

//        Belanjaan belanjaan = new Belanjaan("Besok Pagi","Daya pagi","2-2-2000","2-2-2000");
//        belanjaViewModel.insert(belanjaan);

//        Barang barang = new Barang("Tomat");
//        barangViewModel.insert(barang);
//
//        Item item = new Item(2,"Ada",3,3);
//        item.setId(3);
//        itemViewModel.delete(item);

//        itemViewModel.getItem(1).observe(this, new Observer<List<ItemWithBarang>>() {
//            @Override
//            public void onChanged(List<ItemWithBarang> itemWithBarangs) {
//                Log.d(TAG, "onChanged barang: "+itemWithBarangs.get(2).getNamaBarang());
//            }
//        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
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