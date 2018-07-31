/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Clases.Controles;

/**
 *
 * @author Sociograph
 */
public class Controlador {
    
    private Controles controles;
    
    public Controlador(){
        
    }
    
    public Controlador(Controles controles){
        this.controles = controles;
    }
    
    public long getTime(){
       return controles.getTime();
    }
    
    
    
    
}
