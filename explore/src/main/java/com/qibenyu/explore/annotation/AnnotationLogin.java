package com.qibenyu.explore.annotation;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AnnotationLogin implements ILogin {

    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public void login(Context context, int type) {

        switch (type) {
            case 0:
                context.startActivity(new Intent(context, LoginActivity.class));
                break;
            case 1:
                Toast.makeText(context,"请登陆", Toast.LENGTH_LONG).show();
                break;
            default:
                break;

        }

    }
}
