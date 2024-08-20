package zacharie.gestion_projet_logiciel.dto;

import jakarta.validation.constraints.*;
import zacharie.gestion_projet_logiciel.model.Statut;
import zacharie.gestion_projet_logiciel.validation.ValidProjectDates;

import java.time.LocalDate;
import java.util.List;

@ValidProjectDates // annotation créer par moi même ...
public class TacheDTO {
    private Long id;

    @NotNull(message = "Le nom de la tâche est obligatoire ! ")
    @Size(min = 3, max = 100, message = "Le nom doit être compris entre 3 et 100 caractères")
    private String nom;

    @FutureOrPresent(message = "La date de début doit être dans le futur")
    private LocalDate date_debut;

    @FutureOrPresent(message = "La date de fin doit être dans le futur")
    private LocalDate date_fin;

    @NotNull(message = "La priorité est obligatoire !")
    @Min(value = 1, message = "La priorité doit être au moins de 1")
    @Max(value = 10, message = "La priorité doit être au plus de 10")
    private int priorite;

    private String responsable;

    @NotNull(message = "La description de la tâche est obligatoire ! ")
    @Size(min = 10, message = "La description doit avoir au moins 10 caractères")
    private String description;  // Ajoutez ce champ

    @NotNull(message = "Le projet associé à la tâche est obligatoire ! ")
    private Long projet_id;  // Ajoutez ce champ
    private List<ActiviteDTO> activites;

    @NotNull(message = "Le statut de la tâche est obligatoire !")
    private Statut statut; // Champ statut ajouté

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

//    public List<ActiviteDTO> getActivites() {
//        return activites;
//    }
//
//    public void setActivites(List<ActiviteDTO> activites) {
//        this.activites = activites;
//    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
}
