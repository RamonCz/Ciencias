/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Alejandro Hernández Mora <alejandrohmora@ciencias.unam.mx>
 */
public class NodoDivision extends NodoOperador {

    /**
     *
     * @param izq
     * @param der
     */
    public NodoDivision(CompositeEA izq, CompositeEA der) {
        super(izq, der);
        precedence=1;
    }

    /**
     * La evaluación del nodo, divide la evaluación de los hijos izquierdo y
     * derecho.
     *
     * @return la división del hijo izquierdo entre el hijo derecho.
     */
    @Override
    public double evalua() {
        double d = der.evalua();
        if (Double.compare(0, d) == 0) {
            throw new ArithmeticException("Error: Dividir entre cero");
        }
        return izq.evalua() / d;
    }
}
