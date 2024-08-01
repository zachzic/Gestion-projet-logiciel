package zacharie.gestion_projet_logiciel.service;

import zacharie.gestion_projet_logiciel.model.Tache;
import zacharie.gestion_projet_logiciel.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TacheService {
    @Autowired
    private TacheRepository tacheRepository;

    public Tache createTache(Tache tache) {
        return tacheRepository.save(tache);
    }

    public Optional<Tache> getTacheById(Long id) {
        return tacheRepository.findById(id);
    }

    public List<Tache> getAllTaches() {
        return tacheRepository.findAll();
    }

    public Tache updateTache(Tache tache) {
        return tacheRepository.save(tache);
    }

    public void deleteTache(Long id) {
        tacheRepository.deleteById(id);
    }
}
