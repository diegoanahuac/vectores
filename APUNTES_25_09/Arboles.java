class ArbolBinario {

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

    public void imprimirEntreOrden(Nodo reco) {
        if (reco != null) {
            imprimirEntreOrden(reco.izq);
            System.out.print(reco.info + " ");
            imprimirEntreOrden(reco.der);
        }
    }
}

public class Arboles {
    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        arbol.insertar(50);
        arbol.insertar(30);
        arbol.insertar(70);
        arbol.insertar(20);
        arbol.insertar(40);
        arbol.insertar(60);
        arbol.insertar(80);
        arbol.insertar(50);
        arbol.imprimirEntreOrden(arbol.raiz);
        System.out.println();
    }
}
