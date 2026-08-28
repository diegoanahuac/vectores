[15, 25, 35, 45]
['oveja negra', 'vaca fugaz', 'lombriz roja']

['spam', 2.0, 5, [10, 20]]


quesos = ['Oaxaca', 'Manchego', 'Americano']
numeros = [20, 200]
vacia = []
print(quesos, numeros, vacia)

print(quesos[0])

numeros = [45, 200]
numeros[1] = 99
print(numeros)


quesos = ['Oaxaca', 'Manchego', 'Americano']
'Oaxaca' in quesos
'Asadero' in quesos

#Recorriendo una lista
for queso in quesos:
    print(queso)

for i in range(len(numeros)):
    numeros [i] = numeros [i] * 2

#Operaciones de listas
a = [10, 20, 30]
b = [40, 50, 60]
c = a + b
print (c)

[0] * 4
[1,2,3] * 3

#Rebanado de listas 
t = ['g', 'h', 'i', 'j', 'k', 'l']
t[1:3]

t[:4]

t[3:]

t[:]

t = ['g', 'h', 'i', 'j', 'k', 'l']
t[1:3] = ['p', 'q']
print(t)

#Métodos de listas
t = ['g', 'h', 'i']
t.append('j')
print(t)

t1 = ['g', 'h', 'i']
t2 = ['j', 'k']
t1.extend(t2)
print(t1)

t = ['j', 'i', 'k', 'h', 'g']
t.sort()
print(t)

#Eliminando elementos 

t = ['g', 'h', 'i']
x = t.pop(1)
print(t)
print(x)

t = ['g', 'h', 'i']
del t[1]
print(t)

t = ['g', 'h', 'i']
t.remove('h')
print(t)

t = ['g', 'h', 'i', 'j', 'k', 'l']
del t[1:5]
print(t)

#Listas y funciones 
nums = [7, 52, 18, 3, 61, 24]
print(len(nums))

print(max(nums))

print(min(nums))

print(sum(nums))

print(sum(nums)/len(nums))

#Listas y cadenas  

s = 'coco'
t = list(s)
print(t)

s = 'caminando por el bosque'
t = s.split()
print(t)
print(t[2])


s = 'coco-coco-coco'
delimiter = '-'
s.split(delimiter)

t = ['caminando', 'por', 'el', 'bosque']
delimiter = ' '
delimiter.join(t)

#Objetos y valores
a = 'mango'
b = 'mango'
a is b


a = [2, 4, 6]
b = [2, 4, 6]
a is b


#Alias
a = [2, 4, 6]
b = a
b is a


b[0] = 17
print(a)

#Listas como argumentos
def remover_primero(t):
    del t[0]

letras = ['g', 'h', 'i']
remover_primero(letras)
print(letras)

t1 = [6, 7]
t2 = t1.append(8)
print(t1)
print(t2)


t3 = t1 + [8]
print(t3)
t2 is t3


def mal_eliminar_primero(t):
    t = t[1:]              # ¡EQUIVOCADO!

def cola(t):
    return t[1:]

letras = ['g', 'h', 'i']
resto = cola(letras)
print(resto)
