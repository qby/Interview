package com.qibenyu.explore.annotation;

import android.os.Debug;
import android.os.Trace;
import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class TraceInsertAspect {

    private static final String TAG = "TraceInsertAspect";
    @Pointcut("execution(@com.qibenyu.explore.annotation.TraceInsert * *(..))")
    public void methodExecution() {

    }

    @Around("methodExecution()")
    public void aspect(ProceedingJoinPoint joinPoint) {

        Log.d(TAG, "aspect() called with: joinPoint = [" + joinPoint + "]");

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        TraceInsert annotation = methodSignature.getMethod().getAnnotation(TraceInsert.class);
        if (annotation == null) {
            return ;
        }

        Log.d(TAG, "aspect: begin = " + methodSignature.getName());

        Debug.startMethodTracing(methodSignature.getName());
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        Debug.stopMethodTracing();
        Log.d(TAG, "aspect: end = " + methodSignature.getName());
    }
}
