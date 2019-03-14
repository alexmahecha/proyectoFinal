/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplotanque;

import javax.swing.JFrame;

public class EjemploTanque extends JFrame {
    
    public EjemploTanque() {
        initUI();
    }

    private void initUI() {
        add(new Tablero());
        setSize(800, 580);
        setTitle("Ejercicio 1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }    
    
    public static void main(String[] args) {
        EjemploTanque ex = new EjemploTanque();
        ex.setVisible(true);
       
    }
}