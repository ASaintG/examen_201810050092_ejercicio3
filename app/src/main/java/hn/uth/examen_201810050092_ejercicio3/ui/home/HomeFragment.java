package hn.uth.examen_201810050092_ejercicio3.ui.home;

import android.content.Intent;
import android.net.Uri;
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
        if(accion == 0){
            Bundle bundle = new Bundle();
            bundle.putSerializable("lugar", data);

            NavController navController = Navigation.findNavController(this.getActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.navigation_dashboard, bundle);
        }else if(accion ==1) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("lugar", data);

            if(data == null){
                Snackbar.make(binding.clHome, R.string.no_ubicacion, Snackbar.LENGTH_LONG).show();
            }else{
                Intent contactIntent = new Intent(Intent.ACTION_PICK, android.provider.ContactsContract.Contacts.CONTENT_URI);
                contactIntent.putExtra(Intent.EXTRA_TEXT, data.toText());

                startActivity(contactIntent);
                /*
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Mi ubicación Actual");
                shareIntent.putExtra(Intent.EXTRA_TEXT, data.toText());

                startActivity(Intent.createChooser(shareIntent, "Compartir Texto"));*/
            }
        }else {
            Bundle bundle = new Bundle();
            bundle.putSerializable("lugar", data);
            if(data == null){
                Snackbar.make(binding.clHome, R.string.no_ubicacion, Snackbar.LENGTH_LONG).show();
            }else{
                Uri mapLocation = Uri.parse("geo:"+data.toText()+"?z=14");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapLocation);
                startActivity(mapIntent);
            }

        }


    }

    }