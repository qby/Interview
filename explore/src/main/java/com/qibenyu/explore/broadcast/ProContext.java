package com.qibenyu.explore.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;

public class ProContext extends ContextWrapper {

    private Context mBase;

    public ProContext(Context base) {
        super(base);
        mBase = base;
    }

    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {

        return mBase.registerReceiver(receiver, filter);

    }
}
