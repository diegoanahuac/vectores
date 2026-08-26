class Nodo:
    def __init__(self, dato):
        self.dato = dato
        self.siguiente = None
        self.anterior = None


class ListaCircularDoble:
    def __init__(self):
        self.cabeza = None

    def insertar(self, dato):
        nuevo = Nodo(dato)
        if not self.cabeza:
            self.cabeza = nuevo
            nuevo.siguiente = self.cabeza
            nuevo.anterior = self.cabeza
        else:
            ultimo = self.cabeza.anterior  
            ultimo.siguiente = nuevo
            nuevo.anterior = ultimo
            nuevo.siguiente = self.cabeza
            self.cabeza.anterior = nuevo

    def mostrar_adelante(self):
        if not self.cabeza:
            print("Lista vacía")
            return
        actual = self.cabeza
        while True:
            print(actual.dato, end=" -> ")
            actual = actual.siguiente
            if actual == self.cabeza:
                break
        print("(vuelve a la cabeza)")

    def mostrar_atras(self):
        if not self.cabeza:
            print("Lista vacía")
            return
        ultimo = self.cabeza.anterior
        actual = ultimo
        while True:
            print(actual.dato, end=" -> ")
            actual = actual.anterior
            if actual == ultimo:
                break
        print("(vuelve al último)")

    def brincar(self, inicio, saltos):
        """Brinca 'saltos' nodos hacia adelante (positivo) o hacia atrás (negativo)"""
        if not self.cabeza:
            print("Lista vacía")
            return None

        # Buscar el nodo de inicio
        actual = self.cabeza
        while actual.dato != inicio:
            actual = actual.siguiente
            if actual == self.cabeza:
                print("Nodo de inicio no encontrado")
                return None

        # Brincar hacia adelante o hacia atrás según el signo
        if saltos >= 0:
            for _ in range(saltos):
                actual = actual.siguiente
        else:
            for _ in range(abs(saltos)):
                actual = actual.anterior

        print(f"Desde '{inicio}' brincando {saltos} nodos llegas a: {actual.dato}")
        return actual.dato


# ---- Ejemplo de uso ----
lista = ListaCircularDoble()
for elemento in ["A", "B", "C", "D", "E"]:
    lista.insertar(elemento)

print("Recorrido hacia adelante:")
lista.mostrar_adelante()

print("\nRecorrido hacia atrás:")
lista.mostrar_atras()

print()
lista.brincar("A", 2)    # Adelante -> C
lista.brincar("A", -1)   # Atrás -> E (el último)
lista.brincar("D", 3)    # Da la vuelta -> B