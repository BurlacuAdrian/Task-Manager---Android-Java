package app.main;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@TypeConverters(value = {DateConverter.class})
@Database(entities = {Task.class},version = 1,exportSchema = false)
public abstract class TasksDB extends RoomDatabase {

    private static TasksDB instance=null;
    private static final String DB_FILE_NAME="Tasks.db";

    public static TasksDB getInstance(Context context){
        if(instance!=null)
            return instance;

        instance= Room.databaseBuilder(context,TasksDB.class,DB_FILE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        return instance;
    }

    public abstract TasksDAO getDAO();

}
