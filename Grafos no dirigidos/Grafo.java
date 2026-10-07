import java.util.ArrayList;

public class Grafo {
    private String nombre;
    private ArrayList<Vertice> vertices;
    private ArrayList<Arista> aristas;
    private int gradoTotal;

    public Grafo() {
        nombre = "";
        vertices = new ArrayList<>();
        aristas = new ArrayList<>();
        gradoTotal = 0;
    }

    public Grafo(String nombre) {
        this.nombre = nombre;
        vertices = new ArrayList<>();
        aristas = new ArrayList<>();
        gradoTotal = 0;
    }

    public void agregarVertice(Vertice v) {
        vertices.add(v);
    }

    public void agregarArista(Arista a) {
        aristas.add(a);
    }

    public int calcularGrado(Vertice v) {
        int grado = 0;
        for (Arista a : aristas) {
            if (a.esBucle() && a.getExtremo1() == v) {
                grado += 2;
            } else if (!a.esBucle() && a.incideEn(v)) {
                grado += 1;
            }
        }
        v.setGrado(grado);
        v.setEsAislado(grado == 0);
        return grado;
    }

    public int calcularGradoTotal() {
        gradoTotal = 0;
        for (Vertice v : vertices) {
            gradoTotal += calcularGrado(v);
        }
        return gradoTotal;
    }

    public ArrayList<Vertice> obtenerAdyacentes(Vertice v) {
        ArrayList<Vertice> adyacentes = new ArrayList<>();
        for (Arista a : aristas) {
            if (!a.incideEn(v)) {
                continue;
            }
            Vertice otro;
            if (a.esBucle()) {
                otro = v;
            } else if (a.getExtremo1() == v) {
                otro = a.getExtremo2();
            } else {
                otro = a.getExtremo1();
            }
            if (!adyacentes.contains(otro)) {
                adyacentes.add(otro);
            }
        }
        return adyacentes;
    }

    public ArrayList<Arista> obtenerAristasIncidentes(Vertice v) {
        ArrayList<Arista> incidentes = new ArrayList<>();
        for (Arista a : aristas) {
            if (a.incideEn(v)) {
                incidentes.add(a);
            }
        }
        return incidentes;
    }

    public ArrayList<Arista> obtenerAristasAdyacentes(Arista a) {
        ArrayList<Arista> adyacentes = new ArrayList<>();
        for (Arista b : aristas) {
            if (b != a && (b.incideEn(a.getExtremo1()) || b.incideEn(a.getExtremo2()))) {
                adyacentes.add(b);
            }
        }
        return adyacentes;
    }

    public ArrayList<Arista> obtenerBucles() {
        ArrayList<Arista> bucles = new ArrayList<>();
        for (Arista a : aristas) {
            if (a.esBucle()) {
                bucles.add(a);
            }
        }
        return bucles;
    }

    public ArrayList<String> obtenerParalelas() {
        ArrayList<String> paralelas = new ArrayList<>();
        for (int i = 0; i < aristas.size(); i++) {
            for (int j = i + 1; j < aristas.size(); j++) {
                Arista a = aristas.get(i);
                Arista b = aristas.get(j);
                if (a.esParalela(b)) {
                    paralelas.add("{" + a.getNombre() + ", " + b.getNombre() + "}");
                }
            }
        }
        return paralelas;
    }

    public ArrayList<Vertice> obtenerVerticesAislados() {
        ArrayList<Vertice> aislados = new ArrayList<>();
        for (Vertice v : vertices) {
            if (v.esAislado()) {
                aislados.add(v);
            }
        }
        return aislados;
    }

    public boolean verificarTeoremaSaludo() {
        return calcularGradoTotal() == 2 * aristas.size();
    }

    public boolean puedeExistirGrafo(int[] grados) {
        int suma = 0;
        for (int g : grados) {
            if (g < 0) {
                return false;
            }
            suma += g;
        }
        return suma % 2 == 0;
    }

    public void mostrarTablaExtremos() {
        System.out.println("\n--- Tabla Punto Extremo - Arista ---");
        System.out.printf("| %-8s | %-22s |%n", "Arista", "Punto(s) Extremo(s)");
        System.out.println("|----------|------------------------|");
        for (Arista a : aristas) {
            System.out.printf("| %-8s | %-22s |%n", a.getNombre(), a.extremosTexto());
        }
    }

    public void mostrarAnalisisCompleto() {
        System.out.println("===== Análisis de " + this + " =====");

        mostrarTablaExtremos();

        calcularGradoTotal();
        System.out.println("\n--- Grados ---");
        for (Vertice v : vertices) {
            System.out.println(v);
        }

        System.out.println("\n--- Adyacencia e incidencia ---");
        for (Vertice v : vertices) {
            System.out.println(v.getNombre() + " -> adyacentes: " + nombres(obtenerAdyacentes(v))
                    + " | aristas incidentes: " + nombresAristas(obtenerAristasIncidentes(v)));
        }
        for (Arista a : aristas) {
            System.out.println(a.getNombre() + " -> aristas adyacentes: " + nombresAristas(obtenerAristasAdyacentes(a)));
        }

        System.out.println("\n--- Terminología ---");
        System.out.println("Bucles: " + nombresAristas(obtenerBucles()));
        System.out.println("Aristas paralelas: " + obtenerParalelas());
        System.out.println("Vértices aislados: " + nombres(obtenerVerticesAislados()));

        System.out.println("\n--- Teorema del Saludo de Mano ---");
        System.out.println("Grado total = " + gradoTotal + ", 2 x |E| = " + (2 * aristas.size()));
        System.out.println("¿Se cumple? " + verificarTeoremaSaludo());
    }

    private String nombres(ArrayList<Vertice> lista) {
        ArrayList<String> n = new ArrayList<>();
        for (Vertice v : lista) {
            n.add(v.getNombre());
        }
        return n.toString();
    }

    private String nombresAristas(ArrayList<Arista> lista) {
        ArrayList<String> n = new ArrayList<>();
        for (Arista a : lista) {
            n.add(a.getNombre());
        }
        return n.toString();
    }

    @Override
    public String toString() {
        return "Grafo " + nombre + ": |V| = " + vertices.size() + ", |E| = " + aristas.size();
    }

    public static void main(String[] args) {
        Grafo g = new Grafo("G");

        Vertice v1 = new Vertice("v1", 1);
        Vertice v2 = new Vertice("v2", 2);
        Vertice v3 = new Vertice("v3", 3);
        Vertice v4 = new Vertice("v4", 4);
        g.agregarVertice(v1);
        g.agregarVertice(v2);
        g.agregarVertice(v3);
        g.agregarVertice(v4);

        g.agregarArista(new Arista("e1", 1, v1, v2));
        g.agregarArista(new Arista("e2", 2, v1, v3));
        g.agregarArista(new Arista("e3", 3, v1, v3));
        g.agregarArista(new Arista("e4", 4, v2, v3));
        g.agregarArista(new Arista("e5", 5, v3, v3));

        g.mostrarAnalisisCompleto();

        System.out.println("\n--- ¿Puede existir un grafo con estos grados? ---");
        System.out.println("{3, 2, 5, 0}: " + g.puedeExistirGrafo(new int[]{3, 2, 5, 0}));
        System.out.println("{3, 2, 2}:    " + g.puedeExistirGrafo(new int[]{3, 2, 2}));
    }
}
