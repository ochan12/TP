
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
public class Gestor {

    public void leerDoc()
    {
        try
    {
        Vocabulario v= new Vocabulario ();
        File f = new File("22975-8.txt");
        Scanner sc = new Scanner(f);
        ArrayList <Character> a= new ArrayList <>();
        
        while(sc.hasNextLine())
        {
            String linea= sc.nextLine();
            
            for(Character car: linea.toCharArray())
            {
                if(Character.isAlphabetic(car))
                {
                    a.add(car);
                }
                else
                {
                    String s= a.toArray().toString();
                    v.agregarPalabra(s, "");
                    a.clear();
                }     
            }
        }
    }catch(IOException e){};
        
    }
    
    
}
