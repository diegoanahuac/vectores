class Vector: 

    def __init__(self, tamaño):
        self.tamaño = tamaño
        self.elementos = [0] * tamaño  
        self.cantidad = 0

    def recorrer (self):
        for i in range(self.cantidad):
            print(self.elementos[i])


    def insertar(self, elemento):
        if self.cantidad < self.tamaño:
            self.elementos[self.cantidad] = elemento
            self.cantidad += 1
        else:
            print("El vector está lleno. No se puede insertar el elemento.")

    def eliminar(self, elemento):
        for i in range(self.cantidad):
            if self.elementos[i] == elemento:
                for j in range(i, self.cantidad - 1):
                    self.elementos[j] = self.elementos[j + 1]
                self.elementos[self.cantidad - 1] = 0  
                self.cantidad -= 1
                return
        print("El elemento no se encontró en el vector.")

    def ordenarAscendente(self):
        for i in range(self.cantidad):
            for j in range(i + 1, self.cantidad):
                if self.elementos[i] > self.elementos[j]:
                    self.elementos[i], self.elementos[j] = self.elementos[j], self.elementos[i]

    def ordenarDescendente(self):
        for i in range(self.cantidad):
            for j in range(i + 1, self.cantidad):
                if self.elementos[i] < self.elementos[j]:
                    self.elementos[i], self.elementos[j] = self.elementos[j], self.elementos[i]

    def buscar (self, elemento):
        for i in range(self.cantidad):
            if self.elementos[i] == elemento:
                return i  
        return -1
    
    v = vector(5)

    insertar(10)
    



    

