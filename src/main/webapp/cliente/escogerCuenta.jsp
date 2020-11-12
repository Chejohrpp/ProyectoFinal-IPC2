<%-- 
    Document   : escogerCuenta
    Created on : 12/11/2020, 12:19:30 AM
    Author     : sergi
--%>

<%@page import="ConnectionDB.CuentaModelo"%>
<%@page import="java.util.List"%>
<%@page import="objetos.Cuenta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Billeton</title>
        <link rel="styleSheet" href="../css/estilo.css">
    </head>
    <body>        
        <%
            int id = 0;
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            if (session.getAttribute("id") == null) {
                response.sendRedirect("../login");
            }else{
                
                String codigo = (String)session.getAttribute("id");
                id = Integer.parseInt(codigo);
            }
        %>
               
        <a href="inicio.jsp"><h1>Billeton</h1></a>
        
        <%--Sus Acciones --%>
        <div class="inicio">
            <h2>Elija una cuenta para usar</h2>            
                <% CuentaModelo cuentaModelo = new CuentaModelo();
                   List<Cuenta> cuentas = cuentaModelo.cuentasCliente(id);
                   for (int i = 0; i < cuentas.size(); i++) { %>   
                <form action="../inicioCliente" method="POST">                    
                   <input type="submit" value="<%= cuentas.get(i).getCodigo() %>" name="noCuenta">  
                </form>
                <% } %>                                 
        </div>        
    </body>
</html>
