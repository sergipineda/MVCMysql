/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mvcmysql;

import static com.sun.org.apache.bcel.internal.util.SecuritySupport.getResourceAsStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import mvcmysql.view.VistaActors;
import mvcmysql.model.Model;
import mvcmysql.controller.Controlador;

/**
 *
 * @author profe
 */
public class MVCMySQL  {
//    static {
//    loadProperties();
//}

        
    
    static Controlador controlador;
    
    static VistaActors vista=new VistaActors();
    
    static Model modelo=new Model ("jdbc:mysql://localhost:3306/world","root","alumne");
    

   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        
        // TODO code application logic here
        
        controlador=new Controlador(modelo, vista);
        
    }
   
//    static Properties prop;
//   private static void loadProperties() {
//    prop = new Properties();
//    InputStream in = MVCMySQL.class
//            .getResourceAsStream("mysql.properties");
//            
//    try {
//        prop.load(in);
//        in.close();
//    } catch (IOException e) {
//        e.printStackTrace();
//    }}
    public static void propiedades() {
    Properties properties = new Properties();

		try {
			properties.load(new FileInputStream(
					"mysql.properties"));
			
			
			// Display all the values in the form of key value
			for (String key : properties.stringPropertyNames()) {
				String value = properties.getProperty(key);
				System.out.println("Key:- " + key + "Value:- " + value);
			}

		} catch (IOException e) {
			System.out.println("Exception Occurred" + e.getMessage());
		}

   
}}
