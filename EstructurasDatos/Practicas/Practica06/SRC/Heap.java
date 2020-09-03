import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase para heaps mínimos (<i>min heaps</i>). Podemos crear un heap
 * mínimo con <em>n</em> elementos en tiempo <em>O</em>(<em>n</em>), y podemos
 * agregar y actualizar elementos en tiempo <em>O</em>(log <em>n</em>). Eliminar
 * el elemento mínimo también nos toma tiempo <em>O</em>(log <em>n</em>).
 */
public abstract class Heap<T extends Comparable<T>> implements Coleccionable<T> {

    /* Clase privada para iteradores de heaps. */
    private class Iterador<T extends Comparable<T>> implements Iterator<T> {

        /* Índice del iterador. */
        private int actual;
        /* El heap mínimo. */
        private Heap<T> heap;

        /* Construye un nuevo iterador, auxiliándose del heap mínimo. */
        public Iterador(Heap<T> heap) {
            heap = heap;
            actual =0;
        }

        /* Nos dice si hay un siguiente elemento. */
        @Override public boolean hasNext() {
          return actual < arreglo.length && actual < siguiente;
        }

        /* Regresa el siguiente elemento. */
        @Override public T next() {
            if (hasNext()) {
              T e = (T) arreglo[actual];
              actual++;
              return e;
            }
            return null;
        }

        /* No lo implementamos: siempre lanza una excepción. */
        @Override public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* El siguiente índice dónde agregar un elemento. */
    private int siguiente;
    /* El arreglo que guarda los datos con la estructura de árbol ue requiere el heap.*/
    private T[] arreglo;

    /* Truco para crear arreglos genéricos. Es necesario hacerlo así por cómo
       Java implementa sus genéricos; de otra forma obtenemos advertencias del
       compilador. */
    @SuppressWarnings("unchecked") private T[] creaArregloGenerico(int n) {
        return (T[])(new Comparable[n]);
    }

    /**
     * Constructor sin parámetros. Es más eficiente usar {@link
     * #HeapMinimo(Lista)}, pero se ofrece este constructor por completez.
     */
    public Heap() {
      arreglo = creaArregloGenerico(50);
      siguiente = 0;

    }

    /**
     * Constructor para heap mínimo que recibe una lista. Es más barato
     * construir un heap con todos sus elementos de antemano (tiempo
     * <i>O</i>(<i>n</i>)), que el insertándolos uno por uno (tiempo
     * <i>O</i>(<i>n</i> log <i>n</i>)).
     * @param lista la lista a partir de la cuál queremos construir el
     *              heap.
     */
    public Heap(Lista<T> lista) {
	     arreglo = creaArregloGenerico(lista.longitud()+1);
       for (T e : lista ) {
         arreglo[siguiente] = e;
         siguiente++;
       }
       for (int i = siguiente-1; i >=0 ; i-- ) {
         reordena(i);
       }

    }

    /*
     * Métodos auxiliares.
     */
    private void intercambia(int i, int j){
	     T elem = arreglo[i];
       arreglo[i] = arreglo[j];
       arreglo[j] = elem;
    }

    private int izquierdo(int i){
	     return (2*i)+1;
    }

    private int derecho(int i){
	     return  2*(i+1);
    }

    private int padre(int i){
	     return (i-1)/2;
    }

    /**
     * Agrega un nuevo elemento en el heap.
     * @param elemento el elemento a agregar en el heap.
     */
    @Override public void agrega(T elemento) {
      if (siguiente < arreglo.length-1) {
        arreglo[siguiente]= elemento;
        siguiente++;
          reordena(siguiente-1);
      }else {
        T[] ax = creaArregloGenerico(arreglo.length*2);
        for (int i =0;i < arreglo.length ;i++ ) {
          ax[i]=arreglo[i];
        }
        arreglo = ax;
        arreglo[siguiente]= elemento;
        siguiente++;
        reordena(siguiente-1);
      }

    }

    /**
     * Elimina el elemento hasta arriba del heap.
     * @return el elemento mínimo o máximo del heap.
     * @throws IllegalStateException si el heap es vacío.
     */
    public T elimina() {
      if (esVacio()) {
        throw  new IllegalStateException("El heap esta vacio, no se puede eliminar nada");
      }else {
        T elem = arreglo[0];
        intercambia(0,siguiente-1);
        arreglo[siguiente-1]= null;
        siguiente--;
        reordena(siguiente-1);
        return elem;
      }
    }

