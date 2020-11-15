<%-- 
    Document   : ParametrosHistorialCambios
    Created on : 15/11/2020, 11:58:35 AM
    Author     : sergi
--%>

<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Billeton</title>
        <link rel="styleSheet" href="../css/estilo.css">
    </head>
    <body>   
               <h1>Elige una entidad</h1>
               <div class="datos">               
                   <form action="../historialCambios" method="GET">
                     <%--Tipo--%> 
                     <select name="tipo">
                         <option>CAJERO</option>
                         <option>CLIENTE</option>
                         <option>GERENTE</option>
                     </select>
                     <%--boton--%>    
                     <input type="submit" value="Aceptar">
                    </form>
                     <form action="reportesGerente.jsp">
                        <input type="submit" value="Regresar">
                    </form> 
               </div>              
     </body>
</html>