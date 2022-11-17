/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelDAO;

import db.Conexion;
import fechas.Fecha;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cuenta;
import model.Transaccion;

/**
 *
 * @author georg
 */
public class TransaacionDAO extends Conexion{
      private PreparedStatement ps;
    private ResultSet rs;
 

    

    public ArrayList getTransaccion() {
        ArrayList<Cuenta> listCuentas = new ArrayList<>();
        String query = "select * from transaccion";
        Cuenta cuent;

        try {
            ps = super.getConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                cuent = new Cuenta();
                cuent.setNumero_cuenta(rs.getString("cod_cliente"));
                cuent.setNombre_cuenta(rs.getString("nombre_cuenta"));
                cuent.setMonto_apertura(rs.getDouble("monto_apertura"));
                cuent.setFecha_apertura(rs.getDate("fecha_apertura"));
                cuent.setSaldo(rs.getDouble("saldo"));
                cuent.setCod_cliente(rs.getInt("cod_cliente"));
                cuent.setEstado_cuenta(rs.getString("estado_cuenta"));
                listCuentas.add(cuent);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR LIST CUENTAS: " + ex.getMessage());
        }
        return listCuentas;
    }

    public boolean agregarTransacion(Transaccion trans) {
        Fecha fechsql = new Fecha();
        boolean ingreso = false;
        String query = "INSERT INTO transaccion(valor_monteario,fecha_transaccion,id_tipo_transaccion,numero_cuenta) VALUES(?,?,?,?)";
        try {
            ps = super.getConexion().prepareStatement(query);
            ps.setDouble(1, trans.getValor_monteario());
            ps.setDate(2, fechsql.getFechaRegistro());
            ps.setDouble(3, trans.getIdTipoTransaccion());
            ps.setString(4, trans.getNumero_cuenta());
            ps.execute();
            ingreso = true;
            System.out.println("INGRESO TRANSACCIÓN");
            super.getConexion().close();

        } catch (SQLException ex) {
            System.out.println("ERROR   TRANSACCIÓN: " + ex.getMessage());
        }
        return ingreso;
    }
}
