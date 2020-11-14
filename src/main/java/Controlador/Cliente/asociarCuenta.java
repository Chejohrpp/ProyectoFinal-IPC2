/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Cliente;

import ConnectionDB.*;
import java.io.IOException;
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
@WebServlet("/asociarCuenta")
public class asociarCuenta extends HttpServlet{
   CuentasAsociadasModelo cuentasAsociadasModelo = new CuentasAsociadasModelo();
   CuentaModelo cuentaModelo = new CuentaModelo();
   ClienteModelo clienteModelo = new ClienteModelo();
   
   @Override
     public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         try{             
             request.setAttribute("noIntentos", 0);
             request.getRequestDispatcher("/cliente/asociarCuenta.jsp").forward(request, response);         
         }catch(Exception e){
             
         }
     }
     
     @Override
     public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         try{           
              String codigoCliente = (String) request.getSession().getAttribute("id");
              
              String codigoKingCuenta = (String) request.getParameter("kingCuenta");
              String dpi = (String) request.getParameter("dpi");
              String noCuenta = (String) request.getParameter("noCuenta");
              
              Cliente cliente = clienteModelo.obtenerClienteGlobal(Integer.parseInt(codigoKingCuenta));             
              CuentasAsociadas cuentaAsociada = cuentasAsociadasModelo.obtenerCuentaAsociada(Integer.parseInt(noCuenta), Integer.parseInt(codigoCliente));
              Cuenta cuenta = cuentaModelo.cuentaCliente(Integer.parseInt(noCuenta));
              
              if (cliente != null) {
                 if (codigoKingCuenta.equalsIgnoreCase(codigoCliente)) {
                    error(request,response,"No puedes asociar cuentas que te pertenecen",0); 
                }else if(cuenta == null){
                    error(request,response,"No existe la cuenta ingresada",0); 
                }
                 else{
                     if (cliente.getDpi().equals(dpi) && cuenta.getCliente_codigo() == cliente.getCodigo()) {
                         if (cuentaAsociada == null) {
                               CuentasAsociadas newCuentaAsociada = new CuentasAsociadas(false,true,1,Integer.parseInt(noCuenta),Integer.parseInt(codigoCliente),Integer.parseInt(codigoKingCuenta));
                               cuentasAsociadasModelo.addCuentasAsociadasSinCodigo(newCuentaAsociada);
                               request.setAttribute("success", 1);
                               request.setAttribute("noIntentos", 1);
                               request.getRequestDispatcher("/cliente/asociarCuenta.jsp").forward(request, response);

                         }else{
                             if (cuentaAsociada.getNo_intentos() > 3) {
                                 error(request,response,"Superastes el limite de intentos para asociar esta cuenta", cuentaAsociada.getNo_intentos()); 
                             }else if (cuentaAsociada.getEstado()){
                                 error(request,response,"Esta cuenta ya esta asociada",cuentaAsociada.getNo_intentos()); 
                             }else{
                                 cuentaAsociada.setEstado_enviar(Boolean.TRUE);
                                 cuentasAsociadasModelo.modCuentasAsociadas(cuentaAsociada);
                                 request.setAttribute("success", 1);
                                 request.setAttribute("noIntentos", cuentaAsociada.getNo_intentos());
                                 request.getRequestDispatcher("/cliente/asociarCuenta.jsp").forward(request, response);
                             }
                         }
                     }else{
                         error(request,response,"el codigo ingresado, el dpi o el numero de cuenta no coinciden ",0); 
                     }
                 }
             }else{
                  error(request,response,"no existes el codigo del cliente",0);
              }
                    
         }catch(Exception e){
             System.out.println("error " + e.getMessage());
             error(request,response,"Hubo un dato invalido, vuelva a intentarlo",0); 
         }
     }
     
     private void error(HttpServletRequest request,HttpServletResponse response,String error, int noIntentos) throws ServletException, IOException{
         request.setAttribute("success", 0);
         request.setAttribute("mensajeError", error);
         request.setAttribute("noIntentos", noIntentos);
         request.getRequestDispatcher("/cliente/asociarCuenta.jsp").forward(request, response);
     }
   
}
