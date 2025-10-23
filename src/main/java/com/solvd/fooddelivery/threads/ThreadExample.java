package com.solvd.fooddelivery.threads;

import com.solvd.fooddelivery.pool.ConnectionPool;
import com.solvd.fooddelivery.pool.MockConnection;

public class ThreadExample extends Thread {

    private final ConnectionPool pool;

    public ThreadExample(ConnectionPool pool, String name) {
        super(name);
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            MockConnection conn = pool.getConnection();
            conn.read();
            Thread.sleep(1000);
            conn.delete();
            pool.releaseConnection(conn);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
