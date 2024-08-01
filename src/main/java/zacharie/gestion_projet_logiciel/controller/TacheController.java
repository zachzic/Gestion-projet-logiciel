package zacharie.gestion_projet_logiciel.controller;

import zacharie.gestion_projet_logiciel.model.Tache;
import zacharie.gestion_projet_logiciel.service.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taches")
public class TacheController {
    @Autowired
    private TacheService tacheService;

    @PreAuthorize("hasRole('CHEF_DE_PROJET')")
    @PostMapping
    public ResponseEntity<Tache> createTache(@RequestBody Tache tache) {
        Tache newTache = tacheService.createTache(tache);
        return ResponseEntity.ok(newTache);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tache> getTacheById(@PathVariable Long id) {
        return tacheService.getTacheById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Tache>> getAllTaches() {
        List<Tache> taches = tacheService.getAllTaches();
        return ResponseEntity.ok(taches);
    }

    @PreAuthorize("hasRole('CHEF_DE_PROJET')")
    @PutMapping("/{id}")
    public ResponseEntity<Tache> updateTache(@PathVariable Long id, @RequestBody Tache tache) {
        tache.setId(id);
        Tache updatedTache = tacheService.updateTache(tache);
        return ResponseEntity.ok(updatedTache);
    }

    @PreAuthorize("hasRole('CHEF_DE_PROJET')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTache(@PathVariable Long id) {
        tacheService.deleteTache(id);
        return ResponseEntity.noContent().build();
    }
}
