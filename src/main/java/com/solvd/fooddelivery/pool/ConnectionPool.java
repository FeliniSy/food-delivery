package com.solvd.fooddelivery.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static volatile ConnectionPool instance;
    private final BlockingQueue<MockConnection> pool;

    private ConnectionPool(int size) {
        pool = new ArrayBlockingQueue<>(size);
        for (int i = 1; i <= size; i++) {
            pool.offer(new MockConnection(i));
        }
    }

    public static ConnectionPool getInstance(int size) {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool(size);
                }
            }
        }
        return instance;
    }

    public MockConnection getConnection() throws InterruptedException {
        MockConnection conn = pool.take(); // will wait if none available
        System.out.println(Thread.currentThread().getName() + " got connection " + conn.getId());
        return conn;
    }

    public void releaseConnection(MockConnection conn) {
        if (conn != null) {
            pool.offer(conn);
            System.out.println(Thread.currentThread().getName() + " released connection " + conn.getId());
        }
    }
}