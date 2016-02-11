/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mvcmysql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import static mvcmysql.MVCMySQL.hola;
import static mvcmysql.MVCMySQL.nom;
import mvcmysql.view.VistaActors;
import mvcmysql.model.Model;
import mvcmysql.controller.Controlador;

/**
 *
 * @author profe
 */
public class MVCMySQL  {
    


    static String nom;
    static String pass;
    static String url;
    
    static String hola[];
    static Controlador controlador;
    
    static VistaActors vista=new VistaActors();
    
   
    

   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        
       
        
        // TODO code application logic here
          Model modelo=new Model ("");
        controlador=new Controlador(modelo, vista);
        
    }
   

    
    @SuppressWarnings("empty-statement")
     public static String conexio() throws SQLException {
   try {
   

   Properties propiedades = new Properties();
    
   
   propiedades
     .load(new FileInputStream(
       "mysql.properties"));
 
  
    nom = propiedades.getProperty("usuari");
    pass = propiedades.getProperty("pass");
    url = propiedades.getProperty("url");
  Connection conexio=null;
    conexio= DriverManager.getConnection(url,nom,pass);
       
    
  } catch (FileNotFoundException e) {
   System.out.println("Error, El archivo no exite");
  } catch (IOException e) {
   System.out.println("Error, No se puede leer el archivo");
  }
        return "";
        
     }
      public static Connection GetConnection()
    {
        Connection conexion=null;
     
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String servidor = "jdbc:mysql://localhost/DBVentas";
            String usuarioDB="root";
            String passwordDB="030191";
            conexion= DriverManager.getConnection(servidor,usuarioDB,passwordDB);
        }
        catch(ClassNotFoundException ex)
        {
            
            conexion=null;
        }
        catch(SQLException ex)
        {
            conexion=null;
        }
        catch(Exception ex)
        {
            conexion=null;
        }
        finally
        {
            return conexion;
        }
    }
}
