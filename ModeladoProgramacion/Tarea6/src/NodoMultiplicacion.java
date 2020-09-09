/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Alejandro Hernández Mora <alejandrohmora@ciencias.unam.mx>
 */
public class NodoMultiplicacion extends NodoOperador {

    /**
     *
     * @param izq
     * @param der
     */
    public NodoMultiplicacion(CompositeEA izq, CompositeEA der) {
        super(izq, der);
        precedence=1;
    }

    /**
     *
     * @return
     */
    @Override
    public double evalua() {
        return izq.evalua() * der.evalua();
    }

}
