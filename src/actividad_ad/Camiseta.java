package actividad_ad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Camiseta {

	private static int totalLineasAnalizadas = 0;

//	1: VALIDANDO LÍNEAS
	private final static String ORIGINAL = "archivos/camisetas/camisetas.txt";
	private final static String OUTPUT_VALIDAS = "archivos/camisetas/camisetas_sin_errores_de_linea.txt";

//	2: REPORT ERRORES
	private final static String OUTPUT_REPORT = "archivos/camisetas/camisetas_con_errores_de_linea.log";

	private static List<String> lineasInvalidas = new ArrayList<>();

//	4: ARCHIVO NORMALIZADO
	private final static String OUTPUT_NORMALIZADO = "archivos/camisetas/camisetas_texto_normalizado.txt";

//	5: REPORT FRECUENCIA ANTES DE DEPURACION
	private final static String OUTPUT_FRECUENCIAS_ANTES = "archivos/camisetas/camisetas_frecuencias_antes.log";

//	5: REPORT FRECUENCIA DESPUES DE DEPURACION
	private final static String OUTPUT_FRECUENCIAS_DESPUES = "archivos/camisetas/camisetas_frecuencias_despues.log";

//	6: ARCHIVO FINAL
	private final static String OUTPUT_FINAL = "archivos/camisetas/camisetas_finales.txt";

//	7: ARCHIVO SQL
	private final static String OUTPUT_SQL = "archivos/camisetas/camisetas.sql";

//	1: VALIDANDO LÍNEAS

	private static void filtrarCamisetasSinErrores() {

		/*
		 * 
		 * Verifica la estructura: 6 campos (5 comas) Cuenta líneas validas e inválidas
		 * No limpia texto ni maneja vacío
		 */

		lineasInvalidas.clear();
		totalLineasAnalizadas = 0;

//		campos validos: 
//		id, cantidad, color, marca, modelo, talla

		try (BufferedReader br = new BufferedReader(new FileReader(ORIGINAL));
				BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_VALIDAS, false));) {

			String linea;
			int contadorLineasValidas = 0;

			while ((linea = br.readLine()) != null) {

				totalLineasAnalizadas++;

				int sumaComas = 0;

				for (char c : linea.toCharArray()) {
					if (c == ',')
						sumaComas++;
				}

				if (sumaComas == 5) {
					bw.write(linea);
					bw.newLine();
					contadorLineasValidas++;
				} else {
					lineasInvalidas.add(linea);
				}

			}

			System.out.println(
					"Se han guardado " + contadorLineasValidas + " líneas válidas en '" + OUTPUT_VALIDAS + "'.");

		} catch (IOException e) {
			System.out.println("❌Error validando líneas: " + e.getMessage());
		}
	}

//	2: REPORT ERRORES

	private static void generarReporteErrores() {

		/*
		 * Reporta el resultado de datos (lineas analizadas y lineas invalidas)
		 */

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_REPORT, false))) {

			bw.write("Total líneas analizadas: " + totalLineasAnalizadas + "\n");
			bw.write("Total líneas eliminadas: " + lineasInvalidas.size() + "\n\n");
			bw.write("Las líneas eliminadas son: " + "\n");

			for (String lineas : lineasInvalidas) {
				bw.write(lineas);
				bw.newLine();
			}

			System.out.println("Se ha generado el reporte de errores en '" + OUTPUT_REPORT + "'.");

		} catch (IOException e) {
			System.out.println("❌Error generando reporte: " + e.getMessage());
		}

	}

//	3: REPORT FRECUENCIA

	private static void sumarValoresFrecuencias(Map<String, Integer> mapa, String clave) {
		mapa.put(clave, mapa.getOrDefault(clave, 0) + 1);
	}

	private static void escribirFrecuencias(BufferedWriter bw, String nombre, Map<String, Integer> datos)
			throws IOException {

		bw.write("Columna: " + nombre + "\n");

		for (String clave : datos.keySet()) {
			bw.write(" " + clave + " → " + datos.get(clave) + "\n");
		}

		bw.write("\n");
	}

	private static void generarReporteFrecuencia(String archivoEntrada, String archivoSalida) {

		/*
		 * Cuenta cuántas veces aparece cada valor por columna: color, marca, modelo,
		 * talla, cantidad
		 */

		Map<String, Integer> cantidad = new TreeMap<>();
		Map<String, Integer> color = new TreeMap<>();
		Map<String, Integer> marca = new TreeMap<>();
		Map<String, Integer> modelo = new TreeMap<>();
		Map<String, Integer> talla = new TreeMap<>();

		try (BufferedReader br = new BufferedReader(new FileReader(archivoEntrada))) {

			String linea;

			while ((linea = br.readLine()) != null) {

				String[] campos = linea.split(",", -1);

				sumarValoresFrecuencias(cantidad, campos[1].trim());
				sumarValoresFrecuencias(color, campos[2].trim());
				sumarValoresFrecuencias(marca, campos[3].trim());
				sumarValoresFrecuencias(modelo, campos[4].trim());
				sumarValoresFrecuencias(talla, campos[5].trim());

			}

		} catch (IOException e) {
			System.out.println("Error leyendo archivo: " + e.getMessage());
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida, false))) {

			bw.write("=== REPORTE DE FRECUENCIAS ===\n\n");

			escribirFrecuencias(bw, "Color", color);
			escribirFrecuencias(bw, "Marca", marca);
			escribirFrecuencias(bw, "Modelo", modelo);
			escribirFrecuencias(bw, "Talla", talla);
			escribirFrecuencias(bw, "Cantidad", cantidad);

			System.out.println("Se ha generado el reporte de frecuencias en '" + archivoSalida + "'.");

		} catch (IOException e) {
			System.out.println("Error escribiendo reporte: " + e.getMessage());
		}

	}

