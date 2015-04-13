package dominio.algoritmos;

public class GrafLouvain extends Graf {
	
	private Double sumaFila(Integer i) {
		Double sum = 0.0;
		for (Integer j = 0; j < Matriu.get(0).size(); ++j) {
			sum += Matriu.get(i).get(j);
		}
		return sum;
	}
	
	private Double sumaColumna(Integer j) {
		Double sum = 0.0;
		for (Integer i = 0; i < Matriu.get(0).size(); ++i) {
			sum += Matriu.get(i).get(j);
		}
		return sum;
	}
	
	public Double sumaPesos() {
		Double sum = 0.0;
		for (Integer i = 0; i < Matriu.size(); ++i) {
			sum += sumaFila(i);
		}
		return sum/2;
	}
	
	
}