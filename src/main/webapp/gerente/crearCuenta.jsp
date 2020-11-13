<%-- 
    Document   : crearCuenta
    Created on : 13/11/2020, 12:17:17 AM
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
        <link rel="styleSheet" href="css/estilo.css">
    </head>
    <body>
        <c:if test="${verificar == 0}">
            <h1>No esta en su horario de trabajo, no puede crear una cuenta</h1>            
        </c:if>
            
        <c:if test="${verificar == 1}">
               <h1>Crear Cuenta</h1>
               <div class="datos">                   
                   <c:if test="${success == 0}">
                       <label id="error" style="color:red;text-align: center">${mensajeError}</label>            
                    </c:if>             
                    <c:if test="${success == 1}">
                       <label id="bien" style="color:green; text-align: center">Se Creo la cuenta exitosamente</label>
                       <br>
                       <label id="bien" style="color:green; text-align: center">El codigo de la Cuenta es: ${noCuenta}</label>  
                    </c:if>  
                       
                   <form action="crearCuenta" method="POST">
                    <%--Codigo --%>
                    <label for="codigo">Codigo del cliente</label>
                     <input type="number" name="codigo" placeholder="codigo del cliente"> 
                    <%--DPI --%>                   
                    <label for="dpi">DPI</label>
                     <input type="number" name="dpi" placeholder="DPI"> 
                     <%--Monto--%>                   
                    <label for="monto">Monto</label>
                     <input type="number" step="any" min="0" name="monto" placeholder="Monto">
                     <%--fecha de creacion--%> 
                     <label for="usuario">fecha de creacion</label>
                    <input type="date" name="fecha" value="<%= LocalDate.now()%>"> 
                     <%--boton--%>    
                     <input type="submit" value="Aceptar">
                    </form>
                     <form action="gerente/inicio.jsp">
                        <input type="submit" value="Regresar">
                    </form> 
               </div>
        </c:if>               
     </body>
</html>
