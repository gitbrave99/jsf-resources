/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import model.Usuario;

/**
 *
 * @author georg
 */
@Named
@RequestScoped
public class UsuarioController {

    private Usuario user;

    @PostConstruct
    public void init() {
        this.user = new Usuario();
        System.out.println("post-construct");
    }
    
    public UsuarioController(){
        System.out.println("construct");
    }
    
    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public String login() {
        System.out.println("nombre : "+this.user.getUsername()+" pass: "+this.user.getPassword());
        return (this.user.getUsername().equals("isaac") && this.user.getPassword().equals("123"))?"homeuser":"index";
    }

}
