package zacharie.gestion_projet_logiciel.validation;

import org.keycloak.adapters.springsecurity.KeycloakAuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.security.oauth2.jwt.JwtException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class SecurityExceptionHandler {

    // Gestion des erreurs d'authentification (ex: token JWT manquant ou invalide)
    @ExceptionHandler(KeycloakAuthenticationException.class)
    public ResponseEntity<Map<String, String>> handleAuthenticationException(KeycloakAuthenticationException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("erreur", "Échec de l'authentification");
        errorResponse.put("message", "Vous devez être authentifié pour accéder à cette ressource.");

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED); // 401 Unauthorized
    }

    @ExceptionHandler(OAuth2AuthenticationException.class)
    public ResponseEntity<String> handleOAuth2AuthenticationException(OAuth2AuthenticationException ex) {
        return new ResponseEntity<>("Erreur d'authentification OAuth2 : " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }


    // Gestion des erreurs d'autorisation (ex: accès refusé à une ressource)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, String>> handleAccessDeniedException(AccessDeniedException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("erreur", "Accès refusé");
        errorResponse.put("message", "Vous n'avez pas les autorisations nécessaires pour accéder à cette ressource.");
        System.out.println("Exception: " + ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN); // 403 Forbidden
    }


    // Gestion des erreurs liées à un token JWT expiré
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<Map<String, String>> handleJwtException(JwtException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("erreur", "Token expiré");
        errorResponse.put("message", "Votre session a expiré. Veuillez vous reconnecter.");

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED); // 401 Unauthorized
    }
}
