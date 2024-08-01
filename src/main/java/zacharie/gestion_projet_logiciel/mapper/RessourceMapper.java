package zacharie.gestion_projet_logiciel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import zacharie.gestion_projet_logiciel.dto.RessourceDTO;
import zacharie.gestion_projet_logiciel.model.Ressource;

@Mapper
public interface RessourceMapper {
    RessourceMapper INSTANCE = Mappers.getMapper(RessourceMapper.class);

    RessourceDTO ressourceToRessourceDTO(Ressource ressource);

    Ressource ressourceDTOToRessource(RessourceDTO ressourceDTO);
}
