package zacharie.gestion_projet_logiciel.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import zacharie.gestion_projet_logiciel.dto.ProjetDTO;
import zacharie.gestion_projet_logiciel.service.ProjetService;

import java.util.List;

@RestController
@RequestMapping("/api/mes-projets")
@PreAuthorize("isAuthenticated()")
public class MesProjetsController {

    @Autowired
    private ProjetService projetService;

    @GetMapping()
    public ResponseEntity<?> getMesProjets() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<ProjetDTO> mesProjets = projetService.getProjetsByResponsable(username);

        if (mesProjets.isEmpty()) {
            return ResponseEntity.ok("Vous n'avez pas de projets affiliés.");
        }

        return ResponseEntity.ok(mesProjets);
    }
}

