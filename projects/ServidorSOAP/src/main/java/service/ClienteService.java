/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;
import entity.Cliente;

/**
 *
 * @author georg
 */
@WebService
public interface ClienteService {
    
    @WebMethod
    public boolean create(Cliente nCliente);
    
    @WebMethod
    public ArrayList<Cliente> read();
    
    @WebMethod
    public boolean update(Cliente nCliente);
    
    @WebMethod
    public boolean delete(Cliente nCliente);
    
    
}
