package zacharie.gestion_projet_logiciel.controller;

import zacharie.gestion_projet_logiciel.model.Activite;
import zacharie.gestion_projet_logiciel.service.ActiviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activites")
public class ActiviteController {
    @Autowired
    private ActiviteService activiteService;

//    @PreAuthorize("hasRole('CHEF_DE_PROJET') or hasRole('DEVELOPPEUR')")
    @PostMapping
    public ResponseEntity<Activite> createActivite(@RequestBody Activite activite) {
        Activite newActivite = activiteService.createActivite(activite);
        return ResponseEntity.ok(newActivite);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activite> getActiviteById(@PathVariable Long id) {
        return activiteService.getActiviteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Activite>> getAllActivites() {
        List<Activite> activites = activiteService.getAllActivites();
        return ResponseEntity.ok(activites);
    }

//    @PreAuthorize("hasRole('CHEF_DE_PROJET') or hasRole('DEVELOPPEUR')")
    @PutMapping("/{id}")
    public ResponseEntity<Activite> updateActivite(@PathVariable Long id, @RequestBody Activite activite) {
        activite.setId(id);
        Activite updatedActivite = activiteService.updateActivite(activite);
        return ResponseEntity.ok(updatedActivite);
    }

//    @PreAuthorize("hasRole('CHEF_DE_PROJET')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivite(@PathVariable Long id) {
        activiteService.deleteActivite(id);
        return ResponseEntity.noContent().build();
    }
}
