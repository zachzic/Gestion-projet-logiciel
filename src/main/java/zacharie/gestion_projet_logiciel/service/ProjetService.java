package zacharie.gestion_projet_logiciel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zacharie.gestion_projet_logiciel.dto.ProjetDTO;
import zacharie.gestion_projet_logiciel.dto.TacheDTO;
import zacharie.gestion_projet_logiciel.mapper.ProjetMapper;
import zacharie.gestion_projet_logiciel.mapper.TacheMapper;
import zacharie.gestion_projet_logiciel.model.Projet;
import zacharie.gestion_projet_logiciel.model.Statut;
import zacharie.gestion_projet_logiciel.model.Tache;
import zacharie.gestion_projet_logiciel.repository.ProjetRepository;
import zacharie.gestion_projet_logiciel.repository.TacheRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjetService {
    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private TacheRepository tacheRepository;

    public ProjetDTO createProjet(ProjetDTO projetDTO) {
        Projet projet = ProjetMapper.INSTANCE.projetDTOToProjet(projetDTO);
        projet = projetRepository.save(projet);
        return ProjetMapper.INSTANCE.projetToProjetDTO(projet);
    }

    public List<ProjetDTO> getAllProjets() {
        return projetRepository.findAll().stream()
                .map(ProjetMapper.INSTANCE::projetToProjetDTO)
                .collect(Collectors.toList());
    }

    public ProjetDTO getProjetById(Long id) {
        Projet projet = projetRepository.findById(id).orElseThrow(() -> new RuntimeException("Projet non trouvé"));
        mettreAJourStatut(projet);
        return ProjetMapper.INSTANCE.projetToProjetDTO(projet);
    }
    private void mettreAJourStatut(Projet projet) {
        LocalDate today = LocalDate.now();
        if (projet.getDate_debut().isAfter(today)) {
            projet.setStatut(Statut.NON_COMMENCE);
        } else if (projet.getDate_fin().isBefore(today)) {
            projet.setStatut(Statut.EN_RETARD);
        } else if (projet.getDate_debut().isBefore(today) || projet.getDate_debut().isEqual(today)) {
            projet.setStatut(Statut.EN_COURS);
        }

        // Optionnel : Ne pas changer le statut si le projet est terminé
        if (projet.getStatut() == Statut.TERMINE) {
            return;
        }
    }
    public List<TacheDTO> getTachesByProjetId(Long projetId) {
        List<Tache> taches = tacheRepository.findByProjetId(projetId); // à gerer
        return taches.stream()
                .map(TacheMapper.INSTANCE::tacheToTacheDTO)
                .collect(Collectors.toList());
    }

    public ProjetDTO updateProjet(Long id, ProjetDTO projetDTO) {
        Projet projet = projetRepository.findById(id).orElseThrow(() -> new RuntimeException("Projet non trouvé"));
        projet.setNom(projetDTO.getNom());
        projet.setDate_debut(projetDTO.getDate_debut());
        projet.setDate_fin(projetDTO.getDate_fin());
        projet.setBudget(projetDTO.getBudget());
        projet.setStatut(projetDTO.getStatut());
        projet = projetRepository.save(projet);
        return ProjetMapper.INSTANCE.projetToProjetDTO(projet);
    }

    public void deleteProjet(Long id) {
        projetRepository.deleteById(id);
    }
}
