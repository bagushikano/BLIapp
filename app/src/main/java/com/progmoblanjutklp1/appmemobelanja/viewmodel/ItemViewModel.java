package com.progmoblanjutklp1.appmemobelanja.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.progmoblanjutklp1.appmemobelanja.database.ItemDatabaseRepository;
import com.progmoblanjutklp1.appmemobelanja.model.Item;
import com.progmoblanjutklp1.appmemobelanja.model.ItemWithBarang;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {
    private LiveData<List<ItemWithBarang>> item;
    private LiveData<List<Item>> myItem;
    private ItemDatabaseRepository repository;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        repository = new ItemDatabaseRepository(application);

    }

    public LiveData<List<ItemWithBarang>> getItem(int idBelanjaan){
        item = repository.getItems(idBelanjaan);
        return item;
    }

    public LiveData<List<Item>> getMyItem(int idBelanjaan){
        myItem = repository.getMyItem(idBelanjaan);
        return myItem;
    }


    public void insert(Item item){
        repository.insert(item);
    }

    public void update(Item item){
        repository.update(item);
    }

    public void delete(Item item){
        repository.delete(item);
    }
}
