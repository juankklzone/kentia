<%-- 
    Document   : menu
    Created on : 22/05/2016, 01:08:45 AM
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
        <title>Menu Principal - Administración de Datos</title>
    </head>
    <body>
        <h1>Menu Principal</h1>
        <hr color="red"/>
        <br><br>
        <p><a href="registroPrendas.jsp">Agregar prendas</a></p>
        <p><a href="consultaPrendas.jsp">Consultar prendas</a></p>
        <p><a href="eliminarPrendas.jsp">Eliminar prendas</a></p>
        <br>
        <form action="CerrarSesion" method="get">
            <p><input type="submit" value="Cerrar Sesion"></p>
        </form>
    </body>
</html>
