/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Consultas;

import Conexiones.Conexion;
import entidades.Cuenta;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author george
 */
public class Con_cuenta extends Conexion {

    private ResultSet res;
    private Cuenta cuent;

    public void cargar_cuentas(JComboBox jcombolicu, String queryvista) {

//        DefaultTableModel modelo = (DefaultTableModel) jtableprod.getModel();
//        modelo.setRowCount(0);
        res = super.Consulta(queryvista);
        ArrayList<Cuenta> listacli = new ArrayList();
        try {
            while (res.next()) {
                cuent = new Cuenta();
                cuent.setCodigo(res.getString(2));
                cuent.setNombre(res.getString(3));
                listacli.add(cuent);
            }
            for (Cuenta cue : listacli) {
//                String[] row={cue.getCodigo(), cue.getNombre()};
                jcombolicu.addItem(cue.getNombre()); 
            }

        } catch (SQLException e) {
            System.out.println("fallo carga de cuenta" + e.getMessage());
        }
    }
}
