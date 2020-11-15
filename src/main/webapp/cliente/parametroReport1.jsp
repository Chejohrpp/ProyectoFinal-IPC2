<%-- 
    Document   : parametroReport1
    Created on : 15/11/2020, 01:07:08 AM
    Author     : sergi
--%>

<%@page import="objetos.Cuenta"%>
<%@page import="java.util.List"%>
<%@page import="ConnectionDB.CuentaModelo"%>
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
               <h1>Escoja una cuenta</h1>
               <div class="datos">               
                   <form action="../transaccionesCuenta15" method="GET">
                     <%--fecha de inicio--%> 
                     <select name="noCuenta">
                         <% CuentaModelo cuentaModelo = new CuentaModelo();
                                List<Cuenta> cuentas = cuentaModelo.cuentasCliente(id);
                                for (int i = 0; i < cuentas.size(); i++) { %>
                                
                                <option><%= cuentas.get(i).getCodigo() %></option>  

                             <% } %> 
                     </select>
                     <%--boton--%>    
                     <input type="submit" value="Aceptar">
                    </form>
                     <form action="inicio.jsp">
                        <input type="submit" value="Regresar">
                    </form> 
               </div>              
     </body>
</html>
