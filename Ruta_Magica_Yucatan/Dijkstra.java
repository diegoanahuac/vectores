import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra {
    private Map<String, Double> distancia;
    private Map<String, String> anterior;

    public Dijkstra(Grafo grafo, String origen) {
        distancia = new HashMap<>();
        anterior = new HashMap<>();
        for (String pueblo : grafo.getPueblos()) {
            distancia.put(pueblo, Double.POSITIVE_INFINITY);
        }
        distancia.put(origen, 0.0);

        PriorityQueue<Object[]> cola = new PriorityQueue<>((x, y) -> Double.compare((double) x[1], (double) y[1]));
        cola.add(new Object[] { origen, 0.0 });

        while (!cola.isEmpty()) {
            Object[] actual = cola.poll();
            String pueblo = (String) actual[0];
            double dist = (double) actual[1];
            if (dist > distancia.get(pueblo)) {
                continue;
            }
            for (Arista a : grafo.getVecinos(pueblo)) {
                double nueva = dist + a.getDistancia();
                if (nueva < distancia.get(a.getDestino())) {
                    distancia.put(a.getDestino(), nueva);
                    anterior.put(a.getDestino(), pueblo);
                    cola.add(new Object[] { a.getDestino(), nueva });
                }
            }
        }
    }

    public double getDistancia(String destino) {
        return distancia.get(destino);
    }

    public List<String> getRuta(String destino) {
        List<String> ruta = new ArrayList<>();
        if (distancia.get(destino) == Double.POSITIVE_INFINITY) {
            return ruta;
        }
        for (String p = destino; p != null; p = anterior.get(p)) {
            ruta.add(p);
        }
        Collections.reverse(ruta);
        return ruta;
    }
}
