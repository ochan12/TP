package tp;

public class Libro implements Comparable<Libro> {
    private String autor;
    private String titulo;
    private String fechaLanzamiento;
    private String idioma;
    
    //Constructor
    public Libro (){
         autor = "";
         titulo = "";
         fechaLanzamiento = "";
         idioma = "";
    }
    public Libro(String autor, String titulo, String fechaLanzamiento, String idioma){
        this.autor = autor;
        this.titulo = titulo;
        this.fechaLanzamiento = fechaLanzamiento;
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

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public String getFechaLanzamiento()
    {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento)
    {
        this.fechaLanzamiento = fechaLanzamiento;
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
        if (this.getAutor().equals(comparable.getAutor()) && this.getTitulo().equals(comparable.getAutor())) {
            return 0;
        }
        return 1;
    }
    
    public String toString()
    {
        return "Autor: " + autor + " - Título: " + titulo +  " - Fecha de lanzamiento: " + fechaLanzamiento + " - Idioma: " + idioma;
    }    
    
}