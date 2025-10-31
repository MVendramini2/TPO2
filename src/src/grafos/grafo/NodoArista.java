package src.grafos.grafo;

public class NodoArista<E> {
	private int peso;
	private NodoVertice<E> verticeDestino;
	private NodoArista<E> sigArista;
	
	public NodoArista() {}
	
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public NodoVertice<E> getVerticeDestino() {
		return verticeDestino;
	}
	public void setVerticeDestino(NodoVertice<E> verticeDestino) {
		this.verticeDestino = verticeDestino;
	}
	public NodoArista<E> getSigArista() {
		return sigArista;
	}
	public void setSigArista(NodoArista<E> sigArista) {
		this.sigArista = sigArista;
	}
	

}
