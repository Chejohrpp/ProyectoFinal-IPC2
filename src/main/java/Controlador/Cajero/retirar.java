/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Cajero;

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
@WebServlet("/retirar")
public class retirar extends HttpServlet{
    
    CajeroModelo cajeroModelo = new CajeroModelo();
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try{
            String codigo = (String)request.getSession().getAttribute("id");            
            if (cajeroModelo.verificarRequisitos(Integer.parseInt(codigo))) {
                request.setAttribute("verificar", 1);
                request.getRequestDispatcher("/cajero/retirar.jsp").forward(request, response);
            }else{
                request.setAttribute("verificar", 0);
                request.getRequestDispatcher("/cajero/retirar.jsp").forward(request, response);
            }           
        } catch(NumberFormatException e){
            System.out.println("error " + e.getMessage());
        } catch (SQLException ex) {
            System.out.println("erro sql " + ex.getMessage());
        }
    }
    
}
