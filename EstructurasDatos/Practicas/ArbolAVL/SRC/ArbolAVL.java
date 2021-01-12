/**
 * <p>Clase para árboles AVL.</p>
 *
 * <p>Un árbol AVL cumple que para cada uno de sus nodos, la diferencia entre
 * la áltura de sus subárboles izquierdo y derecho está entre -1 y 1.</p>
 */
public class ArbolAVL<T extends Comparable<T>>
    extends ArbolBinarioBusqueda<T> {

    /**
     * Clase interna protegida para nodos de árboles AVL. La única diferencia
     * con los nodos de árbol binario, es que tienen una variable de clase
     * para la altura del nodo.
     */
    protected class NodoAVL<T> extends ArbolBinario<T>.Nodo<T> {

        /** La altura del nodo. */
        public int altura;

        /**
         * Constructor único que recibe un elemento.
         * @param elemento el elemento del nodo.
         */
        public NodoAVL(T elemento){
          super(elemento);
          altura = super.altura();
        }
        public int daAltura(){
          altura = super.altura();
          return altura;
        }

        private boolean equals(NodoAVL v, NodoAVL v2){
            return v.equals(v2);
        }

        /**
         * Compara el nodo con otro objeto. La comparación es
         * <em>recursiva</em>.
         * @param o el objeto con el cual se comparará el nodo.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link NodoAVL}, su elemento es igual al elemento de éste
         *         nodo, los descendientes de ambos son recursivamente
         *         iguales, y las alturas son iguales; <code>false</code> en
         *         otro caso.
         */
        @Override public boolean equals(Object o) {
            if (o == null)
                return false;
            if (getClass() != o.getClass())
                return false;
            @SuppressWarnings("unchecked") NodoAVL nodo = (NodoAVL)o;
            // Aquí va tu código.
            Nodo<T> n = (Nodo<T>) o;
            if (this.elemento.equals(n.elemento) || this.altura() == n.altura()) {
              boolean r1 = false;
              boolean r2 = false;
              if (this.izquierdo != null) {
                r1 = izquierdo.equals(n.izquierdo);
              }else if (!n.hayIzquierdo()) {
                r1 = true;
              }
              if (this.derecho != null) {
                r2 = derecho.equals(n.derecho);
              }else if (!n.hayDerecho()) {
                r2  = true;
              }
              return (r1 || r2);
            }
            return false;
        }

    }

    public ArbolAVL(Coleccionable<T> coleccion){
	     super(coleccion);
    }
    public ArbolAVL(){
      super();
    }
//1 + Math.max(alturaIzquierda, alturaDerecho
    private void actualizaAltura(NodoAVL v){
       Nodo x = (v.hayIzquierdo())? nodoAVL(v.izquierdo): null;
       Nodo y = (v.hayDerecho())? nodoAVL(v.derecho): null;
	     int alturaIzquierda = (x != null)? getAltura(x) : 0;
       int alturaDerecho = (y != null)? getAltura(y):0;
       v.altura = (1 + (Math.max(alturaIzquierda,alturaDerecho)));
    }

    private void rebalancea(NodoAVL nodo){
      if (nodo != null) {
        NodoAVL x = (nodo.hayPadre())? nodoAVL(nodo.padre): null;
        int d = (nodo.hayIzquierdo()? getAltura(nodoAVL(nodo.izquierdo)):0 ) - (nodo.hayDerecho()? getAltura(nodoAVL(nodo.derecho)):0);
        if (d==0 || d==1 || d==-1) {
          actualizaAltura(nodo);
          if (x != null) {
            actualizaAltura(x);
          }
          rebalancea(x);
        }else if ( d > 1) {
          super.giraDerecha(nodo);
          actualizaAltura(nodo);
          actualizaAltura(nodoAVL(nodo.padre));
          if (x != null) {
            actualizaAltura(x);
          }
          rebalancea(x);
        }else if ( d < -1) {
          super.giraIzquierda(nodo);
          actualizaAltura(nodo);
          actualizaAltura(nodoAVL(nodo.padre));
          if (x != null) {
            actualizaAltura(x);
          }
          rebalancea(x);
        }
      }
    }

    /**
     * Agrega un nuevo elemento al árbol. El método invoca al método {@link
     * ArbolBinarioBusqueda#agrega}, y después balancea el árbol girándolo como
     * sea necesario. La complejidad en tiempo del método es <i>O</i>(log
     * <i>n</i>) garantizado.
     * @param elemento el elemento a agregar.
     */
    @Override public void agrega(T elemento) {

        NodoAVL<T> nuevo= new NodoAVL(elemento);

        super.agregaN(nuevo);
        //  actualizaAltura(nodoAVL(raiz()));
        rebalancea(nuevo);

    }

    /**
     * Elimina un elemento del árbol. El método elimina el nodo que contiene
     * el elemento, y gira el árbol como sea necesario para rebalancearlo. La
     * complejidad en tiempo del método es <i>O</i>(log <i>n</i>) garantizado.
     * @param elemento el elemento a eliminar del árbol.
     */
    @Override public void elimina(T elemento) {
      NodoAVL<T> nodo = nodoAVL(super.busca(elemento));
      super.eliminar(nodo);
      rebalancea(nodo);
    }
    /**
    private void eliminar(Nodo<T> nodo){
      if (!nodo.hayIzquierdo() && !nodo.hayDerecho()) {
        if (nodo.padre.izquierdo == nodo) {
          nodo.padre.izquierdo = null;
        }else {
          nodo.padre.derecho = null;
        }
      }else{
        Nodo<T> max = minimo(nodo);
        if (max == nodo) {
          if (nodo.padre.izquierdo == nodo) {
            nodo.padre.izquierdo = nodo.derecho;
            nodo.derecho.padre = nodo.padre;
          }else {
            nodo.padre.derecho = nodo.izquierdo;
            nodo.izquierdo.padre = nodo.padre;
          }
        }else {
          nodo.elemento = max.elemento;
          if (max.padre.izquierdo == nodo) {
            nodo.padre.izquierdo = max.izquierdo;
            if (max.izquierdo != null) {
              max.izquierdo.padre = max.padre;
            }
          }else {

          }
        }
      }

    }
*/
    /**
     * Regresa la altura del nodo AVL.
     * @param nodo el nodo del que queremos la altura.
     * @return la altura del nodo AVL.
     * @throws ClassCastException si el nodo no es instancia de {@link
     *         NodoAVL}.
     */
    public int getAltura(Nodo<T> nodo) {
	     return (nodoAVL(nodo).daAltura());
    }


    /**
     * Convierte el nodo (visto como instancia de {@link
     * Nodo}) en nodo (visto como instancia de {@link
     * NodoAVL}). Método auxililar para hacer este cast en un único
     * lugar.
     * @param nodo el nodo de árbol binario que queremos como nodo AVL.
     * @return el nodo recibido visto como nodo AVL.
     * @throws ClassCastException si el nodo no es instancia de {@link
     *         NodoAVL}.
     */
    protected NodoAVL nodoAVL(Nodo<T> nodo) {
        return (NodoAVL)nodo;
    }
}
