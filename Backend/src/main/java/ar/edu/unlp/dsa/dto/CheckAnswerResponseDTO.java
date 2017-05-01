package ar.edu.unlp.dsa.dto;


/**
 * Created by Mokocchi on 2017/04/25.
 */
public class CheckAnswerResponseDTO {
	private String date;
	private String id_juego;
	private Long id_equipo;
	private Long id_usuario;
	private Long id_desafio;
	private Boolean resultado;
	private String descripcion;
	private Long puntaje;

	public CheckAnswerResponseDTO() {
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getId_juego() {
		return id_juego;
	}

	public void setId_juego(String id_juego) {
		this.id_juego = id_juego;
	}

	public Long getId_equipo() {
		return id_equipo;
	}

	public void setId_equipo(Long id_equipo) {
		this.id_equipo = id_equipo;
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Long getId_desafio() {
		return id_desafio;
	}

	public void setId_desafio(Long id_desafio) {
		this.id_desafio = id_desafio;
	}

	public Boolean getResultado() {
		return resultado;
	}

	public void setResultado(Boolean resultado) {
		this.resultado = resultado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(Long puntaje) {
		this.puntaje = puntaje;
	}
}
