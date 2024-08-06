package zacharie.gestion_projet_logiciel.controller;

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

    @PostMapping
    public ResponseEntity<Tache> createTache(@RequestBody TacheDTO tacheDTO) {
        Tache newTache = tacheService.createTache(tacheDTO);
        return ResponseEntity.ok(newTache);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TacheDTO> getTacheById(@PathVariable Long id) {
        return tacheService.getTacheById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TacheDTO>> getAllTaches() {
        List<TacheDTO> taches = tacheService.getAllTaches();
        return ResponseEntity.ok(taches);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TacheDTO> updateTache(@PathVariable Long id, @RequestBody TacheDTO tacheDTO) {
        TacheDTO updatedTache = tacheService.updateTache(id, tacheDTO);
        return ResponseEntity.ok(updatedTache);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTache(@PathVariable Long id) {
        tacheService.deleteTache(id);
        return ResponseEntity.noContent().build();
    }
}
