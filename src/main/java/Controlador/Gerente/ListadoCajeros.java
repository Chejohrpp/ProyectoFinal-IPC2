/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Gerente;

import ConnectionDB.CajeroModelo;
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
@WebServlet("/listadoCajeros")
public class ListadoCajeros extends HttpServlet {
    CajeroModelo cajeroModelo = new CajeroModelo();
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    
            
         try{            
            request.setAttribute("cajeros", cajeroModelo.todosCajeros());
            request.getRequestDispatcher("/gerente/listaCajero.jsp").forward(request, response);
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error : " + e.getMessage());
        }      
     }
}
