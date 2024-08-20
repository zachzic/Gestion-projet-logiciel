package zacharie.gestion_projet_logiciel.dto;

import jakarta.validation.constraints.NotNull;

public class RessourceDTO {
    private Long id;

    @NotNull(message = "Le nom de la ressource est obligatoire ! ")
    private String nom;

    @NotNull(message = "L'url' est obligatoire ! ")
    private String url;

    @NotNull(message = "L'activité associé à la ressource est obligatoire ! ")
    private Long activite_id;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getActivite_id() {
        return activite_id;
    }

    public void setActivite_id(Long activite_id) {
        this.activite_id = activite_id;
    }
}
