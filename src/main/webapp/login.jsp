<%-- 
    Document   : login
    Created on : 8/11/2020, 12:38:50 AM
    Author     : sergi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Billeton login</title>
        <link rel="styleSheet" href="css/estilo.css">
        <style>
            #error {
                color: red;
            }
        </style>
    </head>
    <body>
        <div class="caja">
            <h1>Billeton</h1> 
            
            <c:if test="${success == 0}">
            <label id="error">Usuario o Contraseña Incorrecto</label>            
            </c:if> 
            
            <form action="login" method="POST">
                <%--Nombre de usuario--%>
                <label for="usuario">Codigo</label>
                <input type="number" name="codigo" placeholder="Codigo">
                <%--Contraseña --%>
                <label for="password">Contraseña</label>
                <input type="password" name="pass" placeholder="Contraseña">                
                <select name="tipo">
                    <option>cliente</option>
                    <option>cajero</option>
                    <option>gerente</option>
                </select>
                <%--boton --%>
                <input type="submit" value="Ingresar">                
            </form>
        </div>       
    </body>
</html>
