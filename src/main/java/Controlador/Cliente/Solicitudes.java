/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Cliente;

import ConnectionDB.CuentasAsociadasModelo;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objetos.CuentasAsociadas;

/**
 *
 * @author sergi
 */
@WebServlet("/solicitudes")
public class Solicitudes extends HttpServlet{
    CuentasAsociadasModelo cuentaAsociadaModelo = new CuentasAsociadasModelo();
    
    
     @Override
     public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         try{ 
             String codigo = (String) request.getParameter("codigo");
             String estado = (String) request.getParameter("estado");
             if (codigo == null && estado == null) {
                 llenarMensajes(request,response);
                request.getRequestDispatcher("/cliente/solicitudes.jsp").forward(request, response); 
             }else{
                 doPost(request,response);
             }
                     
         }catch(Exception e){
             System.out.println("error get " + e.getMessage());
         }
     }
     
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         try{             
             String codigo = (String) request.getParameter("codigo");
             String estado = (String) request.getParameter("estado");
             CuentasAsociadas cuentaAsociada = cuentaAsociadaModelo.obtenerCuentaAsociadaCodigo(Integer.parseInt(codigo));
             if (cuentaAsociada == null) {
                 System.out.println("vacio");
             }
             if (estado.equalsIgnoreCase("true")) {
                 cuentaAsociada.setEstado(Boolean.TRUE);
                 cuentaAsociada.setEstado_enviar(Boolean.FALSE);
                 cuentaAsociadaModelo.modCuentasAsociadas(cuentaAsociada);          
             }else{
                 cuentaAsociada.setEstado(Boolean.FALSE);
                 cuentaAsociada.setEstado_enviar(Boolean.FALSE);
                 cuentaAsociada.setNo_intentos(cuentaAsociada.getNo_intentos() + 1);
                 cuentaAsociadaModelo.modCuentasAsociadas(cuentaAsociada); 
             }             
             llenarMensajes(request,response);
             request.getRequestDispatcher("/cliente/solicitudes.jsp").forward(request, response); 
        
         }catch(Exception e){
             System.out.println("error post " + e.getMessage());
         }
     }
    
    private void llenarMensajes(HttpServletRequest request, HttpServletResponse response) throws SQLException{
        String codigo = (String) request.getSession().getAttribute("id");
        List<CuentasAsociadas> solicitudes = cuentaAsociadaModelo.solicitudesAsociacion(Integer.parseInt(codigo));
        request.setAttribute("solicitudes", solicitudes);
    }
}
