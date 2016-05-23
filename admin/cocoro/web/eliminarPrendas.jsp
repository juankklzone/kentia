<%-- 
    Document   : eliminarPrendas
    Created on : 21/05/2016, 08:01:03 PM
    Author     : Yeyo-chan
--%>
<%@taglib prefix="t" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<t:if test="${sessionScope['sessionUsuario']==null}">
    <% response.sendRedirect("index.jsp");%>
</t:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminación de prendas - Administración de Datos</title>
    </head>
    <body>
        <h1>Eliminación de prendas</h1>
        <hr color="red"/>
        <br><br>
        <p style="color: #00ff00">${sessionScope['success']}</p>
        <form action="EliminarPrendas" method="post">
            ID prenda: <input type="text" name="idPrenda">            
            <br><br>
            <input type="submit" value="Eliminar">
        </form>
        <br><br>
        <hr color="red"/>
        <br>
        <p><a href="registroPrendas.jsp">Agregar prendas</a></p>
        <p><a href="consultaPrendas.jsp">Consultar prendas</a></p>
        <p><a href="menu.jsp">Menu Principal</a></p>
        <br>
        <form action="CerrarSesion" method="get">
            <p><input type="submit" value="Cerrar Sesion"></p>
        </form>
        
    </body>
</html>
