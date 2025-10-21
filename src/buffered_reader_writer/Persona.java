package buffered_reader_writer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Persona {

	private String nombre;
	private String apellido1;
	private String apellido2;
	private int nacimiento;

	private static String[] nombres;
	private static String[] apellidos;
	
	private static Random random = new Random();

	// creamos un constructor que tedrá:
	// nombres, apellidos y fecha de nacimiento aleatorios
	public Persona() {
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
	
	

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", nacimiento="
				+ nacimiento + "]";
	}

	public static void main(String[] args) {

		System.out.println(nombres);
		Persona p = new Persona();
		System.out.println(p);

	}

}
