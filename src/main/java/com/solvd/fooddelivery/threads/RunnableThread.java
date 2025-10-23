package com.solvd.fooddelivery.threads;

import com.solvd.fooddelivery.pool.ConnectionPool;
import com.solvd.fooddelivery.pool.MockConnection;

public class RunnableThread implements Runnable {
    private final ConnectionPool pool;

    public RunnableThread(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            MockConnection conn = pool.getConnection();
            conn.create();
            Thread.sleep(1000);
            conn.update();
            pool.releaseConnection(conn);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
