package fpcore25_26_AD.file;

import java.io.File;

public class FileUtils {

	public static boolean analiza(File f) {

		if (f == null) {
			System.out.println("El fichero es nulo...");
			return false;
		} else if (!f.exists()) {
			System.out.println("El fichero no existe...");
			return false;
		} else {

			System.out.printf("Permisos de lectura: %s %nPermisos de escritura: %s %nPermisos de ejecución: %s %n",
					f.canRead() ? "sí" : "no", f.canWrite() ? "sí" : "no", f.canExecute() ? "sí" : "no");
			System.out.printf("El parent es: %s (%s) %n", f.getParent(), f.getName());

			if (f.isFile()) {
				System.out.printf("Es un fichero cuyo tamaño es %.4f Mb %n", (double) f.length() / 1024 / 1024);
			} else if (f.isDirectory()) {
				File[] listaArchivos = f.listFiles();
				for (File file : listaArchivos) {
					if (file != null && file.isFile()) {
						System.out.printf("Archivos contenidos en el directorio: %s %n", file.getName());
					}
				}
			}

			return true;
		}

	}

}
