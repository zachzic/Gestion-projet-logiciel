package zacharie.gestion_projet_logiciel.repository;

import zacharie.gestion_projet_logiciel.model.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RessourceRepository extends JpaRepository<Ressource, Long> {
}