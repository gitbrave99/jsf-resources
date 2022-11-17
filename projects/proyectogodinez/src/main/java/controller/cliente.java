/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import modelDAO.ClienteDAO;

/**
 *
 * @author georg
 */
public class cliente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    ClienteDAO conCliente = new ClienteDAO();
    ArrayList<Cliente> listP = new ArrayList();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        listP = this.conCliente.getClientes();
        request.setAttribute("listclientes", listP);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vista = "";
        String accion = request.getParameter("opcion");
        if (accion.equalsIgnoreCase("registrar")) {

            Cliente nwCli = new Cliente();
            String nombre = request.getParameter("inRegClNombre");
            String apellidos = request.getParameter("inRegClApellidos");
            String dui = request.getParameter("inRegClDUI");

            nwCli.setNombre(nombre);
            nwCli.setApellido(apellidos);
            nwCli.setDui(dui);

            if (this.conCliente.validarDUIcliente(dui)) {
                request.setAttribute("error", "DUI ya existes");
            } else {
                if (this.conCliente.agregarCliente(nwCli)) {
                    System.out.println("ITENTANDO INGRESAR");
                    request.setAttribute("mensaje", "INGRESO CORRECTO");
                } else {
                    request.setAttribute("error", "FALLO INGRESO");
                }
            }

            vista = "index.jsp";

        }
        listP = this.conCliente.getClientes();
        request.setAttribute("listclientes", listP);
        RequestDispatcher viewToLoad = request.getRequestDispatcher(vista);
        viewToLoad.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
