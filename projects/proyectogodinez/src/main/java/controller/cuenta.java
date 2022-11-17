/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import fechas.RandomNumCuenta;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Cuenta;
import model.Transaccion;
import modelDAO.ClienteDAO;
import modelDAO.CuentaDAO;
import modelDAO.TransaacionDAO;

/**
 *
 * @author georg
 */
public class cuenta extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    CuentaDAO conCuenta = new CuentaDAO();
    ArrayList<Cuenta> listC = new ArrayList();
    ClienteDAO conCliente = new ClienteDAO();
    ArrayList<Cliente> listP = new ArrayList();
    TransaacionDAO conTrans = new TransaacionDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet cuenta</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet cuenta at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        response.setContentType("text/html;charset=UTF-8");
        String vista = "";
        String accion = request.getParameter("opcion");
        if (accion.equalsIgnoreCase("getcuentasByUsuario")) {
            try ( PrintWriter out = response.getWriter()) {
                int idUser = Integer.parseInt(request.getParameter("idCliente"));
                ArrayList<Cuenta> listCuentas = conCuenta.getCuentaByCliente(idUser);
                String json = "{\"cuenta\":[";
                //inicio json body
                for (int i = 0; i < listCuentas.size(); i++) {
                    json
                            += "{\"idCuentas\": \"" + listCuentas.get(i).getNumero_cuenta() + "\","
                            + "\"nombreCuenta\":\"" + listCuentas.get(i).getNombre_cuenta() + "\"}";
                    if (i < (listCuentas.size() - 1)) {
                        json += ",";
                    }
                }
                json += "]}";
                out.print(json);
            } catch (Exception e) {
            }
        } else if (accion.equalsIgnoreCase("getSaldoByCuentaUsuario")) {
            int idUser = Integer.parseInt(request.getParameter("idCliente"));
            String nmCuenta = request.getParameter("nmCuenta");
            double saldoActualCuenta = conCuenta.getSaldoByCuentaCliente(idUser, nmCuenta);

            String jsonsaldo = "{";
            try ( PrintWriter out = response.getWriter()) {
//                jsonsaldo+="\"saldo\":1223}";
                out.print(saldoActualCuenta);
            } catch (Exception e) {
            }
        } else if (accion.equalsIgnoreCase("getNumCuentaCliente")) {
            int idUser = Integer.parseInt(request.getParameter("idCliente"));
            String numCuenta = conCuenta.getNumCuentaCliente(idUser);

            String jsonsaldo = "{";
            try ( PrintWriter out = response.getWriter()) {
                out.print(numCuenta);
            } catch (Exception e) {
            }
        }

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
        response.setContentType("text/html;charset=UTF-8");
        String vista = "";
        String accion = request.getParameter("opcion");
        if (accion.equalsIgnoreCase("crearcuenta")) {
            Cuenta nwCun = new Cuenta();
            int idCuenta = Integer.parseInt(request.getParameter("CreacCidUsuario"));
            String nomCuenta = request.getParameter("inCreCNombreCuenta");
            double montoApertura = Double.parseDouble(request.getParameter("inCreCMontoApertura"));

            //CREA NUMERO CUENTA RANDOM
            RandomNumCuenta ranNumcuenta = new RandomNumCuenta();
            String newNunCuenta = ranNumcuenta.getNumCuentaRandom();
            if (this.conCuenta.getNumeroCuentas(newNunCuenta)) {
                nwCun.setNumero_cuenta(newNunCuenta);
            } else {
                nwCun.setNumero_cuenta(newNunCuenta);
            }

            nwCun.setCod_cliente(idCuenta);
            nwCun.setNombre_cuenta(nomCuenta);
            nwCun.setMonto_apertura(montoApertura);

            if (this.conCuenta.agregarCuenta(nwCun)) {
                System.out.println("ITENTANDO INGRESAR CLIENTE");
                request.setAttribute("mensaje", "Cuenta Creada");
            } else {
                request.setAttribute("error", "Cuenta no creada");
            }
            vista = "index.jsp";

        } else if (accion.equalsIgnoreCase("agregarTransaccion")) {
            Transaccion trans = new Transaccion();
            double saldoDbCuenta = 0;
            if (request.getParameter("outTransSaldo") != null) {
                saldoDbCuenta = Double.valueOf(request.getParameter("outTransSaldo"));
            } else {
                saldoDbCuenta = 0;
            }

            int idTiptrans = Integer.valueOf(request.getParameter("inTransTipoTransaccion"));
            double valorT = Double.valueOf(request.getParameter("inTransValor"));
            String numCuenta = request.getParameter("numCuentaCliente");
            request.setAttribute("mensaje", "valor nucuenta: ");

            trans.setValor_monteario(valorT);
            trans.setIdTipoTransaccion(idTiptrans);
            trans.setNumero_cuenta(numCuenta);
            if (valorT > saldoDbCuenta && idTiptrans > 1) {
                request.setAttribute("error", "Retiro mayor que saldo");
                
            }

            if (idTiptrans == 1 && valorT < saldoDbCuenta) {
                if (this.conTrans.agregarTransacion(trans)) {
                    request.setAttribute("mensaje", "Deposito realizada");
                } else {
                    request.setAttribute("error", "Deposito no realizada");
                }
            } else {
                if (this.conTrans.agregarTransacion(trans)) {
                    request.setAttribute("mensaje", "Retiro realizada");
                } else {
                    request.setAttribute("error", "Retiro no realizada");
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
