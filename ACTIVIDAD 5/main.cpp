#include <iostream>
using namespace std;

bool esPar(int n);
bool esImpar(int n);
int sumaCuadrados(int n);
double capitalFinal(double m, double x, int n);

bool esPar(int n){
    if (n==0)
        return true;
    else
        return esImpar(n-1);
}

bool esImpar(int n){
    if (n==0)
        return false;
    else
        return esPar(n-1);
}

// Ejercicio 1: suma de los n primeros numeros al cuadrado
int sumaCuadrados(int n){
    if (n==0)
        return 0;
    else
        return n*n + sumaCuadrados(n-1);
}

// Ejercicio 2: capital final con interes
double capitalFinal(double m, double x, int n){
    if (n==0)
        return m;
    else
        return capitalFinal(m + m*(x/100), x, n-1);
}

int main(){
    int num;
    cout << "Ingrese un numero entero para probar esPar/esImpar: ";
    cin >> num;
    if (esPar(num))
        cout << num << " es par." << endl;
    else
        cout << num << " es impar." << endl;

    int n;
    cout << "\nIngrese un numero entero positivo (<=50): ";
    cin >> n;
    cout << "La suma de los " << n << " primeros numeros al cuadrado es: "
         << sumaCuadrados(n) << endl;

    double m, x;
    int anios;
    cout << "\nIngrese el capital inicial: ";
    cin >> m;
    cout << "Ingrese el porcentaje de interes anual (ej. 5 para 5%): ";
    cin >> x;
    cout << "Ingrese el numero de anios: ";
    cin >> anios;
    cout << "El capital final despues de " << anios << " anios es: "
         << capitalFinal(m, x, anios) << endl;

    return 0;
}