<%-- 
    Document   : inicio
    Created on : 12/11/2020, 12:19:38 AM
    Author     : sergi
--%>

<%--INICIO DEL CLIENTE --%>

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
            <form action="../bandejaEntrada">
                <input type="submit" value="Mensajes">
            </form>
            <form action="../logout">
                <input type="submit" value="Cerrar sesion">
            </form>
        </div>            
        <a href="inicio.jsp"><h1>Billeton</h1></a>       
        
        <%--Sus Acciones --%>
        <div class="inicio">            
        <h3>No. de cuenta: <%= session.getAttribute("noCuenta") %> </h3>
        <h3>Credito que posee: <%= session.getAttribute("credito") %> </h3>  
        
            <form action="#" method="GET">
                <input type="submit" value="Realizar una Transaccion">
            </form>
            <form action="asociarCuenta" method="GET">                
                <input type="submit" value="Asociar una cuenta">
            </form>
            <form action="reportesCliente.jsp">
                <input type="submit" value="Reportes">
            </form>             
        </div>        
    </body>
</html>
