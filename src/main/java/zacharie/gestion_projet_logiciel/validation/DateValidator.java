package zacharie.gestion_projet_logiciel.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import zacharie.gestion_projet_logiciel.dto.ProjetDTO;

public class DateValidator implements ConstraintValidator<ValidProjectDates, ProjetDTO> {

    @Override
    public boolean isValid(ProjetDTO projetDTO, ConstraintValidatorContext context) {
        if (projetDTO.getDate_debut() == null || projetDTO.getDate_fin() == null) {
            return true; // handled by @NotNull validation
        }
        return projetDTO.getDate_debut().isBefore(projetDTO.getDate_fin());
    }
}

