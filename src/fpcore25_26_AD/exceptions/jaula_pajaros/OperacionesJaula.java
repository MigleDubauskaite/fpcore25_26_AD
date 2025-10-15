package fpcore25_26_AD.exceptions.jaula_pajaros;

import java.util.Optional;
import java.util.Random;

public class OperacionesJaula implements AutoCloseable {

	private Jaula jaula;
	private static Random random = new Random();

	public OperacionesJaula(Jaula jaula) throws JaulaNotAvailableException, PuertaAtascadaException {
		int numRandom = random.nextInt(1, 6);
		this.jaula = jaula;

		if (jaula.isPuertaAbierta())
			throw new JaulaNotAvailableException("Jaula ya está siendo utilizada");
		if (numRandom == 1)
			throw new PuertaAtascadaException("La puerta está atascada");
		else {
			open();
		}
	}

	public int contarPajaros() {
		System.out.print("Cantidad de pajaros: ");
		return jaula.getAlmacenamientoPajaros().size();
	}

	public Pajaro meterPajaro(Pajaro pajaro) throws EspacioInsuficienteException {

		if (jaula.getCapacidadMax() <= jaula.getAlmacenamientoPajaros().size())
			throw new EspacioInsuficienteException("No queda espacio en la jaula...");
		else {
			System.out.print("El pajaro añadido: ");
			jaula.getAlmacenamientoPajaros().add(pajaro);
		}
		return pajaro;
	}

	private Optional<Pajaro> sacarPajaro(Pajaro pajaro) {

		if (jaula.getAlmacenamientoPajaros().contains(pajaro)) {
			jaula.getAlmacenamientoPajaros().remove(pajaro);
			return Optional.of(pajaro);
		}
		return Optional.empty();
	}
	
	public void sacarPajaroMensaje(Pajaro pajaro) {
		sacarPajaro(pajaro).ifPresentOrElse(p -> System.out.println("Pajaro sacado: " + p),
				() -> System.out.println("No se pudo sacar el pajaro (porque no está dentro de jaula): "));
	}

	@Override
	public void close() throws PuertaAtascadaException {
		int numRandom = random.nextInt(1, 6);
		if (!jaula.isPuertaAbierta()) throw new PuertaAtascadaException("Puerta ya está cerrada");
		if (numRandom == 1)	throw new PuertaAtascadaException("Puerta está atascada");
		
		else {
			System.out.println("Cerramos puerta!");
			jaula.setPuertaAbierta(false);
		}
	}

	public void open() {
		jaula.setPuertaAbierta(true);
		System.out.println("Abriendo puerta");
	}

	public static void main(String[] args) {

		Jaula j1 = new Jaula();
		Pajaro p1 = new Pajaro();

		System.out.println(j1);
		System.out.println(p1);

		try (OperacionesJaula op1 = new OperacionesJaula(j1)) {

			// 1
			System.out.println(op1.contarPajaros());

			// 2
			try {
				System.out.println(op1.meterPajaro(new Pajaro()));
				System.out.println(op1.meterPajaro(p1));
			} catch (EspacioInsuficienteException e) {
				System.out.println(e.getMessage());
			}

			// 3
			op1.sacarPajaroMensaje(p1);

		} catch (JaulaNotAvailableException e) {
			System.out.println(e.getMessage());
		} catch (PuertaAtascadaException e) {
			System.out.println(e.getMessage());
		} 

		System.out.println(j1);

	}
}
