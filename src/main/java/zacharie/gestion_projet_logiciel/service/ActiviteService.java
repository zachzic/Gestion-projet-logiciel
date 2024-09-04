package zacharie.gestion_projet_logiciel.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import zacharie.gestion_projet_logiciel.dto.ActiviteDTO;
import zacharie.gestion_projet_logiciel.dto.RessourceDTO;
import zacharie.gestion_projet_logiciel.dto.TacheDTO;
import zacharie.gestion_projet_logiciel.mapper.ActiviteMapper;
import zacharie.gestion_projet_logiciel.mapper.RessourceMapper;
import zacharie.gestion_projet_logiciel.mapper.TacheMapper;
import zacharie.gestion_projet_logiciel.model.*;
import zacharie.gestion_projet_logiciel.repository.ActiviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zacharie.gestion_projet_logiciel.repository.RessourceRepository;
import zacharie.gestion_projet_logiciel.repository.TacheRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActiviteService {
    @Autowired
    private ActiviteRepository activiteRepository;
    @Autowired
    private TacheRepository tacheRepository;
    @Autowired
    private RessourceRepository ressourceRepository;

    public Activite createActivite(ActiviteDTO activiteDTO) {
        Activite activite = ActiviteMapper.INSTANCE.activiteDTOToActivite(activiteDTO);
        if (activiteDTO.getTache_id() != null) {
            Tache tache = tacheRepository.findById(activiteDTO.getTache_id())
                    .orElseThrow(() -> new RuntimeException("Tache non trouvée"));
            activite.setTache(tache);
        }
        return activiteRepository.save(activite);
    }

    public Optional<ActiviteDTO> getActiviteById(Long id) {
        return activiteRepository.findById(id)
                .map(ActiviteMapper.INSTANCE::activiteToActiviteDTO);
    }

    private void mettreAJourStatut(Activite activite) {
        LocalDate today = LocalDate.now();

        if (activite.getStatut() != Statut.TERMINE) {  // Optionnel : ne pas changer le statut si l'activite est terminée
            if (activite.getDate_debut().isAfter(today)) {
                activite.setStatut(Statut.NON_COMMENCE);
            } else if (activite.getDate_fin().isBefore(today)) {
                activite.setStatut(Statut.EN_RETARD);
            } else if (activite.getDate_debut().isBefore(today) || activite.getDate_debut().isEqual(today)) {
                activite.setStatut(Statut.EN_COURS);
            }

            // Enregistrer la tâche avec le nouveau statut
            activiteRepository.save(activite);
        }
    }

    public class ActiviteNotFoundException extends RuntimeException {
        public ActiviteNotFoundException(Long id) {
            super("Activite avec ID " + id + " non trouvée");
        }
    }

    @Transactional // garantit que toutes les opérations de base de données dans la méthode seront validées ensemble!
    public ActiviteDTO annulerActivite(Long activiteId) {
        Activite activite = activiteRepository.findById(activiteId)
                .orElseThrow(() -> new ActiviteService.ActiviteNotFoundException(activiteId));

        if (activite.getStatut() == Statut.TERMINE || activite.getStatut() == Statut.ANNULE) {
            throw new IllegalStateException("L'activite ne peut pas être annulée car elle est déjà terminée ou annulée.");
        }

        activite.setStatut(Statut.ANNULE);
        activiteRepository.save(activite);
        return ActiviteMapper.INSTANCE.activiteToActiviteDTO(activite);
    }

    @Transactional
    public ActiviteDTO terminerActivite(Long activiteId) {
        Activite activite = activiteRepository.findById(activiteId)
                .orElseThrow(() -> new ActiviteService.ActiviteNotFoundException(activiteId));

        if (activite.getStatut() == Statut.ANNULE) {
            throw new IllegalStateException("L'activite annulée ne peut pas être marquée comme terminée.");
        }

        activite.setStatut(Statut.TERMINE);
        activiteRepository.save(activite);
        return ActiviteMapper.INSTANCE.activiteToActiviteDTO(activite);
    }


    @Scheduled(cron = "0 0 * * * ?")  // Toute les heures ... parce que l'appli ne tourne pas h 24
    @Transactional
    public void mettreAJourStatutsDesActivites() {
        List<Activite> activites = activiteRepository.findAll();
        for (Activite activite : activites) {
            mettreAJourStatut(activite);
        }
    }

    public List<ActiviteDTO> getActivitesByStatut(Statut statut) {
        return activiteRepository.findByStatut(statut).stream()
                .map(ActiviteMapper.INSTANCE::activiteToActiviteDTO)
                .collect(Collectors.toList());
    }

    public List<ActiviteDTO> getAllActivites() {
        return activiteRepository.findAll().stream()
                .map(ActiviteMapper.INSTANCE::activiteToActiviteDTO)
                .collect(Collectors.toList());
    }

    public List<ActiviteDTO> getActivitesByResponsable(String responsable) {
        return activiteRepository.findByResponsable(responsable).stream()
                .map(ActiviteMapper.INSTANCE::activiteToActiviteDTO)
                .collect(Collectors.toList());
    }
    public List<RessourceDTO> getRessourcesByActiviteId(Long activiteId) {
        List<Ressource> ressources = ressourceRepository.findByActiviteId(activiteId);
        return ressources.stream()
                .map(RessourceMapper.INSTANCE::ressourceToRessourceDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ActiviteDTO updateActivite(Long id, ActiviteDTO activiteDTO) {
        Activite activite = activiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activité non trouvée"));
        activite.setNom(activiteDTO.getNom());
        activite.setDate_debut(activiteDTO.getDate_debut());
        activite.setDate_fin(activiteDTO.getDate_fin());
        activite.setResponsable(activiteDTO.getResponsable());
        activite.setStatut(activiteDTO.getStatut());
        if (activiteDTO.getTache_id() != null) {
            Tache tache = tacheRepository.findById(activiteDTO.getTache_id())
                    .orElseThrow(() -> new RuntimeException("Tache non trouvée"));
            activite.setTache(tache);
        }

        // Appel de la méthode pour mettre à jour le statut de l'activité
        mettreAJourStatut(activite);

        return ActiviteMapper.INSTANCE.activiteToActiviteDTO(activiteRepository.save(activite));
    }

    public void deleteActivite(Long id) {
        Activite activite = activiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activité avec ID " + id + " non trouvée"));
        activiteRepository.deleteById(id);
    }
}
