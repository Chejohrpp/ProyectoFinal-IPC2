<%-- 
    Document   : parametrosReport3
    Created on : 15/11/2020, 12:56:43 AM
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
               <h1>Coloca un intervalo de Fechas</h1>
               <div class="datos">               
                   <form action="../cuentaMasDineroTransaccionesIntervalo" method="GET">
                     <%--fecha de inicio--%> 
                     <label for="usuario">fecha de Inicio</label>
                    <input type="date" name="fechaInicio" value="<%= LocalDate.now()%>" required="required"> 
                    <%--fecha de Fin--%> 
                     <label for="usuario">fecha Final</label>
                    <input type="date" name="fechaFinal" value="<%= LocalDate.now()%>" required="required" max="<%= LocalDate.now()%>"> 
                     <%--boton--%>    
                     <input type="submit" value="Aceptar">
                    </form>
                     <form action="inicio.jsp">
                        <input type="submit" value="Regresar">
                    </form> 
               </div>              
     </body>
</html>