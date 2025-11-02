package TDAs.abb;
import java.util.Comparator;

public class Test {

	public static void main(String[] args) {
		
		//Análisis del compareTo
		String Nombre1 = "";
		String Nombre2 = "";
		Nombre1.compareTo(Nombre2);
		//Comparator c = new DefaultComparator<String>();
		//c.compare(Nombre1, Nombre2);
		
		Nombre n1 = new Nombre("Pepe");
		Nombre n2 = new Nombre("Jose");
		Comparator c = new DefaultComparator<Nombre>();
		c.compare(n1, n2);
		
		
		
		//Creación de un árbol que se imprime por pantalla
		arbolBB<Integer> t = new arbolBB<Integer>( new DefaultComparator<Integer>() );
		insertar_imprimir( t, 20 );   //t.insertar(20): (20)
		insertar_imprimir( t, 16 );   //t.insertar(16): ((16)20)
		insertar_imprimir( t, 64 );   //t.insertar(64): ((16)20(64))
		insertar_imprimir( t, 6 );    //t.insertar(6): (((6)16)20(64))
		insertar_imprimir( t, 100 );  //t.insertar(100): (((6)16)20(64(100))) 
		insertar_imprimir( t, 3 );    //t.insertar(3): ((((3)6)16)20(64(100)))
		
	}
	
	private static void insertar_imprimir( arbolBB<Integer> t, int x ) {
		t.insertar( x );  System.out.println( "t.insertar("+ x + "): " + t );  
	}
	
	private static int potenciaRecursiva(int x, int n) {
		//caso base n es 0, devuelvo 1
		if(n==0)
			return 1;
		//caso general, devuelvo (x * (x ^ n-1))
		else
			return x * potenciaRecursiva(x,n-1);
	}

}
