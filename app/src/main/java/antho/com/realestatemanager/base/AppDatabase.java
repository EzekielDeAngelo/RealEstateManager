package antho.com.realestatemanager.base;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import antho.com.realestatemanager.db.Service;
import antho.com.realestatemanager.helpers.AppExecutors;
/** Database layer on top of SQLite database, uses the service to issue queries to its database **/
public abstract class AppDatabase extends RoomDatabase
{
    public abstract Service service();
    private static AppDatabase instance;
    private static final String DATABASE_NAME = "estate_database";
    // Constructor - Makes AppDatabase a singleton to prevent having multiple instances of the database opened at the same time
    public static AppDatabase getDatabase(final Context context, final AppExecutors executors)
    {
        if (instance == null)
        {
            synchronized (AppDatabase.class)
            {
                if (instance == null)
                {
                    instance = buildDatabase(context.getApplicationContext(), executors);
                }
            }
        }
        return instance;
    }
    // Build database method - Uses Room's database builder to create a RoomDatabase object in the application context
    private static AppDatabase buildDatabase(final Context appContext, final AppExecutors executors)
    {
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback()
                {
                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db)
                    {
                        super.onCreate(db);
                        executors.diskIO().execute(() ->
                        {
                            new DataGenerator(instance).execute();
                        });
                    }
                }).build();
    }
}
