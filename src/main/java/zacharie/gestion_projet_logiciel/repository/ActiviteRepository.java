package zacharie.gestion_projet_logiciel.repository;

import zacharie.gestion_projet_logiciel.model.Activite;
import org.springframework.data.jpa.repository.JpaRepository;
import zacharie.gestion_projet_logiciel.model.Statut;
import zacharie.gestion_projet_logiciel.model.Tache;

import java.util.List;

public interface ActiviteRepository extends JpaRepository<Activite, Long> {
    List<Activite> findByTacheId(Long tacheId);
    List<Activite> findByResponsable(String responsable);
    List<Activite> findByStatut(Statut statut);

}
