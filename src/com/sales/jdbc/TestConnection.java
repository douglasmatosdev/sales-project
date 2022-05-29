/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sales.jdbc;

import java.sql.SQLException;

/**
 *
 * @author Douglas Matos da Silva
 */
public class TestConnection {

    public static void main(String[] args) throws SQLException {
        try {

            new ConnectionFactory().getConnection();
            System.out.println("Success!!!");

        } catch (RuntimeException e) {

            System.out.println("Error");
            throw new RuntimeException(e);
        }
    }
}
