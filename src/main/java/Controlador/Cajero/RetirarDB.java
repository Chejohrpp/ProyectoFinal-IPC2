/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Cajero;

import ConnectionDB.ClienteModelo;
import ConnectionDB.CuentaModelo;
import ConnectionDB.TransaccionModelo;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objetos.Cliente;
import objetos.Cuenta;

/**
 *
 * @author sergi
 */
@WebServlet("/retirarDB")
public class RetirarDB extends HttpServlet {
    
    TransaccionModelo transaccionModelo = new TransaccionModelo();
    CuentaModelo cuentaModelo = new CuentaModelo();
    ClienteModelo clienteModelo = new ClienteModelo();
    
    @Override
     public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         try{
            String noCuenta = (String) request.getParameter("noCuenta");
            String monto = (String) request.getParameter("monto");
            String dpi = (String) request.getParameter("dpi");
            String codigo = (String) request.getParameter("codigo");
            String codigoCajero = (String) request.getSession().getAttribute("id");
            Cuenta cuenta = cuentaModelo.cuentaCliente(Integer.parseInt(noCuenta));
            Cliente cliente = clienteModelo.obtenerClienteGlobal(Integer.parseInt(codigo));
            
             if (cuenta.getCliente_codigo() == cliente.getCodigo()) {
                 if (cliente.getDpi().equals(dpi)) {
                     if (cuenta.getCredito() >= Double.parseDouble(monto)) {
                        cuenta.setCredito(cuenta.getCredito() - Double.parseDouble(monto));
                        cuentaModelo.cambiarMonto(cuenta);
                        transaccionModelo.crearTransaccionDebito(Double.parseDouble(monto), Integer.parseInt(codigoCajero), cuenta.getCredito(), Integer.parseInt(noCuenta));
                        request.setAttribute("success", 1);
                        request.setAttribute("verificar", 1);
                        request.getRequestDispatcher("/cajero/retirar.jsp").forward(request, response);
                     }else{
                         error(request,response,"El monto pedido supera el credito de la cuenta");
                     }
                 }else{
                     error(request,response,"no coincide el dpi del cliente");                   
                 }
             }else{
                 error(request,response,"la cuenta no coincide con el codigo del cliente");
             }
            
         }catch(IOException | NumberFormatException | SQLException | ServletException e){
            // System.out.println(e.getMessage());
             error(request,response,"algo salio mal al momento de retirar");
         }
            
         
     }     
     private void error(HttpServletRequest request,HttpServletResponse response,String error) throws ServletException, IOException{
         request.setAttribute("success", 0);
         request.setAttribute("verificar", 1);
         request.setAttribute("mensajeError", error);
         request.getRequestDispatcher("/cajero/retirar.jsp").forward(request, response);
     }
}
