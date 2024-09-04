package zacharie.gestion_projet_logiciel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zacharie.gestion_projet_logiciel.dto.ProjetSimplifieDTO;
import zacharie.gestion_projet_logiciel.model.Projet;
import zacharie.gestion_projet_logiciel.service.DiagrammeTemporelService;

import java.util.List;

@RestController
@RequestMapping("/api/diagramme-temporel")
@PreAuthorize("isAuthenticated()")
public class DiagrammeTemporelController {

    @Autowired
    private DiagrammeTemporelService diagrammeTemporelService;

    @GetMapping("/projets")
    public ResponseEntity<List<ProjetSimplifieDTO>> getProjetsAvecDetails() {
        List<ProjetSimplifieDTO> projets = diagrammeTemporelService.getProjetsAvecDetails();
        return ResponseEntity.ok(projets);
    }
}
