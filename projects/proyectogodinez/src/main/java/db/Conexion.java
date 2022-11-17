/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author georg
 */
public class Conexion {
     private String usuario = "root";
    private String clave = "unicaes";
    private String url = "jdbc:mysql://localhost:3306/banco?autoReconnect=false&useSSL=false";
    private Connection conex = null;
    private Statement stmt;
    private ResultSet rs;

    public Connection getConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conex = (Connection) DriverManager.getConnection(url, usuario, clave);
            System.out.println("conexion exitosa");

        } catch (SQLException ex) {
            System.out.println("error: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("error 2: " + ex);
        }
        return this.conex;
    }
}
