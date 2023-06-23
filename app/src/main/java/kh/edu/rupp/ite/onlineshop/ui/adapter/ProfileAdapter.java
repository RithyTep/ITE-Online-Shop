package kh.edu.rupp.ite.onlineshop.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import kh.edu.rupp.ite.onlineshop.api.model.Profiles;
import kh.edu.rupp.ite.onlineshop.databinding.ViewHolderProfileBinding;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private final List<Profiles> profileData = new ArrayList<>();    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderProfileBinding binding = ViewHolderProfileBinding.inflate(layoutInflater, parent, false);
        return new ProfileViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        Profiles profiles = profileData.get(position);
        holder.bind(profiles);
    }

    @Override
    public int getItemCount() {
        return profileData.size();
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setProfile(Profiles profiles){
        profileData.clear();
        profileData.add(profiles);
        notifyDataSetChanged();
    }

    protected static class ProfileViewHolder extends RecyclerView.ViewHolder {
        private final ViewHolderProfileBinding itemBinding;
        public ProfileViewHolder(@NonNull ViewHolderProfileBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
        public void bind (Profiles profiles){
            Picasso.get().load(profiles.getImageUrl()).into(itemBinding.imgCourses);
            itemBinding.txtFullName.setText(profiles.getFirst_name() + " " + profiles.getLast_name());
            itemBinding.txtDes.setText(profiles.getEmail());
            itemBinding.txtEmail.setText(profiles.getEmail());
            itemBinding.txtPH.setText(profiles.getPhoneNumber());
            itemBinding.txtGender.setText(profiles.getGender());
            itemBinding.txtBirthday.setText(profiles.getBirthday());
            itemBinding.txtAddress.setText(profiles.getAddress());
        }
    }

}
