<%-- 
    Document   : listaGerentes
    Created on : 13/11/2020, 03:51:18 PM
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
            <h2>Listado de todos los Gerentes</h2>                
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
                <c:forEach items="${gerentes}" var="gerente">
                    <tr>
                        <td>${gerente.codigo}</td>
                        <td> ${gerente.nombre}</td>
                        <td> ${gerente.turno}</td>
                        <td> ${gerente.dpi}</td>
                        <td> ${gerente.direccion}</td>
                        <td> ${gerente.genero}</td>
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
