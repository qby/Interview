package com.qibenyu.explore.basis.thread;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class CASTest {

    @Test
    public void test1() {

        CAS cas = new CAS();
        AtomicInteger t = cas.test();
        assertEquals(50000,t.get());
    }
}