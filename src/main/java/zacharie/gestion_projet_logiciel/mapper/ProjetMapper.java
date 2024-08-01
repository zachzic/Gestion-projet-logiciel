package zacharie.gestion_projet_logiciel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import zacharie.gestion_projet_logiciel.dto.ProjetDTO;
import zacharie.gestion_projet_logiciel.model.Projet;

@Mapper
public interface ProjetMapper {
    ProjetMapper INSTANCE = Mappers.getMapper(ProjetMapper.class);

    ProjetDTO projetToProjetDTO(Projet projet);

    Projet projetDTOToProjet(ProjetDTO projetDTO);
}
