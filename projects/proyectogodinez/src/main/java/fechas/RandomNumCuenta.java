/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fechas;

import java.util.Random;
import java.util.UUID;

/**
 *
 * @author georg
 */
public class RandomNumCuenta {
    public  String getNumCuentaRandom() {
        String abc= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char letra;
        String RandonNumCuenta="";
        int rdABC = (int)(Math.random()*(26-1+1)+1); 
        
        int rd = (int)(Math.random()*(100000-1+1)+1); 
        letra= abc.charAt(rdABC);
        
        RandonNumCuenta= String.valueOf(letra)+ String.valueOf(rd);  
        
         
//        System.out.println("rd. "+RandonNumCuenta);
        return RandonNumCuenta;
        
    }
}
