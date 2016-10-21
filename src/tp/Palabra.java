/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp;

/**
 *
 * @author a2
 */
public class Palabra {
    private String palabra;
    private int contador;
    
    public Palabra(String s){
        palabra = s;
        contador = 1;
    }
    
    public void sumarContador(){
        setContador(getContador() + 1);
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
}
