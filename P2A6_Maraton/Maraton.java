import java.util.Random;

public class Maraton {

    static final int CORREDORES = 150;
    static final double DISTANCIA_KM = 42.195;
    static final int MIN_SEGUNDOS = 120 * 60;
    static final int MAX_SEGUNDOS = 300 * 60;
    static final String[] ALGORITMOS = {"Burbuja", "Seleccion", "Insercion", "Shell", "Quicksort"};

    static class Corredor {
        int dorsal;
        int tiempo; // en segundos

        Corredor(int dorsal, int tiempo) {
            this.dorsal = dorsal;
            this.tiempo = tiempo;
        }
    }

    // 7385 segundos -> "123:05"
    static String minSeg(int segundos) {
        return String.format("%d:%02d", segundos / 60, segundos % 60);
    }

    static Corredor[] generarCorredores(Random azar) {
        Corredor[] corredores = new Corredor[CORREDORES];
        for (int i = 0; i < CORREDORES; i++) {
            int tiempo = MIN_SEGUNDOS + azar.nextInt(MAX_SEGUNDOS - MIN_SEGUNDOS + 1);
            corredores[i] = new Corredor(i + 1, tiempo);
        }
        return corredores;
    }

    // ---------- Ordenamientos: de menor a mayor tiempo ----------

    static void ordenamientoBurbuja(Corredor[] a) {
        int limite = a.length;
        boolean intercambio = true;
        while (limite > 1 && intercambio) {
            intercambio = false;
            for (int j = 0; j < limite - 1; j++) {
                if (a[j].tiempo > a[j + 1].tiempo) {
                    Corredor aux = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = aux;
                    intercambio = true;
                }
            }
            limite--;
        }
    }

