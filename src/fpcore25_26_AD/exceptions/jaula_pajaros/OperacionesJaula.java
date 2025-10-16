package fpcore25_26_AD.exceptions.jaula_pajaros;

import java.util.Optional;
import java.util.Random;

public class OperacionesJaula implements AutoCloseable {

	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String RESET = "\u001B[0m";

	private Jaula jaula;
	private static Random random = new Random();

	public OperacionesJaula(Jaula jaula) throws JaulaException {
		int numRandom = random.nextInt(1, 6);
		this.jaula = jaula;

		if (jaula == null || jaula.isPuertaAbierta())
			throw new JaulaNotAvailableException("Jaula no está disponible");
		if (numRandom == 1)
			throw new PuertaAtascadaException("LA Puerta está atascada");
		else
			open();
	}

	public int contarPajaros() {
		System.out.print("Cantidad de pajaros: ");
		return jaula.getAlmacenamientoPajaros().size();
	}

	private Optional<Pajaro> meter(Pajaro pajaro) throws EspacioInsuficienteException {

		if (pajaro == null || jaula.getAlmacenamientoPajaros().contains(pajaro))
			return Optional.empty();

		if (jaula.getCapacidad() <= jaula.getAlmacenamientoPajaros().size())
			throw new EspacioInsuficienteException("No queda espacio en la jaula...");

		if (!jaula.isPuertaAbierta())
			return Optional.empty();

		jaula.getAlmacenamientoPajaros().add(pajaro);
		return Optional.of(pajaro);
	}

	public void meterPajaro(Pajaro pajaro) {
		try {
			meter(pajaro).ifPresentOrElse(p -> System.out.println("Pajaro metido: " + p),
					() -> System.out.println("No se pudo meter el pajaro..."));
		} catch (EspacioInsuficienteException e) {
			System.out.println(e.getMessage());
		}
	}

	private Optional<Pajaro> sacar(Pajaro pajaro) {

		if (!jaula.isPuertaAbierta() || !(jaula.getAlmacenamientoPajaros().contains(pajaro)))
			return Optional.empty();

		jaula.getAlmacenamientoPajaros().remove(pajaro);
		return Optional.of(pajaro);
	}

	public void sacarPajaro(Pajaro pajaro) {
		sacar(pajaro).ifPresentOrElse(p -> System.out.println("Pajaro sacado: " + p),
				() -> System.out.println("No se pudo sacar el pajaro (porque no está dentro de jaula)..."));
	}

	@Override
	public void close() throws PuertaAtascadaException {
		int numRandom = random.nextInt(1, 6);
		if (!jaula.isPuertaAbierta())
			throw new PuertaAtascadaException("\nPuerta ya está cerrada");
		if (numRandom == 1)
			throw new PuertaAtascadaException("\nPuerta está atascada");

		System.out.println("\nCerramos puerta de jaula!");
		jaula.setPuertaAbierta(false);
	}

	public void open() {
		jaula.setPuertaAbierta(true);
		System.out.println("Puerta de Jaula se abre!!");
	}

	public static void main(String[] args) {

		Jaula j1 = new Jaula();
		Pajaro p1 = new Pajaro();
		Pajaro p2 = new Pajaro();
		Pajaro p3 = new Pajaro();
		Pajaro p4 = new Pajaro();
		Pajaro p5 = new Pajaro();

		System.out.println("\n===== PRUEBAS =====\n");
		System.out.println("Todos los pájaros existentes: " + p1 + ", " + p2 + ", " + p3 + ", " + p4 + ", " + p5);
		System.out.println("Jaula 1: " + j1 + "\n");

		try (OperacionesJaula op1 = new OperacionesJaula(j1)) {

			// 1 MÉTODO:
			System.out.println("→ " + op1.contarPajaros());

			// 2 MÉTODO:
			System.out.println(RED + "\nIntentando meter pajaro nulo..." + RESET);
			op1.meterPajaro(null);

			System.out.println(GREEN + "\nMetiendo p1..." + RESET);
			op1.meterPajaro(p1);
			System.out.println("→ " + op1.contarPajaros());

			System.out.println(RED + "\nIntentando meter p1 de nuevo..." + RESET);
			op1.meterPajaro(p1);

			System.out.println(GREEN + "\nMetiendo p2..." + RESET);
			op1.meterPajaro(p2);
			System.out.println(GREEN + "Metiendo p3..." + RESET);
			op1.meterPajaro(p3);
			System.out.println(GREEN + "Metiendo p4..." + RESET);
			op1.meterPajaro(p4);
			System.out.println(GREEN + "Metiendo p5..." + RESET);
			op1.meterPajaro(p5);

			System.out.println();
			System.out.println("→ " + op1.contarPajaros());
			System.out.println("Jaula 1 AHORA: " + j1);

			// 3 MÉTODO:
			System.out.println(GREEN + "\nSacando p2..." + RESET);
			op1.sacarPajaro(p2);
			System.out.println("→ " + op1.contarPajaros());

			System.out.println(RED + "\nIntentando sacar p2 otra vez..." + RESET);
			op1.sacarPajaro(p2);

			System.out.println(RED + "\nIntentando sacar " + GREEN + "p5" + RESET);
			op1.sacarPajaro(p5);

		} catch (JaulaNotAvailableException e) {
			System.out.println(RED + e.getMessage() + RESET);
		} catch (PuertaAtascadaException e) {
			System.out.println(RED + e.getMessage() + RESET);
		} catch (JaulaException e1) {
			System.out.println(RED + e1.getMessage() + RESET);
		}
	}
}
