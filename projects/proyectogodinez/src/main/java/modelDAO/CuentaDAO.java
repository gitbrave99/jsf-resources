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
import model.Cliente;
import model.Cuenta;

/**
 *
 * @author georg
 */
public class CuentaDAO extends Conexion {

    private PreparedStatement ps;
    private ResultSet rs;

    public boolean getNumeroCuentas(String newNumeroCuenta) {
        String query = "select numero_cuenta from cuenta order by numero_cuenta asc";
        boolean diferente = false;
        System.out.println("COMPRARNDO RANDOM CUENTA");
        try {
            ps = super.getConexion().prepareStatement(query);
            rs = ps.executeQuery();
            System.out.println("RS= SIN DATOS= "+rs); 
            while (rs.next()) {
                String nmc = rs.getString("numero_cuenta").toLowerCase();
                if (nmc.equalsIgnoreCase(newNumeroCuenta)) {
                    diferente = false;
                } else {
                    diferente = true;
                    System.out.println("NUM CUENTA: " + newNumeroCuenta);
                }

            }
        } catch (SQLException ex) {
            System.out.println("ERROR LIST NUM CUENTAS: " + ex.getMessage());
        }
        return diferente;
    }

    public double getSaldoByCuentaCliente(int idCliente, String nmCuenta) {
        String query = "SELECT saldo FROM cuenta WHERE cod_cliente= ? AND nombre_cuenta = ?";
        double saldoActual = 0;
        try {
            ps = super.getConexion().prepareStatement(query);
            ps.setInt(1, idCliente);
            ps.setString(2, nmCuenta);
            rs = ps.executeQuery();

            while (rs.next()) {
                saldoActual = Double.parseDouble(rs.getString("saldo"));
            }
        } catch (SQLException ex) {
            System.out.println("ERROR GET SALDO CUENTA: " + ex.getMessage());
        }
        return saldoActual;
    }
    
     public double getSaldoCliente(String nmCuenta) {
        String query = "SELECT saldo FROM cuenta WHERE nombre_cuenta = ?";
        double saldoActual = 0;
        try {
            ps = super.getConexion().prepareStatement(query);
            ps.setString(1, nmCuenta);
            rs = ps.executeQuery();

            while (rs.next()) {
                saldoActual = Double.parseDouble(rs.getString("saldo"));
            }
        } catch (SQLException ex) {
            System.out.println("ERROR GET SALDO CUENTA: " + ex.getMessage());
        }
        return saldoActual;
    }
    
    
    public String getNumCuentaCliente(int idCliente) {
        String query = "SELECT numero_cuenta FROM cuenta WHERE cod_cliente= ?";
        String nuCuenta="";
        try {
            ps = super.getConexion().prepareStatement(query);
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();

            while (rs.next()) {
                nuCuenta = rs.getString("numero_cuenta");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR GET num CUENTA: " + ex.getMessage());
        }
        return nuCuenta;
    }
    
    
    
    

    public ArrayList getCuentaByCliente(int idCliente) {
        ArrayList<Cuenta> listCuentas = new ArrayList<>();
        String query = "select * from cuenta WHERE cod_cliente=? AND estado_cuenta=?";
        Cuenta cuent;
        try {
            ps = super.getConexion().prepareStatement(query);
            ps.setInt(1, idCliente);
            ps.setString(2, "A");
            rs = ps.executeQuery();
                
            while (rs.next()) {
                cuent = new Cuenta();
                cuent.setNumero_cuenta(rs.getString("numero_cuenta"));
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

    public ArrayList getCuentas() {
        ArrayList<Cuenta> listCuentas = new ArrayList<>();
        String query = "select * from cuenta";
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

    public boolean agregarCuenta(Cuenta cue) {
        Fecha fechsql = new Fecha();
        boolean ingreso = false;
        String query = "INSERT INTO cuenta(numero_cuenta,nombre_cuenta,monto_apertura,fecha_apertura,saldo,cod_cliente,estado_cuenta) VALUES(?,?,?,?,?,?,?)";
        try {
            ps = super.getConexion().prepareStatement(query);
            ps.setString(1, cue.getNumero_cuenta());
            ps.setString(2, cue.getNombre_cuenta());
            ps.setDouble(3, cue.getMonto_apertura());
            ps.setDate(4, fechsql.getFechaRegistro());
            ps.setDouble(5, cue.getSaldo());
            ps.setInt(6, cue.getCod_cliente());
            ps.setString(7, cue.getEstado_cuenta());
            ps.execute();
            ingreso = true;
            System.out.println("INGRESO CUENTA");
            super.getConexion().close();

        } catch (SQLException ex) {
            System.out.println("ERROR INSER CUENTA: " + ex.getMessage());
        }
        return ingreso;
    }
}
