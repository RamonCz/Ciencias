/**
* @author Cruz Perez Ramon
* @version 2.0
*/
public class ListaCircular {
  private class Nodo {

	   protected Object elemento;
	    protected Nodo sgte;

  	/**
  	 * Constructor que recibe como parï¿½metro el valor que contendrï¿½ el nuevo Nodo.
  	 * Siguiente se inicializa como null.
  	 * @param valor - Elemento que tendrï¿½ el nuevo Nodo.
  	 */
  	Nodo(Object valor) {
  	    elemento = valor;
  	    sgte = null;
  	}

  	/**
  	 * Constructor que recibe como parï¿½metros el valor del nuevo Nodo y el siguiente
  	 * Nodo en la Lista que sucederï¿½ al nuevo Nodo.
  	 * @param valor - Elemento del nuevo Nodo.
  	 * @param n - Siguiente Nodo del nuevo Nodo
  	 */
  	Nodo(Object valor, Nodo n) {
  	    elemento = valor;
  	    sgte = n;
  	}

  	/**
  	 * Devuelve el valor de un Nodo
  	 * @return Object - El valor del Nodo
  	 */
  	public Object daElemento() {
  	    return elemento;
  	}

  	/**
  	 * Devuelve la referencia del siguiente Nodo
  	 * @return Nodo - El siguietne nodo
  	 */
  	public Nodo daSgte() {
  	    return sgte;
  	}

  	/**
  	 * Establece cuï¿½l serï¿½ el siguiente Nodo en la Lista
  	 * @param valor - El nuevo siguiente del Nodo
  	 */
  	public void ponSgte(Nodo n) {
  	    sgte = n;
  	}

  	/**
  	 * Establece un nuevo valor para el Nodo
  	 * @return Nodo - El nuevo elemento del Nodo
  	 */
  	public void ponElemento(Object valor) {
  	    elemento = valor;
  	}
  }



    /**
     * Nodo que representa el inicio de la lista
     */
    private Nodo inicio;
    /**
     * Nodo que representa el fin de la lista
     */
    private Nodo fin;
    /**
     * Numero que representa el tamaño de la lista
     */
    private int nElementos;

    /**
     * Constructor que no recibe parametros.
     * Inicializa a inicio con null
     * Inicializa nElementos con cero
     */
    public ListaCircular() {
	inicio = fin =null;
	nElementos=0;
    }
    public void vaciar(){
      inicio = fin =null;
    	nElementos=0;
    }
    /**
     * Metodo que determina si la lista esta o no vacia
     * @return boolean true si la lista esta vacia y false en otro caso
     */
    public boolean estaVacia() {
	return (inicio ==null);
    }

    /**
     * Inserta el primer elemento de la lista
     * @param obj - Elemento que sera insertado en la lista
     */
    public void insertar(Object obj) {
	if(obj!=null){
	    if(inicio == null){
		inicio= new Nodo(obj);
		fin = inicio;
		inicio.sgte= inicio;
		fin.sgte=inicio;
	    }else{
		fin.sgte= new Nodo(obj);
		fin = fin.sgte;
		fin.sgte= inicio;
	    }
	    nElementos++;
	}
    }

    /**
     * Devuelve el tamaño de la lista
     * @return int - valor del tamaño de la lista
     */
    public int getSize() {
	return nElementos;
    }

    /**
     * Método que devuelve un Iterador con todos los elementos de la Lista.
     * @return Iterador con todos los elementos de la Lista.
     */
    public java.util.Iterator elementos() {
        return new IteradorListaCircular();
    }


    private class IteradorListaCircular implements java.util.Iterator{

	private Nodo posicion;
	private Nodo posicionAnterior;

	public IteradorListaCircular(){
	    posicion = inicio;
	    posicionAnterior= fin;
	}
        /**
         * Metodo que determina si el Iterador tiene o no elementos que aun falten por iterar
         * @return true - Si todavia hay elementos por iterar, -faslse, en otro caso.
         */

        @Override
        public boolean hasNext() {
	    return posicion != null;
        }

        /**
         * Metodo que obtiene el siguiente elemento en nuestro Iterador
         * @return Object - El siguiente elemento en el iterador
         */

        @Override
        public Object next() {
	    if(hasNext()){
		posicionAnterior = posicion;
		posicion = posicion.sgte;
		return posicion.elemento;
	    }
	    throw new java.util.NoSuchElementException("Ya no  hay mas elementos que explorar");
        }

        /**
         * Metodo para eliminar un elemento de Iterador
         */

        @Override
        public void remove() {
	    if(posicion == null){
		throw new IllegalStateException("No hay mas elementos por eliminar");
	    }else{
		if(nElementos==1){
		    posicion = null;
		    posicionAnterior=null;
		}else{
		    posicionAnterior.sgte=posicion.sgte;
		    posicion=posicionAnterior.sgte;
		}
		nElementos--;
	    }
        }
    }
}
