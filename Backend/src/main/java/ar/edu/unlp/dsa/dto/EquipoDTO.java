package ar.edu.unlp.dsa.dto;

import java.util.ArrayList;

/**
 * Created by Mokocchi on 2017/05/11.
 */
public class EquipoDTO {
	private Long id_team;
	private String nombre_team;
	private ArrayList<UsuarioDTO> usuarios;

	public Long getId_team() {
		return id_team;
	}

	public void setId_team(Long id_team) {
		this.id_team = id_team;
	}

	public String getNombre_team() {
		return nombre_team;
	}

	public void setNombre_team(String nombre_team) {
		this.nombre_team = nombre_team;
	}

	public ArrayList<UsuarioDTO> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<UsuarioDTO> usuarios) {
		this.usuarios = usuarios;
	}
}
