package zacharie.gestion_projet_logiciel.service;

import zacharie.gestion_projet_logiciel.model.Activite;
import zacharie.gestion_projet_logiciel.repository.ActiviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActiviteService {
    @Autowired
    private ActiviteRepository activiteRepository;

    public Activite createActivite(Activite activite) {
        return activiteRepository.save(activite);
    }

    public Optional<Activite> getActiviteById(Long id) {
        return activiteRepository.findById(id);
    }

    public List<Activite> getAllActivites() {
        return activiteRepository.findAll();
    }

    public Activite updateActivite(Activite activite) {
        return activiteRepository.save(activite);
    }

    public void deleteActivite(Long id) {
        activiteRepository.deleteById(id);
    }
}
