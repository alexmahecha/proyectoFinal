/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Estudiante
 */
public class MyArchivo implements Archivo{

    @Override
    public int[][] cargar(String ruta) {
        int fila = 0;
        int matriz[][] = new int[13][13];
        File archivo = new File(ruta);
        if(archivo.exists()){
            try {
                Scanner lectura = new Scanner(archivo);
                while(lectura.hasNext()){
                  for (int i = 0; i <13; i++) {
                    matriz[fila][i] = lectura.nextInt();
                  }
                  fila++;
                }
            } catch (FileNotFoundException ex) {
              return null;  
            }
            
            return matriz;
        }else
             return null;
    }
    
    public static void main(String[] args) {
        MyArchivo archivo = new MyArchivo();
        int [][] matriz = archivo.cargar("b.txt");
        for (int i = 0; i < matriz.length; i++) {
          for (int j = 0; j < matriz[i].length; j++) {
              System.out.print(matriz[i][j]);
          }   
            System.out.println("");
        }
    }
}
