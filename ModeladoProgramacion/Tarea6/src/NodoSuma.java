/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Alejandro Hernández Mora <alejandrohmora@ciencias.unam.mx>
 */
public class NodoSuma extends NodoOperador {

    /**
     *
     * @param izq
     * @param der
     */
    public NodoSuma(CompositeEA izq, CompositeEA der) {
        super(izq, der);
        precedence=0;
    }

    /**
     * La evaluación del nodo, suma la evaluación de los hijos izquierdo y derecho
     * @return
     */
    @Override
    public double evalua() {
        return izq.evalua() + der.evalua();
    }
}
