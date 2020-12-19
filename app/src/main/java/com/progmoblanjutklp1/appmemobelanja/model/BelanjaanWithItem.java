package com.progmoblanjutklp1.appmemobelanja.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;


public class BelanjaanWithItem {
    @Embedded public Belanjaan belanjaan;
    @Relation(
            parentColumn = "id",
            entityColumn = "idBelanjaan"
    )
    public List<Item> items;

    public BelanjaanWithItem(Belanjaan belanjaan, List<Item> items) {
        this.belanjaan = belanjaan;
        this.items = items;
    }
}
