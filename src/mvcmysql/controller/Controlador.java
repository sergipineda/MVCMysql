/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcmysql.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mvcmysql.model.Model;
import mvcmysql.view.VistaActors;

/**
 *
 * @author profe
 */
public class Controlador {

    private Model odb;//=new Model("jdbc:mysql://localhost:3306/sakila", "root", "mp8");
    private VistaActors vista;
    private ActionListener actionListener;

    public Controlador(Model odb, VistaActors jf) {
        this.odb = odb;
        this.vista = jf;
        vista.carregaTaula(odb.llistarActors());
        control();
    }

    public void control() {
        actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getActionCommand().equals(VistaActors.borrar)) {
                    if (vista.filasel!=-1){
                            odb.borrarActor(vista.id);
                            vista.borrarCamps();
                            vista.carregaTaula(odb.llistarActors());
                    }
                    else JOptionPane.showMessageDialog(null, "Per borrar un actor primer l'has de seleccionar!!", "Error", JOptionPane.ERROR_MESSAGE);                
                } 
                else 
                    if (actionEvent.getActionCommand().equals(VistaActors.insertar)) {
                        if (!vista.nom.equals("") || !vista.cognom.equals("")) {
                            odb.insertarActor(vista.nom, vista.cognom);
                            vista.borrarCamps();
                            vista.carregaTaula(odb.llistarActors());
                        }
                        else JOptionPane.showMessageDialog(null, "No pots introduir un actor sense nom ni cognom!!", "Error", JOptionPane.ERROR_MESSAGE);
                    } 
                    else
                        if (actionEvent.getActionCommand().equals(VistaActors.modificar)) {
                            if (vista.filasel!=-1 && (!vista.nom.equals("") || !vista.cognom.equals(""))){
                                odb.modificarActor(vista.id,vista.nom, vista.cognom);
                                vista.borrarCamps();
                                vista.carregaTaula(odb.llistarActors());
                            }
                            else JOptionPane.showMessageDialog(null, "Per modificar un actor primer l'has de seleccionar i posar algun valor al nom i/o cognoms!!", "Error", JOptionPane.ERROR_MESSAGE);                
                        }
                        else {
                            try {
                                    odb.finalize();
                            } catch (Throwable ex) {
                                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            vista.sortir();
                        }
            }
        };
        vista.passarBotoBorrar().addActionListener(actionListener);
        vista.passarBotoInsertar().addActionListener(actionListener);
        vista.passarBotoSortir().addActionListener(actionListener);
        vista.passarBotoModificar().addActionListener(actionListener);
    }

}
