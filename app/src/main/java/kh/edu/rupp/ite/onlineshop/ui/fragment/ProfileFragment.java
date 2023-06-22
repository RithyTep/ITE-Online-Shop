package kh.edu.rupp.ite.onlineshop.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import kh.edu.rupp.ite.onlineshop.api.model.Profile;
import kh.edu.rupp.ite.onlineshop.api.service.ApiService;
import kh.edu.rupp.ite.onlineshop.databinding.FragmentProfileBinding;
import kh.edu.rupp.ite.onlineshop.ui.adapter.ProfileAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadProfileListFromServer();
    }

    private void loadProfileListFromServer(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit httpClient = new Retrofit.Builder()
                .baseUrl("https://ferupp.s3.ap-southeast-1.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        ApiService apiService = httpClient.create(ApiService.class);

        Call<List<Profile>> task = apiService.loadProfileList();

        task.enqueue(new Callback<List<Profile>>() {

            @Override
            public void onResponse(@NonNull Call<List<Profile>> call, @NonNull Response<List<Profile>> response) {
                if (response.isSuccessful()) {
                    showProfileList(response.body());
                } else {
                    Toast.makeText(getContext(), "Load province list failed!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Profile>> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Load profile list failed!", Toast.LENGTH_LONG).show();
                Log.e("[ProfileFragment]", "Load profile failed: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
    private void showProfileList(List<Profile> profileList) {
        // Create layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.profileRecyclerView.setLayoutManager(layoutManager);


        // Create adapter
       ProfileAdapter adapter = new ProfileAdapter();
        adapter.submitList(profileList);
        binding.profileRecyclerView.setAdapter(adapter);
    }
}

