/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import entidades.Cuenta;
import Conexiones.Conexion;
import Consultas.Con_cuenta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author george
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexion con = new Conexion();
        con.connect();
//        con.close();
    Con_cuenta con_cuen= new Con_cuenta();
//    con_cuen.cargar_cuentas("SELECT * FROM cuenta");
      
    }
    
  

}
