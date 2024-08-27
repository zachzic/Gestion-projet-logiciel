package zacharie.gestion_projet_logiciel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import zacharie.gestion_projet_logiciel.model.Statut;
import zacharie.gestion_projet_logiciel.validation.ValidProjectDates;

import java.time.LocalDate;
import java.util.List;

@ValidProjectDates // annotation créer par moi même ...
public class ActiviteDTO {
    private Long id;

    @NotNull(message = "Le nom de l'activité est obligatoire ! ")
    @Size(min = 3, max = 100, message = "Le nom doit être compris entre 3 et 100 caractères")
    private String nom;

//    @FutureOrPresent(message = "La date de début doit être dans le futur")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private LocalDate date_debut;

    @FutureOrPresent(message = "La date de fin doit être dans le futur")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private LocalDate date_fin;
    private String responsable;

    @NotNull(message = "La tâche associé à l'activité est obligatoire ! ")
    private Long tache_id;

    @NotNull(message = "Le statut de la tâche est obligatoire !")
    private Statut statut; // Champ statut ajouté

    private List<RessourceDTO> ressources;

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

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Long getTache_id() {
        return tache_id;
    }

    public void setTache_id(Long tache_id) {
        this.tache_id = tache_id;
    }

//    public List<RessourceDTO> getRessources() {
//        return ressources;
//    }
//
//    public void setRessources(List<RessourceDTO> ressources) {
//        this.ressources = ressources;
//    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
}
