public class GrafosDirigidos {

    static char nombre(int i) {
        return (char) ('a' + i);
    }

    static void arista(int[][] m, char origen, char destino, int peso) {
        m[origen - 'a'][destino - 'a'] = peso;
    }

    static void unir(int[][] m, char x, char y) {
        m[x - 'a'][y - 'a'] = 1;
        m[y - 'a'][x - 'a'] = 1;
    }

    static void imprimirMatriz(int[][] m) {
        System.out.print("   ");
        for (int j = 0; j < m.length; j++) {
            System.out.printf("%3c", nombre(j));
        }
        System.out.println();
        for (int i = 0; i < m.length; i++) {
            System.out.printf("%3c", nombre(i));
            for (int j = 0; j < m.length; j++) {
                System.out.printf("%3d", m[i][j]);
            }
            System.out.println();
        }
    }

    static String camino(int[][] m, int origen, int destino) {
        int n = m.length;
        boolean[] visitado = new boolean[n];
        int[] padre = new int[n];
        int[] cola = new int[n];
        int inicio = 0;
        int fin = 0;

        cola[fin++] = origen;
        visitado[origen] = true;
        padre[origen] = -1;

        while (inicio < fin) {
            int actual = cola[inicio++];
            for (int j = 0; j < n; j++) {
                if (m[actual][j] == 1 && !visitado[j]) {
                    visitado[j] = true;
                    padre[j] = actual;
                    cola[fin++] = j;
                }
            }
        }

        if (!visitado[destino]) {
            return null;
        }
        String texto = "" + nombre(destino);
        int v = padre[destino];
        while (v != -1) {
            texto = nombre(v) + " -> " + texto;
            v = padre[v];
        }
        return texto;
    }

    static boolean conexa(int[][] m) {
        for (int j = 1; j < m.length; j++) {
            if (camino(m, 0, j) == null) {
                return false;
            }
        }
        return true;
    }

    static String buscarCiclo(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = i + 1; j < m.length; j++) {
                if (m[i][j] == 1) {
                    m[i][j] = 0;
                    m[j][i] = 0;
                    String regreso = camino(m, j, i);
                    m[i][j] = 1;
                    m[j][i] = 1;
                    if (regreso != null) {
                        return nombre(i) + " -> " + regreso;
                    }
                }
            }
        }
        return null;
    }

    static String idaYVuelta(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                if (m[i][j] == 1) {
                    return nombre(i) + " -> " + nombre(j) + " -> " + nombre(i);
                }
            }
        }
        return "No es posible";
    }

    static boolean completa(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                if (i != j && m[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    static String adyacentes(int[][] m) {
        String texto = "";
        for (int i = 0; i < m.length; i++) {
            for (int j = i + 1; j < m.length; j++) {
                if (m[i][j] == 1) {
                    texto += "{" + nombre(i) + ", " + nombre(j) + "} ";
                }
            }
        }
        return texto;
    }

    static int grado(int[][] m, int v) {
        int grado = 0;
        for (int j = 0; j < m.length; j++) {
            grado += m[v][j];
        }
        return grado;
    }

    static String siNo(boolean valor) {
        return valor ? "Sí" : "No";
    }

    static void analizar(String titulo, int[][] m) {
        System.out.println("\n--- Grafo " + titulo + " ---");
        imprimirMatriz(m);

        String ciclo = buscarCiclo(m);
        String ac = camino(m, 0, 2);

        System.out.println("Conectada / conexa: " + siNo(conexa(m)));
        System.out.println("Cíclica: " + siNo(ciclo != null));
        System.out.println("Completa: " + siNo(completa(m)));
        System.out.println("Vértices adyacentes: " + adyacentes(m));
        System.out.println("Camino de a a c: " + (ac == null ? "No es posible" : ac));
        System.out.println("Camino cerrado: " + (ciclo != null ? ciclo : idaYVuelta(m)));
        System.out.println("Camino simple de a a d: " + camino(m, 0, 3));

        System.out.print("Grados: ");
        for (int i = 0; i < m.length; i++) {
            System.out.print(nombre(i) + "=" + grado(m, i) + "  ");
        }
        System.out.println();
    }

    static void dibujar(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                if (m[i][j] == 1) {
                    if (i == j) {
                        System.out.println(nombre(i) + " -> " + nombre(i) + "   (bucle)");
                    } else if (m[j][i] == 1) {
                        if (i < j) {
                            System.out.println(nombre(i) + " <-> " + nombre(j));
                        }
                    } else {
                        System.out.println(nombre(i) + " -> " + nombre(j));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("===== EJERCICIO 1: MATRIZ DE ADYACENCIA =====");

        int[][] g1 = new int[6][6];
        arista(g1, 'a', 'b', 5);
        arista(g1, 'a', 'e', 8);
        arista(g1, 'b', 'd', 3);
        arista(g1, 'b', 'e', 5);
        arista(g1, 'b', 'f', 4);
        arista(g1, 'c', 'a', 2);
        arista(g1, 'c', 'd', 3);
        arista(g1, 'd', 'e', 5);
        arista(g1, 'd', 'f', 7);
        arista(g1, 'e', 'c', 6);
        System.out.println("\nGrafo 1 (con pesos)");
        imprimirMatriz(g1);

        int[][] g2 = new int[6][6];
        arista(g2, 'a', 'e', 1);
        arista(g2, 'b', 'a', 1);
        arista(g2, 'b', 'c', 1);
        arista(g2, 'b', 'd', 1);
        arista(g2, 'b', 'f', 1);
        arista(g2, 'd', 'c', 1);
        arista(g2, 'd', 'e', 1);
        arista(g2, 'e', 'f', 1);
        arista(g2, 'f', 'a', 1);
        arista(g2, 'f', 'd', 1);
        System.out.println("\nGrafo 2");
        imprimirMatriz(g2);

        System.out.println("\n===== EJERCICIO 2: PROPIEDADES =====");

        int[][] ga = new int[4][4];
        unir(ga, 'a', 'b');
        unir(ga, 'a', 'd');
        unir(ga, 'b', 'd');
        analizar("a)", ga);

        int[][] gb = new int[4][4];
        unir(gb, 'a', 'b');
        unir(gb, 'a', 'c');
        unir(gb, 'a', 'd');
        unir(gb, 'b', 'c');
        unir(gb, 'b', 'd');
        unir(gb, 'c', 'd');
        analizar("b)", gb);

        int[][] gc = new int[4][4];
        unir(gc, 'a', 'b');
        unir(gc, 'b', 'd');
        unir(gc, 'd', 'c');
        analizar("c)", gc);

        int[][] gd = new int[5][5];
        unir(gd, 'a', 'b');
        unir(gd, 'a', 'd');
        unir(gd, 'b', 'e');
        unir(gd, 'e', 'c');
        analizar("d)", gd);

        System.out.println("\n===== EJERCICIO 3: DIBUJAR EL GRAFO =====");

        int[][] g3 = {
            {1, 1, 1, 1, 1},
            {1, 0, 1, 1, 1},
            {1, 1, 0, 1, 0},
            {1, 1, 1, 0, 1},
            {1, 1, 1, 1, 0}
        };
        imprimirMatriz(g3);
        System.out.println("\nAristas del grafo:");
        dibujar(g3);
    }
}
