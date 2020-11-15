<%-- 
    Document   : ParametroNombreCliente
    Created on : 15/11/2020, 12:11:37 PM
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
               <h1>Coloca un Nombre del cliente</h1>               
               <div class="datos">    
                   <h2>Nota: se tiene que colocar como esta Registrado(No importa mayusculas/minusculas)</h2>
                   <form action="../historialTransaccionBusquedaCliente" method="GET">
                     <%--nombre del Cliente--%> 
                     <label for="usuario">Nombre del Cliente</label>
                    <input type="text" name="nombreCliente" required="required" placeholder="Nombre del Cliente"> 
                     <%--boton--%>    
                     <input type="submit" value="Aceptar">
                    </form>
                     <form action="reportesGerente.jsp">
                        <input type="submit" value="Regresar">
                    </form> 
               </div>              
     </body>
</html>
