package fpcore25_26_AD.ficheros_archivos;

import java.io.FileWriter;
import java.io.IOException;

public class PracticandoFileWriter {

	public static void main(String[] args) {

		String linea01 = "¡Hola Mundo!";
		String linea02 = "¡Adiós Mundo!";
		String[] lineas = { linea01, linea02 };

		try (FileWriter fw = new FileWriter("src/ficheros/out.txt")) {
			for (int i = 0; i < lineas.length; i++) {
				fw.append(lineas[i]);
				if (i != lineas.length - 1)
					fw.append("\n\n\n");
			}
			System.out.println("Está creado!");
			fw.flush();
			//while (true);

		} catch (IOException e) {
			System.out.println("Problemas creando el archivo");
		} finally {
			System.out.println("Gracias por su confianza");
		}

	}

}
