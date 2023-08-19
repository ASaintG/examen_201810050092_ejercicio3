package hn.uth.examen_201810050092_ejercicio3.Repo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import hn.uth.examen_201810050092_ejercicio3.DAO.LugarDAO;
import hn.uth.examen_201810050092_ejercicio3.DB.DBLugares;
import hn.uth.examen_201810050092_ejercicio3.entitys.Lugar;

public class LugarRepository {
    private LugarDAO lugarDao;
    private LiveData<List<Lugar>> dataset;


    public LugarRepository(Application app ) {
        DBLugares db = DBLugares.getDataBase(app);
        this.lugarDao = db.lugarDao();
        this.dataset = lugarDao.getLugar();

    }


    public LiveData<List<Lugar>> getAllLugares()
    {
        return lugarDao.getLugar();
    }

    public void insertLugar(Lugar lugar) {
        DBLugares.databaseWriteExecutor.execute(() -> {
            lugarDao.insert(lugar);
        });
    }

    public void updateLugar(Lugar lugar) {
        DBLugares.databaseWriteExecutor.execute(() -> {
            lugarDao.update(lugar);
        });
    }

    public void deleteLugar(Lugar lugar) {
        DBLugares.databaseWriteExecutor.execute(() -> {
            lugarDao.delete(lugar);
        });
    }
}
