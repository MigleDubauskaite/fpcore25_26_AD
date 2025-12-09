package ficheros_binarios;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopiaIdentica {

	public static void main(String[] args) {

		// FileInputStream: leyendo archivos
		// FileOutputStream: escribiendo en archivos
		
		try (FileInputStream fis = new FileInputStream("src/ficheros/binarios/imagen.jpg");
				FileOutputStream fos = new FileOutputStream("src/ficheros/binarios/imagen2.jpg");) {

			int b;

			while ((b = fis.read()) != -1) {
				fos.write(b);
			}
		
			System.out.println("Escritura en el fichero se realizó con éxito");

		} catch (IOException e) {
			System.out.println("Problemas con los archivos");
		} finally {
			System.out.println("Gracias por su confianza");
		}
	}
}
