package kh.edu.rupp.ite.onlineshop.ui.fragment;

import android.os.Bundle;
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

import kh.edu.rupp.ite.onlineshop.api.model.Profiles;
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
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        LoadingProfile();
        return binding.getRoot();
    }
    private void LoadingProfile(){
        binding.profileRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ProfileAdapter profileAdapter = new ProfileAdapter();
        binding.profileRecyclerView.setAdapter(profileAdapter);
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Profiles> task = apiService.loadProfileList();
        task.enqueue(new Callback<Profiles>() {
            @Override
            public void onResponse(Call<Profiles> call, Response<Profiles> response) {
                profileAdapter.setProfile(response.body());
            }

            @Override
            public void onFailure(Call<Profiles> call, Throwable t) {
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_LONG).show();
            }
        });
    }
}
