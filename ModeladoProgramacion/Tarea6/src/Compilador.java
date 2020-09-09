import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Clase diseñada para realizar la compilación del lenguaje de la calculadora.
 * Esta clase provee de un método que hace el análisis léxico y un método que
 * devuelve el árbol de análisis sintáctico.
 *
 * @author Alejandro Hernández Mora <alejandrohmora@ciencias.unam.mx>
 */
public class Compilador {

    /**
     * Método que hace el análisis léxico del lenguaje de la calculadora.
     *
     * @param cadena La expresión aritmética a evaluar.
     * @return Un conjunto de tokens encapsulados en un objeto de la clase
     * {@link StringTokenizer}
     */
    public StringTokenizer analisisLexico(String cadena) {
        cadena = cadena.replace(" ", "");
        StringTokenizer tokenizer = new StringTokenizer(cadena, "()\\+\\*\\-\\/\\s\\c\\t\\r", true);
        return tokenizer;
    }

    /**
     * Método que devuelve un árbol de análisis sintáctico
     *
     * @param tokenizer El objeto con el conjunto de tokens a analizar.
     * @return Un nodo de la clase {@link CompositeEA} que es la raíz del árbol
     * de análisis sintáctico.
     * @throws SyntaxErrorException En caso de que la expresión esté mal formada
     */
    public CompositeEA arbolDeAnalisisSintactico(StringTokenizer tokenizer) throws SyntaxErrorException {
        if (tokenizer == null) {
            return null;
        }

        Stack<NodoOperador> operadores = new Stack<>();
        Stack<CompositeEA> salida = new Stack<>();

        boolean anteriorEsOperador = true;
        while (tokenizer.hasMoreTokens()) {
            CompositeEA n;
            NodoOperador no;
            String actual = tokenizer.nextToken();


            if (actual.equals(")")) {
                casoParentesisDerecho(operadores, salida);
                anteriorEsOperador = true;
            } else {
                try {
                    n = new NodoValor(Double.parseDouble(actual));
                    salida.push(n);
                    anteriorEsOperador = false;
                } catch (NumberFormatException nfe) {

                    no = NodoOperador.factoryMethodOperadorNuevo(actual, anteriorEsOperador);

                    if (no instanceof NodoParentesis) {
                        anteriorEsOperador = true;
                        operadores.push(no);
                    } else {
                        casoOperador(operadores, salida, no);
                        anteriorEsOperador = true;
                    }
                }
            }
        }

        while (!operadores.empty()) {
            NodoOperador top = operadores.pop();
            if (top instanceof NodoParentesis) {
                throw new SyntaxErrorException("Error en el balanceo de parentesis");
            } else {
                popIntoOutput(top, salida);
            }
        }
        return salida.pop();
    }

    private void casoOperador(Stack<NodoOperador> operadores,
            Stack<CompositeEA> salida, NodoOperador no) throws SyntaxErrorException {
        while (!operadores.empty()) {
            NodoOperador top = operadores.pop();
            if ((top.getPrecedence() <= no.getPrecedence() || (top instanceof NodoParentesis))
                    && top.getPrecedence() != 3) {
                operadores.push(top);
                break;
            } else {
                popIntoOutput(top, salida);
            }
        }
        operadores.push(no);
    }

    private void popIntoOutput(NodoOperador op, Stack<CompositeEA> salida) throws SyntaxErrorException {
        try {
            CompositeEA der = salida.pop();
            op.setDer(der);
            if (op.getPrecedence() < 3) {
                CompositeEA izq = salida.pop();
                op.setIzq(izq);
            }
            salida.push(op);

        } catch (EmptyStackException ese) {
            throw new SyntaxErrorException("Error de sintaxis, falta de operando");
        }
    }

    private void casoParentesisDerecho(Stack<NodoOperador> operadores,
            Stack<CompositeEA> salida) throws SyntaxErrorException {
        while (!operadores.empty()) {
            NodoOperador top = operadores.pop();

            if (!(top instanceof NodoParentesis)) {
                popIntoOutput(top, salida);
            } else {
                return;
            }
        }
        throw new SyntaxErrorException("Error en el balanceo de parentesis");

    }

}
