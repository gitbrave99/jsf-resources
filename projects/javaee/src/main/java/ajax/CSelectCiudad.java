/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ajax;

/**
 *
 * @author georg
 */
public class CSelectCiudad {
    private int codigo;
    private String nombre;
    private int depto;

    public CSelectCiudad(int codigo, String nombre, int depto) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.depto = depto;
    }

    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDepto() {
        return depto;
    }

    public void setDepto(int depto) {
        this.depto = depto;
    }
    
    
    
}
