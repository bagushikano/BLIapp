package com.progmoblanjutklp1.appmemobelanja.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.progmoblanjutklp1.appmemobelanja.dao.BelanjaDAO;
import com.progmoblanjutklp1.appmemobelanja.model.Belanjaan;
import com.progmoblanjutklp1.appmemobelanja.model.BelanjaanWithBarang;

import java.util.List;

public class BelanjaanDatabaseRepository {

    private BelanjaDAO belanjaDAO;
    private LiveData<List<BelanjaanWithBarang>> belanjaAll;
    private LiveData<List<Belanjaan>> belanjas;

    public BelanjaanDatabaseRepository(Application application) {
        DatabaseBelanja db = DatabaseBelanja.getDatabase(application);
        belanjaDAO = db.dao();
        belanjaAll = belanjaDAO.getAllBelanjaan();
        belanjas = belanjaDAO.getBelanjaan();
    }

    public LiveData<List<BelanjaanWithBarang>> getAllBelanjaan(){
        return belanjaAll;
    }

    public LiveData<List<Belanjaan>> getBelanjaan(){
        return belanjas;
    }

    public void insert(Belanjaan belanjaan){
        new insertAsyncTask(belanjaDAO).execute(belanjaan);
    }

    public void update(Belanjaan belanjaan){
        new updateAsyncTask(belanjaDAO).execute(belanjaan);
    }

    public void delete(Belanjaan belanjaan){
        new deleteAsyncTask(belanjaDAO).execute(belanjaan);
    }

    private static class insertAsyncTask extends AsyncTask<Belanjaan, Void, Void>{

        private BelanjaDAO myAsyncBelanjaanDao;

        insertAsyncTask(BelanjaDAO dao){
            myAsyncBelanjaanDao = dao;
        }

        @Override
        protected Void doInBackground(Belanjaan... belanjaans) {
            myAsyncBelanjaanDao.insertBelanjaan(belanjaans[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Belanjaan, Void, Void>{
        private BelanjaDAO belanjaDAO;

        private updateAsyncTask(BelanjaDAO dao){
            this.belanjaDAO = dao;
        }

        @Override
        protected Void doInBackground(Belanjaan... belanjaans) {
            belanjaDAO.updateBelanjaan(belanjaans[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Belanjaan, Void, Void>{
        private BelanjaDAO belanjaDAO;

        private deleteAsyncTask(BelanjaDAO dao){
            this.belanjaDAO = dao;
        }

        @Override
        protected Void doInBackground(Belanjaan... belanjaans) {
            belanjaDAO.deleteBelanjaan(belanjaans[0]);
            return null;
        }
    }


}
