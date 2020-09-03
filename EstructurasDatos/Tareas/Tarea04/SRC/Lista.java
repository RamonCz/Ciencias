import java.util.Iterator;
/**
 * <p> Clase concreta para modelar la estructura de datos Lista</p>
 * <p>Esta clase implementa una Lista genérica, es decir que es homogénea pero
 * puede tener elementos de cualquier tipo.
 * @author Alejandro Hernández Mora <alejandrohmora@ciencias.unam.mx>
 * @version 1.0
 */
public class Lista<T> implements Listable<T>, Iterable{

    /* Clase interna para construir la estructura */
    private class Nodo{
    	/* Referencias a los nodos anterior y siguiente */
    	public Nodo anterior, siguiente;
    	/* El elemento que almacena un nodo */
    	public T elemento;

    	/* Unico constructor de la clase */
    	public Nodo(T elemento){
  	    this.elemento = elemento;
  	   }

      public boolean equals(Nodo n){
        return this.elemento.equals(n.elemento);
      }
      public T elemento(){
        return elemento;
      }


    }

    private class IteradorLista implements Iterator{
        /* La lista a recorrer*/
        private Lista<T> lista;
        /* Elementos del centinela que recorre la lista*/
        private Nodo anterior, siguiente;

        public IteradorLista(){
          anterior = null ;
          siguiente = cabeza;
        }
        public IteradorLista(Lista<T> lista){
          this.lista = lista;
          this.anterior = null;
          this.siguiente= lista.cabeza;
        }
        @Override
        public boolean hasNext() {
            return siguiente != null;
        }

        @Override
        public T next() {
          if (siguiente == null) {
            System.out.println("No se puede");
            return null;
          }else{
            anterior = siguiente;
            siguiente = siguiente.siguiente;
            return anterior.elemento();

          }
        }

        @Override
        public void remove() {
            Iterator.super.remove(); //To change body of generated methods, choose Tools | Templates.
        }
    }

    /* Atributos de la lista */
    private Nodo cabeza, cola;
    private int longitud;

    public Lista (){
  		longitud = 0;
  		cabeza = new Nodo(null);
  		cola = new Nodo(null);
  		cabeza.siguiente= cola;
  		cola.anterior=cabeza;
	   }
   public Lista (T[] c){
     longitud =0;
     for (T elem : c){
       this.agregar(elem);
       longitud++;
     }

   }
   public Lista (Lista<T> c){
    Lista<T> l = c;
   }

    /**
     * Método que nos dice si las lista está vacía.
     * @return <code>true</code> si el conjunto está vacío, <code>false</code>
     * en otro caso.
     */
    public boolean esVacia(){
        return longitud == 0;
    }

    /**
     * Método para eliminar todos los elementos de una lista
     */
    public void vaciar(){
      cabeza = cola= null;
      longitud = 0;
    }

    /**
     * Método para obtener el tamaño de la lista
     * @return tamanio Número de elementos de la lista.
     **/
    public int longitud(){
        return longitud;
    }

    /**
     * Método para agregar un elemento a la lista.
     * @param elemento Objeto que se agregará a la lista.
     */
    public void agregar(T elemento){
        Nodo e = new Nodo(elemento);
        if (this.esVacia()) {
          cabeza  = e ;
        }else {
          e.anterior= cola;
          cola.siguiente = e ;
        }
        cola = e;
        longitud++;
    }

    private Nodo buscarNodo(T elem){
      if (!this.esVacia()) {
        Nodo aux = cabeza;
        while(aux != null){
          if ( (aux.elemento).equals(elem) ) {
            return aux;
          }
          aux = aux.siguiente;
        }
      }
      return null;
    }


      /**
     * Método para agregar al inicio un elemento a la lista.
     * @param elemento Objeto que se agregará al inicio de la lista.
     */
    public void agregarAlInicio(T elemento){
      Nodo n = new Nodo(elemento);
      if (esVacia()) {
        cabeza = cola = n ;
      }else{
        n.siguiente=cabeza;
        cabeza.anterior=n;
        cabeza = n;
      }
      longitud++;
    }

    /**
     * Método para agregar al final un elemento a la lista.
     * @param elemento Objeto que se agregará al inicio de la lista.
     */
    public void agregarAlFinal(T elemento){
      this.agregar(elemento);
    }
    /**
     * Método para verificar si un elemento pertenece a la lista.
     * @param elemento Objeto que se va a buscar en la lista.
     * @return <code>true</code> si el elemento esta en el lista y false en otro caso.
     */
    public boolean contiene(T elemento){
      return this.buscarNodo(elemento) != null;
    }

    /**
     * Método para eliminar un elemento de la lista.
     * @param elemento Objeto que se eliminara de la lista.
     */
    public void eliminar(T elemento){
      Nodo n = buscarNodo(elemento);
      if (this.esVacia()) {
        System.out.println("No hay elementos que eliminar");
      }
      if (n != null) {
        if (cabeza == cola) {
          this.vaciar();
          return ;
        }
        if (n.anterior != null) {
          (n.anterior).siguiente = n.siguiente;
        }
        if (n.siguiente != null) {
          (n.siguiente).anterior= n.anterior;
        }
        if (n == cabeza) {
          (cabeza.siguiente).anterior = null;
          cabeza = cabeza.siguiente;
        }
        if (n == cola) {
          (cola.anterior).siguiente = null;
          cola = cola.anterior;
        }
      }
      longitud--;
    }

