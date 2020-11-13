/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Gerente;

import ConnectionDB.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import objetos.*;

/**
 *
 * @author sergi
 */
@WebServlet("/crearCliente")
@MultipartConfig(location="C:/tmp", fileSizeThreshold=1024*1024, 
    maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class CrearCliente extends HttpServlet {
    
    GerenteModelo gerenteModelo = new GerenteModelo();
    ClienteModelo clienteModelo = new ClienteModelo();
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String codigo = (String) request.getSession().getAttribute("id");
            if (gerenteModelo.verificarRequisitos(Integer.parseInt(codigo))) {
                request.setAttribute("verificar", 1);                
            }else{
                request.setAttribute("verificar", 0);
            }
            request.getRequestDispatcher("/gerente/crearCliente.jsp").forward(request, response);
        }catch(IOException | NumberFormatException | SQLException | ServletException e){
            
        }
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{

            String nombre = (String) request.getParameter("nombre");
            String fecha = (String) request.getParameter("fecha");
            String dpi = (String) request.getParameter("dpi");
            String direccion = (String) request.getParameter("direccion");
            String genero = (String) request.getParameter("genero");
            Part dpi_pdf =  request.getPart("dpi_pdf");            
            String pass = (String) request.getParameter("pass");             

                    
            Cliente cliente = new Cliente(nombre,fecha,dpi,direccion,genero,null,pass);
            long newCodigo = clienteModelo.addClienteSinCodigo(cliente);
            
            request.setAttribute("success", 1);
            request.setAttribute("verificar", 1);
            request.setAttribute("newCodigo", newCodigo);
            request.getRequestDispatcher("/gerente/crearCliente.jsp").forward(request, response);

            
        }catch(IOException | NumberFormatException | SQLException | ServletException e){
            error(request,response,"Hubo un dato invalido, vuelva a intentarlo");
        }
    }
    
    private void error(HttpServletRequest request,HttpServletResponse response,String error) throws ServletException, IOException{
         request.setAttribute("success", 0);
         request.setAttribute("verificar", 1);
         request.setAttribute("mensajeError", error);
         request.getRequestDispatcher("/gerente/crearCliente.jsp").forward(request, response);
     }
}
