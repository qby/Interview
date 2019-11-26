package com.qibenyu.algorithm;

import org.jetbrains.annotations.NotNull;

public class MinimumTimeVisitingAllPoints implements IAlgorithm {

    @NotNull
    @Override
    public String problem() {
        return "平面上有 n 个点，点的位置用整数坐标表示 points[i] = [xi, yi]。\n请你计算访问所有这些点需要的最小时间（以秒为单位）。" +
                "每一秒沿水平或者竖直方向移动一个单位长度，或者跨过对角线（可以看作在一秒内向水平和竖直方向各移动一个单位长度）。\n" +
                "必须按照数组中出现的顺序来访问这些点。\n";
    }

    @NotNull
    @Override
    public String condition() {
        return "输入：points = [[1,1],[3,4],[-1,0]]\n" +
                "输出：7";
    }

    @NotNull
    @Override
    public String answer() {

        int count = 0;
        int[][] points = {{1, 1}, {3, 4}, {-1, 0}};
        for (int i = 0; i < points.length - 1; i++) {
            int dx = points[i + 1][0] - points[i][0];
            int dy = points[i + 1][1] - points[i][1];

            int d = Math.max(Math.abs(dy), Math.abs(dx));
            count += d;

        }
        return String.valueOf(count);
    }

    @NotNull
    @Override
    public String thought() {
        return null;
    }
}
