package com.qibenyu.explore.cache;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LruCache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruCacheActivity extends Activity {

    private static final String TAG = "LruCacheActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 不支持null键，null值
     */
    private void lruTest() {
        LruCache<Integer, Integer> lruCache = new LruCache<>(2);
        lruCache.put(1, 1);
        lruCache.put(1, 1);
        lruCache.put(1, 1);
        lruCache.put(1, 1);

        for (Map.Entry<Integer, Integer> entry : lruCache.snapshot().entrySet()) {

            Log.d(TAG, "lruTest: key = " + entry.getKey() + ", value = " + entry.getValue());

        }
    }

    /**
     * sizeOf默认实现返回1，如果存放图片需要重写sizeOf
     */
    private void lruTest2() {

        LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(4 * 1024 * 1024) {

            @Override
            protected int sizeOf(@NonNull String key, @NonNull Bitmap value) {
                return value.getByteCount();
            }
        };

    }

    /**
     *
     * LinkedHashMap 是有序的，分为访问顺序和插入顺序，构造参数`accessOrder` 默认为插入顺序, 改为访问顺序填入true
     */
    private void linkedHashmapTest2() {
        LinkedHashMap<Integer,Integer> map = new LinkedHashMap<Integer, Integer>(2,0.75f,true);

    }

    /**
     * LinkedHashmap 容量可空，重写removeEldestEntry可以控制size
     */
    LinkedHashMap<Integer, Integer> map;
    private void linkedHashmapTest() {
        map = new LinkedHashMap<Integer, Integer>(2) {
            @Override
            protected boolean removeEldestEntry(Entry<Integer, Integer> eldest) {
                return map.size() > 2;
            }
        };
    }
}
