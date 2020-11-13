<%-- 
    Document   : listados
    Created on : 13/11/2020, 03:16:44 PM
    Author     : sergi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listados</title>
        <link rel="styleSheet" href="../css/estilo.css">
    </head>
    <body>
        <div class="datos">
            <h2>Listados</h2>
        </div>
        <div class="informacion"> 
            <%--todos los cliente--%>
            <form action="../listadoClientes" method="POST">
                <input type="submit" value="Listado de todos los clientes" name="listaClientes"> 
            </form>
             <%-- todos los cajeros--%>
            <form action="../listadoCajeros" method="POST">
                <input type="submit" value="listado de todos los cajeros" name="listaCajeros"> 
            </form>
            <%-- todos los gerentes--%>
            <form action="../listadoGerentes" method="POST">
                <input type="submit" value="listado de todos los gerentes" name="listaGerentes"> 
            </form>
            <%-- todos las transacciones--%>
            <form action="../listadoTransacciones" method="POST">
                <input type="submit" value="listado de todas las transacciones" name="listaTransacciones"> 
            </form>
            <form action="inicio.jsp">
                  <input type="submit" value="Regresar">
            </form> 
        </div>
    </body>
</html>
