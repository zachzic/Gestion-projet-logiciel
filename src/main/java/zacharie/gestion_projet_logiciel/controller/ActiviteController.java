package zacharie.gestion_projet_logiciel.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import zacharie.gestion_projet_logiciel.dto.ActiviteDTO;
import zacharie.gestion_projet_logiciel.dto.RessourceDTO;
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

    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin') ")
    @PostMapping
    public ResponseEntity<Activite> createActivite(@RequestBody ActiviteDTO activiteDTO) {
        Activite newActivite = activiteService.createActivite(activiteDTO);
        return ResponseEntity.ok(newActivite);
    }

    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin') or hasRole('MANAGER')")
    @GetMapping("/{id}")
    public ResponseEntity<ActiviteDTO> getActiviteById(@PathVariable Long id) {
        return activiteService.getActiviteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin') or hasRole('MANAGER')")
    @GetMapping
    public ResponseEntity<List<ActiviteDTO>> getAllActivites() {
        List<ActiviteDTO> activites = activiteService.getAllActivites();
        return ResponseEntity.ok(activites);
    }

    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin') or hasRole('MANAGER') ")
    @GetMapping("/{id}/ressources")
    public ResponseEntity<List<RessourceDTO>> getRessourcesByActiviteId(@PathVariable Long id) {
        List<RessourceDTO> ressources = activiteService.getRessourcesByActiviteId(id);
        return ResponseEntity.ok(ressources);
    }

    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin') ")
    @PutMapping("/{id}")
    public ResponseEntity<ActiviteDTO> updateActivite(@PathVariable Long id, @RequestBody ActiviteDTO activiteDTO) {
        ActiviteDTO updatedActivite = activiteService.updateActivite(id,activiteDTO);
        return ResponseEntity.ok(updatedActivite);
    }

    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin') or hasRole('DEVELOPPEUR') or hasRole('DBA') or hasRole('DESIGNER') or hasRole('DEVOPS') or hasRole('TESTEUR')")
    @PutMapping("/{id}/annuler")
    public void annulerActivite(@PathVariable Long id) {
        activiteService.annulerActivite(id);
    }

    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin') or hasRole('DEVELOPPEUR') or hasRole('DBA') or hasRole('DESIGNER') or hasRole('DEVOPS') or hasRole('TESTEUR')")
    @PutMapping("/{id}/terminer")
    public void terminerActivite(@PathVariable Long id) {
        activiteService.terminerActivite(id);
    }


    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin') ")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivite(@PathVariable Long id) {
        activiteService.deleteActivite(id);
        return ResponseEntity.noContent().build();
    }
}
