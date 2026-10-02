# Árboles Binarios de Búsqueda

Actividad de Estructura de Datos y Algoritmos.

Implementación de un Árbol Binario de Búsqueda (ABB) en Java con búsqueda, menor valor y eliminación, todos de forma **recursiva**.

## Reglas de la actividad

- Java puro, sin librerías externas, sin genéricos. Solo `int`.
- Todos los métodos nuevos son recursivos.
- `insertar()` y los recorridos no se reescriben.
- No se cambia la firma de los métodos públicos.

## Estructura

```
Arboles Binarios de Busqueda/
├── README.md
├── assets/              capturas de las pruebas
└── src/
    └── ArbolBinario.java
```

## Compilar y ejecutar

```bash
javac -d out src/ArbolBinario.java && java -cp out ArbolBinario
```

El programa pide en este orden:

1. Cuántos valores se van a insertar.
2. Cada uno de los valores.
3. El valor a buscar.
4. El valor a eliminar.

Después imprime los tres recorridos, el resultado de la búsqueda y el árbol después de eliminar.

---

## Fase 1. Código base

### Árbol de ejemplo

Insertando `50, 30, 20, 40, 70, 60, 80`:

```
            50
          /    \
        30      70
       /  \    /  \
     20   40  60   80
```

**Invariante del ABB:** todo lo que está a la izquierda de un nodo es menor que él y todo lo que está a la derecha es mayor (en este código los valores iguales también van a la derecha).

### `Nodo`

Cada nodo guarda:

- `info`: el valor entero.
- `izq`: referencia al hijo izquierdo.
- `der`: referencia al hijo derecho.

Si no tiene un hijo, la referencia vale `null`.

### Constructor

Inicializa `raiz = null` porque el árbol empieza vacío.

### `insertar(int info)`

1. Crea un nodo nuevo sin hijos.
2. Si el árbol está vacío, el nuevo nodo se vuelve la raíz.
3. Si no, baja desde la raíz con dos referencias: `actual` (el nodo que se revisa) y `anterior` (el padre). Si el valor es menor va a la izquierda, si es mayor o igual va a la derecha.
4. Cuando `actual` llega a `null`, conecta el nuevo nodo como hijo de `anterior`.

### Recorridos

Los tres son recursivos. El caso base es `nodo == null`. Solo cambia el momento en el que se imprime el nodo:

| Recorrido | Orden de visita | Salida |
|---|---|---|
| Inorden | Izquierda → Nodo → Derecha | `20 30 40 50 60 70 80` |
| Preorden | Nodo → Izquierda → Derecha | `50 30 20 40 70 60 80` |
| Postorden | Izquierda → Derecha → Nodo | `20 40 30 60 80 70 50` |

El inorden de un ABB siempre sale ordenado de menor a mayor.

### Prueba

Entrada: `7`, luego `50 30 20 40 70 60 80`.

![Fase 1 - Recorridos](assets/fase1_recorridos.png)

---

## Fase 2. Búsqueda recursiva

### Casos

1. El nodo es `null` → no está, devuelve `false`. **Caso base.**
2. La clave es igual a la del nodo → la encontró, devuelve `true`. **Caso base.**
3. La clave es menor → solo puede estar a la izquierda, busca ahí.
4. La clave es mayor → solo puede estar a la derecha, busca ahí.

Nunca se buscan los dos lados: en cada paso se descarta la mitad del subárbol. Esa es la ventaja del ABB sobre recorrer todo.

### Código

```java
public boolean buscar(int clave) {
    return buscarRec(raiz, clave);
}

private boolean buscarRec(Nodo nodo, int clave) {
    if (nodo == null) {
        return false;
    }
    if (clave == nodo.info) {
        return true;
    }
    if (clave < nodo.info) {
        return buscarRec(nodo.izq, clave);
    } else {
        return buscarRec(nodo.der, clave);
    }
}
```

`buscar` es el método público: le pasa la raíz a `buscarRec`, que es el que hace la recursión.

### Ejemplo

- `buscar(40)`: 50 → izquierda → 30 → derecha → 40 → `true`.
- `buscar(55)`: 50 → derecha → 70 → izquierda → 60 → izquierda → `null` → `false`.

### Prueba

Entrada: `7`, `50 30 20 40 70 60 80`, buscar `40`.

![Fase 2 - Buscar 40](assets/fase2_buscar_40.png)

Entrada: `7`, `50 30 20 40 70 60 80`, buscar `55`.

![Fase 2 - Buscar 55](assets/fase2_buscar_55.png)

---

## Fase 3. Método auxiliar: menor valor

Va antes de la eliminación porque la eliminación lo necesita.

### Idea

Por la invariante del ABB, el menor valor de un subárbol está siempre en el **extremo izquierdo**. No hay que comparar nada, solo caminar a la izquierda hasta que ya no se pueda.

