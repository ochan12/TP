package tp;

public class Palabra implements Comparable<Palabra>{
    
    private String contenido;
    private int contador;
    
    public Palabra(String s){
        contenido = s;
        contador = 1;
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

    public void setContador(int contador) {
        this.contador = contador;
    }
    
    public int compareTo(Palabra p){
        return this.contenido.compareTo(p.contenido);
    }
    
}
