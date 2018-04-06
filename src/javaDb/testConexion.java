/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaDb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author codehero
 */
public class testConexion {
    public Scanner sc = new Scanner(System.in);
    public Conexion conexion = new Conexion();
    
    public static void main(String[] args){
        new testConexion().menu();
    }
    
    public void menu(){
        Integer opcion;
        System.out.println(" ==== Punto de Venta MySQL - Tabla User ===");
        System.out.println("1.- Leer datos");
        System.out.println("2.- Insertar datos");
        System.out.println("3.- Eliminar datos");
        System.out.println("4.- Actualizar datos datos");
        System.out.println("5.- Buscar nombre");
        System.out.println("6.- Salir");
        System.out.print("Opcion seleccionada: ");
        
        try {
            opcion = sc.nextInt();
            switch( opcion ){
                case 1: readTableUser(); break;
                case 2: insertTableUser(); break;
                case 3: deleteTableUser(); break;
                case 4: updateTableUser(); break;
                case 5: searchTableUser(); break;
            }
        } catch (Exception e) {
            System.out.println("Error al seleccionar una opcion: "+ e.getMessage());
        }
    }
  
    public void readTableUser(){
        String query = "SELECT * FROM user";
        try {
            ResultSet results = conexion.resultQuery(query);
            while(results.next()){
                System.out.println(
                                    "ID:"+ results.getInt("id")
                                    +" Nombre: "+ results.getString("nombre") +" "+ results.getString("apellidos")
                                    +" Tipo: "+ results.getString("type_user")
                                  );
            }
        } catch (SQLException e) {
            System.out.println("Hay un error al leer la tabla: "+ e.getMessage());
        }
    }
    
    public void insertTableUser(){
        String query = "INSERT INTO user "
                        + "(nombre, apellidos, type_user) "
                        + "VALUES('Simba','El Rey Leon','Usuario')";
        conexion.execQuery(query, "Insertado");
    }
    
    public void deleteTableUser(){
        String query = "DELETE FROM user WHERE id = 2";
        conexion.execQuery(query, "Eliminado");
    }
    
    public void updateTableUser(){
        String query = "UPDATE user SET type_user = 'Usuario' WHERE id = 2";
        conexion.execQuery(query, "Actualizado");
    }
    
    public void searchTableUser(){
        System.out.print("Nombre a buscar: ");
        sc.nextLine();
        String nombreSrc = sc.nextLine();
        
        String query = "SELECT * FROM user WHERE concat(nombre,' ',apellidos) LIKE '%"+ nombreSrc +"%'";
        try {
            ResultSet results = conexion.resultQuery(query);
            while(results.next()){
                System.out.println(
                                    "ID:"+ results.getInt("id")
                                    +" Nombre: "+ results.getString("nombre") +" "+ results.getString("apellidos")
                                    +" Tipo: "+ results.getString("type_user")
                                  );
            }
        } catch (SQLException e) {
            System.out.println("Hay un error al leer la tabla: "+ e.getMessage());
        }
    }
    
}
