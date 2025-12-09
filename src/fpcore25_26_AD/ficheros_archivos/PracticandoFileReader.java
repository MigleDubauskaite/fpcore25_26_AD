package fpcore25_26_AD.ficheros_archivos;

import java.io.FileReader;
import java.io.IOException;

public class PracticandoFileReader {

	public static void main(String[] args) {

		try (FileReader fr = new FileReader("src/ficheros/prueba2.txt")) {
			System.out.println("Encoding: " + fr.getEncoding());
			int c = 0;
			while ((c = fr.read()) != -1) {
				System.out.print((char) c);
			}
			System.out.println();
		} catch (IOException e) {
			System.out.println("Problemas abriendo el archivo");
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Gracias por tu confianza");
		}

	}

}
