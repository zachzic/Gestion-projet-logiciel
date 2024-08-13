package zacharie.gestion_projet_logiciel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import zacharie.gestion_projet_logiciel.dto.RessourceDTO;
import zacharie.gestion_projet_logiciel.model.Ressource;

@Mapper
public interface RessourceMapper {
    RessourceMapper INSTANCE = Mappers.getMapper(RessourceMapper.class);

    @Mapping(source = "activite.id", target = "activite_id")
    RessourceDTO ressourceToRessourceDTO(Ressource ressource);

    @Mapping(source = "activite_id", target = "activite.id")
    Ressource ressourceDTOToRessource(RessourceDTO ressourceDTO);
}
