package tp;

/**
 * Una tabla hash gen�rica, con listas de desborde.
 * @author Ing. Valerio Frittelli.
 * @version Mayo de 2004.
 */
import java.io.*;
import java.util.Iterator;
public class HashTable implements Serializable
{
   private SimpleList []items;
   private int cantidad;  // la cantidad de objetos de la tabla
   private Class clase;   // la clase de objetos que contiene la tabla
   
   public HashTable()
   {
      this(11);   
   }
   
   public HashTable (int n)
   {
      if (n <= 0) { n = 11; }
      
      items = new SimpleList[n];
      for( int i = 0; i < n; i++ )
      {
          items[i] = new SimpleList();
      } 
      
      clase = null;
      cantidad = 0;
   }
   
   public boolean isEmpty()
   {
       return (cantidad == 0);
   }
      
   /**
    * Busca el objeto x en la tabla, y retorna la direccion del objeto que esta en la tabla 
    * y coincida con x, o null si x no existia en la tabla o no era de la misma clase que el 
    * contenido de la tabla.
    * @param x el objeto a buscar.
    * @return la direccion del objeto que coincide con x en la tabla, o null si no existia.
    */
   public Palabra get (Palabra x)
   {
      // control de homogeneidad a nivel tabla...
      if(!isOk(x)) { return null; }

      int k = x.hashCode();
      int y = h(k);
      return items[y].search(x);
   }
   
   /**
    * Comprueba si x esta en la tabla y retorna true en ese caso, o retorna false si x no 
    * existe en la tabla o no es de la misma clase que el contenido de la tabla.
    * @param x el objeto a buscar.
    * @return true si x esta en la tabla.
    */
   public boolean contains (Palabra x)
   {
      // control de homogeneidad a nivel tabla...
      if(!isOk(x)) { return false; }      
      
      int k = x.hashCode();
      int y = h(k);
      return items[y].contains(x);
   } 
   
   public boolean put( Palabra x )
   {
      if( x == null ) { return false; } 
      if ( clase == null ) { clase = x.getClass(); }
      if( x.getClass() != clase ) { return false; }
      if ( averageLength() >= 8 ) { rehash(); }
      
      // ahora si: primero tomamos el hashCode() del objeto...
      int k = x.hashCode();

      // ... luego dispersamos la clave...
      int y = h(k);

      // si ya existia, salir con false...
      if(items[y].contains(x)) 
        {
            items[y].get(items[y].indexOf(x)).sumarContador();
        }

      // ... y finalmente pedimos a la lista que haga la insercion...
      items[y].addFirst(x);
      cantidad++;
      return true;
}

   /**
    * Elimina el objeto x de la tabla. Retorna false si x no es de la misma clase que los
    * objetos que ya estaban en la tabla, o si x no estaba en la tabla.
    * @param x el objeto a eliminar.
    * @return true si la eliminacion tuvo exito.
    */
   public boolean remove (Palabra x)
   {
      // control de homogeneidad a nivel tabla...
      if(!isOk(x)) { return false; }

      int k = x.hashCode();
      int y = h(k);
      boolean ok = items[y].remove(x);
      if(ok) { cantidad--; }
      return ok;
   }   
   
   /**
    * Devuelve el contenido de la tabla en forma de String.
    * @return un String con el contenido de la tabla.
    */
   @Override
   public String toString()
   {
      StringBuilder cad = new StringBuilder("");
      for (int i = 0; i < items.length; i++)
      {
          cad.append("\nLista ").append(i).append(":\n\t").append(items[i].toString());   
      }
      return cad.toString();
   }

   /**
    *  Incrementa el tamaño de la tabla y reorganiza su contenido. Se invoca automaticamente
    *  cuando se detecta que el promedio de nodos por lista supera a cierto valor critico
    *  (que en nuestra implementacion de es de 8 nodos por lista, aunque seria aceptable 
    *  hasta unos 10 nodos por lista)
    */
   protected void rehash ()
   {
       // calculamos un aumento del 50% sobre el tamaño anterior... 
       int n = (int)(1.5 * items.length);
       
       // y tomamos como nuevo tamaño de la tabla al primer primo mayor a n...
       n = siguientePrimo( n );
       
       // creamos un nuevo arreglo de listas, de ese tamaño...
       SimpleList temp[] = new SimpleList[n];
       for(int j = 0; j < temp.length; j++) { temp[j] = new SimpleList(); }
       
       // recorremos la vieja tabla, para extraer uno a uno los objetos que contenia...
       for (int i = 0; i < items.length; i++)
       {
           // entramos en la lista numero i, y la recorremos con su iterador...
           Iterator it = items[i].iterator();
           while( it.hasNext() )
           {
               // obtenemos un objeto de la vieja lista...
               Palabra x = (Palabra) it.next();
               
               // obtenemos su nuevo valor de dispersion para el nuevo arreglo...
               int k = x.hashCode();
               int y = k % temp.length;
               
               // y lo insertamos en el nuevo arreglo, en la lista numero "y"...
               temp[y].addFirst(x);
           }
       }
       
       // la cantidad de objetos no se toca... es la misma...
       // ... pero cambiamos la referencia items para que apunte a la nueva tabla...
       items = temp;
   }
    
   /**
    * Determina si n es primo.
    * @param n el numero a analizar.
    * @return true si el numero es primo.
    */ 
   public static boolean esPrimo( int n )
   {
        if( n == 2 ) { return true; }
        if( n % 2 == 0 ) { return false; }
        long raiz = ( long ) Math.sqrt( n );
        for( long div = 3; div <= raiz; div += 2 )
        {
            if( n % div == 0 ) { return false; }
        }
        return true;
   }
   
   /**
    * Calcula el siguiente numero primo distinto de 2, comenzando el calculo
    * desde el valor n.
    * @param n el numero a partir del cual se busca el siguiente numero primo.
    * @return el siguiente numero primo a partir de n.
    */ 
    @SuppressWarnings("empty-statement")
    public static int siguientePrimo( int n )
    {
        if( n <= 1 ) { return 3; }
    	if( n % 2  == 0)  { n++; }
    	for( ; ! esPrimo( n ); n += 2 ) ;
    	return n;
    }
   
   /**
    * Funci�o hash. Toma el hashCode() de un objeto, y retorna un indice para entrar en 
    * el arreglo items.
    * @param k el valor hashCode del objeto a almacenar.
    * @return el indice para entrar en la tabla items.
    */
   private int h( int k )
   {
      k = Math.abs( k );
      return k % items.length;
   }
   
   /**
    * Funcion hash. Toma un objeto, y retorna un indice para entrar en 
    * el arreglo items.
    * @param x el objeto a almacenar.
    * @return el indice para entrar en la tabla items.
    */
   private int h( Palabra x )
   {
      return h( x.hashCode() );
   }  
   
   /**
    * Calcula la longitud promedio de las listas de la tabla.
    * @return la longitud promedio de la listas contenidas en la tabla.
    */
   private int averageLength()
   {
      return cantidad / items.length;
   }
   
   private boolean isOk(Palabra x)
   {
      // control de homogeneidad a nivel tabla...
      if( x == null ) { return false; }    
      if( clase == null ) { return false; }
      if( x.getClass() != clase ) { return false; }
      return true;
   }
}
