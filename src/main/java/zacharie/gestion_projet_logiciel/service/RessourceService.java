package zacharie.gestion_projet_logiciel.service;

import zacharie.gestion_projet_logiciel.dto.RessourceDTO;
import zacharie.gestion_projet_logiciel.mapper.RessourceMapper;
import zacharie.gestion_projet_logiciel.model.Activite;
import zacharie.gestion_projet_logiciel.model.Ressource;
import zacharie.gestion_projet_logiciel.repository.ActiviteRepository;
import zacharie.gestion_projet_logiciel.repository.RessourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RessourceService {
    @Autowired
    private RessourceRepository ressourceRepository;
    @Autowired
    private ActiviteRepository activiteRepository;

    public Ressource createRessource(RessourceDTO ressourceDTO) {
        Ressource ressource = RessourceMapper.INSTANCE.ressourceDTOToRessource(ressourceDTO);
        if (ressourceDTO.getActivite_id() != null) {
            Activite activite = activiteRepository.findById(ressourceDTO.getActivite_id())
                    .orElseThrow(() -> new RuntimeException("Activite not found"));
            ressource.setActivite(activite);
        }
        return ressourceRepository.save(ressource);
    }

    public Optional<Ressource> getRessourceById(Long id) {
        return ressourceRepository.findById(id);
    }

    public List<RessourceDTO> getAllRessources() {
        return ressourceRepository.findAll().stream()
                .map(RessourceMapper.INSTANCE::ressourceToRessourceDTO)
                .collect(Collectors.toList());
    }

    public RessourceDTO updateRessource(Long id, RessourceDTO ressourceDTO) {
        Ressource ressource = ressourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ressource not found"));

        // Mappage des champs
        ressource.setNom(ressourceDTO.getNom());
        ressource.setUrl(ressourceDTO.getUrl());

        if (ressourceDTO.getActivite_id() != null) {
            Activite activite = activiteRepository.findById(ressourceDTO.getActivite_id())
                    .orElseThrow(() -> new RuntimeException("Activite not found"));
            ressource.setActivite(activite);
        }

        ressourceRepository.save(ressource);
        return RessourceMapper.INSTANCE.ressourceToRessourceDTO(ressource);

//        return ressourceRepository.save(ressource);
    }

    public void deleteRessource(Long id) {
        ressourceRepository.deleteById(id);
    }
}
