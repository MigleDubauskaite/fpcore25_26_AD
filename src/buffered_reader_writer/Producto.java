package buffered_reader_writer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class Producto {

	private String nombre;
	private String marca;
	private int precio;
	private int stock;

	// Rutas de los ficheros (archivos de texto)
	private final static String FILE_PATH_NOMBRES = "archivos/productos/productos.txt";
	private final static String FILE_PATH_MARCAS = "archivos/productos/marcas.txt";

	// Cargamos los datos de los ficheros al iniciar la clase
	// Estas listas contendrán todos los nombres y marcas disponibles
	private final static List<String> NOMBRES = cargarDatos(FILE_PATH_NOMBRES);
	private final static List<String> MARCAS = cargarDatos(FILE_PATH_MARCAS);

	// Objeto para generar números aleatorios
	private final static RandomGenerator RANDOM = RandomGenerator.getDefault();

	public Producto() {
		nombre = datosAleatorios(NOMBRES);
		marca = datosAleatorios(MARCAS);
		precio = RANDOM.nextInt(50, 501);
		stock = RANDOM.nextInt(1, 101);
	}

	// Devuelve un elemento aleatorio de una lista (nombre o marca)
	private static String datosAleatorios(List<String> lista) {
		return lista.get(RANDOM.nextInt(lista.size()));
	}

	// Lee un archivo de texto y devuelve una lista.
	// filePath ruta del archivo a leer
	private static List<String> cargarDatos(String filePath) {

		List<String> listaDatos = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

			String linea;

			// Leemos el archivo línea a línea (mientras no es nulo)
			while ((linea = br.readLine()) != null) {

				// Cada línea puede tener varios elementos separados por comas
				// El método .split(",") divide una cadena en partes usando la coma (,) como
				// separador.
				// y lo asigna a un array de cadenas (String[])
				String[] partes = linea.split(",");

				// Recorremos las partes y las limpiamos de espacios (con .trim())
				for (String parte : partes) {
					String palabra = parte.trim();

					// Solo añadimos a la lisya palabras no vacías
					if (!palabra.isEmpty())
						listaDatos.add(palabra);
				}
			}

		} catch (IOException e) {
			System.err.printf("Error al leer %s: %s %n", filePath, e.getMessage());
		}

		// Si el archivo estaba vacío o falló la lectura, devolvemos una lista con valor
		// por defecto
		return listaDatos.isEmpty() ? List.of("Desconocido/Nulo") : listaDatos;
	}

	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", marca=" + marca + ", precio=" + precio + ", stock=" + stock + "]";
	}

	public static void main(String[] args) {
		System.out.println(NOMBRES);
		System.out.println(MARCAS);

		Producto p1 = new Producto();
		System.out.println(p1);

		List<Producto> inventario = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			inventario.add(new Producto());
		}

		inventario.forEach(System.out::println);
	}

}
