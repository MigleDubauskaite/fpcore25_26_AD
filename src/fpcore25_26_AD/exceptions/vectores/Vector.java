package fpcore25_26_AD.exceptions.vectores;

/*
class VectorOutOfBoundsException extends Exception {
	public VectorOutOfBoundsException(String message) {
		super(message);
	}
}*/

public class Vector {

	private int x;
	private int y;

	public Vector(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Vector(int valor) {
		x = y = valor;
	}

	public Vector sumar(Vector vector) throws VectorOutOfBoundsException {
		if (vector.x + (long)x > Integer.MAX_VALUE || 
			vector.y + (long)y > Integer.MAX_VALUE ||
		    vector.x + (long)x < Long.MIN_VALUE ||
		    vector.y + (long)y < Long.MIN_VALUE)
			throw new VectorOutOfBoundsException("Suma fuera de rango");

		Vector v = new Vector(x + vector.x, y + vector.y);
		return v;
	}

	@Override
	public String toString() {
		return "Vector [x=" + x + ", y=" + y + "]";
	}

	public static void main(String[] args) throws VectorOutOfBoundsException {
		Vector v = new Vector(2);
		Vector v2 = new Vector(2, 1);
		System.out.println(v.sumar(v2));

//		System.out.println("Máximo valor de Long: " + Long.MAX_VALUE);
//		Vector vMax1 = new Vector(Integer.MAX_VALUE);
//		System.err.println(vMax1.sumar(v));
		
		System.out.println("\n\nPRUEBAS");
		
		// Pruebas:
		Vector vMax = new Vector(Integer.MAX_VALUE);
		Vector vMin = new Vector(Integer.MIN_VALUE);
		Vector vPositivo = new Vector(1);
		Vector vNegativo = new Vector(-1);
		
		try {
			Vector vector = vPositivo.sumar(vNegativo);
			System.out.println(vector);
			
			Vector vector2 = vPositivo.sumar(vMax);
			System.out.println(vector2);
			
			Vector vector3 = vMin.sumar(vMax);
			System.out.println(vector3);
			
		} catch (VectorOutOfBoundsException e) {
			System.out.println("No se puede sumar");
		}
		
		try {
			Vector vector3 = vMin.sumar(vMax);
			System.out.println(vector3);
		} catch (VectorOutOfBoundsException e) {
			System.out.println("No se puede sumar");
		}
		
		

	}

}
