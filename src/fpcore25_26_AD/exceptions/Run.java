package fpcore25_26_AD.exceptions;

public class Run {

	public static void main(String[] args) {
		System.out.println("Main antes de llamar a M1()");
		MyClass.m1();
		System.out.println("Main después de llamar a M1() \n");

		MyClass.m11();

		System.out.println("\n\nMULTICATCH: ");
		MyClass.m111();
		
		System.out.println("\n\nPROBANDO LAS CHECKED EXCEPTIONS: \n");
		MyClassExceptionsChecked.m1();
	}

}
