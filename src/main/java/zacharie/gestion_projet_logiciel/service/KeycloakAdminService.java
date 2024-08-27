//package zacharie.gestion_projet_logiciel.service;
//
//import jakarta.ws.rs.core.Response;
//import org.keycloak.admin.client.Keycloak;
//import org.keycloak.admin.client.KeycloakBuilder;
//import org.keycloak.representations.idm.GroupRepresentation;
//import org.keycloak.representations.idm.UserRepresentation;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class KeycloakAdminService {
//
//    private final Keycloak keycloak;
//    private final String realm;
//
//    public KeycloakAdminService(
//            @Value("${app.keycloak.server.url}") String serverUrl,
//            @Value("${app.keycloak.realm.name}") String realm,
//            @Value("${app.keycloak.client-id}") String clientId,
//            @Value("${app.keycloak.client-secret}") String clientSecret,
//            @Value("${app.keycloak.admin.username}") String adminUsername,
//            @Value("${app.keycloak.admin.password}") String adminPassword) {
//
//        this.realm = realm;
//        this.keycloak = KeycloakBuilder.builder()
//                .serverUrl(serverUrl)
//                .realm("gestion-projet")  // Realm utilisé pour l'authentification de l'administrateur
//                .clientId(clientId)
//                .clientSecret(clientSecret)
//                .username(adminUsername)
//                .password(adminPassword)
//                .build();
//    }
//
//    // Créer un utilisateur et l'ajouter à un groupe
//    public void createUserInGroup(String groupId, UserRepresentation user) {
//        // Créer l'utilisateur
//        Response response = keycloak.realm(realm).users().create(user);
//        String userId = getUserIdFromResponse(response);
//
//        // Ajouter l'utilisateur au groupe
//        keycloak.realm(realm).users().get(userId).joinGroup(groupId);
//    }
//
//    // Méthode pour obtenir les groupes de l'utilisateur
//    public List<GroupRepresentation> getGroupsOfUser(String userId) {
//        return keycloak.realm(realm).users().get(userId).groups();
//    }
//
//    // Méthode pour obtenir l'ID d'un utilisateur par son nom d'utilisateur
//    public String getUserIdFromUsername(String username) {
//        List<UserRepresentation> users = keycloak.realm(realm).users().search(username);
//        return users.isEmpty() ? null : users.get(0).getId();
//    }
//
//    // Méthode pour extraire l'ID d'utilisateur de la réponse HTTP de Keycloak
//    private String getUserIdFromResponse(Response response) {
//        String locationHeader = response.getHeaderString("Location");
//        return locationHeader != null ? locationHeader.substring(locationHeader.lastIndexOf("/") + 1) : null;
//    }
//}
