package com.qibenyu.explore.broadcast;

import android.content.IntentFilter;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HackAMS {

    public static void hookAMSAfter26() throws Exception {
        // 第一步：获取 IActivityManagerSingleton
        Class<?> aClass = Class.forName("android.app.ActivityManager");
        Field declaredField = aClass.getDeclaredField("IActivityManagerSingleton");
        declaredField.setAccessible(true);
        Object value = declaredField.get(null);

        Class<?> singletonClz = Class.forName("android.util.Singleton");
        Field instanceField = singletonClz.getDeclaredField("mInstance");
        instanceField.setAccessible(true);
        Object iActivityManagerObject = instanceField.get(value);

        // 第二步：获取我们的代理对象，这里因为 IActivityManager 是接口，我们使用动态代理的方式
        Class<?> iActivity = Class.forName("android.app.IActivityManager");
        InvocationHandler handler = new AMSInvocationHandler(iActivityManagerObject);
        Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new
                Class<?>[]{iActivity}, handler);

        // 第三步：偷梁换柱，将我们的 proxy 替换原来的对象
        instanceField.set(value, proxy);

    }

    static class AMSInvocationHandler implements InvocationHandler {

        private static final String TAG = "AMSInvocationHandler";

        Object iamObject;

        public AMSInvocationHandler(Object iamObject) {
            this.iamObject = iamObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //            Log.e(TAG, method.getName());
            if ("startActivity".equals(method.getName())) {
                Log.i(TAG, "ready to startActivity");
//                for (Object object : args) {
//                    Log.d(TAG, "invoke: object=" + object);
//                }
            } else if ("registerReceiver".equals(method.getName())) {
                for (Object object : args) {
                    Log.d(TAG, "invoke: object=" + object);
                }
//                Object object = args[2];
//                Field[] dispatcherField = object.getClass().getFields();
//
//                for (Field field : dispatcherField) {
//                    Log.d(TAG, "invoke: filed name = " + field.getName());
//
//                }

//                dispatcherField.setAccessible(true);

//                dispatcherField.setAccessible(true);
//                Object dispatcher = dispatcherField.get(object);
//                Class<?> dispatcherClazz = Class.forName(CLS_ReceiverDispatcher);
//                Field fieldActs = dispatcherClazz.getDeclaredField(HK_mReceiver);
//                fieldActs.setAccessible(true);
//                Object receiverObject = fieldActs.get(dispatcher);

//                Log.d(TAG, "invoke: receiverObject = " + object.getClass() + ", num = " + dispatcherField.length);

            }
            return method.invoke(iamObject, args);
        }

    }

    public static final String CLS_ReceiverDispatcher = "android.app.LoadedApk$ReceiverDispatcher";
    public static final String HK_mReceiver = "mReceiver";

}
