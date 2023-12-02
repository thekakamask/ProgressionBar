package com.dcac.progressionbar.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dcac.progressionbar.R;
import com.dcac.progressionbar.databinding.FragmentNumberToConsoleBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NumberToConsoleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NumberToConsoleFragment extends Fragment {

    FragmentNumberToConsoleBinding binding;
    private int mCompteur = 0;
    public static NumberToConsoleFragment newInstance() {
        return new NumberToConsoleFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNumberToConsoleBinding.inflate(inflater, container, false);

        int backgroundColor = ContextCompat.getColor(getContext(), R.color.light_orange);

        binding.buttonNumberConsole.setBackgroundColor(backgroundColor);
        binding.buttonNumberConsole.setEnabled(true);

        // Initialisez le compteur et le TextView.
        binding.affichageNumberConsole.setText(String.valueOf(mCompteur));

        // Configurez le listener pour le bouton.
        binding.buttonNumberConsole.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), IntentServiceExample.class);
            i.putExtra(IntentServiceExample.EXTRA_COMPTEUR, mCompteur);


            mCompteur++;
            binding.affichageNumberConsole.setText(String.valueOf(mCompteur));

            // Assurez-vous que le service est déclaré dans votre fichier AndroidManifest.xml
            getActivity().startService(i);
        });

        return binding.getRoot();
    }
}