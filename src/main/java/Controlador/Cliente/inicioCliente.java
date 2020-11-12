/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Cliente;

import ConnectionDB.CuentaModelo;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet("/inicioCliente")
public class inicioCliente extends HttpServlet {
    CuentaModelo cuentaModelo = new CuentaModelo();
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int noCuenta = Integer.parseInt(request.getParameter("noCuenta"));
            Cuenta cuenta = cuentaModelo.cuentaCliente(noCuenta);
            request.getSession().setAttribute("noCuenta", String.valueOf(cuenta.getCodigo()));
            request.getSession().setAttribute("credito", String.valueOf(cuenta.getCredito()));
            response.sendRedirect(request.getContextPath() + "/cliente/inicio.jsp");            
        } catch(NumberFormatException e){
            
        } catch (SQLException ex) {
            
        }
    }
}
