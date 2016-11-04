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
        libros = new ArrayList <> ();
    }
    
    public Palabra(String s, Libro libroNuevo)
    {
        contenido = s;
        contador = 1;
        if(libros==null){
             libros = new ArrayList <> ();
             
        }
        libros.add(libroNuevo);
        
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
        if(libro != null) getLibros().add(libro);
    }

    @Override
    public String toString() {
        return "Palabra{" + "contenido=" + contenido + ", contador=" + contador + ", libros=" + getLibros() + '}';
    }

    public ArrayList <Libro> getLibros() {
        return libros;
    }

    public void setLibros(ArrayList <Libro> libros) {
        this.libros = libros;
    }
    
    public String librosDondeSeEncuentra(){
        StringBuilder s = new StringBuilder();
        for (Libro libro : libros) {
            s.append(libro.getTitulo()+" - ");
        }
        s.delete(s.length()-2, s.length()-1);
        return s.toString();
    }
    
    public void agregarLibro(Libro libroNuevo){
        for (Libro libro : libros) {
           if(libro.getTitulo().equalsIgnoreCase(libroNuevo.getTitulo())) return;
        }
        this.libros.add(libroNuevo);
    }
    
}
