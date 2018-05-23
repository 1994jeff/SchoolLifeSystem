package com.my.app.schoollifesystem.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by yf on 18-5-23.
 */

public class ThreadPoolUtils {

    private ThreadPoolUtils(){}

    public static ThreadPoolExecutor instance(){
        return A.executor;
    }

    private static class A{
        static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    }

}
