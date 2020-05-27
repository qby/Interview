package com.qibenyu.algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class DynamicProgrammingTest {


    @Test
    public void massage() {

        DynamicProgramming dynamicProgramming = new DynamicProgramming();


        int result = dynamicProgramming.massage(new int[]{
                2, 7, 9, 3, 1
        });

        assertEquals(result, 2);


    }
}