/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scope;

import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author georg
 */
@Named
//@RequestScoped//alcance corto
//@ViewScoped // se mantiene mientras estemos en la misma pagina
//@SessionScoped //compartir el estado del entre diferentes pantallas o vistas
@ApplicationScoped //poder compartir datos entre multiplies usuarios brindrar persitencia durante toda la aplicacion

public class TablaBean implements Serializable{
    
    private ArrayList<Persona> listaPersona= new ArrayList();//al ser estatica es global permanece mientras hacemos la peticion
    private Persona person;
    
//    @PostConstruct
    public TablaBean() {
        this.person = new Persona();
        
    }
    
    public void agregarPersona(){
        this.listaPersona.add(this.person);
        this.person= new Persona();
    }
    
    public void eliminarPersona(Persona rp){
        this.listaPersona.remove(rp);
    }
    
     public void editarPersona(Persona rp){
        this.setPerson(rp);
    }
    
    
    public ArrayList<Persona> getListaPersona() {
        return listaPersona;
    }

    public void setListaPersona(ArrayList<Persona> listaPersona) {
        this.listaPersona = listaPersona;
    }

    public Persona getPerson() {
        return person;
    }

    public void setPerson(Persona person) {
        this.person = person;
    }


    
    
    
    
}
