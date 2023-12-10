package app.main;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public abstract interface TasksDAO {

    @Query("select * from tasks")
    public List<Task> getAll();

    @Insert
    public void insert(Task task);

    @Insert
    public long insertReturnId(Task task);

    @Insert
    public void insertAll(List<Task> task);

    @Delete
    public void delete(Task task);

    @Query("delete from tasks where uid = :id")
    public void deleteById(int id);

    @Query("delete from tasks")
    public void deleteAll();

    @Update
    public void update(Task task);

    @Update
    public int updateReturnId(Task task);


}
