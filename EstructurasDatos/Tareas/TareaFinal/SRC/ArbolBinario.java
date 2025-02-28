import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Clase abstracta para modelar la estructura de datos Arbol Binario</p>
 * <p>Esta clase es genérica, es decir que es homogénea pero
 * puede tener elementos de cualquier tipo.</p>
 * <p>Puesto que todos los árboles binarios comparten algunas características similares,
 * esta clase sirve para modelarlas. Sin embargo no es lo suficientemente
 * específica para modelar algun árbol completamente. Por lo que la implementación
 * final depende de las clases concretas que hereden de ésta.</p>
 * @author Alejandro Hernández Mora <alejandrohmora@ciencias.unam.mx>
 * @version 1.0
 */

public abstract class ArbolBinario<T> implements Coleccionable<T> {
    /**
     * Clase interna protegida para nodos.
     */
    protected class Nodo<T> {

        /** El elemento del nodo. */
        public T elemento;
        /** Referencia a los nodos padre, e hijos. */
        public Nodo padre, izquierdo, derecho;

        /**
         * Constructor único que recibe un elemento.
         * @param elemento el elemento del nodo.
         */
        public Nodo(T elemento) {
	         this.elemento = elemento;
           padre = izquierdo = derecho = null;
        }

        /**
         * Nos dice si el nodo tiene un padre.
         * @return <tt>true</tt> si el nodo tiene padre,
         *         <tt>false</tt> en otro caso.
         */
        public boolean hayPadre() {
	         return padre != null;
        }
        public Nodo<T> getPadre(){
          return padre;
        }
        /**
         * Nos dice si el nodo tiene un izquierdo.
         * @return <tt>true</tt> si el nodo tiene izquierdo,
         *         <tt>false</tt> en otro caso.
         */
        public boolean hayIzquierdo() {
  	       return (izquierdo != null);
        }

        /**
         * Nos dice si el nodo tiene un derecho.
         * @return <tt>true</tt> si el nodo tiene derecho,
         *         <tt>false</tt> en otro caso.
         */
        public boolean hayDerecho() {
	         return (derecho !=  null);
        }

        /**
         * Regresa la altura del nodo.
         * @return la altura del nodo.
         */
        public int altura() {
	         if (!hayDerecho() && !hayIzquierdo()) {
             return 0;
           }
           int x = (hayIzquierdo())? (izquierdo).altura():0;
           int y = (hayDerecho())? (derecho).altura():0;
           return  (1+ Math.max(x,y)) ;
        }

        /**
         * Regresa el elemento al que apunta el nodo.
         * @return el elemento al que apunta el nodo.
         */
        public T get() {
  	       return elemento;
        }

        /**
         * Compara el nodo con otro objeto. La comparación es
         * <em>recursiva</em>. Las clases que extiendan {@link Nodo} deben
         * sobrecargar el método {@link Nodo#equals}.
         * @param o el objeto con el cual se comparará el nodo.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link Nodo}, su elemento es igual al elemento de éste
         *         nodo, y los descendientes de ambos son recursivamente
         *         iguales; <code>false</code> en otro caso.
         */
        @Override
        public boolean equals(Object o) {
          if (o == null || getClass() != o.getClass())
              return false;
              @SuppressWarnings("unchecked") Nodo nodo = (Nodo)o;
              return elemento.equals(nodo.elemento);



            //@SuppressWarnings("unchecked") Nodo n = (Nodo)o;
          //  return elemento.equals(nodo.elemento);
          }


        /**
         * Regresa una representación en cadena del nodo.
         * @return una representación en cadena del nodo.
         */
        public String toString() {
	         return elemento.toString();
         }
       }

    /** La raíz del árbol. */
    protected Nodo raiz;
    /** El número de elementos */
    protected int tamanio;

    /**
     * Constructor sin parámetros.
     */
    public ArbolBinario() {
      raiz = null;
      tamanio = 0;
    }

    /**
     * Construye un árbol binario a partir de una colección. El árbol binario
     * tendrá los mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol
     *        binario.
     */
    public ArbolBinario(Coleccionable<T> coleccion) {
	     for ( T e : coleccion  ) {
          this.agrega(e);
       }
    }

    /**
     * Construye un nuevo nodo, usando una instancia de {@link Nodo}. Para
     * crear nodos se debe utilizar este método en lugar del operador
     * <code>new</code>, para que las clases herederas de ésta puedan
     * sobrecargarlo y permitir que cada estructura de árbol binario utilice
     * distintos tipos de nodos.
     * @param elemento el elemento dentro del nodo.
     * @return un nuevo nodo con el elemento recibido dentro del mismo.
     */
    protected Nodo<T> nuevoNodo(T elemento) {
	     Nodo x = new Nodo(elemento);
       return x;
    }


    /**
     * Regresa la altura del árbol. La altura de un árbol es la altura de su
     * raíz.
     * @return la altura del árbol.
     */
    public int altura() {
       if (esVacio()) {
         return 0;
       }
       else {
          Nodo<T> x = raiz();
          int t = (x.hayIzquierdo())? (x.izquierdo).altura():0;
          int y = (x.hayDerecho())?(x.derecho).altura():0 ;
          return (1 + Math.max(t,y));
       }
    }

    /**
     * Regresa el número de elementos que se han agregado al árbol.
     * @return el número de elementos en el árbol.
     */
    @Override
    public int getTamanio() {
	     return tamanio;
    }

