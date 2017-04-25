package ar.edu.unlp.dsa.dto;

import java.util.Collection;

/**
 * Created by Mokocchi on 2017/04/17.
 */
public class ChallengeListDTO {
	private String date;
	private String id_juego;
	private Long id_usuario;
	private Long id_equipo;

	public ChallengeListDTO() {
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

	public String getId_juego() {
		return id_juego;
	}

	public void setId_juego(String id_juego) {
		this.id_juego = id_juego;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
