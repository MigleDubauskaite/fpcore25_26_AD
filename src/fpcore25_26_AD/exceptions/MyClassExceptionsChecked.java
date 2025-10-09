package fpcore25_26_AD.exceptions;

public class MyClassExceptionsChecked {
	
	public static void m1() {
		System.out.println("Soy un M1, antes de llamar a M2()");
		try {
			m2();
		} catch (MyCheckedPadreException e) {
			System.out.println("Detectado error: " + e.getMessage());
		}
		System.out.println("Soy un M1, después de llamar a M2()");

	}

	public static void m2() throws MyCheckedPadreException, MyCheckedHijaException {
		System.out.println("Soy un M2 (entrando)");
		
		boolean b = true;
		if(b) throw new MyCheckedPadreException("Soy la excepción checked padre");
		
		throw new MyCheckedPadreException("");
		
		//System.out.println("Soy un M2 (saliendo)");
	}

}
