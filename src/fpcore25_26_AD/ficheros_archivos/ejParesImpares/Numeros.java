package fpcore25_26_AD.ficheros_archivos.ejParesImpares;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Numeros {

	public static void main(String[] args) {

		List<Integer> numerosPares = new ArrayList<>();
		List<Integer> numerosImpares = new ArrayList<>();

		try (FileWriter fw1 = new FileWriter("src/ficheros/numerosPares.txt");
				FileWriter fw2 = new FileWriter("src/ficheros/numerosImpares.txt");) {
			
			for (int i = 0; i < 20; i++) {
				if(i % 2 == 0) fw1.write(i + "\n");
				else fw2.write(i + "\n");
				
			}
			fw1.flush();
			System.out.println("Numeros agregados a los archivos");

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		finally {
			System.out.println("Gracias por confiar en nosotros!");
		}
		
		try (FileReader fr1 = new FileReader("src/ficheros/numerosPares.txt");
				FileReader fr2 = new FileReader("src/ficheros/numerosImpares.txt");
				FileWriter frTodos = new FileWriter("src/ficheros/todosNumeros.txt");
				FileWriter frOrdenados = new FileWriter("src/ficheros/numerosOrdenados.txt");
				) {
			
			int c; 
			while((c = fr1.read()) != -1) {
				frTodos.write(c);
			}
			while((c = fr2.read()) != -1) {
				frTodos.write(c);
			}
			System.out.println();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	

}
