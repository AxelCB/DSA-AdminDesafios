package ar.edu.unlp.dsa.dto;

/**
 * Created by Mokocchi on 2017/04/25.
 */
public class HintStatusDTO {
	private String date;
	private String id_juego;
	private Long id_equipo;
	private Long id_usuario;
	private Long id_hint;
	private String descripcion;
	private Long porcentaje;

	public HintStatusDTO() {
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Long porcentaje) {
		this.porcentaje = porcentaje;
	}

	public Long getId_hint() {
		return id_hint;
	}

	public void setId_hint(Long id_hint) {
		this.id_hint = id_hint;
	}
}
