/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelDAO;

import fechas.Fecha;
import db.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cliente;

/**
 *
 * @author georg
 */
public class ClienteDAO extends Conexion {

    private PreparedStatement ps;
    private ResultSet rs;

    public boolean validarDUIcliente(String pdui) {
        boolean yaExiste = false;
        String duiDb = "";
        String query = "select dui from cliente";
        try {
            ps = super.getConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                duiDb = rs.getString("dui");
                if (pdui.toLowerCase().equals(duiDb.toLowerCase())) {
                    System.out.println("DUI YA EXISTE");
                    return true;
                } else {
                    System.out.println("DUI NO EXISTE");
                }
            }
        } catch (SQLException ex) {
            System.out.println("ERROR VALIDAR DUI CLIENTE: " + ex.getMessage());
            yaExiste = false;
        }
        return yaExiste;
    }

    public ArrayList getClientes() {
        ArrayList<Cliente> listProducto = new ArrayList<>();
        String query = "select * from cliente";
        Cliente cli;
        try {
            ps = super.getConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                cli = new Cliente();
                cli.setCodCliente(rs.getInt("cod_cliente"));
                cli.setNombre(rs.getString("nombre"));
                cli.setApellido(rs.getString("apellidos"));
                cli.setDui(rs.getString("dui"));

                listProducto.add(cli);
            }

        } catch (SQLException ex) {
            System.out.println("ERROR LIST CLIENTES: " + ex.getMessage());
        }
        return listProducto;
    }

    public boolean agregarCliente(Cliente cli) {
        Fecha fechsql = new Fecha();
        boolean ingreso = false;
        String query = "insert into cliente(nombre,apellidos,dui,fecha_registro) values(?,?,?,?)";
        try {
            ps = super.getConexion().prepareStatement(query);
            ps.setString(1, cli.getNombre());
            ps.setString(2, cli.getApellido());
            ps.setString(3, cli.getDui());
            ps.setDate(4, fechsql.getFechaRegistro());
            ps.execute();

            System.out.println("INGRESO USUARIO");
            super.getConexion().close();
            return true;

        } catch (SQLException ex) {
            System.out.println("ERROR INSER CLIENTE: " + ex.getMessage());
            ingreso = false;
        }
        return ingreso;
    }

    public boolean eliminarProducto(int idCliente) {
        String query = "DELETE FROM producto WHERE id_producto=?";
        try {
            ps = super.getConexion().prepareStatement(query);
            ps.setInt(1, idCliente);
            ps.execute();
            System.out.println("Eliminado");
            return true;
        } catch (SQLException ex) {
            System.out.println("ERROR DELETE: " + ex.getMessage());
        }
        return false;
    }
}
