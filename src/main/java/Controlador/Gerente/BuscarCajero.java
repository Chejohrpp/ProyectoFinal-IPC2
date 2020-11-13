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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objetos.*;

/**
 *
 * @author sergi
 */
@WebServlet("/buscarCajero")
public class BuscarCajero extends HttpServlet{
    GerenteModelo gerenteModelo = new GerenteModelo();
    CajeroModelo cajeroModelo = new CajeroModelo();
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String codigo = (String) request.getSession().getAttribute("id");
            if (gerenteModelo.verificarRequisitos(Integer.parseInt(codigo))) {
                request.setAttribute("verificar", 1);                
            }else{
                request.setAttribute("verificar", 0);
            }
            request.getRequestDispatcher("/gerente/buscarCajero.jsp").forward(request, response);
        }catch(IOException | NumberFormatException | SQLException | ServletException e){
            
        }
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String codigo = (String) request.getParameter("codigo");
            String dpi = (String) request.getParameter("dpi");
            Cajero cajero = cajeroModelo.obtenerCajero(Integer.parseInt(codigo));            
            if (cajero != null) {
                if (cajero.getDpi().equals(dpi)) {                    
                    request.setAttribute("codigo", cajero.getCodigo());
                    request.setAttribute("direccion", cajero.getDireccion());
                    request.setAttribute("dpi", cajero.getDpi());
                    request.setAttribute("genero", cajero.getGenero());
                    request.setAttribute("nombre", cajero.getNombre());
                    request.setAttribute("pass", cajero.getPassword());
                    request.setAttribute("turno", cajero.getTurno());
                    request.setAttribute("verificar", 1);
                    request.getRequestDispatcher("/gerente/actualizarCajero.jsp").forward(request, response);
                }else{
                     error(request,response,"el dpi no coincide con el codigo");
                }
            }else{
                 error(request,response,"El codigo del cajero no existe, cree un cajero");
            }
            
        }catch(IOException | NumberFormatException | SQLException | ServletException e){
            error(request,response,"Hubo un dato invalido, vuelva a intentarlo");
        }
    }    
    private void error(HttpServletRequest request,HttpServletResponse response,String error) throws ServletException, IOException{
         request.setAttribute("success", 0);
         request.setAttribute("verificar", 1);
         request.setAttribute("mensajeError", error);
         request.getRequestDispatcher("/gerente/buscarCajero.jsp").forward(request, response);
     }
    
    
}
