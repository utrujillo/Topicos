/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaDb;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private PreparedStatement pstmt = null;
    
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
    
    public void execQuery(String query, String action, ArrayList<Object> values){
        try {
            // Preparando la consulta
            Connection conn = this.getConnection();
            pstmt = conn.prepareStatement(query);
            
            // Setteando los valores en la consulta preparada
            for (int i = 0; i < values.size(); i++) {
                try {
                    switch( values.get(i).getClass().getName() ){
                        case "java.lang.String":    
                            System.out.println("Posicion: "+ i +" valor: "+ values.get(i));
                            pstmt.setString( i + 1, (String) values.get(i)); break;
                        case "java.lang.Integer":
                            pstmt.setInt(i, (Integer) values.get(i)); break;
                        case "java.lang.Float":
                            pstmt.setFloat( i + 1, (Float) values.get(i)); break;
                        case "java.lang.Boolean":
                            pstmt.setBoolean( i + 1, (Boolean) values.get(i)); break;
                        case "java.lang.Double":
                            pstmt.setDouble( i + 1, (Double) values.get(i)); break;
                    }
                } catch (SQLException ex) {
                    System.out.println(action +" dato error al settear variable: "+ ex.getMessage());
                }
            }
            
            // Ejecutando la consulta
            if( pstmt.executeUpdate() == 1){
                System.out.println("Elemento "+ action +"...");
            }else{
                System.out.println("Elemento no "+ action);
            }
            
        } catch (SQLException e) {
            System.out.println(action +" dato error: "+ e.getMessage());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
