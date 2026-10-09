public class Nodo {
    Object dato;
    Nodo siguiente;

    Nodo (Object objeto){
        this(objeto, null);
    }

    Nodo (Object objeto, Nodo nodo){
        dato = objeto;
        siguiente = nodo;
    }
}