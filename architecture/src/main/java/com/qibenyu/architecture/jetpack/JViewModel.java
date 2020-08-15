package com.qibenyu.architecture.jetpack;

import android.app.Application;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;


public class JViewModel extends AndroidViewModel {

    private Handler handler = new Handler();

    public JViewModel(@NonNull Application application) {
        super(application);
    }

    public void initViewBean() {
        handler.postDelayed(run, 3000);
    }

    private Runnable run = new Runnable() {
        @Override
        public void run() {

            handler.postDelayed(run, 3000);
        }
    };

}
