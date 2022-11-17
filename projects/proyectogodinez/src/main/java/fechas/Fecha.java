package fechas;

import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author georg
 */
public class Fecha {

    public java.sql.Date getFechaRegistro() {
        Date dt = new Date();
        Long timeinMiliseconds = dt.getTime();
        java.sql.Date dtsql = new java.sql.Date(timeinMiliseconds);
        return dtsql;
    }

}
