package fpcore25_26_AD.exceptions;

public class MyClass {

	public static void m1() {
		System.out.println("Soy un M1, antes de llamar a M2()");
		try {
			m2();
		} catch (ArithmeticException e) {
			System.err.print("Excepción capturada en M1 s→ ");
			System.out.println("Excepción producida: " + e.getMessage());
		} finally {
			System.out.println("Esto es FINALLY (se ejecuta siempre)");
		}
		System.out.println("Soy un M1, después de llamar a M2()");

	}

	public static void m2() {
		System.out.println("Soy un M2, antes de excepción");
		System.out.println("El resultado de: 2/0 es: " + 2 / 0);
	}

	//
	public static void m11() {
		System.out.println("Soy un M11, antes de llamar a M22()");
		try {
			m22();
		} catch (Exception e) {
			System.err.print("Excepción capturada en M11 → ");
			System.out.println("Excepción producida: " + e.getMessage());
		} finally {
			System.out.println("Esto es FINALLY (se ejecuta siempre)");
		}
		System.out.println("Soy un M11, después de llamar a M22()");

	}

	public static void m22() {
		int[] array = {};
		System.out.println("Soy un M22, antes de excepción");
		System.out.println("El resultado es: " + array[2]);
	}

	// MULTICATCH:
	public static void m111() {
		System.out.println("Soy un M111, antes de llamar a M222()");
		try {
			String m = null;
			m.toLowerCase();

			m1();
			m2();
		} catch (ArithmeticException | IndexOutOfBoundsException e) {
			System.err.print("Excepción capturada → ");
			System.out.println("Excepción producida: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("BRR");
			System.out.println("Excepción producida: " + e.getMessage());
		} finally {
			System.out.println("Esto es FINALLY (se ejecuta siempre)");
		}
		System.out.println("Soy un M111, después");

	}

}
