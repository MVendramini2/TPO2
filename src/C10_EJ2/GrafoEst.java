package C10_EJ2;

import TDAs.grafos.grafo.GrafoTDA;

public class GrafoEst<E> implements GrafoTDA<E> {
    private int[][] mAdy;  
    private E[] etiqs;   
    private int cantNodos; 


    public GrafoEst(int n) {
        this.mAdy = new int[n][n];
        this.etiqs = (E[]) new Object[n];
        this.cantNodos = 0;
    }

    private void validarVertice(int v) {
        if (v < 0 || v >= cantNodos) {
            throw new IllegalArgumentException("Vértice fuera de rango: " + v);
        }
    }
    @SuppressWarnings("unchecked")
	public void inicializarGrafo() {
		mAdy = new int[100][100];
		etiqs = (E[])new Object[100];
		cantNodos = 0;
	}
	
	public void agregarVertice(E v) {
		etiqs[cantNodos] = v;
		for (int i = 0; i <= cantNodos; i++) {
			mAdy[cantNodos][i] = 0;
			mAdy[i][cantNodos] = 0;
		}
		cantNodos++;
	}
	
	public void eliminarVertice(E v) {
		int ind = vert2Indice(v);
		for (int k = 0; k < cantNodos; k++)
			mAdy[ind][k] = mAdy[cantNodos-1][k];
		for (int k = 0; k < cantNodos; k++)
			mAdy[k][ind] = mAdy[k][cantNodos-1];
		etiqs[ind] = etiqs[cantNodos-1];
		cantNodos--;
	}
	
	private int vert2Indice(E v) {
		int i = 0;
		while(i < this.cantNodos && !etiqs[i].equals(v))
			i++;
		return i;
	}
	
	public E[] vertices() {
		@SuppressWarnings("unchecked")
		E[] salida = (E[])new Object[100];
		for (int i = 0; i < cantNodos; i++) {
			salida[i]=etiqs[i];
		}
		return salida;
	}
	
	public void agregarArista(E v1, E v2, int peso) {
		int o = vert2Indice(v1);
		int d = vert2Indice(v2);
		mAdy[o][d] = peso;
	}
	
	public void eliminarArista(E v1, E v2) {
		int o = vert2Indice(v1);
		int d = vert2Indice(v2);
		mAdy[o][d] = 0;
	}
	
	public boolean existeArista(E v1, E v2) {
		int o = vert2Indice(v1);
		int d = vert2Indice(v2);
		return mAdy[o][d] != 0;
	}
	
	public int pesoArista(E v1, E v2) {
		int o = vert2Indice(v1);
		int d = vert2Indice(v2);
		return mAdy[o][d];
	}


    public int maxCostoSaliente(int v) {
        validarVertice(v);
        int max = Integer.MIN_VALUE;
        boolean tiene = false;

        for (int j = 0; j < cantNodos; j++) {
            int w = mAdy[v][j];
            if (w != 0) { 
                tiene = true;
                if (w > max) max = w;
            }
        }

        if (!tiene)
            throw new IllegalStateException("El vértice " + v + " no tiene aristas salientes");
        return max;
    }


    public int[] predecesores(int v) {
        validarVertice(v);

        int count = 0;
        for (int i = 0; i < cantNodos; i++) {
            int w = mAdy[i][v];
            if (w != 0) count++;
        }

        int[] res = new int[count];
        int k = 0;
        for (int i = 0; i < cantNodos; i++) {
            int w = mAdy[i][v];
            if (w != 0) res[k++] = i;
        }
        return res;
    }
}