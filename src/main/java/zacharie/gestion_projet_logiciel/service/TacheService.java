package zacharie.gestion_projet_logiciel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zacharie.gestion_projet_logiciel.dto.ActiviteDTO;
import zacharie.gestion_projet_logiciel.dto.TacheDTO;
import zacharie.gestion_projet_logiciel.mapper.ActiviteMapper;
import zacharie.gestion_projet_logiciel.mapper.TacheMapper;
import zacharie.gestion_projet_logiciel.model.Activite;
import zacharie.gestion_projet_logiciel.model.Projet;
import zacharie.gestion_projet_logiciel.model.Statut;
import zacharie.gestion_projet_logiciel.model.Tache;
import zacharie.gestion_projet_logiciel.repository.ActiviteRepository;
import zacharie.gestion_projet_logiciel.repository.ProjetRepository;
import zacharie.gestion_projet_logiciel.repository.TacheRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TacheService {
    @Autowired
    private TacheRepository tacheRepository;

    @Autowired
    private ProjetRepository projetRepository;

    @Autowired
    private ActiviteRepository activiteRepository;

    public Tache createTache(TacheDTO tacheDTO) {
        Tache tache = TacheMapper.INSTANCE.tacheDTOToTache(tacheDTO);
        if (tacheDTO.getProjet_id() != null) {
            Projet projet = projetRepository.findById(tacheDTO.getProjet_id())
                    .orElseThrow(() -> new RuntimeException("Projet non trouvé"));
            tache.setProjet(projet);
        }
        return tacheRepository.save(tache);
    }

    public Optional<TacheDTO> getTacheById(Long id) {
        // Récupérer la tâche depuis la base de données
        Optional<Tache> tacheOpt = tacheRepository.findById(id);

        // Si la tâche existe, mettre à jour son statut
        tacheOpt.ifPresent(this::mettreAJourStatut);

        // Convertir la tâche en DTO et la retourner
        return tacheOpt.map(TacheMapper.INSTANCE::tacheToTacheDTO);
    }

    private void mettreAJourStatut(Tache tache) {
        LocalDate today = LocalDate.now();

        if (tache.getStatut() != Statut.TERMINE) {  // Optionnel : ne pas changer le statut si la tâche est terminée
            if (tache.getDate_debut().isAfter(today)) {
                tache.setStatut(Statut.NON_COMMENCE);
            } else if (tache.getDate_fin().isBefore(today)) {
                tache.setStatut(Statut.EN_RETARD);
            } else if (tache.getDate_debut().isBefore(today) || tache.getDate_debut().isEqual(today)) {
                tache.setStatut(Statut.EN_COURS);
            }

            // Enregistrer la tâche avec le nouveau statut
            tacheRepository.save(tache);
        }
    }

    public void annulerTache(Long tacheId) {
        Tache tache = tacheRepository.findById(tacheId)
                .orElseThrow(() -> new RuntimeException("Tâche non trouvée"));
        tache.setStatut(Statut.ANNULE);
        tacheRepository.save(tache);
    }

    public void terminerTache(Long tacheId) {
        Tache tache = tacheRepository.findById(tacheId)
                .orElseThrow(() -> new RuntimeException("Tâche non trouvée"));
        tache.setStatut(Statut.TERMINE);
        tacheRepository.save(tache);
    }

    @Scheduled(cron = "0 0 * * * ?")  // Toute les heures ... parce que l'appli ne tourne pas h 24
    @Transactional
    public void mettreAJourStatutsDesTaches() {
        List<Tache> taches = tacheRepository.findAll();
        for (Tache tache : taches) {
            mettreAJourStatut(tache);
        }
    }

    public List<TacheDTO> getAllTaches() {
        return tacheRepository.findAll().stream()
                .map(TacheMapper.INSTANCE::tacheToTacheDTO)
                .collect(Collectors.toList());
    }

    public List<ActiviteDTO> getActivitesByTacheId(Long tacheId) {
        List<Activite> activites = activiteRepository.findByTacheId(tacheId); // à gerer
        return activites.stream()
                .map(ActiviteMapper.INSTANCE::activiteToActiviteDTO)
                .collect(Collectors.toList());
    }

    public TacheDTO updateTache(Long id, TacheDTO tacheDTO) {
        Tache tache = tacheRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tache non trouvée"));
        tache.setNom(tacheDTO.getNom());
        tache.setDate_debut(tacheDTO.getDate_debut());
        tache.setDate_fin(tacheDTO.getDate_fin());
        tache.setPriorite(tacheDTO.getPriorite());
        tache.setResponsable(tacheDTO.getResponsable());
        tache.setDescription(tacheDTO.getDescription());
        tache.setStatut(tacheDTO.getStatut());
        if (tacheDTO.getProjet_id() != null) {
            Projet projet = projetRepository.findById(tacheDTO.getProjet_id())
                    .orElseThrow(() -> new RuntimeException("Projet non trouvé"));
            tache.setProjet(projet);
        }
        return TacheMapper.INSTANCE.tacheToTacheDTO(tacheRepository.save(tache));
    }

    public void deleteTache(Long id) {
        tacheRepository.deleteById(id);
    }
}
