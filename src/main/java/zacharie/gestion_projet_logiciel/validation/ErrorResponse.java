package zacharie.gestion_projet_logiciel.validation;

import java.util.Map;


public class ErrorResponse {
    private int statusCode;
    private String message;
    private Map<String, String> details;

    // Constructeur pour les erreurs simples (code + message)
    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    // Constructeur pour les erreurs avec détails (validation)
    public ErrorResponse(int statusCode, String message, Map<String, String> details) {
        this.statusCode = statusCode;
        this.message = message;
//        this.details = details;
    }

    // Getters and setters
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public Map<String, String> getDetails() {
//        return details;
//    }
//
//    public void setDetails(Map<String, String> details) {
//        this.details = details;
//    }
}

