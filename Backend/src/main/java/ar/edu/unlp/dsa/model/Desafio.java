package ar.edu.unlp.dsa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Mokocchi on 2017/03/15.
 */
public class Desafio  {

	private Long id_desafio;
	private String titulo;
	private String categoria;
	private Long puntos;
	private String descripcion;
	private Pista hint1;
	private Pista hint2;
	private String adjunto;

	public Long getId_desafio() {
		return id_desafio;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getCategoria() {
		return categoria;
	}

	public Long getPuntos() {
		return puntos;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Pista getHint1() {
		return hint1;
	}

	public Pista getHint2() {
		return hint2;
	}

	public void setId_desafio(Long id_desafio) {
		this.id_desafio = id_desafio;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setPuntos(Long puntos) {
		this.puntos = puntos;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setHint1(Pista hint1) {
		this.hint1 = hint1;
	}

	public void setHint2(Pista hint2) {
		this.hint2 = hint2;
	}

	public void setAdjunto(String adjunto) {
		this.adjunto = adjunto;
	}

	public String getAdjunto() {
		return adjunto;
	}

	public Desafio (Challenge challenge, Pista hint1, Pista hint2) {
		this.id_desafio = challenge.getId();
		this.titulo = challenge.getTitle();
		this.categoria = challenge.getCategory().getName();
		this.puntos = challenge.getPoints();
		this.descripcion = challenge.getDescription();
		this.hint1 = hint1;
		this.hint2 = hint2;
		this.adjunto = challenge.getAttachedFileUrl();
	}
}