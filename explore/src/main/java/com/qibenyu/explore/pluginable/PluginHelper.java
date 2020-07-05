package com.qibenyu.explore.pluginable;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Method;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;

public class PluginHelper {

    private static final String CLASS_DEX_PATH_LIST = "dalvik.system.DexPathList";
    private static final String FIELD_PATH_LIST = "pathList";
    private static final String FIELD_DEX_ELEMENTS = "dexElements";
    private static Resources sPluginResources;

    public static void loadPlugin(Context context, ClassLoader hostClassLoader) throws Exception {
        loadPluginClass(context, hostClassLoader);
        initPluginResource(context);
        Toast.makeText(context, "插件加载成功", Toast.LENGTH_SHORT).show();
    }

    private static void loadPluginClass(Context context, ClassLoader hostClassLoader) throws Exception {

        File file = context.getFilesDir();

        if (file == null || !file.exists() || file.listFiles().length == 0) {
            Toast.makeText(context, "plugin not exists", Toast.LENGTH_SHORT).show();
            return;
        }

        File pluginFile = file.listFiles()[0];

        DexClassLoader pluginDexLoader = new DexClassLoader(pluginFile.getAbsolutePath(),
                null, null, hostClassLoader);

        Object pluginDexPathList = ReflectUtils.getField(BaseDexClassLoader.class, pluginDexLoader, FIELD_PATH_LIST);

        Object pluginElements =
                ReflectUtils.getField(Class.forName(CLASS_DEX_PATH_LIST), pluginDexPathList, FIELD_DEX_ELEMENTS);

        Object hostDexPathList =
                ReflectUtils.getField(BaseDexClassLoader.class, hostClassLoader, FIELD_PATH_LIST);

        Object hostElements =
                ReflectUtils.getField(Class.forName(CLASS_DEX_PATH_LIST), hostDexPathList, FIELD_DEX_ELEMENTS);

        Object array = combineArray(hostElements, pluginElements);

        // Step8. 将合并的dexElements设置到宿主ClassLoader
        ReflectUtils.setField(Class.forName(CLASS_DEX_PATH_LIST), hostDexPathList, FIELD_DEX_ELEMENTS, array);
    }

    private static Object combineArray(Object hostElements, Object pluginElements) {
        Class<?> componentType = hostElements.getClass().getComponentType();
        int i = Array.getLength(hostElements);
        int j = Array.getLength(pluginElements);
        int k = i + j;
        Object result = Array.newInstance(componentType, k);
        System.arraycopy(pluginElements, 0, result, 0, j);
        System.arraycopy(hostElements, 0, result, j, i);
        return result;
    }

    public static void initPluginResource(Context context) throws Exception {
        Class<AssetManager> clazz = AssetManager.class;
        AssetManager assetManager = clazz.newInstance();
        Method method = clazz.getMethod("addAssetPath", String.class);
        method.invoke(assetManager, context.getExternalFilesDir("plugin").listFiles()[0].getAbsolutePath());
        sPluginResources = new Resources(assetManager, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
    }

    public static Resources getPluginResources() {
        return sPluginResources;
    }
}
