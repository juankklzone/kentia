/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.File;
import java.sql.*;

/**
 *
 * @author Yeyo-chan
 */
public class DAO {
    
    public Connection conexion;
    public final static String userDB = "root";
    public final static String passDB = "lima";
    
    private Connection connection;
    public static  String matrizaux[][];
    private Statement statement;
    
    
    //Conectar a la base de datos
    public void conectar() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/cocoro", userDB, passDB);
    }
    
    //Desconectar a la base de datos
    public void desconectar() throws SQLException, ClassNotFoundException{
        conexion.close();
    }
    
    //Registrar prendas con csv
    public void registrarPrendas(String ruta) throws SQLException{
        String sql = "LOAD DATA LOCAL INFILE '"+ruta+"' INTO TABLE cocoro.prendas FIELDS TERMINATED BY ',' (foto, color_id, clima_id, tipo_prenda_id, ocasionid);";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.executeUpdate();
    }
    
    //Eliminar una prenda
    public void borrarPrendas(String idPrenda) throws SQLException{
        String sql = "DELETE FROM cocoro.prendas WHERE id ='"+idPrenda+"'";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.executeUpdate();
    }
    
    //Revisa si el usuario y contraseña son correctos
    public boolean siExisteCuenta(String usuario, String pass) throws SQLException{
        String sql = "SELECT * FROM cocoro.administrador WHERE usuario ='"+usuario+"' AND contraseña ='"+pass+"';";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        return rs.next();
    }
    
    
    public String[][] hasConsulta(){
        
	int filas,columnas;
	filas=0;
	try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Cargado");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/cocoro", "root", "lima");
            statement = connection.createStatement();
            System.out.println("Conexion Establecida");
            ResultSet resultado;
            resultado=statement.executeQuery("SELECT id, color_id, clima_id, tipo_prenda_id, ocasionid, brillo, usuario_id, foto FROM cocoro.prendas");
            columnas=resultado.getMetaData().getColumnCount();
            while(resultado.next()){
                filas++;
            }
            resultado.beforeFirst();
            matrizaux=new String[filas][columnas];
            for(int i=0;i<filas;i++){
                resultado.next();
                for(int j=0;j<columnas;j++){
                    matrizaux[i][j]=resultado.getString(j+1);
                    System.out.print(matrizaux[i][j]+"");
                }
		System.out.println();
            }
        }catch ( ClassNotFoundException | SQLException e) {
        }
        return matrizaux;
    }
    
}