
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author a2
 */
public class Main {

    public static void main(String[] args) {
        // Buscar NULK INSERT para hacer varios insert juntos
        // autocommit desactivado
        Vocabulario v= new Vocabulario ();
         try
    {
        
        File f = new File("22975-8.txt");
        Scanner sc = new Scanner(f);
        String a = "";
        
        while(sc.hasNextLine())
        {
            String linea= sc.nextLine();
            
            for(Character car: linea.toCharArray())
            {
                if(Character.isAlphabetic(car))
                {
                    a+=car;
                }
                else
                {
                    v.agregarPalabra(a, "hola");
                    a="";
                }     
            }
        }
    }catch(IOException e){};
        
        System.out.println(v);
    }
    
}