### Casos

1. El nodo no tiene hijo izquierdo → ese es el menor, devuelve su valor. **Caso base.**
2. Tiene hijo izquierdo → sigue bajando por la izquierda. **Caso recursivo.**

### Código

```java
private int minValor(Nodo nodo) {
    if (nodo.izq == null) {
        return nodo.info;
    }
    return minValor(nodo.izq);
}
```

### Ejemplo

`minValor(raiz.der)` empieza en 70 → tiene hijo izquierdo → baja a 60 → no tiene hijo izquierdo → devuelve **60**.

### Prueba

Se comprueba al eliminar la raíz `50`: su lugar lo toma `minValor(raiz.der)`, que es **60**.

Entrada: `7`, `50 30 20 40 70 60 80`, buscar `40`, eliminar `50`.

![Fase 3 - minValor](assets/fase3_minvalor.png)

---

## Fase 4. Eliminación recursiva

Primero se **baja** hasta el nodo (igual que en la búsqueda) y luego se **resuelve** según cuántos hijos tenga.

### Casos

| Caso | Situación | Qué se hace |
|---|---|---|
| 0 | El nodo es `null` | La clave no existe, el árbol no cambia |
| 1 | Es hoja (sin hijos) | Se devuelve `null`: el padre lo suelta |
| 2 | Tiene un solo hijo | Se devuelve ese hijo: el padre se conecta directo con el nieto |
| 3 | Tiene dos hijos | Se reemplaza su valor por el sucesor y se borra el sucesor |

El **sucesor** es el menor valor del subárbol derecho (`minValor`). Es el único valor que puede ocupar ese lugar sin romper la invariante: es mayor que todo el subárbol izquierdo y menor que el resto del derecho.

### Código

```java
public void eliminar(int clave) {
    raiz = eliminarRec(raiz, clave);
}

private Nodo eliminarRec(Nodo nodo, int clave) {
    if (nodo == null) {
        return null;
    }
    if (clave < nodo.info) {
        nodo.izq = eliminarRec(nodo.izq, clave);
    } else if (clave > nodo.info) {
        nodo.der = eliminarRec(nodo.der, clave);
    } else {
        if (nodo.izq == null) {
            return nodo.der;
        }
        if (nodo.der == null) {
            return nodo.izq;
        }
        nodo.info = minValor(nodo.der);
        nodo.der = eliminarRec(nodo.der, nodo.info);
    }
    return nodo;
}
```

**Por qué el caso 1 no se escribe aparte:** si el nodo es hoja, `nodo.izq == null` es verdadero y devuelve `nodo.der`, que también es `null`. El mismo `if` cubre "sin hijos" y "solo hijo derecho".

**Por qué se reasigna `nodo.izq = eliminarRec(...)`:** el método devuelve el subárbol ya corregido, así que el padre lo tiene que volver a enlazar. Si solo se llama sin asignar, el árbol no cambia. Por lo mismo, `eliminar` hace `raiz = eliminarRec(raiz, clave)`: si se borra la raíz, el árbol necesita una nueva.

### Ejemplo: eliminar 30 (dos hijos)

```
Antes:                   Después:
        50                       50
      /    \                   /    \
    30      70               40      70
   /  \    /  \             /       /  \
 20   40  60   80         20      60   80
```

1. 30 es menor que 50 → izquierda.
2. Lo encontró. Tiene dos hijos.
3. `minValor` del subárbol derecho de 30 → 40.
4. El 30 se reemplaza por 40 y se borra el 40 original (es hoja, caso 1).

### Pruebas

**Caso 1 - Hoja.** Entrada: `7`, `50 30 20 40 70 60 80`, buscar `40`, eliminar `20`.
Resultado esperado: `Preorden: 50 30 40 70 60 80`

![Fase 4 - Eliminar hoja](assets/fase4_hoja.png)

**Caso 2 - Un hijo.** El árbol original no tiene ningún nodo con un solo hijo, así que se agrega `65` (queda como hijo derecho de 60).
Entrada: `8`, `50 30 20 40 70 60 80 65`, buscar `40`, eliminar `60`.
Resultado esperado: `Preorden: 50 30 20 40 70 65 80`

![Fase 4 - Eliminar con un hijo](assets/fase4_un_hijo.png)

**Caso 3 - Dos hijos.** Entrada: `7`, `50 30 20 40 70 60 80`, buscar `40`, eliminar `30`.
Resultado esperado: `Preorden: 50 40 20 70 60 80`

![Fase 4 - Eliminar con dos hijos](assets/fase4_dos_hijos.png)

**Caso 0 - No existe.** Entrada: `7`, `50 30 20 40 70 60 80`, buscar `40`, eliminar `55`.
Resultado esperado: el árbol queda igual.

![Fase 4 - Eliminar valor inexistente](assets/fase4_no_existe.png)
