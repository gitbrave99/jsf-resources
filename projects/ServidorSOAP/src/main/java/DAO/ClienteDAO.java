/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import db.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entity.Cliente;
import service.ClienteService;

/**
 *
 * @author georg
 */
public class ClienteDAO extends Conexion implements ClienteService {

    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public boolean create(Cliente nCliente) {
        boolean ingreso = false;
        String query = "INSERT INTO cliente(nombre,apellidos,email,telefono,saldo) values(?,?,?,?,?)";
        try {
            ps = super.getConexion().prepareStatement(query);
            ps.setString(1, nCliente.getNombre());
            ps.setString(2, nCliente.getApellidos());
            ps.setString(3, nCliente.getEmail());
            ps.setString(4, nCliente.getTelefono());
            ps.setInt(5, nCliente.getSaldo());
            ps.execute();
            System.out.println("INGRESO cliente");
            super.getConexion().close();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("ERROR INSER cliente: " + ex.getMessage());
            ingreso = false;
        } finally {
            super.close(super.getConexion());
            super.close(ps);
            super.close(rs);
        }
        return ingreso;
    }

    @Override
    public ArrayList<Cliente> read() {
        ArrayList<Cliente> listProducto = new ArrayList<>();
        String query = "SELECT * FROM cliente";
        Cliente cli;
        try {
            ps = super.getConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                cli = new Cliente();
                cli.setIdCliente(rs.getInt("id_cliente"));
                cli.setNombre(rs.getString("nombre"));
                cli.setApellidos(rs.getString("apellidos"));
                cli.setEmail(rs.getString("email"));
                cli.setTelefono(rs.getString("telefono"));
                cli.setSaldo(rs.getInt("saldo"));
                listProducto.add(cli);
                
                System.out.println("nombre: "+ cli.getNombre());
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("ERROR LIST pasteles: " + ex.getMessage());
        } finally {
            super.close(super.getConexion());
            super.close(ps);
            super.close(rs);
        }
        return listProducto;
    }

    @Override
    public boolean update(Cliente nCliente) {
        boolean actualizado = false;
        String query = "UPDATE cliente SET nombre = ?, apellidos = ?, email = ?, telefono = ?, saldo = ? WHERE id_cliente=?";
        try {
            ps = super.getConexion().prepareStatement(query);
            ps.setString(1, nCliente.getNombre());
            ps.setString(2, nCliente.getApellidos());
            ps.setString(3, nCliente.getEmail());
            ps.setString(4, nCliente.getTelefono());
            ps.setInt(5, nCliente.getSaldo());
            ps.setInt(6, nCliente.getIdCliente());

            if (ps.executeUpdate() == 1) {
                actualizado = true;
                System.out.println("ACTUALIZACIÓN cliente");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("ERROR ACTUALIZACION cliente: " + ex.getMessage());
            actualizado = false;
        } finally {
            super.close(super.getConexion());
            super.close(ps);
            super.close(rs);
        }
        return actualizado;
    }

    @Override
    public boolean delete(Cliente nCliente) {
        boolean eliminado = false;
        String query = "DELETE FROM cliente WHERE id_cliente=?";
        try {
            ps.setInt(1, nCliente.getIdCliente());
            if (ps.executeUpdate() == 1) {
                eliminado = true;
                System.out.println("ELIMINACION cliente");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("ERROR ELIMINACION cliente: " + ex.getMessage());
            eliminado = false;
        } finally {
            super.close(super.getConexion());
            super.close(ps);
            super.close(rs);
        }
        return eliminado;
    }
 
}
