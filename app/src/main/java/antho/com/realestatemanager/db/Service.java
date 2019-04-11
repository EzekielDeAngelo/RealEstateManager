package antho.com.realestatemanager.db;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import antho.com.realestatemanager.db.entity.EstateEntity;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface Service
{
    // SQL query for viewModel to get all estates from estate table
    @Query("SELECT * FROM estate_table")
    LiveData<List<EstateEntity>> getAllEstates();
    // SQL query for viewModel to get the estate of the day from estate table
    @Query("select * from estate_table where type = 0")
    LiveData<EstateEntity> getEstate();
    // SQL query to insert a estate into estate table
    @Insert(onConflict = IGNORE)
    void insert(EstateEntity estateEntity);
    // SQL query to update an estate from estate table
    @Update
    void update(EstateEntity ... estateEntities);
    // SQL query to load a estate from estate table
    @Query("select * from estate_table where type = :type")
    EstateEntity load(String type);
}

