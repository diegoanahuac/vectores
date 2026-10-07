/**
 * Clase Arista - Representa una arista (conexión) de un grafo.
 *
 * Conceptos reforzados:
 * - Arista como elemento de E(G) que conecta puntos extremos
 * - Bucle: arista con un solo punto extremo (extremo1 == extremo2)
 * - Aristas paralelas: dos aristas distintas con los mismos extremos
 * - Incidencia: una arista incide sobre cada uno de sus puntos extremos
 *
 * @author Diego Olea
 * @version 1.0
 * Periodo 202660
 */
public class Arista {

    // ============================
    // ATRIBUTOS
    // ============================
    private String nombre;     // Nombre de la arista (ej: "e1", "e2")
    private int id;            // Identificador numérico único
    private Vertice extremo1;  // Primer punto extremo
    private Vertice extremo2;  // Segundo punto extremo
    private boolean esBucle;   // true si extremo1 == extremo2

    // ============================
    // CONSTRUCTORES
    // ============================

    public Arista() {
        nombre = "";
        id = 0;
        extremo1 = null;
        extremo2 = null;
        esBucle = false;
    }

    public Arista(String nombre, int id, Vertice extremo1, Vertice extremo2) {
        this.nombre = nombre;
        this.id = id;
        this.extremo1 = extremo1;
        this.extremo2 = extremo2;
        esBucle = (extremo1 == extremo2); // bucle si ambos extremos son el mismo vértice
    }

    // ============================
    // GETTERS
    // ============================

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public Vertice getExtremo1() {
        return extremo1;
    }

    public Vertice getExtremo2() {
        return extremo2;
    }

    public boolean esBucle() {
        return esBucle;
    }

    // ============================
    // MÉTODOS DE LÓGICA
    // ============================

    /**
     * Dos aristas distintas son paralelas si tienen los mismos extremos.
     * {v1, v3} es igual a {v3, v1}.
     */
    public boolean esParalela(Arista otra) {
        if (this.id == otra.id) {
            return false; // es la misma arista
        }
        boolean mismoOrden = (this.extremo1 == otra.extremo1 && this.extremo2 == otra.extremo2);
        boolean ordenInverso = (this.extremo1 == otra.extremo2 && this.extremo2 == otra.extremo1);
        return mismoOrden || ordenInverso;
    }

    /**
     * La arista incide en v si v es uno de sus puntos extremos.
     */
    public boolean incideEn(Vertice v) {
        return extremo1 == v || extremo2 == v;
    }

    // ============================
    // MÉTODOS AUXILIARES
    // ============================

    /**
     * Formato: "e1: {v1, v2}" o "e6: {v5} [BUCLE]"
     */
    @Override
    public String toString() {
        return nombre + ": " + extremosTexto();
    }

    /**
     * Devuelve los extremos como texto: "{v1, v2}" o "{v5} [BUCLE]"
     */
    public String extremosTexto() {
        if (esBucle) {
            return "{" + extremo1.getNombre() + "} [BUCLE]";
        }
        return "{" + extremo1.getNombre() + ", " + extremo2.getNombre() + "}";
    }
}
