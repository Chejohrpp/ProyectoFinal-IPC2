<%-- 
    Document   : listaClientes
    Created on : 13/11/2020, 03:27:06 PM
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
            <h2>Listado de todos los Clientes</h2>                
        </div> 
        <div class="informacion">
            <table>
            <tr>
                <th>codigo</th>
                <th>nombre</th>
                <th>fecha de nacimiento</th>
                <th>dpi</th>
                <th>direccion</th>
                <th>genero</th>
            </tr>
                <c:forEach items="${clientes}" var="cliente">
                    <tr>
                        <td>${cliente.codigo}</td>
                        <td> ${cliente.nombre}</td>
                        <td> ${cliente.birth}</td>
                        <td> ${cliente.dpi}</td>
                        <td> ${cliente.direccion}</td>
                        <td> ${cliente.genero}</td>
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
