package ar.edu.unlp.dsa.dto;

/**
 * Created by acollard on 22/3/17.
 */
public class ChallengeDTO {

    private Long id_desafio;
    private String titulo;
    private String categoria;
    private Long puntos;
    private String descripcion;
    private HintDTO hint1;
    private HintDTO hint2;
    private String adjunto;

    public ChallengeDTO(){

    }

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

    public HintDTO getHint1() {
        return hint1;
    }

    public HintDTO getHint2() {
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

    public void setHint1(HintDTO hint1) {
        this.hint1 = hint1;
    }

    public void setHint2(HintDTO hint2) {
        this.hint2 = hint2;
    }

    public void setAdjunto(String adjunto) {
        this.adjunto = adjunto;
    }

    public String getAdjunto() {
        return adjunto;
    }
}
