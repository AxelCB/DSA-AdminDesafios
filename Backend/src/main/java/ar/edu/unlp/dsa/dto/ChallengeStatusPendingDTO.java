package ar.edu.unlp.dsa.dto;

/**
 * Created by Mokocchi on 2017/04/18.
 */
public class ChallengeStatusPendingDTO extends ChallengeStatusDTO {
	private HintDTO hint1;
	private HintDTO hint2;
	private String adjunto;

	public ChallengeStatusPendingDTO() {
	}

	public HintDTO getHint1() {
		return hint1;
	}

	public void setHint1(HintDTO hint1) {
		this.hint1 = hint1;
	}

	public HintDTO getHint2() {
		return hint2;
	}

	public void setHint2(HintDTO hint2) {
		this.hint2 = hint2;
	}

	public String getAdjunto() {
		return adjunto;
	}

	public void setAdjunto(String adjunto) {
		this.adjunto = adjunto;
	}
}
