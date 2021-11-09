package com.ripalay.retrofits11.ui.film_list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ripalay.retrofits11.App;
import com.ripalay.retrofits11.R;
import com.ripalay.retrofits11.data.models.Films;
import com.ripalay.retrofits11.databinding.FragmentFilmsBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmsFragment extends Fragment {
    private FragmentFilmsBinding binding;
    private FilmsAdapter adapter;

    public FilmsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new FilmsAdapter();
        adapter.setOnClickItem(new FilmsAdapter.onClick() {
            @Override
            public void onItemClick(int position) {
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                Films film = adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString("id", film.getId());
                navController.navigate(R.id.demoFilmFragment,bundle);

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilmsBinding.inflate(inflater);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerView.setAdapter(adapter);

        App.api.getFilms().enqueue(new Callback<List<Films>>() {
            @Override
            public void onResponse(Call<List<Films>> call, Response<List<Films>> response) {
                if(response.isSuccessful() && response.body() != null){
                    adapter.setList(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<Films>> call, Throwable t) {
                Toast.makeText(requireContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}