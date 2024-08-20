package zacharie.gestion_projet_logiciel.service;

import zacharie.gestion_projet_logiciel.dto.ActiviteDTO;
import zacharie.gestion_projet_logiciel.dto.RessourceDTO;
import zacharie.gestion_projet_logiciel.mapper.ActiviteMapper;
import zacharie.gestion_projet_logiciel.mapper.RessourceMapper;
import zacharie.gestion_projet_logiciel.model.Activite;
import zacharie.gestion_projet_logiciel.model.Projet;
import zacharie.gestion_projet_logiciel.model.Ressource;
import zacharie.gestion_projet_logiciel.model.Tache;
import zacharie.gestion_projet_logiciel.repository.ActiviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zacharie.gestion_projet_logiciel.repository.RessourceRepository;
import zacharie.gestion_projet_logiciel.repository.TacheRepository;

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

    public List<ActiviteDTO> getAllActivites() {
        return activiteRepository.findAll().stream()
                .map(ActiviteMapper.INSTANCE::activiteToActiviteDTO)
                .collect(Collectors.toList());
    }

    public List<RessourceDTO> getRessourcesByActiviteId(Long activiteId) {
        List<Ressource> ressources = ressourceRepository.findByActiviteId(activiteId); //gerer ça aussi
        return ressources.stream()
                .map(RessourceMapper.INSTANCE::ressourceToRessourceDTO)
                .collect(Collectors.toList());
    }

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
        return ActiviteMapper.INSTANCE.activiteToActiviteDTO(activiteRepository.save(activite));
    }

    public void deleteActivite(Long id) {
        activiteRepository.deleteById(id);
    }
}
