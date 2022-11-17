/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package controller;

import entity.Cliente;
import implement.ClienteServiceImp;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author georg
 */
@WebService(serviceName = "clientews")
public class clientews {
    private ClienteServiceImp conCliente= new ClienteServiceImp();
    
    /**
     * This is a sample web service operation
    */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
     
    @WebMethod(operationName = "read")
    public ArrayList<Cliente> listar() {
        return conCliente.read();
    }
    
    
    
}
