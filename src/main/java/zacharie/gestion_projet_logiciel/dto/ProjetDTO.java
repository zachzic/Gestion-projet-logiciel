package zacharie.gestion_projet_logiciel.dto;

import java.time.LocalDate;
import java.util.List;

public class ProjetDTO {
    private Long id;
    private String nom;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private Double budget;
//    private List<TacheDTO> taches;

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

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

//    public List<TacheDTO> getTaches() {
//        return taches;
//    }
//
//    public void setTaches(List<TacheDTO> taches) {
//        this.taches = taches;
//    }
}
