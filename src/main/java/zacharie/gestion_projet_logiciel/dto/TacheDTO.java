package zacharie.gestion_projet_logiciel.dto;

import java.time.LocalDate;
import java.util.List;

public class TacheDTO {
    private Long id;
    private String nom;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private int priorite;
    private String responsable;
    private String description;  // Ajoutez ce champ
    private Long projet_id;  // Ajoutez ce champ
    private List<ActiviteDTO> activites;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    public int getPriorite() {
        return priorite;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProjet_id() {
        return projet_id;
    }

    public void setProjet_id(Long projet_id) {
        this.projet_id = projet_id;
    }

    public List<ActiviteDTO> getActivites() {
        return activites;
    }

    public void setActivites(List<ActiviteDTO> activites) {
        this.activites = activites;
    }
}
