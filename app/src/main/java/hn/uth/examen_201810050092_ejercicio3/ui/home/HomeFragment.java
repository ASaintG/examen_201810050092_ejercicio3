package hn.uth.examen_201810050092_ejercicio3.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import hn.uth.examen_201810050092_ejercicio3.OnItemClickListener;
import hn.uth.examen_201810050092_ejercicio3.R;
import hn.uth.examen_201810050092_ejercicio3.databinding.FragmentHomeBinding;
import hn.uth.examen_201810050092_ejercicio3.entitys.Lugar;


public class HomeFragment extends Fragment implements OnItemClickListener<Lugar> {

    private FragmentHomeBinding binding;
    private LugarAdapter adaptador;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        adaptador = new LugarAdapter(new ArrayList<>(), this);
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        homeViewModel.getAllLugares().observe(getViewLifecycleOwner(), lugares -> {
            if(lugares.isEmpty()){
                Snackbar.make(binding.clHome,"No hay clientes creados", Snackbar.LENGTH_LONG).show();
            }else{
                adaptador.setItems(lugares);
            }
        });

        setupRecyclerView();

        return root;
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        binding.rvLugar.setLayoutManager(linearLayoutManager);
        binding.rvLugar.setAdapter(adaptador);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onItemClick(Lugar data, int accion) {

            Bundle bundle = new Bundle();
            bundle.putSerializable("lugar", data);

            NavController navController = Navigation.findNavController(this.getActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.navigation_dashboard, bundle);


        }
    }