package antho.com.realestatemanager.db.repository;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import antho.com.realestatemanager.base.AppDatabase;
import antho.com.realestatemanager.db.Service;
import antho.com.realestatemanager.db.entity.EstateEntity;
import antho.com.realestatemanager.helpers.AppExecutors;

public class DataRepository
{
    private Service service;
    private LiveData<EstateEntity> mMood;
    private LiveData<List<EstateEntity>> mAllMoods;
    // Constructor - Gets a handle to the database and initializes the members variables
    public DataRepository(Application application)
    {
        AppExecutors executors = new AppExecutors();
        AppDatabase db = AppDatabase.getDatabase(application, executors);
        service = db.service();
        mAllMoods = service.getAllEstates();
        mMood = service.getEstate();
    }
    // Wrapper - Observed LiveData will notify the observer when the data has changed
    public LiveData<List<EstateEntity>> getAllMoods()
    {
        return mAllMoods;
    }
    public LiveData<EstateEntity> getMood()
    {
        return mMood;
    }
    // Wrapper - Load
    public EstateEntity load(String type)
    {
        return service.load(type);
    }
    // Wrapper - Update
    public void update(EstateEntity mood)
    {
        new updateAsyncTask(service).execute(mood);
    }
    private static class updateAsyncTask extends AsyncTask<EstateEntity,Void,Void>
    {
        private Service mAsyncTaskDao;
        updateAsyncTask(Service dao)
        {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(EstateEntity... estateEntities) {
            mAsyncTaskDao.update(estateEntities[0]);
            return null;
        }
    }
}
