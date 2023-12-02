package com.dcac.progressionbar.ui.fragment;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;


public class CompteurWorker extends Worker {

    public static final String EXTRA_COMPTEUR = "com.dcac.progressionbar.ui.fragment.EXTRA_COMPTEUR";
    private final static String TAG = "CompteurWorker";

    public CompteurWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }


    @NonNull
    @Override
    public Result doWork() {
        int compteur = getInputData().getInt(EXTRA_COMPTEUR, -1);
        Log.d(TAG, "Le compteur valait : " + compteur);

        int i = 0;
        while(i < 100000000) i++; // Simule un traitement long

        return Result.success();
    }
}
