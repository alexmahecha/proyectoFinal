/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplotanque;
import parejas.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import modelo.Archivo;
import modelo.MyArchivo;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import java.awt.image.ImageObserver;


/**
 *
 * @author Estudiante
 * Jpanel dado que voy a pintar en el Canvas
 * ActionListener: Para poder ejecutar el escenario cada ciertos milisegundos
 */
public class Tablero extends JPanel implements ActionListener{
    private Timer timer ;
    Archivo archivo;
    private int x;
    private int[][] mapa;
    private Image arbol = loadImage("tileArbol.png");
    private Image roca = loadImage("tileRoca.png");
    private Image pasto = loadImage("tilePasto.png");
    private Image caja = loadImage("tileCaja.png");
    private Image entrada = loadImage("tileEntrada.png");
    private Image persona = loadImage("tilePersona.png");

    ImageObserver myObserver ;

    
    public Tablero(){
        //Lanza un evento de tipo ActionListener cada 25 Milisegundo
        //Se hace referencia a this porque la misma clase (Tablero) procesa el evento
        
        //Cargando el mapa
        archivo = new MyArchivo();
        mapa = archivo.cargar("b.txt");
        
        //Registrar evento del Teclado
         addKeyListener(new Eventos());
        setFocusable(true); //Debe estar en modo Focus para que puede detectar el evento

    }

    //Metodo donde se pintan los objetos 
     @Override
    public void paintComponent(Graphics g){
       super.paintComponent(g);
       
        for (int i = 0; i < 9; i++) {
          for (int j = 0; j < 13; j++) {
              if(mapa[i][j] == 0){
               
                g.drawImage(pasto, j*60, i*60, myObserver);

              }else if(mapa[i][j] == 1){
              
                 g.drawImage(arbol, j*60, i*60, myObserver);
              }else if(mapa[i][j] == 2){
             
               g.drawImage(roca, j*60, i*60, myObserver);

              }else if(mapa[i][j] == 3){
               
               g.drawImage(entrada, j*60, i*60, myObserver);

              }else if(mapa[i][j] == 4){
               
               g.drawImage(caja, j*60, i*60, myObserver);

              } else if (mapa[i][j] == 7) {
                    
                    g.drawImage(persona, j * 60, i * 60, myObserver);
                   
                }
              
          }   
            
        }
    }

    //Metodo que se ejecuta cada vez que se lanza un ActionListener
    public void actionPerformed(ActionEvent e) {
      //  System.out.println("Repaint");
        repaint();
    }
    
       public Image loadImage(String imageName) {
             ImageIcon ii = new ImageIcon(imageName);
             Image image = ii.getImage();
             return image;
         }
       
       public void moverAbajo(){
           boolean movio=true;
        for (int i = 0; i < mapa.length-1; i++) {
          for (int j = 0; j < mapa[i].length; j++) {
                if(mapa[i][j]== 7&&movio){
                    if(mapa[i+1][j] == 0){
                        mapa[i][j]=0;
                        mapa[i+1][j]=7;
                        movio=false;
                         repaint();            
                        
                    }
                    if(mapa[i+1][j] == 0){
                        
                    }
                }
          } 
        }
       }
       
    public void moverArriba(){
        for (int i = 0; i < mapa.length; i++) {
          for (int j = 0; j < mapa[i].length; j++) {
                if(mapa[i][j]== 7){
                    if(mapa[i-1][j] == 0){
                        mapa[i][j]=0;
                        mapa[i-1][j]=7;
                         repaint();            
                        
                    }
                    if(mapa[i-1][j] == 3){
                        mapa[i-1][j] = 0;
                        Juego juego = new Juego();
                        
                    }
                }
          } 
        }
       }
    
        public void moverDerecha(){
            boolean movio=true;
        for (int i = 0; i < mapa.length; i++) {
          for (int j = 0; j < mapa[i].length-1; j++) {
                if(mapa[i][j]== 7&&movio){
                    if(mapa[i][j+1] == 0){
                        mapa[i][j]=0;
                        mapa[i][j+1]=7;
                        movio=false;
                         repaint();            
                    }
                    if(mapa[i][j+1] == 3){
                        mapa[i][j+1] = 0;
                        Picas xxx = new Picas();
                        
                    }
                }
          } 
        }
       }
        
        public void moverIzquierda(){
        for (int i = 0; i < mapa.length; i++) {
          for (int j = 0; j < mapa[i].length; j++) {
                if(mapa[i][j]== 7){
                    if(mapa[i][j-1] == 0){
                        mapa[i][j]=0;
                        mapa[i][j-1]=7;
                         repaint();            
                        
                    }
                }
          } 
        }
       }
       
       public void ip(){
                 for (int i = 0; i < mapa.length; i++) {
          for (int j = 0; j < mapa[i].length; j++) {
                     
                     System.out.print(mapa[i][j]);   
                    
                
          }System.out.println(" "); 
        }
       }

 

       private class Eventos extends KeyAdapter{
       
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

       
        
        int key = e.getKeyCode();
            if(key == KeyEvent.VK_RIGHT){
                moverDerecha();
            }
            
            if(key == KeyEvent.VK_LEFT){

            moverIzquierda();
            
             
            }
            if(key == KeyEvent.VK_DOWN){
             moverAbajo();
            
            }
            if(key == KeyEvent.VK_UP){
             moverArriba();
            }
            

       
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
       
      
    
       
       
    }}


