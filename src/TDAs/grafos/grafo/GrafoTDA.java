package TDAs.grafos.grafo;

public interface GrafoTDA<E> {
	void agregarVertice(E v); //grafo inicializado y ∄ vértice
	void eliminarVertice(E v); //grafo inicializado y ∃ vértice
	E[] vertices(); //grafo inicializado
	void agregarArista(E v1, E v2, int peso); //grafo inicializado, ∄ arista y ∃ ambos vértices
	void eliminarArista(E v1, E v2); //grafo inicializado y ∃ arista
	boolean existeArista(E v1, E v2); //grafo inicializado y ∃ ambos vértices
	int pesoArista(E v1, E v2); //grafo inicializado y ∃ arista
	void inicializarGrafo();
}

