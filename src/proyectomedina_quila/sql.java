/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomedina_quila;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.driver.OracleDriver;

/**
 *
 * @author Panchitox
 */
public class sql {
    //usuario bd
    private final String USUARIO = "usuario01";
    //password
    private final String PASS = "usuario01";
    //SID de la bd, este lo registramos en la instalacion
    private final String SID = "XE";
    //host de la bd
    private final String HOST = "pvd-pm-03-01.aws.smartcloud.cl";
    //puerto 1521 es el estandar para este tipo d instalaciones
    private final int PUERTO = 1521;
    //Object donde se almacena la conexion
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    /*public void setConnection(Connection connection) {
        this.connection = connection;
    }*/
    
    //instanciar objeto de tipo OracleDriver para registrar y posterior uso de el
    //este Object lo provee el driver jlbc, acuerdate de cargarlo en libreria JAR
    public void registrarDriver() throws SQLException{
        OracleDriver oracleDriver = new oracle.jdbc.driver.OracleDriver();
        DriverManager.registerDriver(oracleDriver);
    }  //el metodo getConnection lanza la excepcion la cual propagamos "throws SQLException"
    
    //ingresamos desde netbeans a Subversion de Oracle
    public void conectar() throws SQLException{
        if(connection == null|| connection.isClosed()==true){
            String cadenaConexion = "jdbc:oracle:thin:@"+HOST+":"+PUERTO+":"+SID;
            registrarDriver();
            connection = DriverManager.getConnection(cadenaConexion,USUARIO,PASS);
        }
    }
    
    //METODO QUE CIERRA LA CONEXION UNA VEZ Q HAYAMOS TEMRINADO DE USAR LA BBDD
    public void cerrar() throws SQLException{
        if (connection != null && connection.isClosed()==false){
            connection.close();
        }
    }
   
    public static void main(String[] args)  {
        sql conexionOracle = new sql();
        try {
            conexionOracle.conectar();
            Connection conn = conexionOracle.getConnection();
            try(//driver@machineName:port:SID,userid,password
                Statement stmt = conn.createStatement()){
                System.out.println("Conexion realizada");
                String query = "select * from cliente;";
                System.out.println("ok");
                PreparedStatement buscar = conn.prepareStatement(query);
                ResultSet rs = buscar.executeQuery();
                
                while (rs.next()){
                    System.out.println("hola mundo");
                    System.out.println(rs.getInt(1)+" " + rs.getString(2) + " " + rs.getString(3)+" " + rs.getString(4)+" " + rs.getString(5) );
                }
                
            }
            conexionOracle.cerrar();
        
    }catch (SQLException ex){
            Logger.getLogger(sql.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
}
