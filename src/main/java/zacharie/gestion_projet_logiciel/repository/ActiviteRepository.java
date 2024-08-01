package zacharie.gestion_projet_logiciel.repository;

import zacharie.gestion_projet_logiciel.model.Activite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiviteRepository extends JpaRepository<Activite, Long> {
}
