/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Gerente;

import ConnectionDB.CajeroModelo;
import ConnectionDB.CambiosModelo;
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
@WebServlet("/actualizarCajero")
public class ActualizarCajero extends HttpServlet{
    CajeroModelo cajeroModelo = new CajeroModelo();
    CambiosModelo cambiosModelo = new CambiosModelo(); 
    
    @Override
     public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         try{
             String codigoGerente = (String) request.getSession().getAttribute("id");
             String codigo = (String) request.getParameter("codigo");
             String nombre = (String) request.getParameter("nombre");
             String turno = (String) request.getParameter("turno");
             String dpi = (String) request.getParameter("dpi");
             String direccion = (String) request.getParameter("direccion");
             String genero = (String) request.getParameter("genero");
             String pass = (String) request.getParameter("pass");
             
             Cajero cajero = new Cajero(Integer.parseInt(codigo),nombre,turno,dpi,direccion,genero,pass);
             cajeroModelo.ModCajero(cajero);
             
             Cambios cambios = new Cambios("CLIENTE",Integer.parseInt(codigo),nombre,null,Integer.parseInt(codigoGerente));
             cambiosModelo.addCambios(cambios);
             
             response.sendRedirect("gerente/inicio.jsp");
         }catch(NumberFormatException | SQLException e){
             System.out.println("error " + e.getMessage());
//             request.setAttribute("success", 1);
//             response.sendRedirect("perfil");
         }
     }
}
