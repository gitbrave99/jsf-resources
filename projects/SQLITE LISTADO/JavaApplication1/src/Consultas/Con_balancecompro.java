/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Consultas;

import Conexiones.Conexion;
import entidades.BalanceCompro;
import entidades.Cuenta;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author george
 */
public class Con_balancecompro extends Conexion {

    private ResultSet res;
    private BalanceCompro bal_compro;

    public void cargar_balancecomprobacion(JTable jtbalcomrp, String queryvista) {
        DefaultTableModel modelo = (DefaultTableModel) jtbalcomrp.getModel();
        modelo.setRowCount(0);
        res = super.Consulta(queryvista);
        ArrayList<BalanceCompro> list_balcompro = new ArrayList();
        try {
            while (res.next()) {
                bal_compro = new BalanceCompro();
                bal_compro.setId_BalanceCompro(res.getInt(1));
                bal_compro.setCuenta(res.getString(2));
                bal_compro.setDeudorm(res.getDouble(3));
                bal_compro.setAcreedorm(res.getDouble(4));
                bal_compro.setDeudors(res.getDouble(5));
                bal_compro.setAcreedors(res.getDouble(6));
                list_balcompro.add(bal_compro);
            }
            for (BalanceCompro blcom : list_balcompro) {
                String[] row = {Integer.toString(blcom.getId_BalanceCompro()),blcom.getCuenta(), Double.toString(blcom.getDeudorm()), Double.toString(blcom.getAcreedorm()), Double.toString(blcom.getDeudors()), Double.toString(blcom.getAcreedors())};
                modelo.addRow(row);
            }

        } catch (SQLException e) {
            System.out.println("fallo carga de cuenta" + e.getMessage());
        }
    }

    public void insert_balance_row(BalanceCompro balc) {
        try {
            PreparedStatement st = super.connect().prepareStatement("INSERT INTO balancecomprobacion(id_cuenta,deudorm,acreedorm,deudors,acreedors) values (?,?,?,?,?)");
            st.setInt(1, balc.getId_cuenta());
            st.setDouble(2, balc.getDeudorm());
            st.setDouble(3, balc.getAcreedorm());
            st.setDouble(4, balc.getDeudors());
            st.setDouble(5, balc.getAcreedors());
            st.execute();
            System.out.println("insercon corecta blance row");
        } catch (SQLException ex) {
            System.err.println("erros insert balacne row" + ex.getMessage());
        }
    }
    public void deletee_balancecompro_selected(int id_balcomprob){
      try {
            PreparedStatement st = super.connect().prepareStatement("DELETE FROM balancecomprobacion where id_balancecomprobacion=? ");
            st.setInt(1, id_balcomprob);
            st.execute();
            JOptionPane.showMessageDialog(null, "eliminado correctamente");
        } catch (SQLException ex) {
            System.err.println("error en eliminacion de balance slected" + ex.getMessage());
        }
    }

    public int get_id_cuenta_selected(String cuenta) {
        int id_cuenta = 0;
        res = super.Consulta("SELECT id_cuenta FROM cuenta where cuenta='"+cuenta+"';");
        try {
            while (res.next()) {
                id_cuenta = res.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("error carga de factura venta= " + e.getMessage());

        }
        return id_cuenta;
    }


}
