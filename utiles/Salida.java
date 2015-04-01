package utiles; // Prueba Egit

public class Salida {
	
	public void Escriu(String s){
    	System.out.println(s);
	}
	
	public void Escriu(char c){
		System.out.printf("%c%n", c);
	}
	
	public void Escriu(int n){
		System.out.printf("%d%n", n);
	}
	
	public void Escriu(Integer n){
    	System.out.println(n.toString());
	}
	
	public void Escriu(Double a){
    	System.out.println(a.toString());
	}
	
	public void Escriu(Boolean b){
    	System.out.println(b.toString());
	}

	public void Escriu(Long l){
    	System.out.println(l.toString());
	}
	
	public void Escriu(int n, char[] a){
		for (int i = 0; i < n; ++i){
			if (i != n-1) System.out.printf("%c, ", a[i]);
			else System.out.printf("%c%n", a[i]);
		}
	}
	
	public void Escriu(int n, int[] a){
		for (int i = 0; i < n; ++i){
			if (i != n-1) System.out.printf("%d, ", a[i]);
			else System.out.printf("%d%n", a[i]);
		}
	}
	
	public void Escriu(int n, Integer[] a){
		for (int i = 0; i < n; ++i){
			if (i != n-1) System.out.print(a[i].toString() + ", ");
			else {
				System.out.print(a[i].toString());
				System.out.printf("%n");
			}
		}
	}
	
	public void Escriu(int n, Double[] a){
		for (int i = 0; i < n; ++i){
			if (i != n-1) System.out.print(a[i].toString() + ", ");
			else {
				System.out.print(a[i].toString());
				System.out.printf("%n");
			}
		}
	}
	
	public void Escriu(int n, Boolean[] a){
		for (int i = 0; i < n; ++i){
			if (i != n-1) System.out.print(a[i].toString() + ", ");
			else {
				System.out.print(a[i].toString());
				System.out.printf("%n");
			}
		}
	}

	public void Escriu(int n, Long[] a){
		for (int i = 0; i < n; ++i){
			if (i != n-1) System.out.print(a[i].toString() + ", ");
			else {
				System.out.print(a[i].toString());
				System.out.printf("%n");
			}
		}
	}
	
}