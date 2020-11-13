<%-- 
    Document   : buscarCliente
    Created on : 13/11/2020, 01:38:07 PM
    Author     : sergi
--%>

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
               <h1>Buscar Cliente</h1>
               <div class="datos">                   
                   <c:if test="${success == 0}">
                       <label id="error" style="color:red;text-align: center">${mensajeError}</label>            
                    </c:if>             
                    <c:if test="${success == 1}">
                       <label id="bien" style="color:green; text-align: center">Se Creo la cuenta exitosamente</label>
                       <br>
                       <label id="bien" style="color:green; text-align: center">El codigo de la Cuenta es: ${noCuenta}</label>  
                    </c:if>  
                       
                   <form action="buscarCliente" method="POST">
                    <%--Codigo --%>
                    <label for="codigo">Codigo del cliente</label>
                     <input type="number" name="codigo" required="required" placeholder="codigo del cliente"> 
                    <%--DPI --%>                   
                    <label for="dpi">DPI</label>
                    <input type="number" name="dpi" required="required" placeholder="DPI"> 
                     <%--boton--%>    
                     <input type="submit" value="buscar">
                    </form>
                     <form action="gerente/inicio.jsp">
                        <input type="submit" value="Regresar">
                    </form> 
               </div>
        </c:if>               
     </body>
