package ar.edu.unlp.dsa.dto;

import java.util.Collection;

/**
 * Created by Mokocchi on 2017/04/17.
 */
public class SolvedChallengeListDTO extends ChallengeListDTO {
	private Collection<SolvedChallengeDTO> desafios;

	public SolvedChallengeListDTO() {
	}

	public Collection<SolvedChallengeDTO> getDesafios() {
		return desafios;
	}

	public void setDesafios(Collection<SolvedChallengeDTO> desafios) {
		this.desafios = desafios;
	}
}
