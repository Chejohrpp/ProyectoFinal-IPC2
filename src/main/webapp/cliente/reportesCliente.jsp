<%-- 
    Document   : reportesCliente
    Created on : 14/11/2020, 03:31:05 PM
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
            <%--Las últimas 15 transacciones más grandes realizadas en el último año, por cuenta--%>
            <form action="../parametros" method="POST">
                <input type="submit" value="Las últimas 15 transacciones más grandes realizadas en el último año, por cuenta" name="resport1"> 
            </form>
             <%----%>
            <form action="../parametros" method="POST">
                <input type="submit" value="Listado de todas las transacciones realizadas dentro de un intervalo de tiempo" name="report2"> 
            </form>
            <%----%>
            <form action="../parametros" method="POST">
                <input type="submit" value="La cuenta con más dinero y sus transacciones con intervalo de tiempo" name="report3"> 
            </form>
            <%----%>
            <form action="../AsociacionRealizadas" method="GET">
                <input type="submit" value="Asociaciones Realizadas" name="report4"> 
            </form>
            <%----%>
            <form action="../AsociacionRecibidas" method="GET">
                <input type="submit" value="Asociaciones Recibidas" name="report5"> 
            </form>
            <%--Regresar--%>
            <form action="inicio.jsp">
                     <input type="submit" value="Regresar">
            </form> 
        </div>
    </body>
</html>
