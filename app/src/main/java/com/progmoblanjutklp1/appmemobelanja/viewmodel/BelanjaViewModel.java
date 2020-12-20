package com.progmoblanjutklp1.appmemobelanja.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.progmoblanjutklp1.appmemobelanja.database.BelanjaanDatabaseRepository;
import com.progmoblanjutklp1.appmemobelanja.database.ItemDatabaseRepository;
import com.progmoblanjutklp1.appmemobelanja.model.Belanjaan;
import com.progmoblanjutklp1.appmemobelanja.model.BelanjaanWithBarang;

import java.util.List;

public class BelanjaViewModel extends AndroidViewModel {
    private LiveData<List<BelanjaanWithBarang>> belanjaan;
    private LiveData<List<Belanjaan>> belanjas;
    private String TAG = "DELETE_BELANJAAN";

    private BelanjaanDatabaseRepository repository;
    private ItemDatabaseRepository itemDatabaseRepository;

    public BelanjaViewModel(Application application){
        super(application);
        repository = new BelanjaanDatabaseRepository(application);
        itemDatabaseRepository = new ItemDatabaseRepository(application);
        belanjaan =  repository.getAllBelanjaan();
        belanjas = repository.getBelanjaan();
    }

    public LiveData<List<BelanjaanWithBarang>> getAllBelanjaan(){
        return belanjaan;
    }

    public LiveData<List<Belanjaan>> getBelanjas(){return belanjas;}

    public void insert(Belanjaan belanjaan){
        repository.insert(belanjaan);
    }
    public void update(Belanjaan belanjaan){
        repository.update(belanjaan);
    }
    public void delete(Belanjaan belanjaan){
//        Log.d(TAG, "delete: "+belanjaan.getId());
//        itemDatabaseRepository.deleteItemBelanjaan(belanjaan.getId());
        repository.delete(belanjaan);

    }

}