//	4 con 5: ARCHIVO NORMALIZADO

	private final static String normalizarTexto(String texto) {

		if (texto == null)
			return "(vacío)";

//		\p{M} en regex significa “marca de diacrítico” (acentos, diéresis ¨, tilde...)
		String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("\\p{M}", "")
				.replaceAll("ñ", "n").replaceAll("Ñ", "N");

		return textoNormalizado;
	}

	private static void normalizarArchivo() {

		try (BufferedReader br = new BufferedReader(new FileReader(OUTPUT_VALIDAS));
				BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_NORMALIZADO, false))) {

			String linea;

			while ((linea = br.readLine()) != null) {

				String[] campos = linea.split(",", -1);

				for (int i = 0; i < campos.length; i++) {
					campos[i] = normalizarTexto(campos[i].trim());

					if (campos[i].isEmpty())
						campos[i] = "(vacío)";
				}

//				Guardamos la línea limpia (normalizada)
				String lineaNormalizada = String.join(",", campos);

				bw.write(lineaNormalizada);
				bw.newLine();
			}

			System.out.println("Archivo normalizado generado en '" + OUTPUT_NORMALIZADO + "'.");

		} catch (IOException e) {
			System.out.println("❌ Error normalizando texto: " + e.getMessage());
		}

	}

//	6: GENERANDO FICHERO FINAL

	private static void generarArchivoFinal() {

		try (BufferedReader br = new BufferedReader(new FileReader(OUTPUT_NORMALIZADO));
				BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FINAL, false))) {

			String linea;
			while ((linea = br.readLine()) != null) {
				bw.write(linea);
				bw.newLine();
			}

			System.out.println("Archivo final generado en '" + OUTPUT_FINAL + "'.");

		} catch (IOException e) {
			System.out.println("❌ Error generando archivo final: " + e.getMessage());
		}

	}

//	7: GENERANDO FICHERO SQL

//	método auxiliar (para evitar el error en sql con ')

	private static String manejadorComillasSql(String texto) {
		if (texto == null)
			return "";
		return texto.replace("'", "''");
	}

	public static void generarSQL() {

		try (BufferedReader br = new BufferedReader(new FileReader(OUTPUT_FINAL));
				BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_SQL, false))) {

			bw.write(String.format("CREATE DATABASE IF NOT EXISTS camisetas;%n"));
			bw.write(String.format("SHOW DATABASES;%n"));
			bw.write(String.format("USE camisetas;%n"));
			bw.write(String.format(
					"CREATE TABLE IF NOT EXISTS camisetas (id INT AUTO_INCREMENT PRIMARY KEY, cantidad INT, color VARCHAR(50), marca VARCHAR(50), modelo VARCHAR(50), talla VARCHAR(30));%n"));
			bw.write(String.format("DESCRIBE camisetas;%n"));

			String linea;

			while ((linea = br.readLine()) != null) {

				String[] datos = linea.split(",", -1);

				if (datos.length != 6)
					continue;

				String cantidad = datos[1].trim();
				String color = manejadorComillasSql(datos[2].trim());
				String marca = manejadorComillasSql(datos[3].trim());
				String modelo = manejadorComillasSql(datos[4].trim());
				String talla = manejadorComillasSql(datos[5].trim());

				bw.write(String.format(
						"INSERT INTO camisetas (cantidad, color, marca, modelo, talla) VALUES (%s, '%s', '%s', '%s', '%s');%n",
						cantidad, color, marca, modelo, talla));
			}

			System.out.println("Archivo SQL generado en '" + OUTPUT_SQL + "'.");

		} catch (IOException e) {
			System.out.println("❌ Error generando SQL: " + e.getMessage());
		}

	}

	public static void main(String[] args) {

		try {
			filtrarCamisetasSinErrores();
			generarReporteErrores();

//			frecuencias antes depurar-normalizar
			generarReporteFrecuencia(OUTPUT_VALIDAS, OUTPUT_FRECUENCIAS_ANTES);

			normalizarArchivo();

//			frecuencias despues depurar-normalizar
			generarReporteFrecuencia(OUTPUT_NORMALIZADO, OUTPUT_FRECUENCIAS_DESPUES);

			generarArchivoFinal();

			generarSQL();

		} catch (Exception e) {
			System.out.println("Error inesperado en main: " + e.getMessage());
		}

	}

}
