
package tp;

public class Main {
    private static HashTable t ;
    
    public static void main(String[] args) throws InterruptedException {
        
        interfaz.Principal P = new interfaz.Principal();
        P.setVisible(true);
        t = new HashTable(P.getListaPalabras().size());
        for (Object p : P.getListaPalabras()) {
            t.put((Palabra)p);
            
        }
        
    }
    
}