    static void ordenacionSeleccion(Corredor[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j].tiempo < a[menor].tiempo) {
                    menor = j;
                }
            }
            if (menor != i) {
                Corredor aux = a[i];
                a[i] = a[menor];
                a[menor] = aux;
            }
        }
    }

    static void ordenacionInsercion(Corredor[] a) {
        for (int i = 1; i < a.length; i++) {
            Corredor actual = a[i];
            int j = i - 1;
            while (j >= 0 && a[j].tiempo > actual.tiempo) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = actual;
        }
    }

    // Igual que en clase: el intervalo empieza en n/2 y se reduce a la mitad
    static void ordenacionShell(Corredor[] a) {
        int intervalo, i, j, k;
        int n = a.length;
        intervalo = n / 2;
        while (intervalo > 0) {
            for (i = intervalo; i < n; i++) {
                j = i - intervalo;
                while (j >= 0) {
                    k = j + intervalo;
                    if (a[j].tiempo <= a[k].tiempo) {
                        j = -1;
                    } else {
                        Corredor aux = a[j];
                        a[j] = a[k];
                        a[k] = aux;
                        j -= intervalo;
                    }
                }
            }
            intervalo = intervalo / 2;
        }
    }

    // Igual que en clase: el pivote es el elemento central
    static void quicksort(Corredor[] a, int primero, int ultimo) {
        int i, j, central;
        int pivote;
        central = (primero + ultimo) / 2;
        pivote = a[central].tiempo;
        i = primero;
        j = ultimo;
        do {
            while (a[i].tiempo < pivote) i++;
            while (a[j].tiempo > pivote) j--;
            if (i <= j) {
                Corredor aux = a[i];
                a[i] = a[j];
                a[j] = aux;
                i++;
                j--;
            }
        } while (i <= j);
        if (primero < j) quicksort(a, primero, j);
        if (i < ultimo) quicksort(a, i, ultimo);
    }

    static void ordenar(int algoritmo, Corredor[] a) {
        switch (algoritmo) {
            case 0: ordenamientoBurbuja(a); break;
            case 1: ordenacionSeleccion(a); break;
            case 2: ordenacionInsercion(a); break;
            case 3: ordenacionShell(a); break;
            default: quicksort(a, 0, a.length - 1);
        }
    }

    static boolean estaOrdenado(Corredor[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1].tiempo > a[i].tiempo) {
                return false;
            }
        }
        return true;
    }

    // Imprime los 150 corredores en columnas para que quepan en pantalla
    static void imprimirColumnas(Corredor[] a, int columnas, boolean conPosicion) {
        int filas = (a.length + columnas - 1) / columnas;
        for (int f = 0; f < filas; f++) {
            StringBuilder linea = new StringBuilder();
            for (int c = 0; c < columnas; c++) {
                int i = c * filas + f;
                if (i < a.length) {
                    if (conPosicion) {
                        linea.append(String.format("%4d. #%03d %7s  ", i + 1, a[i].dorsal, minSeg(a[i].tiempo)));
                    } else {
                        linea.append(String.format("#%03d %7s   ", a[i].dorsal, minSeg(a[i].tiempo)));
                    }
                }
            }
            System.out.println(linea.toString().replaceAll("\\s+$", ""));
        }
    }

    public static void main(String[] args) {
        // Con semilla (java Maraton 2026) salen los mismos tiempos; sin semilla cambian cada vez
        Random azar = args.length > 0 ? new Random(Long.parseLong(args[0])) : new Random();
        Corredor[] registro = generarCorredores(azar);

        System.out.println("==========================================================================");
        System.out.println("                    SIMULACION DE MARATON  -  42.195 km");
        System.out.println("==========================================================================");
        System.out.println("Corredores: " + CORREDORES + "    Tiempos aleatorios entre 120:00 y 300:00 (min:seg)");
        System.out.println();
        System.out.println("Tiempos registrados (por numero de dorsal):");
        imprimirColumnas(registro, 6, false);

        // Calentamiento: la JVM optimiza el codigo despues de usarlo,
        // asi que cada algoritmo se corre una vez antes de medirlo.
        for (int alg = 0; alg < ALGORITMOS.length; alg++) {
            ordenar(alg, registro.clone());
        }

        System.out.println();
        System.out.println("Tiempo de cada algoritmo (los mismos " + CORREDORES + " tiempos para todos):");
        long[] nanos = new long[ALGORITMOS.length];
        Corredor[] resultado = null;
        for (int alg = 0; alg < ALGORITMOS.length; alg++) {
            Corredor[] copia = registro.clone();
            long inicio = System.nanoTime();
            ordenar(alg, copia);
            nanos[alg] = System.nanoTime() - inicio;
            System.out.printf("  %-10s %9.4f ms   %s%n", ALGORITMOS[alg], nanos[alg] / 1_000_000.0,
                    estaOrdenado(copia) ? "ordenado correctamente" : "ERROR: no quedo ordenado");
            resultado = copia;
        }
        int masRapido = 0;
        for (int alg = 1; alg < ALGORITMOS.length; alg++) {
            if (nanos[alg] < nanos[masRapido]) {
                masRapido = alg;
            }
        }
        System.out.println("  Algoritmo mas rapido: " + ALGORITMOS[masRapido]);

        System.out.println();
        System.out.println("Posiciones finales (de menor a mayor tiempo):");
        imprimirColumnas(resultado, 5, true);

        System.out.println();
        System.out.println("Podio:");
        String[] lugares = {"1er lugar", "2do lugar", "3er lugar"};
        for (int p = 0; p < 3; p++) {
            Corredor c = resultado[p];
            double kmPorHora = DISTANCIA_KM / (c.tiempo / 3600.0);
            System.out.printf("  %s  Dorsal #%03d  %s  (ritmo %s min/km, %.2f km/h)%n", lugares[p], c.dorsal,
                    minSeg(c.tiempo), minSeg((int) Math.round(c.tiempo / DISTANCIA_KM)), kmPorHora);
        }
        System.out.println();
        System.out.println("Mejor tiempo: Dorsal #" + String.format("%03d", resultado[0].dorsal) + " con "
                + minSeg(resultado[0].tiempo));
    }
}
