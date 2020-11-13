<%-- 
    Document   : listaTransacciones
    Created on : 13/11/2020, 03:52:49 PM
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
            <h2>Listado de todos las Transacciones</h2>                
        </div> 
        <div class="informacion">
            <table>
            <tr>
                <th>codigo </th>
                <th>monto </th>
                <th>tipo </th>
                <th>hora </th>
                <th>fecha </th>
                <th>codigo del cajero</th>
                <th>No. de Cuenta</th>
            </tr>
                <c:forEach items="${transacciones}" var="tran">
                    <tr>
                        <td>${tran.codigo}</td>
                        <td> ${tran.monto}</td>
                        <td> ${tran.tipo}</td>
                        <td> ${tran.hora}</td>
                        <td> ${tran.fecha}</td>
                        <td> ${tran.cajero_codigo}</td>
                        <td> ${tran.cuenta_codigo}</td>
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