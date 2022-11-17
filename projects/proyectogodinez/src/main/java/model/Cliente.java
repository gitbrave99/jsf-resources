/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

 

/**
 *
 * @author georg
 */
public class Cliente {
    private int codCliente;
    private String nombre;
    private String apellido;
    private String dui;
    private Date fechaRegistro;

    
    public Cliente(){
    }
    
    public Cliente(int cod_cliente, String nombre, String apellido, String dui, Date fechaRegistro) {
        this.codCliente = cod_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dui = dui;
        this.fechaRegistro = fechaRegistro;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

   
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    
    
}
