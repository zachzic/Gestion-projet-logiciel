package zacharie.gestion_projet_logiciel.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import zacharie.gestion_projet_logiciel.dto.ActiviteDTO;
import zacharie.gestion_projet_logiciel.dto.ProjetDTO;
import zacharie.gestion_projet_logiciel.dto.TacheDTO;
import zacharie.gestion_projet_logiciel.model.Statut;
import zacharie.gestion_projet_logiciel.service.ActiviteService;
import zacharie.gestion_projet_logiciel.service.ProjetService;
import zacharie.gestion_projet_logiciel.service.TacheService;

import java.util.List;

@RestController
@RequestMapping("/api/statut")
@PreAuthorize("hasRole('CHEF_PROJET')or hasRole('MANAGER')")
public class StatutController {

    @Autowired
    private ProjetService projetService;

    @Autowired
    private TacheService tacheService;

    @Autowired
    private ActiviteService activiteService;

    // Endpoint pour obtenir les projets par statut
    @GetMapping("/projets")
    public ResponseEntity<List<ProjetDTO>> getProjetsByStatut(@RequestParam Statut statut) {
        List<ProjetDTO> projets = projetService.getProjetsByStatut(statut);
        return ResponseEntity.ok(projets);
    }

    // Endpoint pour obtenir les tâches par statut
    @GetMapping("/taches")
    public ResponseEntity<List<TacheDTO>> getTachesByStatut(@RequestParam Statut statut) {
        List<TacheDTO> taches = tacheService.getTachesByStatut(statut);
        return ResponseEntity.ok(taches);
    }

    // Endpoint pour obtenir les activités par statut
    @GetMapping("/activites")
    public ResponseEntity<List<ActiviteDTO>> getActivitesByStatut(@RequestParam Statut statut) {
        List<ActiviteDTO> activites = activiteService.getActivitesByStatut(statut);
        return ResponseEntity.ok(activites);
    }
}

