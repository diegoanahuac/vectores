public class Arista {
    private String nombre;
    private int id;
    private Vertice extremo1;
    private Vertice extremo2;
    private boolean esBucle;

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
        esBucle = (extremo1 == extremo2);
    }

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

    public boolean esParalela(Arista otra) {
        if (this.id == otra.id) {
            return false;
        }
        boolean mismoOrden = (this.extremo1 == otra.extremo1 && this.extremo2 == otra.extremo2);
        boolean ordenInverso = (this.extremo1 == otra.extremo2 && this.extremo2 == otra.extremo1);
        return mismoOrden || ordenInverso;
    }

    public boolean incideEn(Vertice v) {
        return extremo1 == v || extremo2 == v;
    }

    @Override
    public String toString() {
        return nombre + ": " + extremosTexto();
    }

    public String extremosTexto() {
        if (esBucle) {
            return "{" + extremo1.getNombre() + "} [BUCLE]";
        }
        return "{" + extremo1.getNombre() + ", " + extremo2.getNombre() + "}";
    }
}
