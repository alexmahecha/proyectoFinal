
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parejas;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Timer;
import javax.swing.JOptionPane;

/**
 *
 * @author Home
 */
public  class Juego implements ActionListener{
    
    JFrame frame;
    JPanel panel;
    JLabel numero;
    
    Cartas matrizBotones [][]= new Cartas[3][4];
    int matrizNumeros[][] = new int [3][4];
    int cont = 0;
    int[] vectorNumeros = new int[2];
    Cartas[] vectorBotones = new Cartas[2];
    Timer tiempo;
    int contSegundos;
    int gana = 0;
    Random numGana; 


    ImageIcon icon;
    ImageIcon cara;

    Random numRandom;
   
    public Juego() {
        numRandom = new Random();
        icon = new ImageIcon("fondo.png");
        cara = new ImageIcon("cara.png");
        

        
        frame = new JFrame("Encontrar Parejas");
        frame.setSize(430, 600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        
        panel = new JPanel();
        panel.setSize(430,600 );
        panel.setLayout(null);
        panel.setVisible(true);
        
     
        
        InicializarMatriz(matrizNumeros,-1);
        LlenarMatrizRandom(matrizNumeros);

        addBotones();
        addCartas();
        logicaJuego();
    
         frame.add(panel);
         
  
        
         
                   
}
    
        public void InicializarMatriz(int matriz[][], int a){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    matriz [i][j]= a;
                }
            }
        }
    
        public void LlenarMatrizRandom(int matriz[][]){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    matriz[i][j] = numRandom.nextInt(6);
                    int cont;
                    do{
                      cont = 0;
                    if(Comparar(matriz[i][j], matriz) == 3){
                        matriz[i][j]= numRandom.nextInt(6);
                        cont = Comparar(matriz[i][j], matriz);
                    }
               
                 }while(cont == 3);
               }
            }
            
         
        }
        
        public int Comparar(int numero, int matriz[][]){
            int cont = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    if(numero == matriz[i][j]){
                        cont = cont + 1;
                    }
                }
            }
            return cont;
        }

 
   
       public void addCartas(){

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    Cartas carta = new Cartas();
                    carta.setIcon(new ImageIcon(matrizNumeros[i][j] + ".png"));
                    carta.setBounds(110 + (i*70), 40 + (j*120), 70, 120);
                   carta.setVisible(true);
                   panel.add(carta);
                 
               }
           }
        
        }
        
    
   
        public void addBotones(){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    matrizBotones[i][j] = new Cartas (new ImageIcon("cara.png"));
                    matrizBotones[i][j].setBounds(110+(i*70),40+(j*120), 70, 120);
                    matrizBotones[i][j].setVisible(true);
                    matrizBotones[i][j].addActionListener(this);
                    matrizBotones[i][j].setI(i);
                    matrizBotones[i][j].setJ(j);
                    panel.add(matrizBotones[i][j]);
                    
                }
            }
        
        }
        
        
   
    
 

    
    

    
    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
    
    public void logicaJuego() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                matrizBotones[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {

                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 4; l++) {
                                if (e.getSource() == matrizBotones[k][l]) {
                                   //System.out.println(k + " " + l);
                                   if( matrizBotones[k][l].isEnabled()){
                                      matrizBotones[k][l].setVisible(false);
                                      vectorNumeros[cont] = matrizNumeros[k][l];
                                      vectorBotones[cont] = matrizBotones[k][l];
                                      cont++;
                                      
                                      if(cont == 2){

                
                                         if(vectorNumeros[0] != vectorNumeros[1]){
                                             matrizBotones[vectorBotones[0].getI()][vectorBotones[0].getJ()].setEnabled(true);
                                             matrizBotones[vectorBotones[0].getI()][vectorBotones[0].getJ()].setVisible(true);
                                             
                                             matrizBotones[vectorBotones[1].getI()][vectorBotones[1].getJ()].setEnabled(true);
                                             matrizBotones[vectorBotones[1].getI()][vectorBotones[1].getJ()].setVisible(true);

                                         }else {gana++;};
                                          cont = 0;
                                          //System.out.println(gana);
                                          if(gana == 6){
                                            //  System.out.println("gano");
                                            JOptionPane.showMessageDialog(null,"numero secreto: 8");
                                            frame.dispose();
                                          }
                                      }
                                   }
                                }
                            }
                        }
                    }

                });
            }
        }
    }

   
    
    public void Tiempo() {
        contSegundos = 0;
        tiempo = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                contSegundos++;
                
            }

        }
        );
        tiempo.start();
        tiempo.stop();
        contSegundos = 0;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
    
  

    

    
}


    


    


 