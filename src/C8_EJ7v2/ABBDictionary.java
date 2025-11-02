package C8_EJ7v2;

import TDAs.abb.DefaultComparator;
import TDAs.abb.arbolBB;

// TODO: ejercicio 7
public class ABBDictionary<K,V extends Comparable<V>> implements Dictionary<K,V> {
    /*
    La estructura es parecida a ArrayDictionary pero en vez de guardar
    arrays de valores (V[]), guardo un ABB para cada clave.

    Cada entry tiene:
    - Una clave K
    - Un arbolBB<V> con todos los valores de esa clave

    Ejemplo:
    put("frutas", "manzana")
    put("frutas", "pera")
    → array[0] = Entry("frutas", ABB{manzana, pera})

    La ventaja del ABB es que los valores quedan ordenados automáticamente
    y las búsquedas son más rápidas.

    V tiene que ser Comparable porque el ABB necesita comparar elementos
    para saber si van a la izquierda o derecha.
     */
    private Entry<K, arbolBB<V>>[] array;
    private int size;
    private DefaultComparator<V> comp;

    public ABBDictionary(DefaultComparator<V> comp) {
        this.comp = comp;
        array = (Entry<K,arbolBB<V>>[])new Entrada[100];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public V[] get(K k) {
        int i=0;
        boolean encontreClave=false;
        while(!encontreClave && i<size) {
            if(array[i].getKey().equals(k))
                encontreClave=true;
            else
                i++;
        }
        if(!encontreClave)
            return null;
        else {
            arbolBB<V> arbol = array[i].getValue();
            return arbol.elementosIn();  // mismo que arrayDic pero devuelvo elementos ordenados aprovechando el beneficio de que con arboles puedo ordenar
        }
    }

    public void put(K k, V v) {
        // Buscar si la clave k ya existe
        int i = 0;
        boolean encontreClave = false;
        while (!encontreClave && i < size) {
            if (array[i].getKey().equals(k))
                encontreClave = true;
            else
                i++;
        }

        // CASO 1: La clave NO existe
        if (!encontreClave) {
            // Si el array está lleno, agrandarlo
            if (size == array.length)
                resize();

            // Crear un nuevo ABB y agregar el primer valor
            arbolBB<V> nuevoArbol = new arbolBB<V>(comp);
            nuevoArbol.insertar(v);

            // Crear la entry con la clave y el árbol
            Entrada<K, arbolBB<V>> nuevaEntrada = new Entrada<>(k, nuevoArbol);
            array[size] = nuevaEntrada;
            size++;
        }
        // CASO 2: La clave YA existe
        else {
            // Obtener el árbol existente y agregar el nuevo valor
            arbolBB<V> arbolExistente = array[i].getValue();
            arbolExistente.insertar(v);
            // No hace falta hacer setValue porque ya modificamos el árbol directamente
        }
    }

    public V[] remove(K k) {
        // Busco la clave
        int i = 0;
        boolean encontreClave = false;
        while (!encontreClave && i < size) {
            if (array[i].getKey().equals(k))
                encontreClave = true;
            else
                i++;
        }

        // Si no la encontré, devuelvo null
        if (!encontreClave)
            return null;
        else {
            // Guardo el árbol antes de eliminarlo
            arbolBB<V> arbolEliminado = array[i].getValue();

            // Hago el "truco del último": pongo el último elemento en esta posición
            array[i] = array[size - 1];
            size--;

            // Devuelvo los valores que tenía (ordenados con in-order)
            return arbolEliminado.elementosIn();
        }
    }

    public V remove(K k, V v) {
        // Busco la clave
        int i = 0;
        boolean encontreClave = false;
        while (!encontreClave && i < size) {
            if (array[i].getKey().equals(k))
                encontreClave = true;
            else
                i++;
        }

        // Si no encontré la clave, devuelvo null
        if (!encontreClave)
            return null;
        else {
            // Obtengo el árbol de esa clave
            arbolBB<V> arbol = array[i].getValue();

            // Intento eliminar v del árbol
            V eliminado = arbol.eliminar(v);

            // Si eliminado es null, v no estaba en el árbol
            if (eliminado == null)
                return null;

            // Si después de eliminar el árbol quedó vacío, elimino toda la clave
            if (arbol.elementosIn().length == 0) { // no puedo usar size del arbol porque no lo tengo accesible, es protected
                array[i] = array[size - 1];
                size--;
            }

            return eliminado;
        }
    }

    public K[] keys() {
        // Creo un array del tamaño del diccionario
        K[] aux = (K[]) new Object[size];

        // Recorro todas las entries sacando la clave de cada una
        for (int i = 0; i < size; i++) {
            aux[i] = array[i].getKey();
        }

        return aux;
    }

    public Entry<K, V[]>[] entries() {
        // Creo un array de entries del tamaño correcto
        Entry<K, V[]>[] auxArray = (Entry<K, V[]>[]) new Entrada[size];

        // Recorro todas las entries
        for (int i = 0; i < size; i++) {
            // Obtengo la clave
            K clave = array[i].getKey();

            // Obtengo el árbol y lo convierto a array ordenado
            arbolBB<V> arbol = array[i].getValue();
            V[] valores = arbol.elementosIn();

            // Creo una nueva entrada con la clave y el array de valores
            auxArray[i] = new Entrada<>(clave, valores);
        }
        return auxArray;
    }

    private void resize() {
        int newCapacity = array.length * 2;
        Entry<K, arbolBB<V>>[] newArray = (Entry<K, arbolBB<V>>[]) new Entrada[newCapacity]; // mismo que array, pero con arbolBB
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }
}
