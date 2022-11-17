/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import model.Producto;

/**
 *
 * @author georg
 */
@Named
@RequestScoped
public class ProductoController {

    private String idgaseosa;
    private List<Producto> listProducts;
    private List<String> productosSeleccionados; //lista para almacenar los elementos seleccionados
    List<String> allProducts;

    public ProductoController() {
        this.listProducts = new ArrayList();
        this.allProducts = new ArrayList();
        this.listProducts.add(new Producto(1, "coca", 1));
        this.listProducts.add(new Producto(2, "fanta", 1));
        this.listProducts.add(new Producto(3, "uva", 1));
        this.listProducts.add(new Producto(4, "7up", 1));
    }

    public String getIdgaseosa() {
        return idgaseosa;
    }

    public void setIdgaseosa(String idgaseosa) {
        this.idgaseosa = idgaseosa;
    }

    public List<String> getListProducts() {
        for (Producto p : this.listProducts) {
            this.allProducts.add(p.getNombre());
        }
        return allProducts;
    }

    public void setListProducts(List<Producto> listProducts) {
        this.listProducts = listProducts;
    }

    public String comprar() {
        return "canasta";
    }

    public List<String> getProductosSeleccionados() {
        return productosSeleccionados;
    }

    public void setProductosSeleccionados(List<String> productosSeleccionados) {
        this.productosSeleccionados = productosSeleccionados;
    }

}
