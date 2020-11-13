<%-- 
    Document   : actualizarCajero
    Created on : 13/11/2020, 01:41:39 PM
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
            <h1>No esta en su horario de trabajo, no puede crear un Cajero</h1>            
        </c:if>
            
        <c:if test="${verificar == 1}">
               <h1>Informacion del cajero</h1>
               <div class="datos">                   
                   <c:if test="${success == 0}">
                       <label id="error" style="color:red;text-align: center">${mensajeError}</label>            
                    </c:if>             
                    <c:if test="${success == 1}">
                       <label id="bien" style="color:green; text-align: center">Se Creo la cuenta exitosamente</label>
                       <br>
                       <label id="bien" style="color:green; text-align: center">El codigo de la Cuenta es: ${noCuenta}</label>  
                    </c:if>  
                       
                   <form action="actualizarCajero" method="POST">
                    <%--Codigo --%>
                    <label for="codigo" >Codigo</label>
                    <input type="text" name="codigo1" required="required" value="${codigo}" placeholder="Codigo" disabled>
                    <input type="hidden" name="codigo" value="${codigo}">
                    <%--nombre --%>
                    <label for="nombre">Nombre</label>
                    <input type="text" name="nombre" required="required" value="${nombre}" placeholder="Nombre">
                    <%--turno --%>
                    <label for="turno">Turno</label>
                    <select name="turno">
                        <option>${turno}</option>
                        <option>MATUTINO</option>
                        <option>VERSPERTINO</option>
                    </select>
                    <%--DPI --%>                   
                    <label for="dpi">DPI</label>
                    <input type="number" min="0" name="dpi1" required="required" value="${dpi}" placeholder="DPI" disabled>
                    <input type="hidden" name="dpi" value="${dpi}">
                     <%--direccion--%>
                     <label for="dpi">Direccion</label>
                    <input type="text" name="direccion" required="required" value="${direccion}" placeholder="Direccion">
                     <%--genero --%>
                     <label for="genero">Genero</label>
                    <select name="genero">
                        <option>${genero}</option>
                        <option>Masculino</option>
                        <option>Femenino</option>
                    </select>
                     <%--password --%>                     
                    <label for="password">Contraseña</label>
                    <input type="text" name="pass" required="required" value="${pass}" placeholder="Contraseña">                     
                     <%--boton--%>    
                     <input type="submit" value="Actualizar">
                    </form>                     
                     <form action="gerente/inicio.jsp">
                        <input type="submit" value="Regresar">
                    </form> 
               </div>
        </c:if>               
     </body>
</html>
