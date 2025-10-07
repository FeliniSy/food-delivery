package org.fooddeliverymodified.delivery;

import java.io.FileWriter;
import java.io.IOException;

public class DeliveryLogger implements AutoCloseable {

    private FileWriter writer;

    public DeliveryLogger(String fileName) throws IOException {
        writer = new FileWriter(fileName, true);
    }

    public void log(String message) throws IOException {
        writer.write(message + "\n");
    }

    @Override
    public void close() throws IOException {
        if (writer != null) {
            writer.close();
            System.out.println("Logger closed.");
        }
    }
}