    /**
     * Método que devuelve la posición en la lista que tiene la primera
     * aparición del <code> elemento</code>.
     * @param elemento El elemnto del cuál queremos saber su posición.
     * @return i la posición del elemento en la lista, -1, si no se encuentra en ésta.
     */
    public int indiceDe(T elemento){
      int cont = 0;
      Nodo n = cabeza;
      if (this.esVacia()) {
        return -1;
      }else {
        while(n != null){
          if ((n.elemento).equals(elemento)) {
            return cont;
          }
          n = n.siguiente;
          cont++;
        }
      }
      return -1;
    }

    /**
     * Método que nos dice en qué posición está un elemento en la lista
     * @param i La posición cuyo elemento deseamos conocer.
     * @return <code> elemento </code> El elemento que contiene la lista,
     * <code>null</code> si no se encuentra
     * @throws IndexOutOfBoundsException Si el índice es < 0 o >longitud()
     */
    public T getElemento(int i)throws IndexOutOfBoundsException{
        if (i<0 || i>longitud) {
          throw new IndexOutOfBoundsException("indice invalido");
        }
        Nodo e = cabeza;
        int cont = 0;
        while(cont !=  i ){
          e = e.siguiente;
          cont++;
        }
        return e.elemento;
    }

    /**
     * Método que devuelve una copia de la lista, pero en orden inverso
     * @return Una copia con la lista l revés.
     */
    public Lista<T> reversa(){
      Lista<T> l = new Lista<T>();
      Nodo n = cabeza;
      if (this.esVacia()) {
        System.out.println("Lista vacia");
        return l;
      }else {
        //l.agregarAlInicio(cabeza.elemento);
        while(n!= null){
          l.agregarAlInicio(n.elemento);
          n = n.siguiente;
        }

      }
      return l;

    }

    /**
     * Método que devuelve una copi exacta de la lista
     * @return la copia de la lista.
     */
    public Lista<T> copia(){
      Lista<T> l = new Lista<T>();
      Nodo n = cabeza;
      if (this.esVacia()) {
        System.out.println("Lista vacia");
        return l;
      }else {
        while(n != null){
          l.agregarAlFinal(n.elemento);
          n = n.siguiente;
        }

        l.longitud = longitud;
      }
      return l;
    }

    /**
     * Método que nos dice si una lista es igual que otra.
     * @param o objeto a comparar con la lista.
     * @return <code>true</code> si son iguales, <code>false</code> en otro caso.
     */
    @Override
    public boolean equals(Object o){
        Lista<T> l = (Lista<T>) o;
        Nodo n = cabeza;
        Nodo n1 = l.cabeza;
        if (longitud != l.longitud) {
          return false;
        }else {
          while(n != null){
            if ( !(n.elemento).equals(n1.elemento) ) {
              return false;
            }
            n = n.siguiente;
            n1 = n1.siguiente;
          }
          return true;
        }

    }

    /**
     * Método que devuelve un iterador sobre la lista
     * @return java.util.Iterador -- iterador sobre la lista
     */
    @Override
    public java.util.Iterator iterator(){
        return new IteradorLista(this);

    }

    /**
     * Método que devuelve una copia de la lista.
     * @param <T> Debe ser un tipo que extienda Comparable, para poder distinguir
     * el orden de los elementos en la lista.
     * @param l La lista de elementos comparables.
     * @return copia de la lista ordenada.
     */

    public static <T extends Comparable<T>> Lista <T> mergesort(Lista<T>l){
      if (l.esVacia()) {
        return null;
      }
      if (l.longitud == 1) {
        return l.copia();
      }
      Lista<T> t1 = new Lista<T>();
      Lista<T> t2 = new Lista<T>();
      Lista<T>.Nodo n = l.cabeza;
      int i =0;
      while(i < l.longitud/2){
        t1.agregar(n.elemento);
        i++;
        n = n.siguiente;
      }
      while(n != null){
        t2.agregar(n.elemento);
        n = n.siguiente;
      }
      t1  = mergesort(t1);
      t2  = mergesort(t2);
      return merge(t1,t2);

    }
    private static <T extends Comparable<T>> Lista<T> merge(Lista<T> t1, Lista<T> t2){
      Lista<T> u = new Lista<T>();
        Lista<T>.Nodo n1 = t1.cabeza;
        Lista<T>.Nodo n2 = t2.cabeza;
      while(n1 !=  null && n2 != null){
        if (n1.elemento.compareTo(n2.elemento) < 0) {
          u.agregar(n1.elemento);
          n1 = n1.siguiente;
        }else {
          u.agregar(n2.elemento);
          n2 = n2.siguiente;
        }
      }
      u.cola.siguiente = (n1 !=null)? n1:n2;
      Lista<T>.Nodo n = (n1 != null)? n1:n2;
      n.anterior = u.cola;
      u.cola = (n1 != null)? t1.cola:t2.cola;
      return u;
    }

    public String toString(){
      String s = "[";
      for (int i =0; i < longitud ;i++ ) {
        s += String.format("%s,",this.getElemento(i));
          //s += String.format("%s] ",(longitud-1));
          //return s;
      }
      s+=String.format("]");
      return s;
    }

}
