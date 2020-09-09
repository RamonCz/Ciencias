/**
*Clase hecha para obtener el sin
*@author Cruz Perez Ramon
**/
public class NodoSin extends NodoOperador {

    /**
     *
     * @param der
     */
    public NodoSin(CompositeEA der, CompositeEA izq) {
        super(izq,der);
        precedence=1;
    }

    /**
     * La evaluación del nodo, divide la evaluación de los hijos izquierdo y
     * derecho.
     *
     * @return el seno del hijo derecho  entre el hijo derecho.
     */
    @Override
    public double evalua() {

      double d = der.evalua();
    //  d = Math.toRadians(d);
      return  Math.sin(d);
    }
}
