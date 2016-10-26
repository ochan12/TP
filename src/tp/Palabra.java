package tp;

import java.util.ArrayList;

public class Palabra implements Comparable<Palabra>{
    
    private String contenido;
    private int contador;
    private ArrayList <Libro> libros;
    
    
    public Palabra(String s){
        contenido = s;
        contador = 1;
        libros = new ArrayList <Libro> ();
    }
    
    public void sumarContador(){
        this.contador++;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getContador() {
        return contador;
    }

    public int compareTo(Palabra p){
        return this.contenido.compareTo(p.contenido);
    }
    
    public void addLibro(Libro libro){
        if(libro != null) libros.add(libro);
    }
    
}