    /**
     * Nos dice si un elemento está en el árbol binario.
     * @param elemento el elemento que queremos comprobar si está en el árbol.
     * @return <code>true</code> si el elemento está en el árbol;
     *         <code>false</code> en otro caso.
     */
    @Override
    public boolean contiene(T elemento) {
      return busca(elemento) != null;
    }

    /**
     * Busca el nodo de un elemento en el árbol. Si no lo encuentra regresa
     * <tt>null</tt>.
     * @param elemento el elemento para buscar el nodo.
     * @return un nodo que contiene el elemento buscado si lo encuentra;
     *         <tt>null</tt> en otro caso.
     */
    public Nodo<T> busca(T elemento) {
  	    return buscaAux(elemento,raiz);
    }
    private Nodo<T> buscaAux(T elemento, Nodo<T> n){
      if ((n.elemento).equals(elemento)) {
        return n;
      }else {
        Nodo<T> izq = (n.izquierdo != null)? buscaAux(elemento,n.izquierdo): null ;
        Nodo<T> der = (n.derecho != null)? buscaAux(elemento, n.derecho): null;
        Nodo<T> respuesta = (izq != null)? izq:der;
        return respuesta;
      }
    }


    /**
     * Regresa el nodo que contiene la raíz del árbol.
     * @return el nodo que contiene la raíz del árbol.
     * @throws NoSuchElementException si el árbol es vacío.
     */
    public Nodo<T> raiz() {
	     if (esVacio()) {
         return null;
      }
      Nodo<T> n = raiz;
      if (raiz != null) {
        while(n.hayPadre()) {
          n = n.padre;
        }
      }

      return n;
    }

    /**
     * Nos dice si el árbol es vacío.
     * @return <code>true</code> si el árbol es vacío, <code>false</code> en
     *         otro caso.
     */
    @Override
    public boolean esVacio() {
      return raiz == null && tamanio == 0;
    }

    /**
     * Limpia el árbol de elementos, dejándolo vacío.
     */
    public void limpia() {
	     this.raiz = null;
       this.raiz.izquierdo = null;
       this.raiz.derecho = null;
       this.tamanio = 0;
    }


    /**
     * Regresa una Lista con el los elementos en inorden del árbol.
     * @return Lista con los elementos del arbol.
     */
    public Lista<T> inOrden(){
  	   Lista<T> l = new Lista<T>();
       if (!this.esVacio()) {
         inordenAux(this.raiz,l);
       }
     return l;
    }

    /**
     * Regresa una Lista con el los elementos en inorden del árbol.
     * @return Lista con los elementos del arbol.
     */

    public Lista<T> preOrden(){
      Lista<T> l = new Lista();
      if (!this.esVacio()) {
        preordenAux(this.raiz,l);
      }
      return l;
    }

    /**
     * Regresa una Lista con el los elementos en inorden del árbol.
     * @return Lista con los elementos del arbol.
     */
    public Lista<T> postOrden(){
      Lista<T> l = new Lista();
      if (!this.esVacio()) {
        postordenAux(this.raiz,l);
      }
      return l;
    }

    private void inordenAux(Nodo<T> n,Lista<T> l){
      if (n.hayIzquierdo()) {
        inordenAux(n.izquierdo,l);
      }
      l.agregar(n.elemento);
      if (n.hayDerecho()) {
        inordenAux(n.derecho,l);
      }
    }

    private void preordenAux(Nodo<T> n, Lista<T> l){
  	   l.agregar(n.elemento);
       if (n.hayIzquierdo()) {
         preordenAux(n.izquierdo,l);
       }
       if (n.hayDerecho()) {
         preordenAux(n.derecho,l);
       }
    }

    private void postordenAux(Nodo<T> n, Lista<T> l){

      if (n.hayIzquierdo()) {
        postordenAux(n.izquierdo,l);
      }
      if (n.hayDerecho()) {
        postordenAux(n.derecho,l);
      }
      l.agregar(n.elemento);
    }


    /**
     * Compara el árbol con un objeto.
     * @param o el objeto con el que queremos comparar el árbol.
     * @return <code>true</code> si el objeto recibido es un árbol binario y los
     *         árboles son iguales; <code>false</code> en otro caso.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        @SuppressWarnings("unchecked")
            ArbolBinario<T> arbol = (ArbolBinario<T>)o;

	           return true;
    }

    /**
     * Regresa una representación en cadena del árbol.
     * @return una representación en cadena del árbol.
     */
    @Override public String toString() {
        	if (raiz == null)
                    return "";

                boolean[] r = new boolean[altura()+1];
                for (int i = 0; i < altura()+1; i++)
                    r[i] = false;
                return cadena(raiz, 0, r);

    }

    private String cadena(Nodo v, int n, boolean[] r) {
        String s = v + "\n";
        r[n] = true;
        if (v.izquierdo != null && v.derecho != null) {
            s += dibujaEspacios(n, r);
            s += "├─›";
            s += cadena(v.izquierdo, n+1, r);
            s += dibujaEspacios(n, r);
            s += "└─»";
            r[n] = false;
            s += cadena(v.derecho, n+1, r);
        } else if (v.izquierdo != null) {
            s += dibujaEspacios(n, r);
            s += "└─›";
            r[n] = false;
            s += cadena(v.izquierdo, n+1, r);
        } else if (v.derecho != null) {
            s += dibujaEspacios(n, r);
            s += "└─»";
            r[n] = false;
            s += cadena(v.derecho, n+1, r);
        }
        return s;
    }


    private String dibujaEspacios(int n, boolean[] r) {
    	String s = "";
    	for (int i = 0; i < n; i++)
    	    if (r[i])
                    s += "│  ";
                else
                    s += "   ";
    	return s;
    }
}
