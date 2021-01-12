/**
 * <p> Clase que modela conjuntos </p> <p>Esta clase sirve para crear
 * conjuntos y hacer operaciones entre ellos</p>
 * @author Alejandro Hernández Mora <alejandrohmora@ciencias.unam.mx>
 * @version 1.0
 */
import java.util.Iterator;

public class Conjunto<T> implements Iterable, Conjuntable<T> {
    /* Arreglo donde guardaremos los elementos de un conjunto */
    private T[] conjunto;
    /* Número de elementos que tiene el conjunto*/
    private int elementos;

    private class IteradorConjunto implements Iterator{
        /* Variable para contar cuantos elementos hay en el conjunto
        no debe tener repeticiones, ni espacios en blanco para que funcione */
    	int contador;
    	public IteradorConjunto(){
    	    contador =0;
    	}

    	public boolean hasNext(){
    	    //Aqui va ti codigo.
          return contador < elementos;
    	}

    	public Object next(){
    	    //Aqui va ti codigo.
          return conjunto[contador++];
    	}
    }

    public Conjunto(){
        //Aqui va ti codigo.
        conjunto = (T[]) new Object[50];
        elementos = 0;
    }

    public Conjunto(Conjunto<T> c){
        //Aqui va ti codigo.
        conjunto = c.conjunto;
        elementos = c.elementos;
    }

    public Conjunto(T[] c){
        conjunto = (T[]) new Object[c.length];
        elementos  =0 ;
        for (int i=0; i < conjunto.length; i++)  {
          conjunto[i]=c[i];
          if (c[i] != null) {
            elementos++;
          }
        }
    }

    /**
     * Método que nos dice si el conjunto está vacío.
     * @return <code>true</code> si el conjunto está vacío, <code>false</code>
     * en otro caso.
     */
    @Override
    public boolean esVacio(){
	//Aqui va ti codigo.
      return elementos == 0 ;
    }

    /**
     * Método para obtener el tamaño de un conjunto
     * @return número de elementos en el conjunto
     */
    @Override
    public int cardinalidad(){
	//Aqui va ti codigo.
    return elementos;
    }

    /**
     * Método para eliminar todos los elementos de un conjunto
     */
    @Override

    public void vaciar(){
	     //Aqui va ti codigo.
      conjunto = (T[]) new Object[50];
      elementos = 0;
    }

    /**
     * Método para agregar un elemento al conjunto
     * @param elemento Objeto que se incorporara al conjunto
     */
    @Override
    public void agregar(T elemento){
      boolean  x = true;
      for (int i = 0; i < elementos; i++ ) {
        if (conjunto[i].equals(elemento)) {
          x = false;
        }
      }
      if (x) {
        if (elementos >= conjunto.length) {
          this.agranda();
        }
        conjunto[elementos]= elemento;
        elementos++;
      }
    }

    private void agranda(){
     T [] aux =(T[]) new Object[conjunto.length*2];
     for (int i=0 ; i<conjunto.length ; i++ ) {
       aux[i] = conjunto[i];
     }
     conjunto = aux;
   }
    /**
     * Método para eliminar un <code>elemento</code> del conjunto
     * @param elemento Objeto que se eliminara del conjunto
     */
    @Override
    public void eliminar(T elemento){
      boolean x = false;
    	     for (int i =0; i < elementos; i++ ) {
             if (conjunto[i].equals(elemento)) {
               conjunto[i] = conjunto [i+1];
               x = true;
             }
             if (x) {
               conjunto[i] = conjunto[i+1];
             }

           }
    }

    /**
     * Método para ver si un elemento pertenece al conjunto
     * @param elemento Objeto que se va a buscar en el conjunto
     * @return <code>true</code> si el elemento esta en el conjunto,
     * <code>false</code> en otro caso.
     */
    @Override
    public boolean contiene (T elemento){
      for (int i =0; i < elementos ;i++ ) {
           if (conjunto[i].equals(elemento)) {
             return true;
           }
         }
         return false;
    }

    /**
     * Método para calcular la union de dos conjuntos
     * @param c1 conjunto con el que se calculará la unión
     * @return Conjuntable conjunto que contiene la unión
     */
    @Override
    public Conjuntable<T>  union(Conjuntable<T>  a){
      Conjunto<T> c = (Conjunto<T>) a;
      T[] aux = (T[])new Object[elementos + c.elementos];
      for (int i =0;i < elementos ;i++ ) {
        aux[i] = conjunto[i];
        }
      Conjunto<T>   union = new Conjunto(aux);

      for (T elem: (T[])c.conjunto) {
        union.agregar(elem);
        }
        return union;
    }

    /**
     * Método para calcular la intersección de dos conjuntos
     * @param c conjunto con el que se calculará la intersección
     * @return Conjuntable que con tiene la intersección
     */
    @Override
    public Conjuntable<T>  interseccion(Conjuntable<T>  a){
      Conjunto<T> c = (Conjunto<T>) a;
      T [] aux =(T[]) new Object[(elementos < c.elementos)? elementos:c.elementos];
    Conjunto<T> inter = new Conjunto(aux);
    for (T elem : conjunto) {
      if (c.contiene(elem)) {
        inter.agregar(elem);
      }
    }
    return inter;
    }

    /**
     * Método para calcular la diferencia de dos conjuntos
     * @param c conjunto con el que se va a calcular la diferencia
     * @return Conjuntable con la diferencia
     */
    @Override
    public Conjuntable<T>  diferencia(Conjuntable<T>  a){
      Conjunto<T> c = (Conjunto<T>) a;
      T [] aux =(T[]) new Object[elementos];
     Conjunto<T> dif = new Conjunto(aux);
     for ( T elem : conjunto) {
       if (!c.contiene(elem)) {
         dif.agregar(elem);
       }
     }
     return dif;
    }

    /**
     * Método para calcular la diferencia simétrica de dos conjuntos
     * @param c conjunto con el que se va a calcular la diferencia simétrica
     * @return Conjuntable con la diferencia simétrica
     */
    @Override
    public Conjuntable<T>  diferenciaSimetrica(Conjuntable<T>  a){
      Conjunto<T> c = (Conjunto<T>) a;
      T [] aux =(T[]) new Object[elementos + c.elementos];
           Conjunto<T> difs = new Conjunto(aux);
           for ( T elem : conjunto ) {
             if (!c.contiene(elem)){
               difs.agregar(elem);
             }
           }
           for ( T elem :(T[]) c.conjunto ) {
             if (!this.contiene(elem)){
               difs.agregar(elem);
             }
           }
           return difs;
    }

    /**
     * Método para determinar si un conjunto esta contenido en otro
     * @param c conjunto en se va a probar si el que llama es subconjunto
     * @return boolean true si el conjunto que llama a este metodo es
     *         subconjunto del parametro y false en otro caso
     */
    @Override
    public boolean subconjunto(Conjuntable<T>  a){
      Conjunto<T> c = (Conjunto<T>) a;
          for (T elem : (T[])c.conjunto ) {
            if (!this.contiene(elem)) {
              return false;
            }
          }
          return true;
    }

    @Override
    public String toString(){
      String s = "{";
      for (int i =0;i<elementos-1 ;i++ ) {
        s+=conjunto[i]+",";
      }
      s+= conjunto[elementos-1]+"}";
      return s;
    }

    /**
     * Método para crear un iterador sobre un conjunto
     * @return Iterator iterador sobre el conjunto.
     */
    @Override
    public java.util.Iterator iterator(){
      IteradorConjunto it = new IteradorConjunto();
           return it;
    }

}
