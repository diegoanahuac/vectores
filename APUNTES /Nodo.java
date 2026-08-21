public class nodo {
    Object dato;
    nodo siguiente;

    nodo (Object objeto){
        this(objeto,null);

    }

    nodo (Object objeto, nodo nodo){
        dato = objeto;
        siguiente = nodo;
    }
}
