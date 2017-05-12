package ar.edu.unlp.dsa.dto;

import ar.edu.unlp.dsa.model.Team;

import java.util.ArrayList;

/**
 * Created by Mokocchi on 2017/05/10.
 */
public class TeamListDTO {
	public ArrayList<EquipoDTO> equipos;

	public ArrayList<EquipoDTO> getEquipos() {
		return equipos;
	}

	public void setEquipos(ArrayList<EquipoDTO> equipos) {
		this.equipos = equipos;
	}
}
