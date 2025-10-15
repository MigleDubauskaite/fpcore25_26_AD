package fpcore25_26_AD.exceptions.jaula_pajaros;

public class Pajaro {

	private static int nextID = 1;
	private int id;

	public Pajaro() {
		id = nextID++;
	}

	@Override
	public String toString() {
		return String.format("P-%d", id);
	}

	public int getId() {
		return id;
	}

}
