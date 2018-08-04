package com.slyone.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ListProperties {
    public static void main(String[] args) {

        Properties prop = new Properties();
        FileInputStream input = null;

        try {
            input = new FileInputStream("config.properties");

            // Load a properties file
            prop.load(input);

            // Get the property values and print them
            System.out.println(prop.getProperty("database"));
            System.out.println(prop.getProperty("dbuser"));
            System.out.println(prop.getProperty("dbpassword"));
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

