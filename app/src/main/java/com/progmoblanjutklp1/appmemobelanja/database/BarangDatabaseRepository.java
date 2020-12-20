package com.progmoblanjutklp1.appmemobelanja.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.progmoblanjutklp1.appmemobelanja.dao.BelanjaDAO;
import com.progmoblanjutklp1.appmemobelanja.model.Barang;
import com.progmoblanjutklp1.appmemobelanja.model.Item;

import java.util.List;

public class BarangDatabaseRepository {

    private BelanjaDAO belanjaDAO;
    private LiveData<List<Barang>> barangsAll;
    private LiveData<List<Barang>> barangs;

    public BarangDatabaseRepository(Application application) {
       DatabaseBelanja db = DatabaseBelanja.getDatabase(application);
       belanjaDAO = db.dao();
        barangsAll = belanjaDAO.getAllBarang();
    }

    public LiveData<List<Barang>> getBarangsAll(){return barangsAll;}

    public LiveData<List<Barang>> getOnlyItem(int id){
        barangs = belanjaDAO.getOnlyItem(id);
        return barangs;
    }

    public void insert(Barang barang){
        new insertBarangAsyncTask(belanjaDAO).execute(barang);
    }

    public void update(Barang barang){
        new updateBarangAsyncTask(belanjaDAO).execute(barang);
    }

    public void delete(Barang barang){
        new deleteBarangAsyncTask(belanjaDAO).execute(barang);
    }


    private static class insertBarangAsyncTask extends AsyncTask<Barang, Void, Void>{

        private BelanjaDAO belanjaDAO;

        private insertBarangAsyncTask(BelanjaDAO dao){
            this.belanjaDAO = dao;
        }

        @Override
        protected Void doInBackground(Barang... barangs) {
            belanjaDAO.insertBarang(barangs[0]);
            return null;
        }
    }

    private static class updateBarangAsyncTask extends AsyncTask<Barang, Void, Void>{

        private BelanjaDAO belanjaDAO;

        private updateBarangAsyncTask(BelanjaDAO dao){
            this.belanjaDAO = dao;
        }

        @Override
        protected Void doInBackground(Barang... barangs) {
            belanjaDAO.updateBarang(barangs[0]);
            return null;
        }
    }

    private static class deleteBarangAsyncTask extends AsyncTask<Barang, Void, Void>{

        private BelanjaDAO belanjaDAO;

        private deleteBarangAsyncTask(BelanjaDAO dao){
            this.belanjaDAO = dao;
        }

        @Override
        protected Void doInBackground(Barang... barangs) {
            belanjaDAO.deleteBarang(barangs[0]);
            return null;
        }
    }
}
