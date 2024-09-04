package zacharie.gestion_projet_logiciel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zacharie.gestion_projet_logiciel.dto.ActiviteSimplifieDTO;
import zacharie.gestion_projet_logiciel.dto.ProjetSimplifieDTO;
import zacharie.gestion_projet_logiciel.dto.TacheSimplifieDTO;
import zacharie.gestion_projet_logiciel.model.Projet;
import zacharie.gestion_projet_logiciel.repository.ActiviteRepository;
import zacharie.gestion_projet_logiciel.repository.ProjetRepository;
import zacharie.gestion_projet_logiciel.repository.TacheRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiagrammeTemporelService {

    @Autowired
    private ProjetRepository projetRepository;

    @Autowired
    private TacheRepository tacheRepository;

    @Autowired
    private ActiviteRepository activiteRepository;

    @Transactional(readOnly = true)
    public List<ProjetSimplifieDTO> getProjetsAvecDetails() {
        List<Projet> projets = projetRepository.findAll();

        return projets.stream().map(projet -> {
            ProjetSimplifieDTO projetDTO = new ProjetSimplifieDTO();
            projetDTO.setId(projet.getId());
            projetDTO.setNom(projet.getNom());
            projetDTO.setDate_debut(projet.getDate_debut());
            projetDTO.setDate_fin(projet.getDate_fin());

            // Mapper les tâches associées
            List<TacheSimplifieDTO> tacheDTOs = tacheRepository.findByProjetId(projet.getId()).stream().map(tache -> {
                TacheSimplifieDTO tacheDTO = new TacheSimplifieDTO();
                tacheDTO.setId(tache.getId());
                tacheDTO.setNom(tache.getNom());
                tacheDTO.setDate_debut(tache.getDate_debut());
                tacheDTO.setDate_fin(tache.getDate_fin());

                // Mapper les activités associées
                List<ActiviteSimplifieDTO> activiteDTOs = activiteRepository.findByTacheId(tache.getId()).stream().map(activite -> {
                    ActiviteSimplifieDTO activiteDTO = new ActiviteSimplifieDTO();
                    activiteDTO.setId(activite.getId());
                    activiteDTO.setNom(activite.getNom());
                    activiteDTO.setDate_debut(activite.getDate_debut());
                    activiteDTO.setDate_fin(activite.getDate_fin());
                    return activiteDTO;
                }).collect(Collectors.toList());

                // Ajouter la liste d'activités dans le DTO de la tâche
                tacheDTO.setActivites(activiteDTOs);

                return tacheDTO;
            }).collect(Collectors.toList());

            // Ajouter la liste de tâches dans le DTO de projet
            projetDTO.setTaches(tacheDTOs);

            return projetDTO;
        }).collect(Collectors.toList());
    }
}
