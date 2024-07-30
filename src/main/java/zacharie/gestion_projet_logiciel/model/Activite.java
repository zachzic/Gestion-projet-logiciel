package zacharie.gestion_projet_logiciel.model;

import jakarta.annotation.Resource;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Activite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private LocalDate data_debut;
    private LocalDate date_fin;
    private String responsable;

    @ManyToOne
    @JoinColumn(name = "tache_id")
    private Tache tache;

    @OneToMany(mappedBy = "activites", cascade = CascadeType.ALL)
    private List<Ressource> ressources;

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

    public LocalDate getData_debut() {
        return data_debut;
    }

    public void setData_debut(LocalDate data_debut) {
        this.data_debut = data_debut;
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

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }
}
