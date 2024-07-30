package zacharie.gestion_projet_logiciel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication// le exclude pour ignorer la config de la base de donnée
public class GestionProjetLogicielApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionProjetLogicielApplication.class, args);

    }

}
