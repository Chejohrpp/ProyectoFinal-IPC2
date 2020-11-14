/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Cliente;

import ConnectionDB.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet("/buscarCuenta")
public class BuscarCuenta extends HttpServlet{
    CuentaModelo cuentaModelo = new CuentaModelo();
    CuentasAsociadasModelo cuentasAsociadasModelo = new CuentasAsociadasModelo();
    TransaccionModelo transaccionModelo = new TransaccionModelo();
    
    
    @Override
     public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         try{             
              generarCuentas(request,response);
             request.getRequestDispatcher("/cliente/transaccion.jsp").forward(request, response);             
         }catch(Exception e){
             
         }
     }
     
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String noCuenta = (String) request.getSession().getAttribute("noCuenta");
            String monto = (String) request.getParameter("monto");
            String noCuentaEnviar = (String) request.getParameter("noCuentaEnviar");
            Cuenta cuentaActual = cuentaModelo.cuentaCliente(Integer.parseInt(noCuenta));
            Cuenta cuentaEnviar = cuentaModelo.cuentaCliente(Integer.parseInt(noCuentaEnviar));
            if (cuentaActual.getCredito() >= Double.parseDouble(monto)) {
                cuentaActual.setCredito(cuentaActual.getCredito() - Double.parseDouble(monto));
                cuentaEnviar.setCredito(cuentaEnviar.getCredito() + Double.parseDouble(monto));
                cuentaModelo.cambiarMonto(cuentaActual);
                cuentaModelo.cambiarMonto(cuentaEnviar);
                transaccionModelo.crearTransaccionCredito(Double.parseDouble(monto), 101, cuentaEnviar.getCredito(), Integer.parseInt(noCuentaEnviar));
                transaccionModelo.crearTransaccionDebito(Double.parseDouble(monto), 101, cuentaActual.getCredito(), Integer.parseInt(noCuenta));
                request.getSession().setAttribute("credito", cuentaActual.getCredito());
                request.setAttribute("success", 1);
                generarCuentas(request,response);
                request.getRequestDispatcher("/cliente/transaccion.jsp").forward(request, response);
                
            }else{
                generarCuentas(request,response);
                error(request,response,"No posee el credito suficiente para transferirlo a la otra cuenta");
            }          
            
            
        }catch(IOException | NumberFormatException | SQLException | ServletException e){            
            try {
                generarCuentas(request,response);
                error(request,response,"Hubo un dato invalido, vuelva a intentarlo");                
            } catch (SQLException ex) {
            }
        }
    }    
    private void error(HttpServletRequest request,HttpServletResponse response,String error) throws ServletException, IOException{
         request.setAttribute("success", 0);
         request.setAttribute("mensajeError", error);
         request.getRequestDispatcher("/cliente/transaccion.jsp").forward(request, response);
     }
    
     private void generarCuentas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
         String codigo = (String) request.getSession().getAttribute("id");
         String noCuenta = (String) request.getSession().getAttribute("noCuenta");
         List<Cuenta> cuentas = cuentaModelo.cuentasClienteSinCuentaActual(Integer.parseInt(codigo), Integer.parseInt(noCuenta));
         List<CuentasAsociadas> cuentass = cuentasAsociadasModelo.cuentasAsociadasCliente(Integer.parseInt(codigo));
         List<Integer> noCuentas = new LinkedList<>();
         for (Cuenta cuenta : cuentas) {
             noCuentas.add(cuenta.getCodigo());
         }
         for (CuentasAsociadas cuenta : cuentass) {
             noCuentas.add(cuenta.getCodigo());
         }
         request.setAttribute("noCuentas", noCuentas);
        }
}
