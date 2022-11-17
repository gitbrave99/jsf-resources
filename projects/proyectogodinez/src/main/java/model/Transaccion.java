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
public class Transaccion {
    private int idTransaccion;
    private double valor_monteario;
    private Date fechaTransaccion;
    private int idTipoTransaccion;
    private String numero_cuenta;

    public Transaccion(){
    
    }
    
    public Transaccion(int idTransaccion, double valor_monteario, Date fechaTransaccion, int idTipoTransaccion, String numero_cuenta) {
        this.idTransaccion = idTransaccion;
        this.valor_monteario = valor_monteario;
        this.fechaTransaccion = fechaTransaccion;
        this.idTipoTransaccion = idTipoTransaccion;
        this.numero_cuenta = numero_cuenta;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public double getValor_monteario() {
        return valor_monteario;
    }

    public void setValor_monteario(double valor_monteario) {
        this.valor_monteario = valor_monteario;
    }

    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public int getIdTipoTransaccion() {
        return idTipoTransaccion;
    }

    public void setIdTipoTransaccion(int idTipoTransaccion) {
        this.idTipoTransaccion = idTipoTransaccion;
    }

    public String getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }
    
    
    
}
