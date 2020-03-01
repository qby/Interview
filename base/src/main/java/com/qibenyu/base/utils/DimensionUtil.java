package com.qibenyu.base.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;

/**
 * UI适配方案
 */
public class DimensionUtil {

    private static float sNoncompatDensity;
    private static float sNoncompatScaleDensity;

    public static void setCustomDensity(@NonNull Activity activity, @NonNull final Application application) {
        final DisplayMetrics metrics = application.getResources().getDisplayMetrics();

        if (sNoncompatDensity == 0) {
            sNoncompatDensity = metrics.density;
            sNoncompatScaleDensity = metrics.scaledDensity;

            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sNoncompatScaleDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        final float targetDensity = metrics.widthPixels / 360f;
        final float targetScaledDensity = targetDensity * (sNoncompatScaleDensity / sNoncompatDensity);
        final int targetDensityDpi = (int) (160 * targetDensity);

        metrics.density = targetDensity;
        metrics.scaledDensity = targetScaledDensity;
        metrics.densityDpi = targetDensityDpi;

        DisplayMetrics thisDisplayMetrics = activity.getResources().getDisplayMetrics();
        thisDisplayMetrics.density = targetDensity;
        thisDisplayMetrics.scaledDensity = targetScaledDensity;
        thisDisplayMetrics.densityDpi = targetDensityDpi;


    }
}
