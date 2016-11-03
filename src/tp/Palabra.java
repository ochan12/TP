package tp;

import java.util.ArrayList;

public class Palabra implements Comparable<Palabra>{
    
    private String contenido; //Letras que conforman la palabra; la palabra en sí
    private int contador; //Contador de apariciones totales
    private ArrayList <Libro> libros; //Lista de libros en los que apareció
    
    //Constructor
    public Palabra(String s)
    {
        contenido = s;
        contador = 1;
        libros = new ArrayList <Libro> ();
    }
    
    //Sumador
    public void sumarContador()
    {
        this.contador++;
    }

    //Seters y Geters
    public String getContenido()
    {
        return contenido;
    }

    public void setContenido(String contenido)
    {
        this.contenido = contenido;
    }

    public int getContador()
    {
        return contador;
    }
    
    //Implementación de compareTo()
    public int compareTo(Palabra p)
    {
        return this.contenido.compareTo(p.contenido);
    }
    
    //Añade un libro al arraylist
    public void addLibro(Libro libro)
    {
        if(libro != null) libros.add(libro);
    }
    
}
