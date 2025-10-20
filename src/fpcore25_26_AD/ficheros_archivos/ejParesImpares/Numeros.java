package fpcore25_26_AD.ficheros_archivos.ejParesImpares;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Numeros {

	public static void main(String[] args) {

		// agregamos los números pares e impares a los ficheros txt
		try (FileWriter fw1 = new FileWriter("src/ficheros/pares.txt");
				FileWriter fw2 = new FileWriter("src/ficheros/impares.txt");) {

			for (int i = 1; i <= 100; i++) {
				if (i % 2 == 0)
					fw1.write(i + "\n");
				else
					fw2.write(i + "\n");
			}
			System.out.println("Numeros agregados a los archivos");

		} catch (IOException e) {
			System.out.println("Problemas creando el archivo. ERROR: " + e.getMessage());
		} finally {
			System.out.println("Gracias por confiar en nosotros!");
		}

		// usamos para acumular todo el contenido de ambos archivos antes de procesarlo
		StringBuilder contenido = new StringBuilder();

		// añadimos pares e impares en el mismo fichero (todos.txt):
		try (FileReader frPares = new FileReader("src/ficheros/pares.txt");
				FileReader frImpares = new FileReader("src/ficheros/impares.txt");
				FileWriter fwTodos = new FileWriter("src/ficheros/todos.txt");) {

			int c;

			// lee cada archivo carácter por carácter: convertimos un int a char
			while ((c = frPares.read()) != -1) {
				contenido.append((char) c);
			}
			while ((c = frImpares.read()) != -1) {
				contenido.append((char) c);
			}

			fwTodos.write(contenido.toString());
			System.out.println("Archivo todos.txt contiene todos los números");

		} catch (IOException e) {
			System.out.println("Problemas leyendo el archivo. ERROR: " + e.getMessage());
		}

		// convertimos el texto a un Integer
		List<Integer> numeros = new ArrayList<>();
		String numerosEnTexto = contenido.toString();

		for (String linea : numerosEnTexto.split("\n")) {
			// evitamos líneas en blanco
			if (!linea.isEmpty()) {
				// cada línea es un número
				// convierte cada línea a un entero y lo guarda en la lista
				numeros.add(Integer.parseInt(linea));
			}
		}

		// Ordenamos los números - orden inverso:
		Collections.sort(numeros, Collections.reverseOrder());

		try (FileWriter fwOrdenados = new FileWriter("src/ficheros/todos_ordenados.txt")) {
			for (Integer num : numeros) {
				fwOrdenados.write(num + "\n");
			}

		} catch (IOException e) {
			System.out.println("❌ Error escribiendo todos_ordenados.txt: " + e.getMessage());
		}

	}

}
