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

        LongestSubSequence test = new LongestSubSequence();

        int result = test.lengthOfLIS(new int[]{
                1,9,5,9,3
        });

        assertEquals(3, result);

        BinarySearch bs = new BinarySearch();

        int res = bs.binarySearch(new int[]{1, 2, 3, 4, 5, 6}, 3);

        System.out.println(res);
    }
}