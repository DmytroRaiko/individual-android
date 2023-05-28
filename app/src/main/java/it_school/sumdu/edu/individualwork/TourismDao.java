package it_school.sumdu.edu.individualwork;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TourismDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTourism(Tourism tourism);

    @Update
    void updateTourism(Tourism tourism);

    @Delete
    void deleteTourism(Tourism tourism);

    @Query("UPDATE tourism SET city='Sarajevo' WHERE title LIKE '%Hotel Hills%'")
    void clearDB();

    @Query("SELECT * FROM tourism")
    LiveData<List<Tourism>> getAllEntities();

    @Query("SELECT DISTINCT country FROM tourism WHERE country LIKE :partString ORDER BY country ASC")
    LiveData<List<String>> getAllEntitiesUniqueCountries(String partString);

    @Query("SELECT * FROM tourism WHERE country LIKE :country AND (" +
            "title LIKE :searchPart " +
            "OR description LIKE :searchPart)")
    LiveData<List<Tourism>> getAllTourismByCountry(String country, String searchPart);

    @Query("SELECT * FROM tourism WHERE title LIKE :s " +
            "OR description LIKE :s " +
            "OR country LIKE :s")
    LiveData<List<Tourism>> search(String s);

}
