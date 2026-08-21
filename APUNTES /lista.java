public class lista {
    Nodo primerNodo;
    Nodo ultimoNodo;

    lista(){
        primerNodo = null;
        ultimoNodo = null;
    }

    public void insertarFinal(Object elemento){
        if (estaVacia()){
            primerNodo = ultimoNodo = new Nodo(elemento);
        } else {
            ultimoNodo = ultimoNodo.siguiente = new Nodo(elemento);
        }
    }

    public void imprimir (){
        if(!estaVacia()){
            Nodo actual = primerNodo;
            while (actual != null){
                System.out.println(actual.dato);
                actual = actual.siguiente;
            }
        }
    }

    boolean estaVacia(){
        return primerNodo == null;
    }
}