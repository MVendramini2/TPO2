package TDAs.grafos.grafo;

public class NodoVertice<E> {
	private E vertice;
	private NodoArista<E> aristas;
	private NodoVertice<E> sigVertice;
	
	public NodoVertice() {}
	
	public E getVertice() {
		return vertice;
	}

	public void setVertice(E vertice) {
		this.vertice = vertice;
	}

	public NodoArista<E> getAristas() {
		return aristas;
	}

	public void setAristas(NodoArista<E> aristas) {
		this.aristas = aristas;
	}

	public NodoVertice<E> getSigVertice() {
		return sigVertice;
	}

	public void setSigVertice(NodoVertice<E> sigVertice) {
		this.sigVertice = sigVertice;
	}

	
	
}
