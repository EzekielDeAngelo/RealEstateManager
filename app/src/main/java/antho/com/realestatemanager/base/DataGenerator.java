package antho.com.realestatemanager.base;

import android.os.AsyncTask;

import java.time.ZonedDateTime;

import antho.com.realestatemanager.base.AppDatabase;
import antho.com.realestatemanager.db.Service;
import antho.com.realestatemanager.db.entity.EstateEntity;

public class DataGenerator extends AsyncTask<Void,Void,Void>
{
    private final Service service;
    // Constructor
    DataGenerator(AppDatabase db)
    {
        service = db.service();
    }
    // Data generator method - Populates database on first start
    @Override
    protected Void doInBackground(final Void... params)
    {
        ZonedDateTime date = ZonedDateTime.now();

        EstateEntity estate = new EstateEntity("0","4" ,"a","a","a","a", "a","a",false, date, date);
        service.insert(estate);
        return null;
    }
}
