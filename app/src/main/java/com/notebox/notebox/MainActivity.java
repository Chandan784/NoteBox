package com.notebox.notebox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.widget.FrameLayout;

import com.notebox.notebox.Fragments.HomeFragment;
import com.notebox.notebox.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

loadFragment(new HomeFragment());

    }

 void loadFragment(Fragment fragment){

     FragmentManager fragmentManager = getSupportFragmentManager();
     FragmentTransaction fragmentTransaction =
             fragmentManager.beginTransaction();
     fragmentTransaction.replace(android.R.id.content, fragment);
     fragmentTransaction.commit();
 }
}