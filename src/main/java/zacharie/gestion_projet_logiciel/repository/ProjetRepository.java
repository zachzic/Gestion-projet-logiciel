package zacharie.gestion_projet_logiciel.repository;

import zacharie.gestion_projet_logiciel.model.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjetRepository extends JpaRepository<Projet, Long> {
    boolean existsByNom(String nom);
    List<Projet> findByResponsable(String responsable);
}
