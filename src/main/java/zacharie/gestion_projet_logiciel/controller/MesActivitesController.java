package zacharie.gestion_projet_logiciel.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import zacharie.gestion_projet_logiciel.dto.ActiviteDTO;
import zacharie.gestion_projet_logiciel.service.ActiviteService;

import java.util.List;

@RestController
@RequestMapping("/api/mes-activites")
@PreAuthorize("isAuthenticated()")
public class MesActivitesController {

    @Autowired
    private ActiviteService activiteService;

    @GetMapping()
    public ResponseEntity<?> getMesActivites() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<ActiviteDTO> mesActivites = activiteService.getActivitesByResponsable(username);

        if (mesActivites.isEmpty()) {
            return ResponseEntity.ok("Vous n'avez pas d'activités affiliées.");
        }

        return ResponseEntity.ok(mesActivites);
    }
}


