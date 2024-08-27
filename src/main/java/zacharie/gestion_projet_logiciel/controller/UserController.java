//package zacharie.gestion_projet_logiciel.controller;
//
//
//import org.keycloak.representations.idm.GroupRepresentation;
//import org.keycloak.representations.idm.UserRepresentation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import zacharie.gestion_projet_logiciel.service.KeycloakAdminService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/users")
//public class UserController {
//
//    @Autowired
//    private KeycloakAdminService keycloakAdminService;
//
//    @PreAuthorize("hasRole('ROLE_CHEF_PROJET')or hasRole('ROLE_Admin')")
//    @PostMapping("/create")
//    public ResponseEntity<Void> createUser(@RequestBody UserRepresentation user) {
//        // Récupérer l'utilisateur connecté
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        // Obtenir l'ID de l'utilisateur connecté
//        String userId = keycloakAdminService.getUserIdFromUsername(username);
//
//        if (userId == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Si l'utilisateur n'est pas trouvé
//        }
//
//        // Obtenir les groupes de l'utilisateur connecté
//        List<GroupRepresentation> groups = keycloakAdminService.getGroupsOfUser(userId);
//
//        // Vérifier si l'utilisateur fait partie d'un groupe (par exemple, une entreprise)
//        if (groups.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Si l'utilisateur n'est dans aucun groupe
//        }
//
//        // Pour simplifier, on prend le premier groupe trouvé (cela peut être amélioré)
//        String groupId = groups.get(0).getId();
//
//        // Créer un nouvel utilisateur dans le groupe de l'utilisateur connecté
//        keycloakAdminService.createUserInGroup(groupId, user);
//
//        return ResponseEntity.ok().build();
//    }
//
//    // Autres méthodes pour gérer les utilisateurs du groupe
//}
