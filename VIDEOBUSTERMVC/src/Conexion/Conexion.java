package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
    
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String driverUrl="jdbc:mysql://localhost:3306/videobuster";
    private static final String Usuario = "root";
    private static final String Password = "8162";       
    
    public Connection getConexion() {
        
        Connection conn = null;
        
        try { 
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(driverUrl, Usuario, Password);
            //System.out.println("CONEXION EXITOSA");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return conn;
    }
    
    public void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    //Cierre del PrepareStatement
    public void close(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    //Cierre de la conexion
    public void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
}
