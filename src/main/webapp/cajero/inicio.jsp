<%-- 
    Document   : inicio
    Created on : 11/11/2020, 10:28:03 PM
    Author     : sergi
--%>

<%--INICIO DEL CAJERO --%>

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
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            if (session.getAttribute("id") == null) {
                response.sendRedirect("../login");
            }
        %>
        
        <%--cabecera --%>        
        <div class="cabecera">    
            <h2>Nombre: <%= session.getAttribute("nombre")%></h2>
            <h3>Codigo: <%= session.getAttribute("id") %></h3>            
            <form action="../logout">
                <input type="submit" value="Cerrar sesion">
            </form>
        </div>            
        <a href="inicio.jsp"><h1>Billeton</h1></a>
        <%--Sus Acciones --%>
        <div class="inicio">
            <form action="depositar" method="GET">
                <input type="submit" value="Depositar">
            </form>
            <form action="retirar" method="GET">                
                <input type="submit" value="Retirar">
            </form>
            <form action="reportesCajero.jsp">
                <input type="submit" value="Reportes">
            </form>             
        </div>        
    </body>
</html>
