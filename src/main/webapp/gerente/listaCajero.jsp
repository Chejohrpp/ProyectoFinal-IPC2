<%-- 
    Document   : listaCajero
    Created on : 13/11/2020, 03:48:57 PM
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
            <h2>Listado de todos los Cajeros</h2>                
        </div> 
        <div class="informacion">
            <table>
            <tr>
                <th>codigo</th>
                <th>nombre</th>
                <th>turno</th>
                <th>dpi</th>
                <th>direccion</th>
                <th>genero</th>
            </tr>
                <c:forEach items="${cajeros}" var="cajero">
                    <tr>
                        <td>${cajero.codigo}</td>
                        <td> ${cajero.nombre}</td>
                        <td> ${cajero.turno}</td>
                        <td> ${cajero.dpi}</td>
                        <td> ${cajero.direccion}</td>
                        <td> ${cajero.genero}</td>
                    </tr>                              
                </c:forEach>
            </table>             
        </div>
        <div class="datos">
            <form action="gerente/listados.jsp">
                <input type="submit" value="Regresar">
            </form> 
            <form action="gerente/inicio.jsp">
                <input type="submit" value="Regresar a Inicio">
            </form> 
         </div>
    </body>
</html>
