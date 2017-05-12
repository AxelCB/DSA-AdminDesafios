package ar.edu.unlp.dsa.dto;

/**
 * Created by Mokocchi on 2017/05/11.
 */
public class UsuarioDTO {
	private Long id_usuario;
	private String nombreDeUsuario;

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombreDeUsuario() {
		return nombreDeUsuario;
	}

	public void setNombreDeUsuario(String nombreDeUsuario) {
		this.nombreDeUsuario = nombreDeUsuario;
	}
}
