package com.solvd.fooddelivery.delivery;

import com.solvd.fooddelivery.lambdas.LambdaUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;

public class DeliveryLogger implements AutoCloseable {

    private static final Logger logger = LogManager.getLogger(DeliveryLogger.class);


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
            logger.info("Logger closed.");
        }
    }
}
