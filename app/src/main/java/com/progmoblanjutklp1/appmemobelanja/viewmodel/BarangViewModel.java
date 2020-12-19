package com.progmoblanjutklp1.appmemobelanja.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.progmoblanjutklp1.appmemobelanja.database.BarangDatabaseRepository;
import com.progmoblanjutklp1.appmemobelanja.model.Barang;

import java.util.List;

public class BarangViewModel extends AndroidViewModel {
    private LiveData<List<Barang>> barangs;
    private BarangDatabaseRepository repository;


    public BarangViewModel(@NonNull Application application) {
        super(application);
        repository = new BarangDatabaseRepository(application);
        barangs = repository.getBarangsAll();
    }

    public LiveData<List<Barang>> getBarangs() {
        return barangs;
    }

    public LiveData<List<Barang>> getOnlyBarang(int id){
        LiveData<List<Barang>> barang = repository.getOnlyItem(id);
        return barang;
    }

    public void insert(Barang barang){
        repository.insert(barang);
    }
    public void update(Barang barang){
        repository.update(barang);
    }
    public void delete(Barang barang){
        repository.delete(barang);
    }
}
