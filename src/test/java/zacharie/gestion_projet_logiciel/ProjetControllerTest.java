package zacharie.gestion_projet_logiciel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateProjetWithInvalidDates() throws Exception {
        String projetJson = "{ " +
                "\"nom\": \"Projet A\", " +
                "\"date_debut\": \"2025-01-01\", " +
                "\"date_fin\": \"2024-01-01\", " +
                "\"budget\": 100000 " +
                "}";

        mockMvc.perform(post("/api/projets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(projetJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.date_debut").exists()) // Vérifie que l'erreur est bien sur la date_debut
                .andExpect(jsonPath("$.date_debut").value("La date de début ne peut pas être dans le futur"))
                .andExpect(jsonPath("$.date_fin").exists()) // Vérifie que l'erreur est bien sur la date_fin
                .andExpect(jsonPath("$.date_fin").value("La date de fin doit être dans le futur"))
                .andExpect(jsonPath("$.nom").exists()) // Vérifie que l'erreur est bien sur le nom
                .andExpect(jsonPath("$.nom").value("Le nom du projet doit être unique"));
    }
}
