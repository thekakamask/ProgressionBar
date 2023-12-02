package com.dcac.progressionbar.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dcac.progressionbar.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

}