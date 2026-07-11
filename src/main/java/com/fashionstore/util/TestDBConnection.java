package com.fashionstore.util;

import java.sql.Connection;

public class TestDBConnection {

    public static void main(String[] args) {

        try {
            Connection connection = DBConnection.getConnection();

            if (connection != null) {
                System.out.println("Database Connected Successfully!");
                connection.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}