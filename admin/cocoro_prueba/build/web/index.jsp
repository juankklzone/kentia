<%-- 
    Document   : index
    Created on : 18/05/2016, 12:09:12 AM
    Author     : Yeyo-chan
--%>
<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%! //conexion a mysql
    Connection conexion = null;
    String driver="com.mysql.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/cocoro";
    String user="root";
    String pwd="LarzRS";
%>
<%  
    PreparedStatement ps = null;
    ResultSet rs = null;
    //querys para llenar los combobox con las diferentes caracteristicas de la prenda
    String query1 = "SELECT * from cocoro.climas";
    String query2 = "SELECT * from cocoro.colors";
    String query3 = "SELECT * from cocoro.ocasions";
    String query4 = "SELECT * from cocoro.tipo_prendas";
    //se inicia la conexion
    if(conexion == null){
        Class.forName(driver).newInstance();
        conexion = DriverManager.getConnection(url,user,pwd);
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Registro de prendas</h1>
        <hr color="red"/>
        <br>
        
        <form action="registroPrenda" method="post">
        
        ID Prenda: <input type="text" name="idPrenda">
        
        <br><br>
        <select name="idClima">
            <option>Tipo Clima</option>
            <%
                    ps = conexion.prepareStatement(query1);
                    rs = ps.executeQuery();
                    while(rs.next()) {
             %>
             <option value="<%= rs.getString("id") %>"><%= rs.getString("Nombre") %></option>
             <%
                }
            %>
        </select>
        
        <select name="idColor">
            <option>Tipo Color</option>
            <%
                    ps = conexion.prepareStatement(query2);
                    rs = ps.executeQuery();
                    while(rs.next()) {
             %>
             <option value="<%= rs.getString("id") %>"><%= rs.getString("Nombre") %></option>
             <%
                }
            %>
        </select>
        
        <select name="idOcasion">
            <option>Tipo Ocasion</option>
            <%
                    ps = conexion.prepareStatement(query3);
                    rs = ps.executeQuery();
                    while(rs.next()) {
             %>
             <option value="<%= rs.getString("id") %>"><%= rs.getString("Nombre") %></option>
             <%
                }
            %>
        </select>
        
        <select name="idTipo_prenda">
            <option>Tipo Prenda</option>
            <%
                    ps = conexion.prepareStatement(query4);
                    rs = ps.executeQuery();
                    while(rs.next()) {
             %>
             <option value="<%= rs.getString("id") %>"><%= rs.getString("nombre") %></option>
             <%
                }
            %>
        </select>
        
        <br><br>
        
        Brillo(int): <input type="text" name="brillo">
        Foto: <input type="text" name="foto">
        
        <br><br>
        <input type="submit" value="Registrar">
        </form>
        
    </body>
</html>