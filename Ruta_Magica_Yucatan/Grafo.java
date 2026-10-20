import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Grafo {
    private Map<String, List<Arista>> adyacencia;

    public Grafo() {
        adyacencia = new LinkedHashMap<>();
    }

    public void agregarPueblo(String pueblo) {
        adyacencia.putIfAbsent(pueblo, new ArrayList<>());
    }

    public void agregarCarretera(String a, String b, double km) {
        agregarPueblo(a);
        agregarPueblo(b);
        adyacencia.get(a).add(new Arista(b, km));
        adyacencia.get(b).add(new Arista(a, km));
    }

    public List<Arista> getVecinos(String pueblo) {
        return adyacencia.getOrDefault(pueblo, new ArrayList<>());
    }

    public List<String> getPueblos() {
        return new ArrayList<>(adyacencia.keySet());
    }

    public boolean existe(String pueblo) {
        return adyacencia.containsKey(pueblo);
    }

    public static Grafo desdeArchivo(String ruta) throws IOException {
        Grafo grafo = new Grafo();
        try (BufferedReader lector = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }
                String[] partes = linea.split("\\s+");
                grafo.agregarCarretera(partes[0], partes[1], Double.parseDouble(partes[2]));
            }
        }
        return grafo;
    }
}
