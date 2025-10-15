package fpcore25_26_AD.exceptions.autocloseable;

public class Window {
	
	public void open() {
		System.out.println("Abriendo puerta");
	}
	
	public void close() throws Exception {
		System.out.println("Cerrando puerta");
	}
	
	public static void main(String[] args) {
		Window w1 = new Window();
		try {
			w1.open();
			int i = 2/0;
		} catch (Exception e) {
			System.out.println("Detectada excepción");
		} finally {
			try {
				w1.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
