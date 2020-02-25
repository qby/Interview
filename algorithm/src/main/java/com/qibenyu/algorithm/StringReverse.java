package com.qibenyu.algorithm;


import org.jetbrains.annotations.NotNull;

/**
 *
 * 算法 "www.toutiao.cn" 反转成 "cn.toutiao.www"
 */
public class StringReverse implements IAlgorithm {

    @NotNull
    @Override
    public String problem() {
        return null;
    }

    @NotNull
    @Override
    public String condition() {
        return null;
    }

    @NotNull
    @Override
    public String answer() {
        return reverse("www.toutiao.cn");
    }

    @NotNull
    @Override
    public String thought() {
        return null;
    }

    private String reverse(String s) {

        String[] a = s.split("\\.");
        StringBuilder builder = new StringBuilder();

        for (int i = a.length - 1; i >= 0; i--) {

            builder.append(a[i]);

            if (i != 0) {
                builder.append(".");
            }
        }

        return builder.toString();

    }
}
