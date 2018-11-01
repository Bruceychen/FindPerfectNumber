package com;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainUseTreadPool {

    private static List<Integer> resultList = new ArrayList<>();

    private static int interval = 10000;
//    private static int interval = 2;
//    private static int interval = 2000000;
//    private static int interval = 30;
//        private static int max = 34000000;
    private static int max = 8000000;

    public static synchronized void addToList(final int number) {
        resultList.add(number);
    }

    public static void main(final String[] args) throws InterruptedException {

        final int availableProcessors = Runtime.getRuntime().availableProcessors();

        // 不想用100% cpu跑
        final int usedProcessors = availableProcessors - 0;

        System.out.println("我可以使用的邏輯處理器" + Runtime.getRuntime().availableProcessors());
        System.out.println("這次使用" + usedProcessors + "個邏輯處理器");

        final ExecutorService executor = Executors.newFixedThreadPool(usedProcessors);

        //        final long start = System.currentTimeMillis();
        final long start = System.nanoTime();
        System.out.println("Start at nanoTime: " + start);
        // Scene 1: assign 30 numbers for each thread a time.
        for (int i = 1; i < max; i += interval) {
            final Worker worker = new Worker(i, i + interval - 1);
            executor.execute(worker);
        }

        // Scene 2: run through
        //        for (int i = 1; i < max; i++) {
        //            executor.submit(new Worker2(i));
        //        }

        executor.shutdown();

        try {
            if (!executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS)) {
                executor.shutdown();
                if (!executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS)) {
                    System.err.println("Executor did not terminated");
                }
            }
        } catch (final Exception e) {
            executor.shutdown();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("main執行緒結束");
            System.out.println(resultList);
            //            final long end = System.currentTimeMillis();
            final long end = System.nanoTime();
            System.out.println("End at nano time: " + end);
            System.out.println("結束應用");
            System.out.println("共用了" + (end - start) + "nano秒");
        }
    }
}
