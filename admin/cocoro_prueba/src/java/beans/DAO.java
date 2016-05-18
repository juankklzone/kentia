/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.sql.*;

/**
 *
 * @author Yeyo-chan
 */
public class DAO {
    
    public Connection conexion;
    public final static String userDB = "root";
    public final static String passDB = "lima";
    
    //Conectar a la base de datos
    public void conectar() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/cocoro", userDB, passDB);
    }
    
    //Desconectar a la base de datos
    public void desconectar() throws SQLException, ClassNotFoundException{
        conexion.close();
    }
    
    //Registrar una prenda
    public void registrarPrenda(String idPrenda, String idColor, String idClima, String idOcasion, String idTipo_prenda, String brillo, String foto) throws SQLException{
        String sql = "INSERT INTO cocoro.prenda(idPrenda,idColor,idClima,idOcasion,idTipo_prenda,brillo,foto) VALUES ('"+idPrenda+"','"+idColor+"','"+idClima+"','"+idOcasion+"','"+idTipo_prenda+"','"+brillo+"','"+foto+"');";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.executeUpdate();
    }
    
    
    
}
