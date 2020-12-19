package com.progmoblanjutklp1.appmemobelanja.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class BelanjaanWithBarang {
    @Embedded
    public Belanjaan belanjaan;

    @Relation(
            parentColumn = "id",
            entityColumn = "idBelanjaan",
            entity = Item.class,
            projection = "idBarang"
    )
    public List<Barang> barangs;

}
