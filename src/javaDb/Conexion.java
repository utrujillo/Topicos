/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaDb;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author codehero
 */
public class Conexion {
    private static Connection conexion;
    private static final String DRIVER ="com.mysql.jdbc.Driver";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "12345678";
    private static final String DB = "punto_venta_tec";
    private static final String PATHDB = "jdbc:mysql://localhost:3306/"+ DB;
    private Statement stmt = null;
    
    public Conexion(){
        conexion = null;
        try {
            Class.forName(DRIVER);
            conexion = (Connection) DriverManager.getConnection(PATHDB,USUARIO,PASSWORD);
            if(conexion != null){
                System.out.println("Conexion establecida...");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Hay un error al conectar la base de datos: "+ ex.getMessage());
        }
    }
    
    public Connection getConnection(){
        return conexion;
    }
    
    public void closeConnection(){
        conexion = null;
        if (conexion == null) {
            System.out.println("Conexion terminada...");
        }
    }
    
    /*
        Metodo utilizado para ejecutar (INSERT, UPDATE, DELETE)
        Queries que no retornan un ResultSet
    */
    public void execQuery(String query, String action){
        try {
            Connection conn = this.getConnection();
            stmt = conn.createStatement();
        
            if( stmt.executeUpdate(query) == 1){
                System.out.println("Elemento "+ action +"...");
            }else{
                System.out.println("Elemento no "+ action);
            }
        } catch (SQLException e) {
            System.out.println(action +" dato error: "+ e.getMessage());
        }
    }
    
    /*
        Metodo utilizado para ejecutar (SELECT)
        Queries que retornan un ResultSet
    */
    public ResultSet resultQuery(String query){
        ResultSet results = null;
        try {
            Connection conn = this.getConnection();
            stmt = conn.createStatement();
            
            results = stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Error en la consulta... \n"+ query +" \n"+ e.getMessage());
        }
        return results;
    }
}
