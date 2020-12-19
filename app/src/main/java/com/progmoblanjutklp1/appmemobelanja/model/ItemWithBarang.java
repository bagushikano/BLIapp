package com.progmoblanjutklp1.appmemobelanja.model;

import androidx.lifecycle.LiveData;
import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ItemWithBarang {
    @Embedded
    public Barang barang;

    @Relation(
            parentColumn = "id",
            entityColumn = "idBarang",
            entity = Item.class
    )
    public Item items;
}
