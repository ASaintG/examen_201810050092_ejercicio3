package hn.uth.examen_201810050092_ejercicio3.DB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import hn.uth.examen_201810050092_ejercicio3.DAO.LugarDAO;
import hn.uth.examen_201810050092_ejercicio3.entitys.Lugar;

@Database(version = 1, exportSchema = false, entities = {Lugar.class})

public abstract class DBLugares extends RoomDatabase {
    public abstract LugarDAO lugarDao();



    private static volatile DBLugares INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    //Generando una sola instancia con el patron singleton
    public static DBLugares getDataBase(final Context context){
        if(INSTANCE == null){
            synchronized (DBLugares.class){
                if (INSTANCE == null){
                    Callback callback = new Callback(){
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db){
                            super.onCreate(db);
                            databaseWriteExecutor.execute(() -> {
                                LugarDAO testDao = INSTANCE.lugarDao();
                                testDao.deleteAll();





                            });
                        }
                    };
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),DBLugares.class,"db_recomendaciones").addCallback(callback).build();

                }
            }
        }
        return INSTANCE;
    }

}