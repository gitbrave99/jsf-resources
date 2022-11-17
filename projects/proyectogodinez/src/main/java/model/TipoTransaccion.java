/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author georg
 */
public class TipoTransaccion {
 private int Id_tipo_transaccion;   
 private String nombre_transaccion;

 public TipoTransaccion(){
 }
 
    public TipoTransaccion(int Id_tipo_transaccion, String nombre_transaccion) {
        this.Id_tipo_transaccion = Id_tipo_transaccion;
        this.nombre_transaccion = nombre_transaccion;
    }

    public int getId_tipo_transaccion() {
        return Id_tipo_transaccion;
    }

    public void setId_tipo_transaccion(int Id_tipo_transaccion) {
        this.Id_tipo_transaccion = Id_tipo_transaccion;
    }

    public String getNombre_transaccion() {
        return nombre_transaccion;
    }

    public void setNombre_transaccion(String nombre_transaccion) {
        this.nombre_transaccion = nombre_transaccion;
    }
 
 
}
