import Pila 

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
        print("\nTorre 1:",  end ="")
        self.torre1.mostrar()

        print("\nTorre 2:")
        self.torre2.mostrar()

        print("Torre 3:")
        self.torre3.mostrar()

    def hanoi(self, n, origen, destino, auxiliar):
        if n > 0 :
            self.hanoi(n-1, origen, auxiliar, destino)

            torreOrigen = self.obtenerTorre(origen)
            torreDestino = self.obtenerTorre(destino)

            disco = torreOrigen.pop() #Se extrae el disco de la torre origen
            torreDestino.push(disco) #Se inserta el disco en la torre destino

            self.mostrarTorres()

            self.hanoi(n-1, auxiliar, destino, origen)

    def obtenerTorre(self, num):
        if num == 1:
            return self.torre1
        if num == 2:
            return self.torre2
        if num == 3:
            return self.torre3






