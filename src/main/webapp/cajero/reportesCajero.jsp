<%-- 
    Document   : reportesCajero
    Created on : 12/11/2020, 01:36:53 PM
    Author     : sergi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="styleSheet" href="../css/estilo.css">
    </head>
    <body>
        <div class="datos">
            <h2>Reportes</h2>
        </div>
        <div class="informacion"> 
            <%--Listado de depósitos y retiros realizados durante su turno de manera ordenada, mostrando el total existente en caja (depósitos - retiros). --%>
            <form action="../transaccionesHoyCajero" method="GET">
                <input type="submit" value="Listado de depósitos y retiros realizados durante su turno" name="resport1"> 
            </form>
             <%--Listado de las transacciones realizadas por día en un intervalo de tiempo, mostrando el balance final. --%>
            <form action="parametro.jsp">
                <input type="submit" value="Listado de las transacciones realizadas por día en un intervalo de tiempo" name="report2"> 
            </form>
            <form action="inicio.jsp">
                     <input type="submit" value="Regresar">
            </form> 
        </div>
    </body>
</html>
