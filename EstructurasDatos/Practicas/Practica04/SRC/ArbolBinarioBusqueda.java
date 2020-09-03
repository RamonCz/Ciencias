import java.util.Iterator;
/**
 * <p>Clase para modelar árboles binarios de búsqueda genéricos.</p>
 * <p>Un árbol instancia de esta clase siempre cumple que:</p>
 * <ul>
 *   <li>Cualquier elemento en el árbol es mayor o igual que todos sus
 *       descendientes por la izquierda.</li>
 *   <li>Cualquier elemento en el árbol es menor o igual que todos sus
 *       descendientes por la derecha.</li>
 * </ul>
 * @author Alejandro Hernández Mora <alejandrohmora@ciencias.unam.mx>
 * @version 1.0
 */
public class ArbolBinarioBusqueda<T extends Comparable<T>>
    extends ArbolBinario<T> {

    /* Clase privada para iteradores de árboles binarios ordenados. */
    private class Iterador implements Iterator<T> {

        /* Pila para recorrer los nodos en DFS in-order. */
        private Pila<Nodo> pila;

        /* Construye un iterador con el nodo recibido. */
        public Iterador() {
            pila = new Pila<Nodo>();
            if (raiz != null) {
              pila.push(raiz);
              Nodo<T> nodo = pila.peek();
              while(nodo != null){
                if (nodo.izquierdo != null) {
                  pila.push(nodo.izquierdo);
                }
                nodo = nodo.izquierdo;
              }
            }

        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            return !pila.esVacio();
        }

        /* Regresa el siguiente elemento en orden DFS in-order. */
        @Override public T next() {
          T elem = null;
            if (hasNext()) {
              Nodo<T> nodo = pila.pop();
              elem = nodo.elemento;
              nodo = nodo.derecho;
              while(nodo != null){
                pila.push(nodo);
                nodo = nodo.izquierdo;
              }
              return elem;
            }
          return elem;
        }
    }


    /**
     * Constructor que no recibe parámeteros. {@link ArbolBinario}.
     */
    public ArbolBinarioBusqueda() {
	     super();
    }

    /**
     * Construye un árbol binario ordenado a partir de una colección. El árbol
     * binario ordenado tiene los mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol
     *        binario ordenado.
     */
    public ArbolBinarioBusqueda(Coleccionable<T> coleccion) {
          for ( T t : coleccion ) {
            this.agrega(t);
          }
    }

    private void agregaNodo(Nodo<T> n, Nodo<T> nuevo){
      if (n.elemento.compareTo(nuevo.elemento) > 0) {
        if (n.derecho!= null) {
          agregaNodo(n.derecho,nuevo);
        }else {
        n.derecho = nuevo;
        nuevo.padre = n;
        }
      }else {
        if (n.izquierdo != null) {
          agregaNodo(n.izquierdo,nuevo);
        }else {
          n.izquierdo = nuevo;
          nuevo.padre = n;
        }
      }
      tamanio++;
    }

    /**
     * Agrega un nuevo elemento al árbol. El árbol conserva su orden in-order.
     * @param elemento el elemento a agregar.
     */
    @Override public void agrega(T elemento) {
      if(raiz == null){
            Nodo<T> nodo = new Nodo(elemento);
            raiz = nodo;
        }else{
            Nodo<T> nuevo = new Nodo(elemento);
            Nodo<T> tmp = raiz();
            while(tmp != null){
              if(nuevo.elemento.compareTo(tmp.elemento) > 0 ){
                  if(!tmp.hayDerecho()){
                    tmp.derecho = nuevo;
                    nuevo.padre = tmp;
                    tamanio++;
                    return ;
                  }else{
                    tmp = tmp.derecho;
                  }
                }else{
                  if(!tmp.hayIzquierdo()){
                    tmp.izquierdo = nuevo;
                    nuevo.padre = tmp;
                    tamanio++;
                    return ;
                  }else{
                    tmp = tmp.izquierdo;
                  }
                }
            }//fin while
        }//fin else
    }//fin metodo

    /**
     * Elimina un elemento. Si el elemento no está en el árbol, no hace nada; si
     * está varias veces, elimina el primero que encuentre (in-order). El árbol
     * conserva su orden in-order.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
      Nodo e = busca(elemento);
        if (e != null){
          if (esHoja(e)) {
            Nodo p = e.padre;
            if(p.derecho == e)
              p.derecho = null;
            if (p.izquierdo == e)
              p.izquierdo =null;
            e = null;
            tamanio--;
            return ;
          }
          Nodo<T> minimo = (e.derecho != null)? minimo(e.derecho): null;
          if(minimo != null){
            e.elemento = minimo.elemento; //minimo.padre.izquierdo = minimo.elemento;
            if(minimo.padre.derecho == minimo){
                minimo.padre.derecho = null;
              }else{
                minimo.padre.izquierdo = null;
              }
            }else{
              Nodo<T> maximo = (e.izquierdo != null)? maximo(e.izquierdo):null;
              e.elemento = maximo.elemento; //minimo.padre.izquierdo = minimo.elemento;
              if(maximo.padre.izquierdo == maximo){
                maximo.padre.izquierdo = null;
              }else{
                maximo.padre.derecho = null;
              }
            }
            tamanio--;
          }
    }

    private  Nodo<T> minimo(Nodo <T> nodo){
      if (nodo != null) {
        Nodo<T> tmp =  nodo;
        while(tmp.hayIzquierdo()){
            tmp = tmp.izquierdo;
        }
        return tmp;
      }
      return null;
    }
    private Nodo<T> maximo(Nodo<T> nodo){
      if (nodo != null) {
        Nodo<T> tmp = nodo;
        while(tmp.hayDerecho()){
          tmp = tmp.derecho;
        }
        return tmp;
      }
      return null;
    }

    private boolean esHoja(Nodo<T> h){
      return !h.hayDerecho() && !h.hayIzquierdo();
    }
    /**
     * Nos dice si un elemento está contenido en el arbol.
     * @param elemento el elemento que queremos verificar si está contenido en
     *                 la arbol.
     * @return <code>true</code> si el elemento está contenido en el arbol,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean contiene(T elemento){
        return busca(elemento) != null;
    }

    /**
     * Gira el árbol a la derecha sobre el nodo recibido. Si el nodo no
     * tiene hijo izquierdo, el método no hace nada.
     * @param nodo el nodo sobre el que vamos a girar.
     */
    public void giraDerecha(Nodo<T> nodo) {
      if(nodo != null && nodo.izquierdo != null){
            Nodo a = nodo.izquierdo;
            Nodo beta = a.derecho;
            Nodo padre = nodo.padre;
            a.padre = padre;
            a.derecho = nodo;
            nodo.padre = a;
            nodo.izquierdo = beta;
            if(beta != null){
                beta.padre = nodo;
            }
            if(padre == null){
                raiz = a;
            }else{
                if(padre.derecho == nodo){// == null
                    padre.derecho = a;
                }else{
                    padre.izquierdo = a;
                }
            }
        }
    }

    /**
     * Gira el árbol a la izquierda sobre el nodo recibido. Si el nodo no
     * tiene hijo derecho, el método no hace nada.
     * @param nodo el nodo sobre el que vamos a girar.
     */
    public void giraIzquierda(Nodo<T> nodo) {
      if(nodo != null && nodo.derecho != null){
            Nodo a = nodo.derecho;
            Nodo beta = a.izquierdo;
            Nodo padre = nodo.padre;
            a.padre = padre;
            a.izquierdo = nodo;
            nodo.padre = a;
            nodo.derecho = beta;
            if(beta != null){
                beta.padre = nodo;
            }
            if(padre == null){
                raiz = a;
            }else{
                if(padre.derecho == nodo){ //==null
                    padre.derecho = a;
                }else{
                    padre.izquierdo = a;
                }
            }
        }
    }


    /**
     * Regresa un iterador para iterar el árbol. El árbol se itera en orden.
     * @return un iterador para iterar el árbol.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

}
