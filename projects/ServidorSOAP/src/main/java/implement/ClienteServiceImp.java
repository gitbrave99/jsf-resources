/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implement;

import DAO.ClienteDAO;
import java.util.ArrayList;
import javax.jws.WebService;
import entity.Cliente;
import service.ClienteService;

/**
 *
 * @author georg
 */

public class ClienteServiceImp implements ClienteService {
    
    private ClienteDAO op = new ClienteDAO();
    
    @Override
    public boolean create(Cliente nCliente) {
        return op.create(nCliente);
    }
    
    @Override
    public ArrayList<Cliente> read() {
        return op.read();
    }

    @Override
    public boolean update(Cliente nCliente) {
        return op.update(nCliente);
    }

    @Override
    public boolean delete(Cliente nCliente) {
        return op.delete(nCliente);
    }

}
