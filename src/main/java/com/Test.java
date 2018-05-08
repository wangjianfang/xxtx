package com;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by wjf on 2018/5/6.
 */
public class Test {

    public static void main(String[] args) throws Exception {
        // 创建一个固定大小的线程池
//        final ExecutorService exec = Executors.newFixedThreadPool(5);
//
//        for (int i=1; i<=10; i++) {
//            System.out.println("创建线程---"+i);
//            Runnable run = new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("启动线程");
//                }
//            };
//            exec.execute(run);
//        }
//        exec.shutdown();
//        exec.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
//        System.out.println("all thread complete");

         test1();
    }


    public static void test1() throws Exception {
        Object o = new Object();

        System.out.println("main thread: holdLock: " + Thread.holdsLock(o));
        Thread.sleep(2000);
    }
}
