package zacharie.gestion_projet_logiciel.service;

import zacharie.gestion_projet_logiciel.model.Ressource;
import zacharie.gestion_projet_logiciel.repository.RessourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RessourceService {
    @Autowired
    private RessourceRepository ressourceRepository;

    public Ressource createRessource(Ressource ressource) {
        return ressourceRepository.save(ressource);
    }

    public Optional<Ressource> getRessourceById(Long id) {
        return ressourceRepository.findById(id);
    }

    public List<Ressource> getAllRessources() {
        return ressourceRepository.findAll();
    }

    public Ressource updateRessource(Ressource ressource) {
        return ressourceRepository.save(ressource);
    }

    public void deleteRessource(Long id) {
        ressourceRepository.deleteById(id);
    }
}
