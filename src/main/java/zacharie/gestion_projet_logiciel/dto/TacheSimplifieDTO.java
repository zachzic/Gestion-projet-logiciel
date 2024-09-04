package zacharie.gestion_projet_logiciel.dto;

import java.time.LocalDate;
import java.util.List;

public class TacheSimplifieDTO {
    private Long id;
    private String nom;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private List<ActiviteSimplifieDTO> activites;


    // Getters et Setters

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

    public List<ActiviteSimplifieDTO> getActivites() {
        return activites;
    }

    public void setActivites(List<ActiviteSimplifieDTO> activites) {
        this.activites = activites;
    }
}
