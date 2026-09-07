import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Comprueba si los parentesis de una expresion (aritmetica o logica) estan
 * correctamente balanceados, usando una pila como se describe en el ejercicio:
 *
 *  - Se crea una pila vacia que solo almacena parentesis de apertura.
 *  - Se recorre la expresion de izquierda a derecha.
 *  - Al encontrar '(' se apila (push).
 *  - Al encontrar ')' se desapila (pop); si la pila ya esta vacia en ese
 *    momento, hay un cierre sin apertura correspondiente -> no balanceado.
 *  - Al terminar de leer la expresion, esta balanceada si y solo si la pila
 *    quedo vacia.
 */
public class BalanceadorParentesis {

    public static boolean estaBalanceado(String expresion) {
        Deque<Character> pila = new ArrayDeque<>();

        for (char c : expresion.toCharArray()) {
            if (c == '(') {
                pila.push(c);
            } else if (c == ')') {
                if (pila.isEmpty()) {
                    return false;
                }
                pila.pop();
            }
        }

        return pila.isEmpty();
    }

    public static void main(String[] args) {
        String[] expresiones = {
            "x= (((y+2)*5)/2 - 5) * 10",
            "(()())",
            "((()())"
        };

        System.out.printf("%-30s %s%n", "Expresion", "Balanceados?");
        for (String expresion : expresiones) {
            boolean balanceado = estaBalanceado(expresion);
            System.out.printf("%-30s %s%n", expresion, balanceado ? "true" : "false");
        }
    }
}