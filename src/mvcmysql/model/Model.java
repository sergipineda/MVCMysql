/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mvcmysql.model;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author profe
 */
public class Model {
    
    private static Connection connexio=null;  
    private static Statement sentencia=null;
    private static ResultSet resultSet = null;
        
    private String url;
    private String usuari;
    private String password;

    public Model(String url, String usuari, String password) {
       
        this.url = url;
        this.usuari = usuari;
        this.password = password;
        crearConnexio(url, usuari, password);
    }

    public Model(String conexio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void finalize() throws Throwable {
        if(sentencia!=null) sentencia.close();
        if(resultSet!=null) resultSet.close();
        if(connexio!=null) connexio.close();
        System.out.println("Tancant la connexió a la BD...");
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }


    private void crearConnexio(String url, String usuari, String password){
        
        try {
            
                connexio = DriverManager.getConnection(
                        url, 
                        usuari, 
                        password);
                sentencia = connexio.createStatement();
                System.out.println("Connectant a la BD...");
        } catch (SQLException ex) {
            System.err.println("No s'ha pogut establir la connexió a la BD...");
        }

    }
    
    public void insertarActor(String first_name, String last_name){
            
        String sql = "INSERT INTO City (Name, District) VALUES ("+"'"+first_name+"','"+last_name+"');";
        try {
            sentencia.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println("Error a l'insertar l'actor!!");
        }  
    
    }
    
    public void borrarActor(int actor_id){
            
        String sql = "DELETE FROM City WHERE ID="+actor_id+";";
        try {
            sentencia.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println("Error al borrar l'actor!!");
        }  
    
    }

    public void modificarActor(int actor_id, String first_name, String last_name){
            
        String sql = "UPDATE City SET Name='"+first_name+"',District='"+last_name+"' "+
                "WHERE ID="+actor_id+";";
        try {
            sentencia.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println("Error al modificar l'actor!!");
        }  
    
    }
    
    public ArrayList<TaulaActors> llistarActors(){
            
        ArrayList llista=new ArrayList();
        String sql = "SELECT ID, Name, District FROM City LIMIT 0 , 30;";
        try {
            this.resultSet=sentencia.executeQuery(sql);
            
            if(this.resultSet!=null){
            
                while(resultSet.next()){
                    int actor_id=resultSet.getInt(1);
                    String first_name=resultSet.getString(2);
                    String last_name=resultSet.getString(3);
                    llista.add(new TaulaActors(actor_id, first_name, last_name));                
                }
            
            
            }
        } catch (SQLException ex) {
            System.err.println("Error al llistar els actors!!");
        }  
        return llista;    
    }
    
    
    
}
