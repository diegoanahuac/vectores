class Nodo:
    """Nodo para la lista enlazada utilizada en la pila."""

    def __init__(self, valor, siguiente=None):
        self.elemento = valor
        self.sgte = siguiente

    def obtener_elemento(self):
        return self.elemento

    def obtener_sgte(self):
        return self.sgte


class Pila:
    """Implementación de una pila usando nodos enlazados."""

    def __init__(self):
        self.tope = None
        self.nDatos = 0

    def esta_vacia(self):
        return self.tope is None

    def vaciar(self):
        self.tope = None
        self.nDatos = 0

    def tamanio(self):
        return self.nDatos

    def top(self):
        return None if self.esta_vacia() else self.tope.elemento

    def pop(self):
        if self.esta_vacia():
            return None

        dato = self.tope.elemento
        self.tope = self.tope.sgte
        self.nDatos -= 1
        return dato

    def push(self, x):
        self.tope = Nodo(x, self.tope)
        self.nDatos += 1

    def mostrar(self):
        actual = self.tope

        while actual is not None:
            print(actual.elemento, end=" ")
            actual = actual.sgte