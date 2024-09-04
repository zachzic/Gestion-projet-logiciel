package zacharie.gestion_projet_logiciel.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import zacharie.gestion_projet_logiciel.dto.ActiviteDTO;
import zacharie.gestion_projet_logiciel.dto.TacheDTO;
import zacharie.gestion_projet_logiciel.model.Tache;
import zacharie.gestion_projet_logiciel.service.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taches")
public class TacheController {
    @Autowired
    private TacheService tacheService;

    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin')")
    @PostMapping
    public ResponseEntity<Tache> createTache(@RequestBody TacheDTO tacheDTO) {
        Tache newTache = tacheService.createTache(tacheDTO);
        return ResponseEntity.ok(newTache);
    }

    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin') or hasRole('MANAGER')")
    @GetMapping("/{id}")
    public ResponseEntity<TacheDTO> getTacheById(@PathVariable Long id) {
        return tacheService.getTacheById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin') or hasRole('MANAGER') ")
    @GetMapping
    public ResponseEntity<List<TacheDTO>> getAllTaches() {
        List<TacheDTO> taches = tacheService.getAllTaches();
        return ResponseEntity.ok(taches);
    }

    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin') ")
    @GetMapping("/{id}/activites")
    public ResponseEntity<List<ActiviteDTO>> getActivitesByTacheId(@PathVariable Long id) {
        List<ActiviteDTO> activites = tacheService.getActivitesByTacheId(id);
        return ResponseEntity.ok(activites);
    }

    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin') ")
    @PutMapping("/{id}")
    public ResponseEntity<TacheDTO> updateTache(@PathVariable Long id, @RequestBody TacheDTO tacheDTO) {
        TacheDTO updatedTache = tacheService.updateTache(id, tacheDTO);
        return ResponseEntity.ok(updatedTache);
    }

    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin') or hasRole('DEVELOPPEUR') or hasRole('DBA') or hasRole('DESIGNER') or hasRole('DEVOPS') or hasRole('TESTEUR')")
    @PutMapping("/{id}/annuler")
    public void annulerTache(@PathVariable Long id) {
        tacheService.annulerTache(id);
    }

    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin') or hasRole('DEVELOPPEUR') or hasRole('DBA') or hasRole('DESIGNER') or hasRole('DEVOPS') or hasRole('TESTEUR')")
    @PutMapping("/{id}/terminer")
    public void terminerTache(@PathVariable Long id) {
        tacheService.terminerTache(id);
    }

    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin') ")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTache(@PathVariable Long id) {
        tacheService.deleteTache(id);
        return ResponseEntity.noContent().build();
    }
}
