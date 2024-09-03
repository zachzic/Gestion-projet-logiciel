package zacharie.gestion_projet_logiciel.controller;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zacharie.gestion_projet_logiciel.service.KeycloakUserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private KeycloakUserService keycloakUserService;

    @PreAuthorize("hasRole('CHEF_PROJET') or hasRole('Admin')")
    @GetMapping
    public ResponseEntity<List<UserRepresentation>> getAllUsers() {
        List<UserRepresentation> users = keycloakUserService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/me")
    public String getCurrentUser(@AuthenticationPrincipal(expression = "claims['preferred_username']") String username) {
        return "Connected user: " + username;
    }
}
