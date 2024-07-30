package zacharie.gestion_projet_logiciel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class GestionProjetLogicielApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionProjetLogicielApplication.class, args);

    }

}
