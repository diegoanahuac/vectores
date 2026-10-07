public class Vertice {
    private String nombre;
    private int id;
    private int grado;
    private boolean esAislado;

    public Vertice() {
        nombre = "";
        id = 0;
        grado = 0;
        esAislado = true;
    }

    public Vertice(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
        grado = 0;
        esAislado = true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrado() {
        return grado;
    }

    public void setGrado(int grado) {
        this.grado = grado;
    }

    public boolean esAislado() {
        return esAislado;
    }

    public void setEsAislado(boolean esAislado) {
        this.esAislado = esAislado;
    }

    @Override
    public String toString() {
        String texto = nombre + " (grado: " + grado + ")";
        if (esAislado) {
            texto += " [AISLADO]";
        }
        return texto;
    }
}
