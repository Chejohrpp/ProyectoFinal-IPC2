/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Gerente;

import ConnectionDB.ClienteModelo;
import ConnectionDB.CuentaModelo;
import ConnectionDB.GerenteModelo;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objetos.*;

/**
 *
 * @author sergi
 */
@WebServlet("/crearCuenta")
public class CrearCuenta extends HttpServlet{
    ClienteModelo clienteModelo = new ClienteModelo();
    CuentaModelo cuentaModelo = new CuentaModelo();
    GerenteModelo gerenteModelo = new GerenteModelo();
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String codigo = (String) request.getSession().getAttribute("id");
            if (gerenteModelo.verificarRequisitos(Integer.parseInt(codigo))) {
                request.setAttribute("verificar", 1);                
            }else{
                request.setAttribute("verificar", 0);
            }
            request.getRequestDispatcher("/gerente/crearCuenta.jsp").forward(request, response);
        }catch(IOException | NumberFormatException | SQLException | ServletException e){
            
        }
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String codigo = (String) request.getParameter("codigo");
            String dpi = (String) request.getParameter("dpi");
            String monto = (String) request.getParameter("monto");
            String fecha = (String) request.getParameter("fecha");
            Cliente cliente = clienteModelo.obtenerClienteGlobal(Integer.parseInt(codigo));
            if (cliente != null) {
                if (cliente.getDpi().equals(dpi)) {
                    Cuenta cuenta = new Cuenta(Double.parseDouble(monto),fecha,Integer.parseInt(codigo));
                    long noCuenta = cuentaModelo.addCuentaSinCodigo(cuenta);
                    request.setAttribute("success", 1);
                    request.setAttribute("verificar", 1);
                    request.setAttribute("noCuenta", noCuenta);
                    request.getRequestDispatcher("/gerente/crearCuenta.jsp").forward(request, response);
                }else{
                     error(request,response,"el dpi no coincide con el codigo");
                }
            }else{
                 error(request,response,"El codigo de cliente no existe, cree un cliente");
            }
            
        }catch(IOException | NumberFormatException | SQLException | ServletException e){
            error(request,response,"Hubo un dato invalido, vuelva a intentarlo");
        }
    }
    
    private void error(HttpServletRequest request,HttpServletResponse response,String error) throws ServletException, IOException{
         request.setAttribute("success", 0);
         request.setAttribute("verificar", 1);
         request.setAttribute("mensajeError", error);
         request.getRequestDispatcher("/gerente/crearCuenta.jsp").forward(request, response);
     }
    
}
