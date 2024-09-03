package zacharie.gestion_projet_logiciel.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import zacharie.gestion_projet_logiciel.dto.ProjetDTO;
import zacharie.gestion_projet_logiciel.dto.TacheDTO;
import zacharie.gestion_projet_logiciel.model.Projet;
import zacharie.gestion_projet_logiciel.service.ProjetService;

import java.util.List;

@RestController
@RequestMapping("/api/projets")
public class ProjetController {

    private static final Logger log = LoggerFactory.getLogger(ProjetController.class);
    @Autowired
    private ProjetService projetService;

    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin')")
    @PostMapping
    public ResponseEntity<ProjetDTO> createProjet(@Valid @RequestBody ProjetDTO projetDTO) {
        ProjetDTO createdProjet = projetService.createProjet(projetDTO);
        return ResponseEntity.ok(createdProjet);
    }

    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin')")
    @GetMapping
    public ResponseEntity<List<ProjetDTO>> getAllProjets() {
        List<ProjetDTO> projets = projetService.getAllProjets();
        return ResponseEntity.ok(projets);
    }

//    @GetMapping("/moi")
//    public ResponseEntity<List<ProjetDTO>> getMesProjets() {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        List<ProjetDTO> mesProjets = projetService.getProjetsByResponsable(username);
//
//        return ResponseEntity.ok(mesProjets);
//    }


    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin')")
    @GetMapping("/{id}")
    public ResponseEntity<ProjetDTO> getProjetById(@PathVariable Long id) {
        ProjetDTO projet = projetService.getProjetById(id);
        return ResponseEntity.ok(projet);
    }

    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin')")
    @GetMapping("/{id}/taches")
    public ResponseEntity<List<TacheDTO>> getTachesByProjetId(@PathVariable Long id) {
        List<TacheDTO> taches = projetService.getTachesByProjetId(id);
        return ResponseEntity.ok(taches);
    }


    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin')")
    @PutMapping("/{id}")
    public ResponseEntity<ProjetDTO> updateProjet(@PathVariable Long id, @RequestBody ProjetDTO projetDTO) {
        ProjetDTO updatedProjet = projetService.updateProjet(id, projetDTO);
        return ResponseEntity.ok(updatedProjet);
    }

    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin')")
    @PutMapping("/{id}/annuler")
    public void annulerProjet(@PathVariable Long id) {
        projetService.annulerProjet(id);
    }


    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin')")
    @PutMapping("/{id}/terminer")
    public void terminerProjet(@PathVariable Long id) {
        projetService.terminerProjet(id);
    }

    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjet(@PathVariable Long id) {
        projetService.deleteProjet(id);
        return ResponseEntity.noContent().build();
    }
}
