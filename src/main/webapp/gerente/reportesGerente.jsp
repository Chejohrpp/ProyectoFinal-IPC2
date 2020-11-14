<%-- 
    Document   : reportesGerente
    Created on : 12/11/2020, 08:13:18 PM
    Author     : sergi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reportes</title>
        <link rel="styleSheet" href="../css/estilo.css">
    </head>
    <body>
        <div class="datos">
            <h2>Reportes</h2>
        </div>
        <div class="informacion"> 
            <%-- --%>
            <form action="../parametros" method="POST">
                <input type="submit" value="Historial de cambios realizados en la información de una entidad en específico" name="report1"> 
            </form>
             <%-- --%>
            <form action="../parametros" method="POST">
                <input type="submit" value="Clientes con transacciones monetarias mayores a un límite establecido" name="report2"> 
            </form>
            <%-- --%>
            <form action="../parametros" method="POST">
                <input type="submit" value="Clientes con transacciones monetarias sumadas " name="report3"> 
            </form>
            <%-- --%>
            <form action="../report4" method="POST">
                <input type="submit" value="Los 10 clientes con más dinero en sus cuentas" name="report4"> 
            </form>
            <%-- --%>
            <form action="../" method="POST">
                <input type="submit" value="Clientes que no han realizado transacciones dentro de un intervalo de tiempo" name="report5"> 
            </form>
            <%-- --%>
            <form action="../parametros" method="POST">
                <input type="submit" value="Historial de transacciones por cliente" name="report6"> 
            </form>
            <%-- --%>
            <form action="../report7" method="POST">
                <input type="submit" value="Cajero que más transacciones ha realizado en un intervalo de tiempo" name="report7"> 
            </form>
             <%--Boton de regresar --%>
            <form action="inicio.jsp">
                  <input type="submit" value="Regresar">
            </form> 
        </div>
    </body>
</html>
