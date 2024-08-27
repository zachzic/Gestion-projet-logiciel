package zacharie.gestion_projet_logiciel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import zacharie.gestion_projet_logiciel.model.Statut;
import zacharie.gestion_projet_logiciel.validation.UniqueProjectName;
import zacharie.gestion_projet_logiciel.validation.ValidProjectDates;

import java.time.LocalDate;
import java.util.List;

@ValidProjectDates // annotation créer par moi même ...
public class ProjetDTO {
    private Long id;
    @NotNull(message = "Le nom du projet est obligatoire")
    @UniqueProjectName // annotation créer par moi même ...
    private String nom;

    @NotNull(message = "La date de début est obligatoire")
//    @FutureOrPresent(message = "La date de début doit être dans le futur")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private LocalDate date_debut;

    @NotNull(message = "La date de fin est obligatoire")
    @FutureOrPresent(message = "La date de fin doit être dans le futur")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private LocalDate date_fin;
    private Double budget;

    @NotNull(message = "Le statut de la tâche est obligatoire !")
    private Statut statut; // Champ statut ajouté
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

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    //    public List<TacheDTO> getTaches() {
//        return taches;
//    }
//
//    public void setTaches(List<TacheDTO> taches) {
//        this.taches = taches;
//    }
}
