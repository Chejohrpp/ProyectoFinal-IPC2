/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Gerente;

import ConnectionDB.GerenteModelo;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sergi
 */
@WebServlet("/listadoGerentes")
public class ListadoGerentes extends HttpServlet{
    GerenteModelo gerenteModelo = new GerenteModelo();
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    
            
         try{            
            request.setAttribute("gerentes", gerenteModelo.todosGerentes());
            request.getRequestDispatcher("/gerente/listaGerentes.jsp").forward(request, response);
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error : " + e.getMessage());
        }      
     }
}
