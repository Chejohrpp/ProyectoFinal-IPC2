<%-- 
    Document   : depositar
    Created on : 12/11/2020, 01:03:22 PM
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
        <link rel="styleSheet" href="css/estilo.css">
    </head>   
    <body> 
                   
        <c:if test="${verificar == 0}">
            <h1>No esta en su horario de trabajo, no puede realizar depositos</h1>            
        </c:if>            
        <c:if test="${verificar == 1}">
               <h1>Depositar</h1>               
               <div class="datos">
                   
                   <c:if test="${success == 0}">
                       <label id="error" style="color:red;text-align: center">Cuenta no encontrada</label>            
                    </c:if>             
                    <c:if test="${success == 1}">
                       <label id="bien" style="color:green; text-align: center">Deposito realizado correctamente</label>            
                    </c:if>  
                       
                  <form action="depositarDB" method="POST">
                        <%--No de cuenta --%>
                        <label for="noCuenta">No. de Cuenta</label>
                         <input type="number" name="noCuenta" placeholder="No. de cuenta"> 
                         <%--Monto--%>                   
                        <label for="monto">Monto</label>
                         <input type="number" step="any" min="0" name="monto" placeholder="Monto">
                         <%--boton--%>    
                         <input type="submit" value="Aceptar">
                    </form>
                 <form action="cajero/inicio.jsp">
                     <input type="submit" value="Regresar">
                 </form>      
               </div>
        </c:if>        
    </body>
</html>
