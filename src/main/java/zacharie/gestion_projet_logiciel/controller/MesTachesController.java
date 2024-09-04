package zacharie.gestion_projet_logiciel.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import zacharie.gestion_projet_logiciel.dto.TacheDTO;
import zacharie.gestion_projet_logiciel.service.TacheService;

import java.util.List;

@RestController
@RequestMapping("/api/mes-taches")
@PreAuthorize("isAuthenticated()")
public class MesTachesController {

    @Autowired
    private TacheService tacheService;

    @GetMapping()
    public ResponseEntity<?> getMesTaches() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<TacheDTO> mesTaches = tacheService.getTachesByResponsable(username);

        if (mesTaches.isEmpty()) {
            return ResponseEntity.ok("Vous n'avez pas de tâches affiliées.");
        }

        return ResponseEntity.ok(mesTaches);
    }
}


