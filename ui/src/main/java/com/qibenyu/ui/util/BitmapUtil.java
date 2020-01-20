package com.qibenyu.ui.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.qibenyu.ui.R;


public class BitmapUtil {


    public static Bitmap getContent(Resources resource, int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resource, R.drawable.maps, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(resource, R.drawable.maps, options);
    }
}
