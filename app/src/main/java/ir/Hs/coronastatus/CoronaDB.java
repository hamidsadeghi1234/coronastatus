package ir.Hs.coronastatus;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import ir.Hs.coronastatus.Views.models.CountryTable;
import ir.Hs.coronastatus.interfaces.CoronaDao;

@Database(entities = {CountryTable.class} , version=9)

public abstract class CoronaDB extends RoomDatabase {
    public  abstract CoronaDao coronaDao();
}