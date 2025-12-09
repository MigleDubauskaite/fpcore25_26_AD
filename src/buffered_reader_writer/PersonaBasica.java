package buffered_reader_writer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class PersonaBasica {

	private String nombre;
	private String apellido1;
	private String apellido2;
	private int nacimiento;

	private static String[] nombres;
	private static String[] apellidos;

	private static Random random = new Random();

	// creamos un constructor que tedrá:
	// nombres, apellidos y fecha de nacimiento aleatorios
	public PersonaBasica() {
		this.nombre = nombres[random.nextInt(nombres.length)];
		this.apellido1 = apellidos[random.nextInt(apellidos.length)];
		this.apellido2 = apellidos[random.nextInt(apellidos.length)];
		this.nacimiento = random.nextInt(1920, 2026);
	}

	private static boolean cargarDatos() {

		try (BufferedReader bwNombres = new BufferedReader(new FileReader("src/ficheros/buffer/nombres.txt"));
				BufferedReader bwApellidos = new BufferedReader(new FileReader("src/ficheros/buffer/apellidos.txt"));) {

			// agregamos a los arrays el contenido de los ficheros:
			// split() permite agregar los elementos en un array (String -> String[])
			nombres = bwNombres.readLine().trim().split(",");
			apellidos = bwApellidos.readLine().trim().split(",");

			System.out.println("Se ha agregado los nombres con éxito");

			return true;
		} catch (IOException e) {
			System.out.println("Error al leer");
			System.out.println(e.getMessage());
			return false;
		}
	}

	private static PersonaBasica[] cantidadPersonas(int numPersonas) {
		PersonaBasica[] personas = new PersonaBasica[numPersonas];
		for (int i = 0; i < personas.length; i++) {
			personas[i] = new PersonaBasica();
		}
		return personas;
	}

	@Override
	public String toString() {
		return String.format("%s %s %s (%d)", nombre, apellido1, apellido2, nacimiento);
	}

	// creamos archivo SQL y añadimos a las personas
	private static boolean creandoSQL(PersonaBasica[] personas) {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/ficheros/buffer/poblador.sql"));) {

			// Creamos base de datos
			bw.append(String.format("CREATE DATABASE mundo; %n"));

			// indicamos que vamos a estar utilizando base de datos de MUNDO
			bw.append(String.format("USE mundo; %n"));

			// Creamos la tabla de personas con sus valores:
			bw.append(String.format("CREATE TABLE personas (nombre VARCHAR(30), " + "apellido1 VARCHAR(30), "
					+ "apellido2 VARCHAR(30), " + "nacimiento INT);   " + "%n"));

			// Insertamos los datos a la tabla de personas:
			bw.append(String.format("INSERT INTO personas (nombre, apellido1, apellido2, nacimiento) VALUES %n"));

			// Escribimos cada persona (MENOS LA ÚLTIMA) en una fila de la tabla SQL
			for (int i = 0; i < personas.length - 1; i++) {
				bw.append(String.format(" ('%s', '%s', '%s', '%d'), %n", personas[i].nombre, personas[i].apellido1,
						personas[i].apellido2, personas[i].nacimiento));
			}

			// Escribimos la última persona en la fila en SQL
			// la agregamos por separado, ya que el último elemento debe acabar en ; y no
			// con ,
			bw.append(String.format(" ('%s', '%s', '%s', '%d'); %n", personas[personas.length - 1].nombre,
					personas[personas.length - 1].apellido1, personas[personas.length - 1].apellido2,
					personas[personas.length - 1].nacimiento));

			System.out.println("El archivo 'poblador.sql' ha sido creado correctamente ✅ ");
			java.awt.Desktop.getDesktop().open(new java.io.File("src/ficheros/buffer/poblador.sql"));
			return true;

		} catch (FileNotFoundException e) {
			System.out.println("El archivo no pudo ser creado❌");
			return false;
		} catch (IOException e) {
			System.out.println("Error al escribir el archivo❌");
			return false;
		}

	}

	public static void main(String[] args) {

		// CARGAMOS LOS DATOS
		// Si no se pudieron cargar los datos se sale del programa inmediatamente
		// Evitamos que el programa siga y luego se intente crear personas con un array
		// null
		// Si se cargan bien devuelve true
		if (!PersonaBasica.cargarDatos())
			return;

		// GENERAMOS PERSONAS
		PersonaBasica[] listaPersonas = PersonaBasica.cantidadPersonas(10);

		// CREAMOS EL ARCHIVO SQL:
		PersonaBasica.creandoSQL(listaPersonas);

	}

}
