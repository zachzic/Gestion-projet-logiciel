package zacharie.gestion_projet_logiciel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import zacharie.gestion_projet_logiciel.dto.TacheDTO;
import zacharie.gestion_projet_logiciel.model.Tache;

@Mapper
public interface TacheMapper {
    TacheMapper INSTANCE = Mappers.getMapper(TacheMapper.class);

    TacheDTO tacheToTacheDTO(Tache tache);

    Tache tacheDTOToTache(TacheDTO tacheDTO);
}
