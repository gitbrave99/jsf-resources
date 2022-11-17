/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import entidades.Cuenta;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author george
 */
public class Conexion {

    String url = "./src/db/estadoresultado.db";
//    String url="./home/george/Documentos/SQLITE/asientoscontales.db";
    Connection connect;

    public Connection connect() {
        try {
            connect = DriverManager.getConnection("jdbc:sqlite:" + url);
            if (connect != null) {
                System.out.println("Conectado");
            }
        } catch (SQLException ex) {
            System.err.println("No se ha podido conectar a la base de datos\n" + ex.getMessage());
        }
        return connect;
    }

    public void close() {
        try {
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveAlumno(Cuenta cun) {
        try {
            PreparedStatement st = connect.prepareStatement("insert into cuenta(codigo,nombre) values (?,?)");
            st.setString(1, cun.getCodigo());
            st.setString(2, cun.getNombre());
            st.execute();
            System.out.println("insercon corecta");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public ResultSet Consulta(String consulta) {
        Connection con = this.connect();
        Statement declara;
        try {
            declara = con.createStatement();
            ResultSet respuesta = declara.executeQuery(consulta);
            return respuesta;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage(),
                    "Error de Conexion", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

}
