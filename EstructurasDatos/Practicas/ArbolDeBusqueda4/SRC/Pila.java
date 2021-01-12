import java.util.Iterator;
/**
 * Clase para pilas genéricas.
 */
public class Pila<T> implements Coleccionable<T>{

		private class Nodo {
        /** El elemento del nodo. */
        public T elemento;
        /** El siguiente nodo. */
        public Nodo siguiente;

        /**
         * Construye un nodo con un elemento.
         * @param elemento el elemento del nodo.
         */
        public Nodo(T elemento) {
            this.elemento = elemento;
        }
				public T getElemento(){
					return elemento;
				}
    }

		private class IteradorPila<T> implements Iterator<T> {
        public Nodo siguiente;

        public IteradorPila() {
					siguiente = tope;
        }

        /* Nos dice si hay un elemento siguiente. */
				@Override
        public boolean hasNext() {
						return siguiente != null ;
        }

        /* Nos da el elemento siguiente. */
				@Override
        public T next() {
          	if (hasNext()) {
          		T elem = (T) siguiente.elemento;
							siguiente = siguiente.siguiente;
							return elem;
          	}
						return null;
        }
				@Override
				public void remove() {
            Iterator.super.remove();
        }

    }
		private Nodo tope;
		private int elementos;

		public Pila(){
			tope = null;
			elementos=0;
		}
		public Pila(T[] elementos){
			this.elementos = 0;
			for ( T e: elementos) {
				this.agrega(e);
			}
		}
		public Pila(Coleccionable<T> elementos){
			this.elementos = 0;
			for ( T e : elementos ) {
				this.agrega(e);
			}
		}

    /**
     * Agrega un elemento al tope de la pila.
     * @param elemento el elemento a agregar.
     */
    public void push(T elemento) {
    	this.agrega(elemento);
    }
    /**
     * Elimina el elemento del tope de la pila y lo regresa.
     * @return el elemento en el tope de la pila.
     */
    public T pop() {
			if (tope != null) {
				T e = tope.elemento;
				tope = tope.siguiente;
				elementos--;
				return e;
			}
			return null;
    }
		/**
     * Nos permite ver el elemento en el tope de la pila
     * @return el elemento en un extremo de la estructura.
     */
		public T peek(){
			if (tope != null) {
				return tope.elemento;
			}
			return null;
		}

		/**
     * Agrega un elemento a la pila.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agrega(T elemento){
			Nodo n = new Nodo(elemento);
			n.siguiente = tope;
			tope = n;
			elementos++;
		}
		/**
     * Nos dice si un elemento está contenido en la pila.
     * @param elemento el elemento que queremos verificar si está contenido en
     *                 la pila.
     * @return <code>true</code> si el elemento está contenido en la pila,
     *         <code>false</code> en otro caso.
     */
		@Override
    public boolean contiene(T elemento){
			for (T e  : this ) {
				if (e.equals(elemento)) {
					return true;
				}
			}
			return false;
		}

		/**
     * Elimina un elemento de la pila.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(T elemento){
			Nodo n = tope;
			if (!this.esVacio()) {
				while(n != null ) {
					if (tope.elemento.equals(elemento)) {
						tope.siguiente = tope.siguiente;
						elementos--;
					}else {
						if (n.siguiente.elemento.equals(elemento)) {
							n.siguiente = n.siguiente.siguiente;
							elementos--;
						}
						n = n.siguiente;
					}
				}
			}

		}
		/**
     * Nos dice si la pila está vacía.
     * @return <tt>true</tt> si la pila no tiene elementos,
     *         <tt>false</tt> en otro caso.
     */
		@Override
    public boolean esVacio() {
				return tope == null;
    }

		/**
     * Regresa el número de elementos en la pila.
     * @return el número de elementos en la pila.
     */
		@Override
    public int getTamanio(){
			return elementos;
		}

		@Override
		public Iterator<T> iterator(){
			return new IteradorPila();
		}

		public String toString(){
      String s = "[";
			Nodo aux = tope;
      while(aux != null ) {
        s += String.format("%s,",aux.getElemento());
				aux = aux.siguiente;
      }
      s+=String.format("]");
      return s;
    }

}
