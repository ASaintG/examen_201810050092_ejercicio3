package hn.uth.examen_201810050092_ejercicio3.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import hn.uth.examen_201810050092_ejercicio3.entitys.Lugar;

@Dao
public interface LugarDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Lugar nuevo);


    @Update
    void update(Lugar actualizar);
    @Delete
    void delete(Lugar eliminar);

    @Query("DELETE FROM lugar_table")
    void deleteAll();

    @Query("SELECT * FROM lugar_table ORDER BY  lugar ASC")
    LiveData<List<Lugar>> getLugar();


}
