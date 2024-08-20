package zacharie.gestion_projet_logiciel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

        if(projet.getStatut() !=Statut.TERMINE){ // Optionnel : Ne pas changer le statut si le projet est terminé
            if (projet.getDate_debut().isAfter(today)) {
                projet.setStatut(Statut.NON_COMMENCE);
            } else if (projet.getDate_fin().isBefore(today)) {
                projet.setStatut(Statut.EN_RETARD);
            } else if (projet.getDate_debut().isBefore(today) || projet.getDate_debut().isEqual(today)) {
                projet.setStatut(Statut.EN_COURS);
            }
        }
    }

    public class ProjetNotFoundException extends RuntimeException {
        public ProjetNotFoundException(Long id) {
            super("Projet avec ID " + id + " non trouvée");
        }
    }

    @Transactional // garantit que toutes les opérations de base de données dans la méthode seront validées ensemble!
    public ProjetDTO annulerProjet(Long projetId) {
        Projet projet = projetRepository.findById(projetId)
                .orElseThrow(() -> new ProjetService.ProjetNotFoundException(projetId));

        if (projet.getStatut() == Statut.TERMINE || projet.getStatut() == Statut.ANNULE) {
            throw new IllegalStateException("Le projet ne peut pas être annulée car il est déjà terminée ou annulée.");
        }

        projet.setStatut(Statut.ANNULE);
        projetRepository.save(projet);
        return ProjetMapper.INSTANCE.projetToProjetDTO(projet);
    }

    @Transactional
    public ProjetDTO terminerProjet(Long projetId) {
        Projet projet = projetRepository.findById(projetId)
                .orElseThrow(() -> new ProjetService.ProjetNotFoundException(projetId));

        if (projet.getStatut() == Statut.ANNULE) {
            throw new IllegalStateException("Le projet annulée ne peut pas être marquée comme terminée.");
        }

        projet.setStatut(Statut.TERMINE);
        projetRepository.save(projet);
        return ProjetMapper.INSTANCE.projetToProjetDTO(projet);
    }

    @Scheduled(cron = "0 0 * * * ?")  // Toute les heures ... parce que l'appli ne tourne pas h 24
    @Transactional
    public void mettreAJourStatutsDesProjet() {
        List<Projet> projets = projetRepository.findAll();
        for (Projet projet : projets) {
            mettreAJourStatut(projet);
        }
    }

    public List<TacheDTO> getTachesByProjetId(Long projetId) {
        List<Tache> taches = tacheRepository.findByProjetId(projetId); // à gerer
        return taches.stream()
                .map(TacheMapper.INSTANCE::tacheToTacheDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProjetDTO updateProjet(Long id, ProjetDTO projetDTO) {
        Projet projet = projetRepository.findById(id).orElseThrow(() -> new RuntimeException("Projet non trouvé"));
        projet.setNom(projetDTO.getNom());
        projet.setDate_debut(projetDTO.getDate_debut());
        projet.setDate_fin(projetDTO.getDate_fin());
        projet.setBudget(projetDTO.getBudget());
        projet.setStatut(projetDTO.getStatut());
        projet = projetRepository.save(projet);

        // Appel de la méthode pour mettre à jour le statut du projet
        mettreAJourStatut(projet);
        return ProjetMapper.INSTANCE.projetToProjetDTO(projet);
    }

    public void deleteProjet(Long id) {
        projetRepository.deleteById(id);
    }
}
