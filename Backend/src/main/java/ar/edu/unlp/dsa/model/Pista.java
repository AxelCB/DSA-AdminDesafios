package ar.edu.unlp.dsa.model;

/**
 * Created by Mokocchi on 2017/03/15.
 */
public class Pista {
	private Long id_hint;
	private boolean disponible; //wasn't 'bought' already by someone in the team
	private Long porcentaje;

	public Long getId_hint() {
		return id_hint;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public Long getPorcentaje() {
		return porcentaje;
	}

	public Pista(Hint hint, boolean disponible) {
		this.id_hint = hint.getId();
		this.disponible = disponible;
		this.porcentaje = hint.getPointsPercentageCost();
	}
}