#Arreglo (lista de postres)
postres = [
    {"nombre": "Pastel de chocolate", "ingredientes":["harina", "cacao", "huevo", "azucar","mantequilla"]},
    {"nombre": "Flan", "ingredientes":["leche", "huevo", "vainilla","azucar"]},
    {"nombre": "Gelatina", "ingredientes":["agua", "grenetina", "azucar", "saborizante" ]}
]

#Con el nombre del postre, imprime los ingredientes
def imprimir_ingredientes(nombre_postre):
    for postre in postres:
        if postre["nombre"].lower() == nombre_postre.lower():
            print(f"Ingredientes de {postre['nombre']}")
            for ingredientes in postre["ingredientes"]:
                print(f"- {ingredientes}")
            return 
    print(f"No se encontro el nombre del postre '{nombre_postre}'")

#Con el nombre del postre agrega nuevos ingredientes 
def agregar_ingrediente(nombre_postre, nuevo_ingrediente):
    for postre in postres:
        if postre["nombre"].lower() == nombre_postre.lower():
            postre["ingredientes"].append(nuevo_ingrediente)
            print(f"Se agrego el ingrediente '{nuevo_ingrediente}' a {postre['nombre']}" )
            return
    print(f"No se encontro el nombre del postre '{nombre_postre}'")


#Con el nombre del postre elimina un ingrediente
def eliminar_ingrediente(nombre_postre, ingrediente=None):
    for postre in postres:
        if postre["nombre"].lower() == nombre_postre.lower():
            if ingrediente is None:
                #Elimina todos los ingredientes
                postre["ingredientes"] = []
                print(f"Se eliminaron todos los ingredientes de {postre['nombre']}")
            else:
                if ingrediente in postre["ingredientes"]:
                    postre["ingredientes"].remove(ingrediente)
                    print(f"Se elimino '{ingrediente}' de {postre['nombre']}")
                else:
                    print (f"El ingrediente '{ingrediente}' no se encuentra en {postre['nombre']}")
            return
    print(f"No se encontro el nombre del postre '{nombre_postre}'")


# Ejemplos de uso
imprimir_ingredientes("Flan")
agregar_ingrediente("Flan", "canela")
imprimir_ingredientes("Flan")
eliminar_ingrediente("Flan", "vainilla")
eliminar_ingrediente("Flan")  # elimina todos
imprimir_ingredientes("Flan")