package tp;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import tp.*;

public class Gestor
{
    private Hashtable<Integer, Palabra> hashTable;    
    
    public Gestor()
    {
        hashTable = new Hashtable<Integer, Palabra>(); //Atributo del gestor
    }
    
    public void cargarPalabras(File archivo) 
    {            
        if (!archivo.getName().endsWith(".txt")) 
        { //Revisamos que el archivo sea .txt
            return;
        }
        try 
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "8859_1")); //Genera el lector del archivo .txt
            String[] palabras = null;

            String delimitadores = "[ _.,;:?!¡¿\\'\\\"\\\\[\\[]\\]()~#*/-]+";
            String linea = in.readLine(); //Lee una linea de texto del archivo
            
            
            while (linea != null) //Un ciclo para "linea" no nulo
            {
                palabras = linea.split(delimitadores); //Dividimos las palabras de "linea" por los delimitadores, y lo almacenamos en "palabras"
                for (String palabra : palabras) //Ciclo que recorre todas las palabras obtenidas anteriormente
                {
                    if (validarPalabra(palabra))
                    { 
                        palabra = palabra.toLowerCase(); //Convertir la palabra a letra minúscula
                        // System.out.println(palabra + " - ");  // PARA PROBAR
                       
                        
                        //Verificamos que la tabla no esté vacía, en caso de no estarlo buscamos si la palabra a ser ingresada existe o no
                        //Si la palabra existe sumamos el contador
                        if (!hashTable.isEmpty() && hashTable.containsKey(palabra.hashCode())) {
                            hashTable.get(Math.abs(palabra.hashCode())).sumarContador();
                        }
                        //De estar vacía la tabla o de no encontrarse la palabra simplemente se inserta una nueva palabra
                        else {
                            hashTable.put(Math.abs(palabra.hashCode()), new Palabra(palabra));
                        }
                    }
                }
                linea = in.readLine();
            }         
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    //Comprueba que la palabra no contenga ninguno de estos caracteres: @(arroba),0,1,2,3,4,5,6,7,8,9
    private boolean validarPalabra(String palabra)
    {
        String[] caracterNoValido = {"@","0","1","2","3","4","5","6","7","8","9"};
        for(String caracter : caracterNoValido)
        {
            if(palabra.contains(caracter))
                return false;
        }
        return true;
    }  
}
