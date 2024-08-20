package zacharie.gestion_projet_logiciel.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import zacharie.gestion_projet_logiciel.repository.ProjetRepository;

public class UniqueProjectNameValidator implements ConstraintValidator<UniqueProjectName, String> {

    @Autowired
    private ProjetRepository projetRepository;

    @Override
    public boolean isValid(String nom, ConstraintValidatorContext context) {
        return !projetRepository.existsByNom(nom); //gerer plus tard
    }
}
