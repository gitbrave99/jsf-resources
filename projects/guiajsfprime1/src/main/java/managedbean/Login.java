/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managedbean;

import java.util.ResourceBundle;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import util.JsfUtill;

/**
 *
 * @author georg
 */
@ManagedBean
@SessionScoped
public class Login {

    private String nombre = "wick";
    private String password;

    public Login() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String validar() {
        if (this.getNombre().equals("wick") && this.getPassword().equals("123")) {
            JsfUtill.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LoginCorrecto"));
            return "bienvenido";
        } else {
            System.out.println("error : "+ResourceBundle.getBundle("/Bundle").getString("LoginError"));
            JsfUtill.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("LoginError"));
            return null;
        }
    }
}
