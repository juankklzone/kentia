<%-- 
    Document   : consultaPrendas
    Created on : 21/05/2016, 07:03:53 PM
    Author     : Yeyo-chan
--%>
<%@taglib prefix="t" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.net.*,java.sql.*" %>
<%@ page import = "beans.DAO" %>
<t:if test="${sessionScope['sessionUsuario']==null}">
    <% response.sendRedirect("index.jsp");%>
</t:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta de prendas - Administración de Datos</title>
    </head>
    <body>
        <h1>Consulta de prendas</h1>
        <hr color="red"/>
        <br>        
        <form method="get" action=" ConsultarPrendas">
            <input type="submit" value="Consultar" />
        </form>
        <hr color="red"/>
        <br>
        <p><a href="registroPrendas.jsp">Agregar prendas</a></p>
        <p><a href="menu.jsp">Menu Principal</a></p>
        <p><a href="eliminarPrendas.jsp">Eliminar prendas</a></p>
        <br>
        <form action="CerrarSesion" method="get">
            <p><input type="submit" value="Cerrar Sesion"></p>
        </form>
        
    </body>
</html>
