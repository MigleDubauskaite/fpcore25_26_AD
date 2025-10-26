package buffered_reader_writer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class Persona2 {

	private String nombre;
	private String apellido1;
	private String apellido2;
	private int anioNacimiento;

	private final static String FILE_PATH_NOMBRES = "archivos/personas/nombres.txt";
	private final static String FILE_PATH_APELLIDOS = "archivos/personas/apellidos.txt";

	private final static List<String> NOMBRES = cargarDatosDeFichero(FILE_PATH_NOMBRES);
	private final static List<String> APELLIDOS = cargarDatosDeFichero(FILE_PATH_APELLIDOS);

	private final static RandomGenerator RANDOM = RandomGenerator.getDefault();

	public Persona2() {
		nombre = datosRandom(NOMBRES);
		apellido1 = datosRandom(APELLIDOS);
		apellido2 = datosRandom(APELLIDOS);
		anioNacimiento = RANDOM.nextInt(1920, 2026);
	}

	private static String datosRandom(List<String> lista) {
		return lista.get(RANDOM.nextInt(lista.size()));
	}

	private static List<String> cargarDatosDeFichero(String filePath) {

		List<String> datos = new ArrayList<String>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String linea;

			// leemos las filas del fichero mientras no son nulas
			while ((linea = br.readLine()) != null) {

				// creamos un array
				// sus elementos serán las palabras divididas por ,
				String[] partes = linea.trim().split(",");

				// recorremos todas las partes
				for (String palabra : partes) {
					// limpiamos los espacios vacíos
					palabra = palabra.trim();
					// a la lista asignamos solo las partes que contiene palabra y no son vacias
					if (!palabra.isEmpty())
						datos.add(palabra);
				}

			}

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		return datos.isEmpty() ? List.of("DESCONOCIDO/NULO") : datos;
	}

	@Override
	public String toString() {
		return String.format("%s %s %s - %d", nombre, apellido1, apellido2, anioNacimiento);
	}

	public static void main(String[] args) {
		System.out.println(NOMBRES);

		Persona2 p1 = new Persona2();
		System.out.println(p1);

		for (int i = 0; i < 5; i++) {
			System.out.println(new Persona2());
		}

	}

}
