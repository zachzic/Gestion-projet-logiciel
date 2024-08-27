package zacharie.gestion_projet_logiciel.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import zacharie.gestion_projet_logiciel.dto.RessourceDTO;
import zacharie.gestion_projet_logiciel.model.Ressource;
import zacharie.gestion_projet_logiciel.service.RessourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ressources")
public class RessourceController {
    @Autowired
    private RessourceService ressourceService;

    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin') or hasRole('DEVELOPPEUR') or hasRole('DBA')")
    @PostMapping
    public ResponseEntity<Ressource> createRessource(@RequestBody RessourceDTO ressourceDTO) {
        Ressource newRessource = ressourceService.createRessource(ressourceDTO);
        return ResponseEntity.ok(newRessource);
    }

    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin') or hasRole('DEVELOPPEUR') or hasRole('DBA')")
    @GetMapping("/{id}")
    public ResponseEntity<Ressource> getRessourceById(@PathVariable Long id) {
        return ressourceService.getRessourceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin') or hasRole('DEVELOPPEUR') or hasRole('DBA')")
    @GetMapping
    public ResponseEntity<List<RessourceDTO>> getAllRessources() {
        List<RessourceDTO> ressources = ressourceService.getAllRessources();
        return ResponseEntity.ok(ressources);
    }

    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin') or hasRole('DEVELOPPEUR') or hasRole('DBA')")
    @PutMapping("/{id}")
    public ResponseEntity<RessourceDTO> updateRessource(@PathVariable Long id, @RequestBody RessourceDTO ressourceDTO) {
        RessourceDTO updatedRessource = ressourceService.updateRessource(id,ressourceDTO);
        return ResponseEntity.ok(updatedRessource);
    }

    @PreAuthorize("hasRole('CHEF_DE_PROJET')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRessource(@PathVariable Long id) {
        ressourceService.deleteRessource(id);
        return ResponseEntity.noContent().build();
    }
}
