/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Gerente;

import ConnectionDB.CambiosModelo;
import ConnectionDB.GerenteModelo;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objetos.Cambios;
import objetos.Gerente;

/**
 *
 * @author sergi
 */
@WebServlet("/perfil")
public class Perfil extends HttpServlet {
    GerenteModelo gerenteModelo = new GerenteModelo();
    CambiosModelo cambiosModelo = new CambiosModelo(); 
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            
            String codigo = (String) request.getSession().getAttribute("id");
            if (gerenteModelo.verificarRequisitos(Integer.parseInt(codigo))) {
            Gerente gerente = gerenteModelo.obtenerGerente(Integer.parseInt(codigo));
            request.setAttribute("nombre", gerente.getNombre());
            request.setAttribute("direccion", gerente.getDireccion());
            request.setAttribute("dpi", gerente.getDpi());
            request.setAttribute("genero", gerente.getGenero());
            request.setAttribute("pass", gerente.getPassword());
            request.setAttribute("turno", gerente.getTurno());
            request.setAttribute("verificar", 1);             
            }else{
                request.setAttribute("verificar", 0);  
            }
            request.getRequestDispatcher("/gerente/miPerfil.jsp").forward(request, response);
        }catch(SQLException|NumberFormatException e){
            
        }
    }
    @Override
     public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         try{
             String codigo = (String) request.getSession().getAttribute("id");
             String nombre = (String) request.getParameter("nombre");
             String turno = (String) request.getParameter("turno");
             String dpi = (String) request.getParameter("dpi");
             String direccion = (String) request.getParameter("direccion");
             String genero = (String) request.getParameter("genero");
             String pass = (String) request.getParameter("pass");
             Gerente gerente = new Gerente(Integer.parseInt(codigo),nombre,turno,dpi,direccion,genero,pass);
             gerenteModelo.ModGerente(gerente);
             request.getSession().setAttribute("nombre", nombre);
             
             Cambios cambios = new Cambios("GERENTE",Integer.parseInt(codigo),nombre,null,Integer.parseInt(codigo));
             cambiosModelo.addCambios(cambios);
             
             response.sendRedirect("gerente/inicio.jsp");
         }catch(NumberFormatException | SQLException e){
             System.out.println("error " + e.getMessage());
//             request.setAttribute("success", 1);
//             response.sendRedirect("perfil");
         }
     }
}
