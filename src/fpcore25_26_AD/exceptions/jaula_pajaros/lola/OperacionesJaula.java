package fpcore25_26_AD.exceptions.jaula_pajaros.lola;

import java.util.Optional;
import java.util.Random;

public class OperacionesJaula implements AutoCloseable {
	
	private Jaula jaula;
	private static Random random = new Random();

	// Métodos auxiliares propios
	private boolean puertaAtascada() {
		int numAleatorio = random.nextInt(1, 6);
		return numAleatorio == 1;
	}

	private boolean jaulaLlena() {
		return pajarosEnJaula() >= jaula.getNumMaximo();
	}

	// Constructor
	public OperacionesJaula(Jaula jaula) throws JaulaException {
		if (jaula == null) {
			throw new JaulaNotAvailableException("EXCEPCIÓN: La jaula no está disponible");
		}

		if (!jaula.isAbierta()) {
			if (puertaAtascada()) {
				throw new PuertaAtascadaException("EXCEPCIÓN: La puerta esta atascada.");
			} else {
				jaula.setAbierta(true);
				System.out.println("La puerta se ha abierto correctamente.");
			}
		}
		this.jaula = jaula;
	}

	// Métodos
	public int pajarosEnJaula() {
		return jaula.getAlmacenamiento().size();
	}

	
	
	public Optional<Pajaro> meterPajaro(Pajaro pajaro) throws EspacioInsuficienteException {

		if (pajaro == null || jaula.getAlmacenamiento().contains(pajaro)) {
			System.out.println("No se puede meter pajaro");
			return Optional.empty();
		}
		
		if (jaulaLlena()) {
			throw new EspacioInsuficienteException("EXCEPCIÓN: Espacio insuficiente en la jaula.");
		}
		
		if(!jaula.isAbierta()) {
			System.out.println("La jaula está cerrada");
			return Optional.empty();
		}
		
		else {
			System.out.print("Paharo ñadido: ");
			jaula.getAlmacenamiento().add(pajaro);
		}
		return Optional.of(pajaro);
	}
	
	
	
	

	public Pajaro sacaPajaro(Pajaro pajaro) {
		if (!jaula.isAbierta() || !jaula.getAlmacenamiento().contains(pajaro)) {
			
		} else {
			jaula.getAlmacenamiento().remove(pajaro);
		}
		return pajaro;
	}

	@Override
	public void close() throws JaulaException {
		if (jaula.isAbierta()) {
			if (puertaAtascada()) {
				throw new PuertaAtascadaException("EXCEPCIÓN: La puerta esta atascada.");
			} else {
				jaula.setAbierta(false);
				System.out.println("La jaula se cerró.");
			}
		}
	}

	public static void main(String[] args) {
		Jaula j1 = new Jaula(1);
		Pajaro p1 = new Pajaro();
		Pajaro p2 = new Pajaro();
		Pajaro p3 = new Pajaro();

		System.out.printf("Jaula al inicio%n");
		System.out.println(j1.toString());

		System.out.printf("Jaula con pajaros%n");
		try (OperacionesJaula oj1 = new OperacionesJaula(j1)) {
			//oj1.meterPajaro(p1);
			//oj1.meterPajaro(p2);
			//oj1.sacaPajaro(p1);
			
			try {
				System.out.println("Meter: " +oj1.meterPajaro(null));
			} catch (EspacioInsuficienteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Sacar " + oj1.sacaPajaro(p2));
			
			System.out.println(j1.toString());
		} catch (JaulaException e) {
			System.out.print(e.getMessage());
		}

	}

}
