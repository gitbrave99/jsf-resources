/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ajax;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author georg
 */
@Named
@RequestScoped
public class SelectControl{

    private String nombre;
    private String departamento;
    private int departamentoID;
    private String ciudad;
    private int ciudadId;
    private static String mensaje="";
    private List<SelectItem> selectCiudades;
    
    @Inject
    private CInfoHelper info;
    
    public SelectControl() {
    }
    
    public void capturar() {
        System.out.println("depto: "+this.departamentoID); 
        System.out.println("city: "+this.ciudadId); 
        if (this.departamentoID != 0) {
            CSelectDepartamento depto = info.getDepartamentosPorCodigo(this.departamentoID);
            CSelectCiudad city = info.getCiudadesPorCodigo(this.ciudadId);
            SelectControl.mensaje = "codigo: " + depto.getNombre() + "ciudad: " + city.getNombre();
        } else {
            SelectControl.mensaje = "Seleccione uno ";
        }
    }
    
    public void actualizarCiudades(AjaxBehaviorEvent e) {
        selectCiudades = new ArrayList();
        List<CSelectCiudad> citiesList = info.getCiudades();
        for (CSelectCiudad city : citiesList) {
            if (city.getDepto() == this.departamentoID) {
                System.out.println("actualo");
                SelectItem item = new SelectItem(city.getCodigo(), city.getNombre());
                selectCiudades.add(item);
            }
        }
    }

    public List<SelectItem> getSelectItemsCiudades() {
        return this.selectCiudades;
    }

    public List<SelectItem> getSelectItemDepartamentos() {
        List<SelectItem> selectItems = new ArrayList();
        List<CSelectDepartamento> departmentsList = info.getDepartamentoList();

        for (CSelectDepartamento depto : departmentsList) {
            SelectItem item = new SelectItem(depto.getCodigo(), depto.getNombre());
            selectItems.add(item);
        }

        return selectItems;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public CInfoHelper getInfo() {
        return info;
    }

    public void setInfo(CInfoHelper info) {
        this.info = info;
    }

    public int getDepartamentoID() {
        return departamentoID;
    }

    public void setDepartamentoID(int departamentoID) {
        this.departamentoID = departamentoID;
    }

    public int getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(int ciudadId) {
        this.ciudadId = ciudadId;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
