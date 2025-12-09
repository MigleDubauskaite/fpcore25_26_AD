package actividad_ad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CamisetaBorrador {

	// 1: VALIDANDO LÍNEAS
	private final static String ORIGINAL = "archivos/camisetas/camisetas.txt";
	private final static String OUTPUT_VALIDAS = "archivos/camisetas/camisetas_sin_errores_de_linea.txt";
	private final static String OUTPUT_ERRORES = "archivos/camisetas/camisetas_con_errores_de_linea.log";

	// 1: VALIDANDO LÍNEAS

	private static void filtrarCamisetasSinErrores() {

//		campos validos: 
//		id, cantidad, color, marca, modelo, talla

		int total = 0;
		int eliminadas = 0;
		StringBuilder sbErrores = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new FileReader(ORIGINAL));
				BufferedWriter bwValido = new BufferedWriter(new FileWriter(OUTPUT_VALIDAS, false));
				BufferedWriter bwError = new BufferedWriter(new FileWriter(OUTPUT_ERRORES, false))) {

			String linea;

			bwError.write("camisetas_con_errores_de_linea.log\n\n");

			while ((linea = br.readLine()) != null) {

				total++;
				
				String[] partes = linea.split(",");

//				tenemos que tener 6 comas porque son 7 campos en total

				if (partes.length == 6) {
					bwValido.write(linea.trim());
					bwValido.newLine();
				} else {
					eliminadas++;
					sbErrores.append(linea).append("\n");
					System.out.println("❌ Línea eliminada: " + linea);
				}

			}

			bwError.write("Total líneas analizadas: " + total + "\n");
			bwError.write("Total líneas eliminadas: " + eliminadas + "\n");
			bwError.write("Las líneas eliminadas son:\n");
			bwError.write(sbErrores.toString());

			System.out.println("\n✅ Proceso terminado.");
			System.out.println("Total líneas analizadas: " + total);
			System.out.println("Líneas válidas: " + (total - eliminadas));
			System.out.println("Líneas eliminadas: " + eliminadas);

		} catch (IOException e) {
			System.out.println("❌Error validando líneas: " + e.getMessage());
		}

	}

	public static void main(String[] args) {

		try {
			filtrarCamisetasSinErrores();
		} catch (Exception e) {
			System.out.println("Error inesperado en main: " + e.getMessage());
		}

	}

}
