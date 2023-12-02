package com.dcac.progressionbar.ui.fragment;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dcac.progressionbar.R;
import com.dcac.progressionbar.databinding.FragmentProgressBarBinding;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProgressBarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProgressBarFragment extends Fragment {

    FragmentProgressBarBinding binding;
    private AlertDialog progressDialog;
    private ProgressBar progressBar;
    private TextView tvProgressPercentage;

    public static ProgressBarFragment newInstance() {
        ProgressBarFragment fragment = new ProgressBarFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProgressBarBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int backgroundColor = ContextCompat.getColor(getContext(), R.color.light_orange);

        binding.buttonStartDownload.setBackgroundColor(backgroundColor);


        binding.buttonStartDownload.setEnabled(true);
        binding.buttonStartDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDownloadDialog();
                startDownload();
            }
        });

    }

    private void showDownloadDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_progress, null);
        progressBar = dialogView.findViewById(R.id.progress_bar);
        tvProgressPercentage = dialogView.findViewById(R.id.tv_progress_percentage);

        builder.setView(dialogView);
        builder.setCancelable(true);
        progressDialog = builder.create();
        progressDialog.show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private void startDownload() {
        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            for (int i = 0; i <= 100; i+=5) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                int finalI=i;
                handler.post(() -> updateProgress(finalI));
            }
            handler.post(this::downloadComplete);
        });
    }

    private void updateProgress (int progress) {

        if (progressBar != null) {
            progressBar.setProgress(progress);
            tvProgressPercentage.setText(String.valueOf(progress) + "%");
        }
    }

    private void downloadComplete() {

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        Toast.makeText(getActivity(), "Download finish", Toast.LENGTH_SHORT).show();
    }




}