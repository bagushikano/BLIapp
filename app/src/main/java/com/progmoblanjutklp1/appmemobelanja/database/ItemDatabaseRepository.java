package com.progmoblanjutklp1.appmemobelanja.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.progmoblanjutklp1.appmemobelanja.dao.BelanjaDAO;
import com.progmoblanjutklp1.appmemobelanja.model.Barang;
import com.progmoblanjutklp1.appmemobelanja.model.Item;
import com.progmoblanjutklp1.appmemobelanja.model.ItemWithBarang;

import java.util.List;

public class ItemDatabaseRepository {
    private BelanjaDAO belanjaDAO;
    private LiveData<List<ItemWithBarang>> items;
    private LiveData<List<Item>> myItem;

    public ItemDatabaseRepository(Application application){
        DatabaseBelanja db = DatabaseBelanja.getDatabase(application);
        belanjaDAO = db.dao();
    }

    public LiveData<List<ItemWithBarang>> getItems(int idBelanjaan) {
        items = belanjaDAO.getItems(idBelanjaan);
        return items;
    }

    public LiveData<List<Item>> getMyItem (int idBelanjaan){
        myItem = belanjaDAO.getJustItems(idBelanjaan);
        return myItem;
    }

    public void insert(Item item){
        new insertItemAsyncTask(belanjaDAO).execute(item);
    }

    public void update(Item item){
        new updateItemAsyncTask(belanjaDAO).execute(item);
    }

    public void delete(Item item){
        new deleteItemAsyncTask(belanjaDAO).execute(item);
    }

    public void deleteItemBelanjaan(int idBelanjaan){new deleteItemBelanjaanAsycTask(belanjaDAO,idBelanjaan);}


    private static class insertItemAsyncTask extends AsyncTask<Item, Void, Void>{

        private BelanjaDAO belanjaDAO;

        private insertItemAsyncTask(BelanjaDAO dao){
            this.belanjaDAO = dao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            belanjaDAO.insertItem(items[0]);
            return null;
        }
    }

    private static class updateItemAsyncTask extends AsyncTask<Item, Void, Void>{

        private BelanjaDAO belanjaDAO;

        private updateItemAsyncTask(BelanjaDAO dao){
            this.belanjaDAO = dao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            belanjaDAO.updateItem(items[0]);
            return null;
        }
    }

    private static class deleteItemAsyncTask extends AsyncTask<Item, Void, Void>{

        private BelanjaDAO belanjaDAO;

        private deleteItemAsyncTask(BelanjaDAO dao){
            this.belanjaDAO = dao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            belanjaDAO.deleteItem(items[0]);
            return null;
        }
    }

    private static class deleteItemBelanjaanAsycTask extends AsyncTask<Item, Void, Void>{
        private BelanjaDAO belanjaDAO;
        private int idBelanjaan;
        private deleteItemBelanjaanAsycTask(BelanjaDAO dao, int idBelanjaan){
            this.belanjaDAO = dao;
            this.idBelanjaan = idBelanjaan;
        }


        @Override
        protected Void doInBackground(Item... items) {
            belanjaDAO.deleteItemBelanjaan(idBelanjaan);
            return null;
        }
    }


}
