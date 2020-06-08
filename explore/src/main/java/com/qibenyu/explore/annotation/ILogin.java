package com.qibenyu.explore.annotation;

import android.content.Context;

public interface ILogin {

    boolean isLogin();

    void login(Context context, int type);

}
