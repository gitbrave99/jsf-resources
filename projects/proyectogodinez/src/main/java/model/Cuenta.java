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
public class Cuenta {
    private String numero_cuenta;
    private String nombre_cuenta;
    private double monto_apertura;
    private Date fecha_apertura;
    private double saldo;
    private int cod_cliente;
    private String estado_cuenta="A";

    public Cuenta(){
    }
    
    public Cuenta(String numero_cuenta, String nombre_cuenta, double monto_apertura, Date fecha_apertura, double saldo, int cod_cliente, String estado_cuenta) {
        this.numero_cuenta = numero_cuenta;
        this.nombre_cuenta = nombre_cuenta;
        this.monto_apertura = monto_apertura;
        this.fecha_apertura = fecha_apertura;
        this.saldo = saldo;
        this.cod_cliente = cod_cliente;
        this.estado_cuenta = estado_cuenta;
    }

    public String getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public String getNombre_cuenta() {
        return nombre_cuenta;
    }

    public void setNombre_cuenta(String nombre_cuenta) {
        this.nombre_cuenta = nombre_cuenta;
    }

    public double getMonto_apertura() {
        return monto_apertura;
    }

    public void setMonto_apertura(double monto_apertura) {
        this.monto_apertura = monto_apertura;
    }

    public Date getFecha_apertura() {
        return fecha_apertura;
    }

    public void setFecha_apertura(Date fecha_apertura) {
        this.fecha_apertura = fecha_apertura;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(int cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public String getEstado_cuenta() {
        return estado_cuenta;
    }

    public void setEstado_cuenta(String estado_cuenta) {
        this.estado_cuenta = estado_cuenta;
    }
    
    
    
    
}
