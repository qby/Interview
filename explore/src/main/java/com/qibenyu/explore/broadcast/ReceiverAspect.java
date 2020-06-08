package com.qibenyu.explore.broadcast;

import android.util.Log;

import androidx.annotation.LongDef;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


@Aspect
public class ReceiverAspect {
    private static final String TAG = "ReceiverAspect";

    private AtomicInteger integer = new AtomicInteger();

    @Pointcut("execution(@com.qibenyu.lib.ReceiverFilter * *(..))")
    public void method() {}

    @Around("method()")
    public Object logAndExecute(ProceedingJoinPoint joinPoint) throws Throwable {

        Signature signature = joinPoint.getSignature();
        MethodSignature ms = (MethodSignature) signature;


        Method method = ms.getMethod();
        Class c = method.getDeclaringClass();
        Log.d(TAG, "logAndExecute: c = " + c.getCanonicalName());

        if (integer.get() < 1000) {
            integer.incrementAndGet();
        }

        Object result = joinPoint.proceed();


        return result;
    }
}
