package abb;

public class Nombre implements Comparable<Nombre>{
	private String Valor;
	public Nombre (String input) {
		Valor = input;
	}
	public String getValor() {return Valor;}
	public int compareTo(Nombre otroNombre) {
		return Valor.compareTo(otroNombre.getValor());
	}
}
