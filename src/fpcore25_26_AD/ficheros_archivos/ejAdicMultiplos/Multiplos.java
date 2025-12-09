package fpcore25_26_AD.ficheros_archivos.ejAdicMultiplos;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Multiplos {

	public static void main(String[] args) {

		/*
		 * Genera los números del 1 al 50.
		 * 
		 * Guarda los múltiplos de 3 en un archivo llamado multiplos3.txt.
		 * 
		 * Guarda los múltiplos de 5 en un archivo llamado multiplos5.txt.
		 * 
		 * Crea un archivo todos_multiplos.txt que contenga todos los números de los dos
		 * archivos anteriores.
		 * 
		 * Convierte los números a enteros, ordénalos de menor a mayor y guarda el
		 * resultado en multiplos_ordenados.txt.
		 * 
		 * Extra: evita duplicados si un número es múltiplo de 3 y 5 (por ejemplo el 15,
		 * 30, 45).
		 * 
		 */

		try (FileWriter fwM3 = new FileWriter("src/ficheros/multiplos/multiplos3.txt");
				FileWriter fwM5 = new FileWriter("src/ficheros/multiplos/multiplos5.txt");) {

			for (int i = 1; i <= 50; i++) {
				if (i % 3 == 0)
					fwM3.write(i + "\n");
				if (i % 5 == 0)
					fwM5.write(i + "\n");
			}
			System.out.println("Ficheros creados con el contenido");

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		StringBuilder contenidoFicheros = new StringBuilder();

		try (FileWriter fwT = new FileWriter("src/ficheros/multiplos/todos_multiplos.txt");
				FileReader frM3 = new FileReader("src/ficheros/multiplos/multiplos3.txt");
				FileReader frM5 = new FileReader("src/ficheros/multiplos/multiplos5.txt");) {

			int c;

			while ((c = frM3.read()) != -1) {
				contenidoFicheros.append((char) c);
			}
			while ((c = frM5.read()) != -1) {
				contenidoFicheros.append((char) c);
			}

			fwT.write(contenidoFicheros.toString());

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		List<Integer> listaMultiplos = new ArrayList<>();

		for (String contenidoLinea : contenidoFicheros.toString().split("\n")) {
			if (!contenidoLinea.isEmpty()) {
				listaMultiplos.add(Integer.parseInt(contenidoLinea));
			}
		}

		// ordenamos numeros
		Collections.sort(listaMultiplos);

		try (FileWriter fwTO = new FileWriter("src/ficheros/multiplos/multiplos_ordenados.txt");) {

			for (Integer multiplo : listaMultiplos) {
				fwTO.write(multiplo + "\n");
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
