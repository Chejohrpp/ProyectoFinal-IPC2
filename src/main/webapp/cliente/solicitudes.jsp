<%-- 
    Document   : solicitudes
    Created on : 13/11/2020, 09:43:54 PM
    Author     : sergi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado</title>
        <link rel="styleSheet" href="css/estilo.css">        
    </head>
    <body>
         <div class="datos">
            <h2>Solicitudes de asociacion de cuentas</h2>                
        </div> 
        <div class="informacion">
            <table>
            <tr>
                <th>codigo del cliente</th>
                <th>codigo de la cuenta</th>
                <th>No. de intentos</th>
                <th>Aceptar</th>
                <th>Rechazar</th>
            </tr>
                <c:forEach items="${solicitudes}" var="solicitud">
                    <tr>
                        <td>${solicitud.cliente_codigo}</td>
                        <td> ${solicitud.cuenta_codigo}</td>
                        <td> ${solicitud.no_intentos}</td>
                        <td><a href="${pageContext.request.contextPath}/solicitudes?codigo=${solicitud.codigo}&estado=true">Aceptar</a></td>
                        <td><a href="${pageContext.request.contextPath}/solicitudes?codigo=${solicitud.codigo}&estado=false">Rechazar</a></td>
                    </tr>                   
                    
                </c:forEach>
            </table>             
        </div>
        <div class="datos">
            <form action="cliente/inicio.jsp">
                <input type="submit" value="Regresar a Inicio">
            </form> 
         </div>
    </body>