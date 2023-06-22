package kh.edu.rupp.ite.onlineshop.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import kh.edu.rupp.ite.onlineshop.R;
import kh.edu.rupp.ite.onlineshop.databinding.ActivityMainBinding;
import kh.edu.rupp.ite.onlineshop.ui.fragment.HomeFragment;
import kh.edu.rupp.ite.onlineshop.ui.fragment.MoreFragment;
import kh.edu.rupp.ite.onlineshop.ui.fragment.ProductFragment;
import kh.edu.rupp.ite.onlineshop.ui.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        kh.edu.rupp.ite.onlineshop.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());

       showFragment(new HomeFragment());

       binding.bottomNavigationView.setOnItemSelectedListener(item -> {

           if(item.getItemId() == R.id.mnHome){
               showFragment(new HomeFragment());
           }
           else if (item.getItemId() == R.id.mnProduct){
               showFragment(new ProductFragment());
           }
           else if(item.getItemId() == R.id.mnProfile){
               showFragment(new ProfileFragment());
           } else {
               showFragment(new MoreFragment());
           }

           return true;
       });
    }
    private void showFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.lytFragment,fragment);

        fragmentTransaction.commit();

    }
}