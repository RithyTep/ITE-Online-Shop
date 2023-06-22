package kh.edu.rupp.ite.onlineshop.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import kh.edu.rupp.ite.onlineshop.api.model.Profile;
import kh.edu.rupp.ite.onlineshop.databinding.ViewHolderProfileBinding;

public class ProfileAdapter extends ListAdapter<Profile,ProfileAdapter.ProfileViewHolder> {

    public ProfileAdapter() {
        super(new DiffUtil.ItemCallback<Profile>() {
            @Override
            public boolean areItemsTheSame(@NonNull Profile oldItem, @NonNull Profile newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Profile oldItem, @NonNull Profile newItem) {
                return oldItem.getImageUrl().equals(newItem.getImageUrl());
            }
        });
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
     ViewHolderProfileBinding binding = ViewHolderProfileBinding.inflate(layoutInflater,parent,false);
     return new ProfileViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        Profile item = getItem(position);
        holder.bind(item);
    }

    public static class ProfileViewHolder extends RecyclerView.ViewHolder{

        private final ViewHolderProfileBinding itemBinding;

        public ProfileViewHolder(ViewHolderProfileBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
        public void bind(@NonNull Profile profile){
            Picasso.get().load(profile.getImageUrl()).into(itemBinding.imgCourses);
            itemBinding.txtFullName.setText(profile.getFirstName()+profile.getLastName());
            itemBinding.txtDes.setText(profile.getEmail());
            itemBinding.txtEmail.setText(profile.getEmail());
            itemBinding.txtPH.setText(profile.getPhoneNumber());
            itemBinding.txtGender.setText(profile.getGender());
            itemBinding.txtBirthday.setText(profile.getBirthDay());
            itemBinding.txtAddress.setText(profile.getAddress());
        }
    }
}
