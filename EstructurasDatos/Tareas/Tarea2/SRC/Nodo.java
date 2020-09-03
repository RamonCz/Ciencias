
public class Nodo {

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

