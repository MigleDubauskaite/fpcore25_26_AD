package buffered_reader_writer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class Libro {

	private String titulo;
	private String autor;
	private int anio;
	private double precio;

	private final static String FILE_PATH_TITULOS = "archivos/libros/titulos.txt";
	private final static String FILE_PATH_AUTORES = "archivos/libros/autores.txt";
	private final static String FILE_PATH_BIBLIOTECA = "archivos/libros/biblioteca.txt";

	private final static List<String> TITULOS = cargarDatos(FILE_PATH_TITULOS);
	private final static List<String> AUTORES = cargarDatos(FILE_PATH_AUTORES);

	private final static RandomGenerator RANDOM = RandomGenerator.getDefault();

	public Libro() {
		titulo = datosRandom(TITULOS);
		autor = datosRandom(AUTORES);
		anio = RANDOM.nextInt(1900, 2026);
		precio = RANDOM.nextDouble(10, 50);
	}

	private static String datosRandom(List<String> lista) {
		return lista.get(RANDOM.nextInt(lista.size()));
	}

	private static List<String> cargarDatos(String filePath) {

		List<String> listaDatos = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

			String linea;

			while ((linea = br.readLine()) != null) {

				String[] partes = linea.trim().split(",");

				for (String parte : partes) {
					parte = parte.trim();
					if (!parte.isEmpty())
						listaDatos.add(parte);
				}

			}

		} catch (IOException e) {
			System.err.printf("Hay problema leyendo %s: %s %n", filePath, e.getMessage());
		}

		return listaDatos.isEmpty() ? List.of("DESCONOCIDA") : listaDatos;
	}

	private static boolean guardarEnBiblioteca(List<Libro> listaLibros, String filePath) {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {

			System.out.println("\n\nREGISTRO DE LIBROS ");
			bw.write("REGISTRO DE LIBROS \n\n");

			for (Libro libro : listaLibros) {
				bw.write(libro.toString());
				bw.newLine();
			}
			// bw.write("\nTotal de libros: " + listaLibros.size());
			return true;

		} catch (IOException e) {
			System.err.println("Error al escribir el archivo " + filePath + ": " + e.getMessage());
			return false;
		}

	}

	// Los placeholders:
	// %-25s ::: Cadena alineada a la izquierda con 25 caracteres de ancho
	// %-15s ::: Cadena alineada a la izquierda con 15 caracteres de ancho
	@Override
	public String toString() {
		return String.format("%-25s | %-15s | Año: %d | Precio: %.2f€", titulo, autor, anio, precio);
	}

	public static void main(String[] args) {

		List<Libro> biblioteca = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			biblioteca.add(new Libro());
		}

		biblioteca.forEach(System.out::println);

		// GUARDAMOS EN BIBLIOTECA:
		boolean exito = guardarEnBiblioteca(biblioteca, FILE_PATH_BIBLIOTECA);
		System.out.println(exito ? "\n✅ Archivo guardado correctamente." : "\n❌ Error al guardar el archivo.");

	}

}
