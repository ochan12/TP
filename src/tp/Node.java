package tp;

/**
 *  Segunda version de una clase para representar un nodo para una lista simple generica.
 *  @author Ing. Valerio Frittelli.
 *  @version Agosto de 2008.
 */
import java.io.*;
public class Node implements Serializable
{
   private Palabra info;
   private Node next;
   
   /**
    *  Constructor por defecto. 
    */
   public Node ( )
   {
   }
   
   /**
    *  Crea un nodo incializando todos los atributos en base a parametros.
    */
   public Node (Palabra x, Node p)
   {
     info = x;
     next = p;
   }
   
   /**
    *  Accede a la direccion del sucesor.
    *  @return la direccion del nodo sucesor.
    */
   public Node getNext()
   {
     return next;
   }
   
   /**
    * Cambia la direccion del sucesor.
    * @param p direccion del nuevo sucesor.
    */
   public void setNext(Node p)
   {
     next = p;
   }
   
   /**
    *  Accede al valor del info.
    *  @return el valor del info.
    */
   public Palabra getInfo()
   {
     return info;
   }
   
   /**
    * Cambia el valor del info.
    * @param p nuevo valor del info.
    */
   public void setInfo(Palabra p)
   {
     info = p;
   }

   /**
    * Convierte el contenido del nodo en String.
    * @return el contenido del nodo convertido en String.
    */
    @Override
   public String toString()
   {
     return info.toString();   
   }
}

