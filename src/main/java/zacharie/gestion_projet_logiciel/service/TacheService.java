package zacharie.gestion_projet_logiciel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zacharie.gestion_projet_logiciel.dto.TacheDTO;
import zacharie.gestion_projet_logiciel.mapper.TacheMapper;
import zacharie.gestion_projet_logiciel.model.Projet;
import zacharie.gestion_projet_logiciel.model.Tache;
import zacharie.gestion_projet_logiciel.repository.ProjetRepository;
import zacharie.gestion_projet_logiciel.repository.TacheRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TacheService {
    @Autowired
    private TacheRepository tacheRepository;

    @Autowired
    private ProjetRepository projetRepository;

    public Tache createTache(TacheDTO tacheDTO) {
        Tache tache = TacheMapper.INSTANCE.tacheDTOToTache(tacheDTO);
        if (tacheDTO.getProjet_id() != null) {
            Projet projet = projetRepository.findById(tacheDTO.getProjet_id())
                    .orElseThrow(() -> new RuntimeException("Projet not found"));
            tache.setProjet(projet);
        }
        return tacheRepository.save(tache);
    }

    public Optional<TacheDTO> getTacheById(Long id) {
        return tacheRepository.findById(id)
                .map(TacheMapper.INSTANCE::tacheToTacheDTO);
    }

    public List<TacheDTO> getAllTaches() {
        return tacheRepository.findAll().stream()
                .map(TacheMapper.INSTANCE::tacheToTacheDTO)
                .collect(Collectors.toList());
    }

    public TacheDTO updateTache(Long id, TacheDTO tacheDTO) {
        Tache tache = tacheRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tache not found"));
        tache.setNom(tacheDTO.getNom());
        tache.setDate_debut(tacheDTO.getDate_debut());
        tache.setDate_fin(tacheDTO.getDate_fin());
        tache.setPriorite(tacheDTO.getPriorite());
        tache.setResponsable(tacheDTO.getResponsable());
        tache.setDescription(tacheDTO.getDescription());
        if (tacheDTO.getProjet_id() != null) {
            Projet projet = projetRepository.findById(tacheDTO.getProjet_id())
                    .orElseThrow(() -> new RuntimeException("Projet not found"));
            tache.setProjet(projet);
        }
        return TacheMapper.INSTANCE.tacheToTacheDTO(tacheRepository.save(tache));
    }

    public void deleteTache(Long id) {
        tacheRepository.deleteById(id);
    }
}
