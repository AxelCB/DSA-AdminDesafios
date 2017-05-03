package ar.edu.unlp.dsa.dto;

/**
 * Created by Mokocchi on 2017/04/17.
 */
public class ChallengeStatusSolvedDTO extends ChallengeStatusDTO {
	private Long quien_resolvio;
	private Long puntaje_obtenido;

	public ChallengeStatusSolvedDTO() {
	}

	public Long getQuien_resolvio() {
		return quien_resolvio;
	}

	public void setQuien_resolvio(Long quien_resolvio) {
		this.quien_resolvio = quien_resolvio;
	}

	public Long getPuntaje_obtenido() {
		return puntaje_obtenido;
	}

	public void setPuntaje_obtenido(Long puntaje_obtenido) {
		this.puntaje_obtenido = puntaje_obtenido;
	}
}
