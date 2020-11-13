/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Cajero;

import ConnectionDB.CuentaModelo;
import ConnectionDB.TransaccionModelo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objetos.Cuenta;

/**
 *
 * @author sergi
 */
@WebServlet("/depositarDB")
public class DepositarDB extends HttpServlet{
    TransaccionModelo transaccionModelo = new TransaccionModelo();
    CuentaModelo cuentaModelo = new CuentaModelo();
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String noCuenta = (String) request.getParameter("noCuenta");
            String monto = (String) request.getParameter("monto");
            String codigoCajero = (String) request.getSession().getAttribute("id");            
            Cuenta cuenta = cuentaModelo.cuentaCliente(Integer.parseInt(noCuenta));
            if (cuenta != null) {
                cuenta.setCredito(cuenta.getCredito() + Double.parseDouble(monto));
                cuentaModelo.cambiarMonto(cuenta);
                transaccionModelo.crearTransaccionCredito(Double.parseDouble(monto), Integer.parseInt(codigoCajero), cuenta.getCredito(), Integer.parseInt(noCuenta));
                request.setAttribute("success", 1);
                request.setAttribute("verificar", 1);
                request.getRequestDispatcher("/cajero/depositar.jsp").forward(request, response);
            }else{
                request.setAttribute("success", 0);
                request.setAttribute("verificar", 1);
                request.getRequestDispatcher("/cajero/depositar.jsp").forward(request, response);
            }            
             //response.sendRedirect(request.getContextPath() + "/login.jsp");
        }catch(Exception e){
            request.setAttribute("success", 0);
            request.setAttribute("verificar", 1);
            request.getRequestDispatcher("/cajero/depositar.jsp").forward(request, response);
        }
       
    }
}
