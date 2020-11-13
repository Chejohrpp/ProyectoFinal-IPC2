/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Gerente;

import ConnectionDB.CambiosModelo;
import ConnectionDB.ClienteModelo;
import ConnectionDB.GerenteModelo;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import objetos.Cambios;
import objetos.Cliente;
import objetos.Gerente;

/**
 *
 * @author sergi
 */
@WebServlet("/actualizarCliente")
public class ActualizarCliente extends HttpServlet{
    GerenteModelo gerenteModelo = new GerenteModelo();
    ClienteModelo clienteModelo = new ClienteModelo();
    CambiosModelo cambiosModelo = new CambiosModelo();  
    
    
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         try{
            String codigoGerente = (String) request.getSession().getAttribute("id");
            
            String codigo = (String) request.getParameter("codigo");
            String nombre = (String) request.getParameter("nombre");
            String fecha = (String) request.getParameter("fecha");
            String dpi = (String) request.getParameter("dpi");
            String direccion = (String) request.getParameter("direccion");
            String genero = (String) request.getParameter("genero");
            //null
            String pass = (String) request.getParameter("pass");
            
             Cliente cliente = new Cliente(Integer.parseInt(codigo),nombre,fecha,dpi,direccion,genero,null,pass);
             clienteModelo.modCliente(cliente);
             
             Cambios cambios = new Cambios("CLIENTE",Integer.parseInt(codigo),nombre,null,Integer.parseInt(codigoGerente));
             cambiosModelo.addCambios(cambios);
             
             response.sendRedirect("gerente/inicio.jsp");
             
         }catch(NumberFormatException | SQLException e){
             System.out.println("error " + e.getMessage());
//             request.setAttribute("success", 1);
//             response.sendRedirect("actualizarCliente");
         }
     }
    
}
