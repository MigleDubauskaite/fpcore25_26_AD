package fpcore25_26_AD.exceptions.jaula_pajaros;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jaula {

	private static int nextID = 1;
	private int id;
	private boolean puertaAbierta;
	private int capacidad;
	private List<Pajaro> almacenamientoPajaros;

	private static Random random = new Random();

	public int getCapacidad() {
		return capacidad;
	}

	public List<Pajaro> getAlmacenamientoPajaros() {
		return almacenamientoPajaros;
	}

	public void setPuertaAbierta(boolean puertaAbierta) {
		this.puertaAbierta = puertaAbierta;
	}

	public boolean isPuertaAbierta() {
		return puertaAbierta;
	}

	public Jaula() {
		id = nextID++;
		puertaAbierta = false;
		this.capacidad = random.nextInt(3, 7);
		almacenamientoPajaros = new ArrayList<>();
	}

//	@Override
//	public String toString() {
//		return String.format("J-%d %s", id, almacenamientoPajaros);
//	}

	@Override
	public String toString() {
		return "Jaula [id=" + id + ", puertaAbierta=" + puertaAbierta + ", capacidad=" + capacidad
				+ ", almacenamientoPajaros=" + almacenamientoPajaros + "]";
	}

}
