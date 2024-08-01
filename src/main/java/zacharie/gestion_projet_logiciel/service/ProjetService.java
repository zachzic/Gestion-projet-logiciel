package zacharie.gestion_projet_logiciel.service;

import zacharie.gestion_projet_logiciel.model.Projet;
import zacharie.gestion_projet_logiciel.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetService {
    @Autowired
    private ProjetRepository projetRepository;

    public Projet createProjet(Projet projet) {
        return projetRepository.save(projet);
    }

    public Optional<Projet> getProjetById(Long id) {
        return projetRepository.findById(id);
    }

    public List<Projet> getAllProjets() {
        return projetRepository.findAll();
    }

    public Projet updateProjet(Projet projet) {
        return projetRepository.save(projet);
    }

    public void deleteProjet(Long id) {
        projetRepository.deleteById(id);
    }
}
