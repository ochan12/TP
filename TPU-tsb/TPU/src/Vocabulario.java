
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author a2
 */
public class Vocabulario {
    private HashTable <Palabra> vocabulario;
    
    public Vocabulario()
    {
        vocabulario= new HashTable <>();
    }
    public Vocabulario(Palabra p)
    {
        vocabulario= new HashTable <>();
        vocabulario.put(p);
    }
    
    public void agregarPalabra (String p, String doc)
    {
        Palabra pal= new Palabra (p,doc);
        
        if(vocabulario.contains(pal))
        {
            Palabra pal2=(Palabra)vocabulario.get(pal);
            pal2.sumarUno();
            pal2.agregarDocumento(doc);
        }
        else
        {
            vocabulario.put(pal);            
        }
    }
    @Override
    public String toString()
    {
        return vocabulario.toString();
    }
}
