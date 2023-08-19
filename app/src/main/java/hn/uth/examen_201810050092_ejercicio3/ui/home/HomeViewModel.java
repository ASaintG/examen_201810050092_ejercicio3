package hn.uth.examen_201810050092_ejercicio3.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import hn.uth.examen_201810050092_ejercicio3.Repo.LugarRepository;
import hn.uth.examen_201810050092_ejercicio3.entitys.Lugar;

public class HomeViewModel extends AndroidViewModel {
    private LugarRepository repository;
    private final LiveData<List<Lugar>> dataset;




    public HomeViewModel(@NonNull Application app) {
        super(app);
        this.repository=new LugarRepository(app);
        this.dataset=repository.getAllLugares();


    }

    public LiveData<List<Lugar>> getAllLugares(){
        return dataset;
    }


}