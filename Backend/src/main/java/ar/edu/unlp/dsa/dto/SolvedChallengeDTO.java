package ar.edu.unlp.dsa.dto;

/**
 * Created by acollard on 22/3/17.
 */
public class SolvedChallengeDTO {

    private Long id_desafio;
    private Long resuelto_por;
    private String titulo;
    private Long puntaje;
    private Integer hints_utilizados;

    public SolvedChallengeDTO(){
    }

	public Long getId_desafio() {
		return id_desafio;
	}

	public void setId_desafio(Long id_desafio) {
		this.id_desafio = id_desafio;
	}

	public Long getResuelto_por() {
		return resuelto_por;
	}

	public void setResuelto_por(Long resuelto_por) {
		this.resuelto_por = resuelto_por;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(Long puntaje) {
		this.puntaje = puntaje;
	}

	public Integer getHints_utilizados() {
		return hints_utilizados;
	}

	public void setHints_utilizados(Integer hints_utilizados) {
		this.hints_utilizados = hints_utilizados;
	}
}
