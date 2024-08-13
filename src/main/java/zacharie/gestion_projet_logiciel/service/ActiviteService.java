package zacharie.gestion_projet_logiciel.service;

import zacharie.gestion_projet_logiciel.dto.ActiviteDTO;
import zacharie.gestion_projet_logiciel.mapper.ActiviteMapper;
import zacharie.gestion_projet_logiciel.model.Activite;
import zacharie.gestion_projet_logiciel.model.Projet;
import zacharie.gestion_projet_logiciel.model.Tache;
import zacharie.gestion_projet_logiciel.repository.ActiviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zacharie.gestion_projet_logiciel.repository.TacheRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ActiviteService {
    @Autowired
    private ActiviteRepository activiteRepository;
    @Autowired
    private TacheRepository tacheRepository;

    public Activite createActivite(ActiviteDTO activiteDTO) {
        Activite activite = ActiviteMapper.INSTANCE.activiteDTOToActivite(activiteDTO);
        if (activiteDTO.getTache_id() != null) {
            Tache tache = tacheRepository.findById(activiteDTO.getTache_id())
                    .orElseThrow(() -> new RuntimeException("Tache not found"));
            activite.setTache(tache);
        }
        return activiteRepository.save(activite);
    }

    public Optional<Activite> getActiviteById(Long id) {
        return activiteRepository.findById(id);
    }

    public List<Activite> getAllActivites() {
        return activiteRepository.findAll();
    }

//    public ActiviteDTO updateActivite(Long id, ActiviteDTO activiteDTO) {
//        Activite activite = activiteRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Activité not found"));
//        activite.setNom(activiteDTO.getNom());
//        activite.setDate_debut(activiteDTO.getDate_debut());
//        activite.setDate_fin(activiteDTO.getDate_fin());
//        activite.setResponsable(activiteDTO.getResponsable());
//        if (activiteDTO.getTache_id()!= null) {
//            Tache tache = tacheRepository.findById(activiteDTO.getTache_id())
//                    .orElseThrow(() -> new RuntimeException("Tache not found"));
//            activite.setTache(tache);
//        }
//        return ActiviteMapper.INSTANCE.activiteToActiviteDTO(activiteRepository.save(activite));
//    }
    public ActiviteDTO updateActivite(Long id, ActiviteDTO activiteDTO) {
        Activite activite = activiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activité not found"));

        // Mappage des champs
        activite.setNom(activiteDTO.getNom());
        activite.setDate_debut(activiteDTO.getDate_debut());
        activite.setDate_fin(activiteDTO.getDate_fin());
        activite.setResponsable(activiteDTO.getResponsable());

        if (activiteDTO.getTache_id() != null) {
            Tache tache = tacheRepository.findById(activiteDTO.getTache_id())
                    .orElseThrow(() -> new RuntimeException("Tache not found"));
            activite.setTache(tache);
        }

        activiteRepository.save(activite);
        // Log pour vérifier les dates après la sauvegarde
        System.out.println("Date début après mapping: " + activite.getDate_debut());
        System.out.println("Date fin après mapping: " + activite.getDate_fin());

        return ActiviteMapper.INSTANCE.activiteToActiviteDTO(activite);

        // Vérification des valeurs des dates avant de retourner le DTO
//        ActiviteDTO updatedDTO = ActiviteMapper.INSTANCE.activiteToActiviteDTO(activite);
//        System.out.println("Date début dans DTO: " + updatedDTO.getDate_debut());
//        System.out.println("Date fin dans DTO: " + updatedDTO.getDate_fin());
//
//        return updatedDTO;

    }


    public void deleteActivite(Long id) {
        activiteRepository.deleteById(id);
    }
}
