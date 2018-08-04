package com.slyone.properties;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class FirstApp {
    public static void main(String[] args) {

        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream("config.properties");

            // Set the properties values
            prop.setProperty("database","localhost");
            prop.setProperty("dbuser","slyone");
            prop.setProperty("dbpassword","password");

            // Save properties to root folder
            prop.store(output, null);
        } catch ( IOException io) {
            io.printStackTrace();
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

    }
}
