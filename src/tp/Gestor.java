package tp;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import tp.*;

public class Gestor {
    private Hashtable<Integer, Palabra> hashTable;    
    
    public Gestor(){
        hashTable = new Hashtable<Integer, Palabra>();
    }
    
    public void cargarPalabras(File f) {
            
        if (!f.getName().endsWith(".txt")) {
            return;
        }

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f), "8859_1"));
            String[] palabras = null;

            String delimitadores = "[ _.,;:?!¡¿\\'\\\"\\\\[\\[]\\]()~#*/-]+";
            String linea = in.readLine();
            while (linea != null) {
                palabras = linea.split(delimitadores);
                for (String p : palabras) {
                    if (!(p.contains("@") || p.contains("¡") || p.contains("0") || p.contains("1") || p.contains("2") || p.contains("3") || p.contains("4") || p.contains("5") || p.contains("6") || p.contains("7") || p.contains("8") || p.contains("9"))) {

                        p = p.toLowerCase();

                        if (!hashTable.isEmpty() && hashTable.containsKey(p.hashCode())) {
                            hashTable.get(Math.abs(p.hashCode())).sumarContador();
                        } else {
                            hashTable.put(Math.abs(p.hashCode()), new Palabra(p));
                        }

                    }
                }
                linea = in.readLine();
            }
            
            System.out.println(hashTable.toString());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
