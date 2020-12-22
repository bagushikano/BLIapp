package com.progmoblanjutklp1.appmemobelanja.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.progmoblanjutklp1.appmemobelanja.model.Barang;
import com.progmoblanjutklp1.appmemobelanja.model.Belanjaan;
import com.progmoblanjutklp1.appmemobelanja.model.BelanjaanWithBarang;
import com.progmoblanjutklp1.appmemobelanja.model.Item;
import com.progmoblanjutklp1.appmemobelanja.model.ItemWithBarang;

import java.util.List;

@Dao
public interface BelanjaDAO {

    @Transaction
    @Insert
    void insertBelanjaan(Belanjaan... belanjaans);

    @Transaction
    @Update
    void updateBelanjaan(Belanjaan... belanjaans);

    @Transaction
    @Delete
    void deleteBelanjaan(Belanjaan... belanjaans);

    @Transaction
    @Query("Select * from Item Inner join Belanjaan on idBarang = Belanjaan.id inner join barang on barang.id = idBarang")
    LiveData<List<BelanjaanWithBarang>> getAllBelanjaan();

    @Query("Select * from Belanjaan")
    LiveData<List<Belanjaan>> getBelanjaan();

    @Insert
    void insertBarang(Barang... barangs);

    @Update
    void updateBarang(Barang... barangs);

    @Delete
    void deleteBarang(Barang... barangs);

    @Transaction
    @Query("Select * from Barang")
    LiveData<List<Barang>> getAllBarang();

    @Insert
    void insertItem(Item... items);

    @Update
    void updateItem(Item... items);

    @Delete
    void deleteItem(Item... items);

    @Transaction
    @Query("Select * from Item where idBelanjaan = :idBelanjaan")
    LiveData<List<ItemWithBarang>> getItems(int idBelanjaan);

    @Transaction
    @Query("Select * from Item where idBelanjaan = :idBelanjaan")
    LiveData<List<Item>> getJustItems(int idBelanjaan);

    @Transaction
    @Query("Select * from Barang where id = :id")
    LiveData<List<Barang>> getOnlyItem(int id);


    @Query("Delete from Item where idBelanjaan = :idBelanjaan")
    void deleteItemBelanjaan(int idBelanjaan);

    @Transaction
    @Query("Select Item.*, barang.namaBarang from Item inner join barang on barang.id = Item.idBarang where idBelanjaan = :idBelanjaan")
    LiveData<List<ItemWithBarang>> getItemBarang(int idBelanjaan);

}
