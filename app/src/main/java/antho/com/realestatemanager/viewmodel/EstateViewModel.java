package antho.com.realestatemanager.viewmodel;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import antho.com.realestatemanager.db.entity.EstateEntity;
import antho.com.realestatemanager.db.repository.DataRepository;

public class EstateViewModel extends AndroidViewModel
{
    private DataRepository mRepository;
    private final LiveData<List<EstateEntity>> mAllEstates;
    private final LiveData<EstateEntity> mEstate;
    // Constructor - Gets a reference to the repository and gets variables from it
    public EstateViewModel(Application application)
    {
        super(application);
        mRepository = new DataRepository(application);
        mAllEstates = mRepository.getAllMoods();
        mEstate = mRepository.getMood();
    }
    // Getter - Expose the LiveData getAllMoods query so the UI can observe it
    public LiveData<List<EstateEntity>> getAllMoods()
    {
        return mAllEstates;
    }
    // Getter - Expose the LiveData getMood query so the UI can observe it
    public LiveData<EstateEntity> getMood()
    {
        return mEstate;
    }
    // Wrapper - Calls repository's update method
    public void update(EstateEntity estateEntity)
    {
        mRepository.update(estateEntity);
    }
}
