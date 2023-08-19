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

                                testDao.insert(new Lugar("San Pedro Sula","Valle",23.23,23.23,"Test San pedro",3));
                                 testDao.insert(new Lugar("Villanueva","Valle",25.23,26.23,"Test Villanueva",3));
                                 testDao.insert(new Lugar("Lago de Yojoa","Valle",25.23,26.23,"Test Lago",3));
                                /**
                                 ContactoDAO contactoDao = INSTANCE.contactoDAO();
                                 contactoDao.deleteAll();
                                 contactoDao.insert(new Contacto("Moises Torres","722435631","moises.torres@gmail.com","San Pedro Sula"));
                                 contactoDao.insert(new Contacto("Alejandra Ayala","722435631","ale.torres@gmail.com","San Pedro Sula"));
                                 contactoDao.insert(new Contacto("Moises Torres","722435631","moises.torres@gmail.com","San Pedro Sula"));
                                 contactoDao.insert(new Contacto("Moises Torres","722435631","moises.torres@gmail.com","San Pedro Sula"));*/




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