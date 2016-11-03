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
    private ArrayList<Libro> librosCargados;

    //Constructor
    public Gestor() {
        hashTable = new Hashtable<Integer, Palabra>();
        librosCargados = new ArrayList<Libro>();
    }

    public void cargarPalabras(ArrayList<File> listaArchivo) {
        for (File archivo : listaArchivo) {
            /*if (!archivo.getName().endsWith(".txt")) //Revisamos que el archivo sea .txt
            {
                return;
            }*/
            try {
                BufferedReader lector = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "8859_1")); //Genera el lector del archivo .txt con codificacion 
                
                               
                for (Libro libro : librosCargados) {
                    if (libro.compareTo(this.tomarDatosLibros(lector)) == 0) {
                        return;
                    }
                }
                Libro nuevoLibro = this.tomarDatosLibros(lector); 
                librosCargados.add(nuevoLibro);
                this.cargarPalabrasEnHash(lector);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("size: "+ hashTable.size());
        for (int i = 0; i < "zzzzzzzzzzzz".hashCode(); i++) {
            if(hashTable.get(i)!=null)
            System.out.println(hashTable.get(i).getContenido() + " " + hashTable.get(i).getContador());
        }
    }
    
    //Comprueba que la palabra no contenga ninguno de estos caracteres: @(arroba),0,1,2,3,4,5,6,7,8,9
    private boolean validarPalabra(String palabra)
    {
        String[] caracterNoValido = {"@","0","1","2","3","4","5","6","7","8","9","="};
        for(String caracter : caracterNoValido)
        {
            if(palabra.contains(caracter))
                return false;
        }
        return true;
    }  
    
    private Libro tomarDatosLibros(BufferedReader lector)
    {
        
        StringBuffer titulo = new StringBuffer();
        StringBuffer autor = new StringBuffer();
        StringBuffer fecha = new StringBuffer();
        StringBuffer lenguaje = new StringBuffer();
        String[] palabras;
        
        try
        {
            String linea = lector.readLine();
            
            while (!linea.contains(titulo.toString()))
            {
                if (linea.contains("Title:")) {
                    palabras = linea.split(" ");

                    for (String palabra : palabras) {
                        if (!palabra.contains(":")) {
                            titulo.append(palabra.toLowerCase() + " ");
                        }
                    }
                } 
                
                else if (linea.contains("Author:")) {
                    palabras = linea.split(" ");

                    for (String palabra : palabras) {
                        if (!palabra.contains(":")) {
                            autor.append(palabra.toLowerCase() + " ");
                        }
                    }
                } 
                
                else if (linea.contains("Language:")) {
                    palabras = linea.split(" ");

                    for (String palabra : palabras) {
                        if (!palabra.contains(":")) {
                            lenguaje.append(palabra.toLowerCase() + " ");
                        }
                    }
                }
                
                else if (linea.contains("Release Date:")) {
                    palabras = linea.split(" ");

                    for (String palabra : palabras) {
                        if (!palabra.contains(":") && !palabra.contains("[") && !palabra.contains("]") && !palabra.contains("Release") ) {
                            fecha.append(palabra.toLowerCase() + " ");
                        }
                    }
                }
                linea = lector.readLine();
            }
        }      
        
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        return new Libro(autor.toString(),titulo.toString(),fecha.toString(), lenguaje.toString());
    }

    private void cargarPalabrasEnHash(BufferedReader lector) {

        try {
            String[] palabras = null;
            String delimitadores = "[ _.,;:?!¡«»¿\\'\\\"\\\\[\\[]\\]()~#*/-]+";

            String linea = lector.readLine(); //Lee una linea de texto del archivo

            while (linea != null) //Un ciclo para "linea" no nulo
            {
                palabras = linea.split(delimitadores); //Dividimos las palabras de "linea" por los delimitadores, y lo almacenamos en "palabras"
                for (String palabra : palabras) //Ciclo que recorre todas las palabras obtenidas anteriormente
                {
                    if (validarPalabra(palabra)) {
                        palabra = palabra.toLowerCase(); //Convertir la palabra a letra minúscula
                        // System.out.println(palabra + " - ");  // PARA PROBAR

                        //Verificamos que la tabla no esté vacía, en caso de no estarlo buscamos si la palabra a ser ingresada existe o no
                        //Si la palabra existe sumamos el contador
                        if (!hashTable.isEmpty() && hashTable.containsKey(palabra.hashCode())) {
                            hashTable.get(Math.abs(palabra.hashCode())).sumarContador();
                        } //De estar vacía la tabla o de no encontrarse la palabra simplemente se inserta una nueva palabra
                        else {
                            hashTable.put(Math.abs(palabra.hashCode()), new Palabra(palabra));
                        }
                    }
                }
                linea = lector.readLine();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

        



}
