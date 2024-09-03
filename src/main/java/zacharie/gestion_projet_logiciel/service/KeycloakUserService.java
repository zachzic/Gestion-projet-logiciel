package zacharie.gestion_projet_logiciel.service;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeycloakUserService {

    private final Keycloak keycloak;
    private final String realm;

    public KeycloakUserService(
            @Value("${app.keycloak.server.url}") String serverUrl,
            @Value("${app.keycloak.realm.name}") String realm,
            @Value("${app.keycloak.client-id}") String clientId,
            @Value("${app.keycloak.client-secret}") String clientSecret,
            @Value("${app.keycloak.admin.username}") String adminUsername,
            @Value("${app.keycloak.admin.password}") String adminPassword) {

        this.realm = realm;
        this.keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm("gestion-projet")  // Utilisation du realm "master" pour s'authentifier en tant qu'administrateur
                .clientId(clientId)
                .clientSecret(clientSecret)
                .username(adminUsername)
                .password(adminPassword)
                .build();
    }

    // Récupérer tous les utilisateurs du realm
    public List<UserRepresentation> getAllUsers() {
        return keycloak.realm(realm).users().list(); // Liste tous les utilisateurs dans le realm spécifié
    }

    // Autres méthodes pour interagir avec Keycloak...
}
