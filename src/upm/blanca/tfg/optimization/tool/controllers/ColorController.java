package upm.blanca.tfg.optimization.tool.controllers;


import java.awt.*;
import javax.swing.*;

public class ColorController {

    private int x;

//Constructor
    public ColorController()    {
        x = 0;
    }

    public void cambiarColor(JPanel panelRecibido) {
        
        if (panelRecibido.getBackground() == Color.YELLOW)
        	panelRecibido.setBackground( Color.PINK );
        else
        	panelRecibido.setBackground( Color.YELLOW );      
    }    
}