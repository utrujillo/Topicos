/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topicos;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author codehero
 */
public class Persona {
    
    private Integer idPersona;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String correo;
    
    public Scanner sc = new Scanner(System.in);
    private ArrayList<Persona> personaAL = new ArrayList<>();

    public ArrayList<Persona> getPersonaAL() {
        return personaAL;
    }

    public void setPersonaAL(ArrayList<Persona> personaAL) {
        this.personaAL = personaAL;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public void muestraMenu(){
        Integer opcion;
        System.out.println("========================");
        System.out.println( "Operaciones Básicas" );
        System.out.println("1.- Agrega Persona");
        System.out.println("2.- Listado de Personas");
        System.out.println("3.- Elimina Personas");
        
        try {
            opcion = sc.nextInt();
            switch( opcion ){
                case 1:
                    agregaPersona();
                    break;
                case 2:
                    listaPersonas();
                    break;
                case 3:
                    System.out.println("Ingresa el ID a eliminar: ");
                    Integer idDelete = sc.nextInt();
                    boolean result = eliminaPersona( idDelete );
                    if (result) {
                        System.out.println("Persona Eliminada");
                    }else{
                        System.out.println("ID no encontrado");
                    }
                    this.muestraMenu();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Existe un error: "+ e.getMessage() );
        }
    }
    
    public void agregaPersona(){
        try {
            System.out.println("Ingresa los datos de la Persona");
            
            System.out.print("ID: ");
            Integer id = sc.nextInt();
            
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            sc.nextLine();
            
            System.out.print("Apellidos: ");
            String apellidos = sc.nextLine();
            
            System.out.print("Telefono: ");
            String telefono = sc.nextLine();
            
            System.out.print("Correo: ");
            String correo = sc.nextLine();
            
            //System.out.println(id+" "+nombre+" "+apellidos+" "+telefono+" "+correo);
            
            Persona person = new Persona();
            person.setIdPersona(id);
            person.setNombre(nombre);
            person.setApellidos(apellidos);
            person.setTelefono(telefono);
            person.setCorreo(correo);
            
            personaAL.add(person);
            
            System.out.println("\nLa persona fue agregada satisfactoriamente\n\n");
            this.muestraMenu();
            
        } catch (Exception e) {
            System.out.println("Error: "+ e.getMessage());
        }
    }
    
    public void agregaPersona(Integer id, String nombre, String apellidos, String telefono, String correo, JTable jtable_person){
        Persona person = new Persona();
        person.setIdPersona(id);
        person.setNombre(nombre);
        person.setApellidos(apellidos);
        person.setTelefono(telefono);
        person.setCorreo(correo);

        personaAL.add(person);
        mostrarJTable(jtable_person);
        //listaPersonas();
    }
    
    public void mostrarJTable(JTable jtable_person){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Telefono");
        model.addColumn("Correo");

        for (Persona persona : personaAL) {
            model.addRow( new Object[]{
                persona.getIdPersona(),
                persona.getApellidos()+" "+persona.getNombre(),
                persona.getTelefono(),
                persona.getCorreo()
            } );
        }

        jtable_person.setModel(model);
        if( personaAL.size() > 0 ){
            jtable_person.addRowSelectionInterval(0, 0);
        }
    }
    
    public void listaPersonas(){
        System.out.println("====== Listado de Personas ======");
        for (Persona persona : personaAL) {
            String personaInfo;
            personaInfo = "ID: "+ persona.getIdPersona()
                          +" Nombre: "+ persona.getNombre()
                          +" Apellidos: "+ persona.getApellidos()
                          +" Telefono: "+ persona.getTelefono()
                          +" Correo: "+ persona.getCorreo();
            System.out.println( personaInfo );
        }
        
        System.out.println("\n\n");
        this.muestraMenu();
    }
    
    public boolean eliminaPersona( Integer idDelete ){
        boolean personDeleted = false;
        
        for (int i = 0; i < personaAL.size(); i++) {
            if( personaAL.get(i).getIdPersona().equals(idDelete) ){
                personaAL.remove(i);
                personDeleted = true;
            }
        }
        
        return personDeleted;
    }
    
    public boolean eliminaPersona( Integer idDelete, JTable jtable_person ){
        boolean personDeleted = false;
        
        for (int i = 0; i < personaAL.size(); i++) {
            if( personaAL.get(i).getIdPersona().equals(idDelete) ){
                personaAL.remove(i);
                personDeleted = true;
            }
        }
        
        mostrarJTable(jtable_person);
        
        return personDeleted;
    }
    
}
