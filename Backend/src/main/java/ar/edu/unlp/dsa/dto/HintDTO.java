package ar.edu.unlp.dsa.dto;

/**
 * Created by acollard on 22/3/17.
 */
public class HintDTO {
    private Long id_hint;
    private boolean disponible; //wasn't 'bought' already by someone in the team
    private Long porcentaje;

    public HintDTO() {
    }

    public Long getId_hint() {
        return id_hint;
    }

    public void setId_hint(Long id_hint) {
        this.id_hint = id_hint;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Long getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Long porcentaje) {
        this.porcentaje = porcentaje;
    }
}