    /**
     * Puesto que no deberíamos eliminar un elemento que
     * no sea el mínimo, lanzamos una excepción. En una
     * implementación más aplicada es posible implementar
     * esta operación con los métodos ya implementados sin mucho
     * esfuerzo.
     * @param elemento a eliminar del heap.
     * @throws IllegalStateException. Esta operación no debería ser posible
     * en un Heap
     */
    @Override public void elimina(T elemento) {
	       throw new IllegalStateException("Esta operación no debería ser válida para Heaps");
    }

    /**
     * Nos dice si un elemento está contenido en el heap.
     * @param elemento el elemento que queremos saber si está contenido.
     * @return <code>true</code> si el elemento está contenido,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
        for (int i = 0; i < siguiente ;i++ ) {
          if((arreglo[i]).compareTo(elemento)==0)
            return true;
        }
        return false;
    }

    /**
     * Nos dice si el heap es vacío.
     * @return <tt>true</tt> si ya no hay elementos en el heap,
     *         <tt>false</tt> en otro caso.
     */
    public boolean esVacio() {
      return siguiente == 0;
    }

    /**
     * Reordena un elemento en el árbol.
     * La implementación se deja a la clase concreta, pues el reordenamiento
     * depende de si es un heap mínimo o máximo.
     * @param elemento el elemento que hay que reordenar.
     */
    public abstract void reordena(int elemento);

    /**
     * Este método hace la chamba, dependiendo de si
     * el heap es mínimo o máximo.
     */
    protected void reordena(int elemento, boolean esMin){
      if (esMin) {
        if (elemento != 0 && elemento > 0) {
          if (derecho(padre(elemento)) == elemento) {
            intercambia(padre(elemento), getMenor(padre(elemento),elemento-1,elemento));
          }else if (elemento+1 < siguiente) {
            intercambia(padre(elemento), getMenor(padre(elemento),elemento+1,elemento));
          }else if((arreglo[elemento].compareTo(arreglo[padre(elemento)]) < 0)) {
            intercambia(padre(elemento),elemento);
          }
          int p = padre(elemento);
          reordena(p);
        }
      }else {
        if (elemento != 0 && elemento > 0) {
          if (derecho(padre(elemento)) == elemento) {
            intercambia(padre(elemento), getMayor(padre(elemento),elemento-1,elemento));
          }else if (elemento+1 < siguiente) {
            intercambia(padre(elemento), getMayor(padre(elemento),elemento+1,elemento));
          }else if((arreglo[elemento].compareTo(arreglo[padre(elemento)]) > 0)) {
            intercambia(padre(elemento),elemento);
          }
          int p = padre(elemento);
          reordena(p);
        }
      }
    }

    /* Método auxiliar que regresa el índice en el arreglo que
     * tiene al elemento menor de tres.
     */
    private int getMenor(int elemento, int i, int d){
	     if (elemento < siguiente && i < siguiente && d < siguiente) {
         if (arreglo[elemento].compareTo(arreglo[i]) <0) {
           return (arreglo[elemento].compareTo(arreglo[d]) < 0)? elemento:d;
         }else {
          return  (arreglo[i].compareTo(arreglo[d]) < 0)? i:d;
         }
       }
       return -1;
    }

    /* Método auxiliar que regresa el índice en el arreglo que
     * tiene al elemento menor de tres.
     */
    private int getMayor(int elemento, int i, int d){
      if (elemento < siguiente && i < siguiente && d < siguiente) {
        if (arreglo[elemento].compareTo(arreglo[i]) > 0) {
          return (arreglo[elemento].compareTo(arreglo[d]) > 0)? elemento:d;
        }else {
         return  (arreglo[i].compareTo(arreglo[d]) > 0)? i:d;
        }
      }
      return -1;
    }

    /**
     * Regresa el número de elementos en el heap.
     * @return el número de elementos en el heap.
     */
    @Override public int getTamanio() {
        return siguiente;
    }

    @Override
    /**
     * Regresa la representación en cadena del heap.
     * @return la representación en cadena del árbol.
     */
    public String toString(){
    	String s = "[";
    	for(int i =0; i<siguiente;i++){
    	    s+= i==siguiente-1? arreglo[i]: arreglo[i]+"|";
    	}
    	return s+="]";
    }

    /**
     * Regresa un iterador para recorrer el heap.
     * @return un iterador para recorrer el heap.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador<T>(this);
    }
}
