import numpy as np

matrix_1 = np.array([
    [1,2,3],
    [4,5,6],
    [7,8,9]
])


matrix_2 = np.array([
    [22,38,6],
    [19,10,2],
    [8,3,43]
])

#Imprimir la matriz
print(matrix_1[1][2])

print(matrix_2[0][0])

#suma
sum = matrix_1 + matrix_2
print("Suma:\n", sum)

#resta
sub = matrix_2 - matrix_1
print("Resta:\n", sub)

#escalar
escalar = 3
mult_escalar = escalar * matrix_1
print("Mult Escalar:\n", mult_escalar)



