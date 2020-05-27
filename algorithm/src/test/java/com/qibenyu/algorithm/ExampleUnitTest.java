package com.qibenyu.algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {

        BinarySearch bs = new BinarySearch();

        int res = bs.binarySearch(new int[]{1, 2, 3, 4, 5, 6}, 3);

        System.out.println(res);
    }
}