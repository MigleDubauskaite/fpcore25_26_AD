package fpcore25_26_AD.ficheros_archivos.ejParesImpares;

import java.io.FileWriter;
import java.io.IOException;

public class Numeros {
	
	public static void main(String[] args) {
		
		int[] numerosPares = {1};
		
		try(FileWriter fw = new FileWriter("src/ficheros/pares.txt")){
			
			for (int i = 0; i < 10; i++) {
				fw.append((char)i);
				if (i != numerosPares.length - 1)
					fw.append("\n");
			}
			fw.flush();
			System.out.println(numerosPares);
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		
	}

}
