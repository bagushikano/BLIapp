package com.progmoblanjutklp1.appmemobelanja.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.progmoblanjutklp1.appmemobelanja.dao.BelanjaDAO;
import com.progmoblanjutklp1.appmemobelanja.model.Barang;
import com.progmoblanjutklp1.appmemobelanja.model.Belanjaan;
import com.progmoblanjutklp1.appmemobelanja.model.BelanjaanWithBarang;
import com.progmoblanjutklp1.appmemobelanja.model.BelanjaanWithItem;
import com.progmoblanjutklp1.appmemobelanja.model.Item;

@androidx.room.Database(entities = {Barang.class, Belanjaan.class, Item.class},version = 2)
public abstract class DatabaseBelanja extends RoomDatabase {

    public abstract BelanjaDAO dao();

    private static volatile DatabaseBelanja INSTANCE;

    static DatabaseBelanja getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (DatabaseBelanja.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DatabaseBelanja.class,"belanja_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
