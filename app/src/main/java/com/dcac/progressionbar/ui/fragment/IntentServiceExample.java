package com.dcac.progressionbar.ui.fragment;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class IntentServiceExample extends IntentService {
    private final static String TAG = "IntentServiceExample";
    public final static String EXTRA_COMPTEUR = "com.dcac.progressionbar.ui.fragment.EXTRA_COMPTEUR";

    public IntentServiceExample() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            Log.d(TAG, "Le compteur valait : " + intent.getIntExtra(EXTRA_COMPTEUR, -1));
            int i = 0;
            // Cette boucle permet de rajouter artificiellement du temps de traitement
            while(i < 100000000) i++;
        }
    }
}
