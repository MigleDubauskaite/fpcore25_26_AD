package fpcore25_26_AD.file;

import java.io.File;

public class Test {
	
	public static void main(String[] args) {
		File f = new File("C:\\Users\\mduba\\eclipse-workspace\\fpcore25_26_AD\\src\\fpcore25_26_AD\\file\\prueba.txt");
		
		System.out.println(f.exists());
		System.out.println(f.getName());
		System.out.println(f.getPath());
		
		File f2 = new File("src/fpcore25_26_AD/file");
		System.out.println(f2.exists());
		
		File f3 = new File("src/fpcore25_26_AD/file/prueba2.txt");
		System.out.println(f3.exists());
		
		File probando1 = new File("C:\\Users\\mduba\\Documents\\vida_laboral.pdf");
		System.out.println(probando1.exists());
		
		//
		System.out.println("\n\nPROBANDO EL EJERCICIO: ");
		FileUtils.analiza(probando1);	
		System.out.println();
		FileUtils.analiza(f3);
		System.out.println();
		FileUtils.analiza(f2);
		
	}

}
