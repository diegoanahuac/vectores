class Hanoi: 
    def __init__(self, n=3):
        self.discos = n

        #Crear las 3 torres
        self.torre1 = Pila()
        self.torre2 = Pila()
        self.torre3 = Pila()

        #Insertar los discos en la torre 1
        for i in range (n, 0, -1):
            self.torre1.push(i)

        self.mostrarTorres()

    def mostrarTorres(self):
        print("Torre 1:")
        self.torre1.mostrar()

        print("Torre 2:")
        self.torre2.mostrar()

        print("Torre 3:")
        self.torre3.mostrar()




