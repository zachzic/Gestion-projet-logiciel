package zacharie.gestion_projet_logiciel.dto;

import java.time.LocalDate;
import java.util.List;

public class ProjetDTO {
    private Long id;
    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private List<TacheDTO> taches;

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

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public List<TacheDTO> getTaches() {
        return taches;
    }

    public void setTaches(List<TacheDTO> taches) {
        this.taches = taches;
    }
}
