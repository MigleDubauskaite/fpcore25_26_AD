package fpcore25_26_AD.exceptions.autocloseable;

public class Door implements AutoCloseable{

	
	public void open() {
		System.out.println("Abriendo puerta");
	}
	
	@Override
	public void close() throws Exception {
		System.out.println("Cerrando puerta");
	}
	
	public static void main(String[] args) {
		try (Door door = new Door()) {
			door.open();
			int i = 2/0;
		} catch (Exception e) {
			System.out.println("Detectada excepción");
		}
        // No es necesario llamar a door.close(), se llama automáticamente al finalizar el bloque try
	}

}
