package com.solvd.fooddelivery;

import com.solvd.fooddelivery.pool.ConnectionPool;
import com.solvd.fooddelivery.pool.MockConnection;
import com.solvd.fooddelivery.threads.RunnableThread;
import com.solvd.fooddelivery.threads.ThreadExample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainForThread {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Part 1: Threads using Runnable and Thread ===");
        ConnectionPool pool = ConnectionPool.getInstance(5);

        // 7 threads (5 will get connections, 2 will wait)
        Thread[] threads = new Thread[7];
        for (int i = 0; i < 3; i++) {
            threads[i] = new Thread(new RunnableThread(pool), "Runnable-" + (i + 1));
        }
        for (int i = 3; i < 7; i++) {
            threads[i] = new ThreadExample(pool, "Thread-" + (i + 1));
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        System.out.println("\n=== Part 2: ExecutorService Implementation ===");
        ExecutorService executor = Executors.newFixedThreadPool(7);

        for (int i = 1; i <= 7; i++) {
            executor.submit(() -> {
                try {
                    MockConnection conn = pool.getConnection();
                    conn.create();
                    Thread.sleep(1000);
                    conn.update();
                    pool.releaseConnection(conn);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("\n=== Program finished successfully ===");
    }
}
