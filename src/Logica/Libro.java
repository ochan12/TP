package Logica;

public class Libro implements Comparable<Libro> {
    private String autor;
    private String titulo;
    private String idioma;
    private int id;
    
    //Constructor
    public Libro (){
         autor = "";
         titulo = "";
         idioma = "";
    }
    public Libro(String autor, String titulo, String idioma){
        this.autor = autor;
        this.titulo = titulo;
        this.idioma = idioma;
    }
    //Seters y Geters
    public String getAutor()
    {
        return autor;
    }

    public void setAutor(String autor)
    {
        this.autor = autor;
    }
    
    public int getID(){
        return id;
    }
    
    public void setID(int id){
        this.id = id;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    
    public String getIdioma()
    {
        return idioma;
    }

    public void setIdioma(String idioma)
    {
        this.idioma = idioma;
    }

    @Override
    public int compareTo(Libro comparable) {
        if (this.getAutor().equalsIgnoreCase(comparable.getAutor()) && this.getTitulo().equalsIgnoreCase(comparable.getTitulo())) {
            return 0;
        }
        return 1;
    }
    
    public String toString()
    {
        return "Autor: " + autor + " - Título: " + titulo +  " - Idioma: " + idioma;
    }    
    
}