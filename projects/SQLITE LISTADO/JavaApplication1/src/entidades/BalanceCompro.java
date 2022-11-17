/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author george
 */
public class BalanceCompro {
    private int id_BalanceCompro;
    private int id_cuenta;
    private String cuenta;
    private double deudorm;
    private double acreedorm;
    private double deudors;
    private double acreedors;

    public int getId_BalanceCompro() {
        return id_BalanceCompro;
    }

    public void setId_BalanceCompro(int id_BalanceCompro) {
        this.id_BalanceCompro = id_BalanceCompro;
    }

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public double getDeudorm() {
        return deudorm;
    }

    public void setDeudorm(double deudorm) {
        this.deudorm = deudorm;
    }

    public double getAcreedorm() {
        return acreedorm;
    }

    public void setAcreedorm(double acreedorm) {
        this.acreedorm = acreedorm;
    }

    public double getDeudors() {
        return deudors;
    }

    public void setDeudors(double deudors) {
        this.deudors = deudors;
    }

    public double getAcreedors() {
        return acreedors;
    }

    public void setAcreedors(double acreedors) {
        this.acreedors = acreedors;
    }
    
    
    
}
