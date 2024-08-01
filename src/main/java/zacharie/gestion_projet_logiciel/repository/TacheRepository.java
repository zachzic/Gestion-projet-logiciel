package zacharie.gestion_projet_logiciel.repository;

import zacharie.gestion_projet_logiciel.model.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacheRepository extends JpaRepository<Tache, Long> {
}
