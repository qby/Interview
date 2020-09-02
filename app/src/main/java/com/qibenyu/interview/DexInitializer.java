package com.qibenyu.interview;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;
import androidx.startup.Initializer;

import java.util.Collections;
import java.util.List;

public class DexInitializer implements Initializer {

    @NonNull
    @Override
    public Object create(@NonNull Context context) {
        MultiDex.install(context);
        return null;
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        return Collections.emptyList();
    }
}
