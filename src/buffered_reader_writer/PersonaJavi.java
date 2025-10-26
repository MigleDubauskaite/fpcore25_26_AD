package buffered_reader_writer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class PersonaJavi {

	private String nombre;
	private String apellido1;
	private String apellido2;
	private int nacido;

	private static String pathNombres = "archivos/personas/nombres.txt";
	private static String pathApellidos = "archivos/personas/apellidos.txt";
	private static String pathSalida = "archivos/personas/poblador.sql";

	// la lista será solo una, por tanto, la ponemos estatica
	// la lista es fija por tanto ponemos final: puede ser modificada pero no sin
	// ¿referencia cambiada?
	private final static List<String> nombres = importarLista(pathNombres);
	private final static List<String> apellidos = importarLista(pathApellidos);

	// solo una y no cambie. Nos da la instancia de Random
	// se encarga de multi thread, una instancia en muchos sitios.
	private static final RandomGenerator RANDOM = RandomGenerator.getDefault();
	// También permite utilizar varios algoritmos con el método de RandomGenerator
	// OF:
	/*
	 * // Crear un generador con un algoritmo moderno específico RandomGenerator
	 * random = RandomGeneratorFactory.of("L64X128MixRandom").create();
	 */

	public PersonaJavi() {
		nombre = elementoAleatorio(nombres);
		apellido1 = elementoAleatorio(apellidos);
		apellido2 = elementoAleatorio(apellidos);
		nacido = RANDOM.nextInt(1920, 2026);
	}

	private static String elementoAleatorio(List<String> lista) {
		return lista.get(RANDOM.nextInt(lista.size()));
	}

	// lo ideal es crear un método estatico para cuando se cree la clase se creen
	// las listas de nombres y apellidos
	private static List<String> importarLista(String file) {

		List<String> listaDatos = null;
		
		try (BufferedReader br = new BufferedReader(new FileReader(file));) {

			// metemos en una lista un Array (no es modificable → retorna Immutable Collections)
			//lista = List.of(br.readLine().trim().split(","));
			
			String[] arrayDatos = br.readLine().trim().split(",");
			for (int i = 0; i < arrayDatos.length; i++) {
				arrayDatos[i] =  arrayDatos[i].trim();
			}
			listaDatos = List.of(arrayDatos);

		} catch (IOException e) {
			System.out.print("ERROR: ");
			System.out.println(e.getMessage());
		}

		return listaDatos;
	}
	
	private static boolean generarSalida(String file, List<PersonaJavi> personas) {
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file));) {
			
			bw.write(String.format("CREATE DATABASE mundo;%n"));
			bw.write(String.format("show databases;%n"));
			bw.write(String.format("USE mundo;%n"));
			bw.write(String.format("CREATE TABLE personas (nombre VARCHAR(30), apellido1 VARCHAR(30), apellido2 VARCHAR(30), nacimiento INT);%n"));
			bw.write(String.format("DESCRIBE personas;%n"));
			bw.write(String.format("INSERT INTO personas (nombre, apellido1, apellido2, nacimiento) VALUES %n"));
			
			for(int i=0; i < personas.size() - 1; i++) {
				bw.write(String.format("('%s', '%s', '%s', %d),%n", 
					personas.get(i).nombre, personas.get(i).apellido1, personas.get(i).apellido2, personas.get(i).nacido));
			}
			
			// el último insert acaba en punto y coma (;)
			bw.write(String.format("('%s', '%s', '%s', %d);", 
				personas.get(personas.size() - 1).nombre, personas.get(personas.size() - 1).apellido1, personas.get(personas.size() - 1).apellido2, 
				personas.get(personas.size() - 1).nacido));
			
			java.awt.Desktop.getDesktop().open(new java.io.File(file));
			return true;
			
		} catch (IOException e) {
			System.out.printf("ERROR escribiendo en un fichero %s %n: ", file);
			return false;
		}
	}

	public static void main(String[] args) {
		int cantidadPersonas = 10;
		List<PersonaJavi> personas = new ArrayList<>();
		for (int i = 1; i <= cantidadPersonas; i++) {
			personas.add(new PersonaJavi());
		}

		System.out.println(PersonaJavi.nombres);
		System.out.println(PersonaJavi.apellidos);
		
		// insertamos base de datos
		System.out.println( generarSalida(pathSalida, personas) ? "✅" : "❌");

	}
}
