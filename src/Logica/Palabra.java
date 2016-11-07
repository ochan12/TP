package Logica;

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
        libros = new ArrayList();
    }
    
    public Palabra(String s, Libro libroNuevo)
    {
        contenido = s;
        contador = 1;
        if(libros==null){
             libros = new ArrayList();
             
        }
        libros.add(libroNuevo);
        
    }
    
    public Palabra(String contenido, int contador){
        this.contador= contador;
        this.contenido = contenido;
        this.libros = new ArrayList();
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
    
    public int librosDondeSeEncuentra(){
        if(libros==null)return 0;
        return libros.size();
    }
    
    public void agregarLibro(Libro libroNuevo) {
        boolean agregar = true;

        try {
            if (libros != null) {
                for (Libro libro : libros) {
                    if (libro.compareTo(libroNuevo) == 0) {
                        agregar = false;
                    }
                }
            }
            if (agregar) {
                this.libros.add(libroNuevo);
            }
        } catch (Exception e) {
            System.out.println("ERROR EN CARGAR LIBRO" + e.getMessage());
        }

    }

}
