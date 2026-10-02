import java.util.Scanner;

public class ArbolBinario {

    class Nodo {
        int info;
        Nodo izq, der;
    }

    Nodo raiz;

    public ArbolBinario() {
        raiz = null;
    }

    public void insertar(int info) {
        Nodo nuevo;
        nuevo = new Nodo();
        nuevo.info = info;
        nuevo.izq = null;
        nuevo.der = null;
        if (raiz == null) {
            raiz = nuevo;
        } else {
            Nodo anterior = null;
            Nodo actual = raiz;
            while (actual != null) {
                anterior = actual;
                if (info < actual.info) {
                    actual = actual.izq;
                } else {
                    actual = actual.der;
                }
            }
            if (info < anterior.info) {
                anterior.izq = nuevo;
            } else {
                anterior.der = nuevo;
            }
        }
    }

    public void preorden(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.info + " ");
            preorden(nodo.izq);
            preorden(nodo.der);
        }
    }

    public void inorden(Nodo nodo) {
        if (nodo != null) {
            inorden(nodo.izq);
            System.out.print(nodo.info + " ");
            inorden(nodo.der);
        }
    }

    public void postorden(Nodo nodo) {
        if (nodo != null) {
            postorden(nodo.izq);
            postorden(nodo.der);
            System.out.print(nodo.info + " ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArbolBinario arbol = new ArbolBinario();
        System.out.print("Cuantos valores quieres insertar? ");
        int n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            System.out.print("Valor " + i + ": ");
            arbol.insertar(sc.nextInt());
        }
        sc.close();
        System.out.print("Preorden: ");
        arbol.preorden(arbol.raiz);
        System.out.println();
        System.out.print("Inorden: ");
        arbol.inorden(arbol.raiz);
        System.out.println();
        System.out.print("Postorden: ");
        arbol.postorden(arbol.raiz);
        System.out.println();
    }
}
