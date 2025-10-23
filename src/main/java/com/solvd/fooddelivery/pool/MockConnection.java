package com.solvd.fooddelivery.pool;

public class MockConnection {

    private final int id;

    public MockConnection(int id) {
        this.id = id;
    }

    public void create() {
        System.out.println("Connection " + id + ": create()");
    }

    public void read() {
        System.out.println("Connection " + id + ": read()");
    }

    public void update() {
        System.out.println("Connection " + id + ": update()");
    }

    public void delete() {
        System.out.println("Connection " + id + ": delete()");
    }

    public int getId() {
        return id;
    }
}