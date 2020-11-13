/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.General;

import ConnectionDB.*;
import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet("/login")
public class login extends HttpServlet {
    
   ClienteModelo clienteModelo = new ClienteModelo();
   GerenteModelo gerenteModelo = new GerenteModelo();
   CajeroModelo cajeroModelo = new CajeroModelo(); 
   CuentaModelo cuentaModelo = new CuentaModelo();
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
         try {
             request.setAttribute("success", 1);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            System.out.println("Login Error: " + e.getMessage());
            e.printStackTrace();
        }
     }
    
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
         try {
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            String pass = (request.getParameter("pass"));
            String tipo = request.getParameter("tipo");
            
            Cliente cliente = clienteModelo.verificarLogin(codigo, pass);
            Gerente gerente = gerenteModelo.verificarLogin(codigo, pass);
            Cajero cajero = cajeroModelo.verificarLogin(codigo, pass);
            
            
             if (tipo.equals("cliente") && cliente !=  null) {
                 
                 request.getSession().setAttribute("id", String.valueOf(codigo));
                 request.getSession().setAttribute("nombre", cliente.getNombre());                 
                 request.setAttribute("codigo", codigo);                
                 
                 response.sendRedirect(request.getContextPath() + "/cliente/escogerCuenta.jsp");
                 
             }else if (tipo.equals("gerente")&& gerente != null) {
                 request.getSession().setAttribute("id", String.valueOf(codigo));
                 request.getSession().setAttribute("nombre", gerente.getNombre());
                 
                 response.sendRedirect(request.getContextPath() + "/gerente/inicio.jsp");
                 
             }else if (tipo.equals("cajero") && cajero != null) {
                 request.getSession().setAttribute("id", String.valueOf(codigo)); 
                 request.getSession().setAttribute("nombre", cajero.getNombre());                  
                 response.sendRedirect(request.getContextPath() + "/cajero/inicio.jsp");
                 
             }else{
                 request.setAttribute("success", 0);
                 request.getRequestDispatcher("/login.jsp").forward(request, response);
             }       
        } catch (IOException | SQLException | NumberFormatException e) {
            //System.out.println("Login Error: " + e.getMessage());
            request.setAttribute("success", 0);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    
    }
}
