package zacharie.gestion_projet_logiciel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zacharie.gestion_projet_logiciel.dto.ProjetDTO;
import zacharie.gestion_projet_logiciel.mapper.ProjetMapper;
import zacharie.gestion_projet_logiciel.mapper.TacheMapper;
import zacharie.gestion_projet_logiciel.model.Projet;
import zacharie.gestion_projet_logiciel.repository.ProjetRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjetService {
    @Autowired
    private ProjetRepository projetRepository;

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
        Projet projet = projetRepository.findById(id).orElseThrow(() -> new RuntimeException("Projet not found"));
        return ProjetMapper.INSTANCE.projetToProjetDTO(projet);
    }

    public ProjetDTO updateProjet(Long id, ProjetDTO projetDTO) {
        Projet projet = projetRepository.findById(id).orElseThrow(() -> new RuntimeException("Projet not found"));
        projet.setNom(projetDTO.getNom());
        projet.setDate_debut(projetDTO.getDate_debut());
        projet.setDate_fin(projetDTO.getDate_fin());
        projet.setBudget(projetDTO.getBudget());
//        projet.setTache(projetDTO.getTaches().stream().map(TacheMapper.INSTANCE::tacheDTOToTache).collect(Collectors.toList()));
        projet = projetRepository.save(projet);
        return ProjetMapper.INSTANCE.projetToProjetDTO(projet);
    }

    public void deleteProjet(Long id) {
        projetRepository.deleteById(id);
    }
}
