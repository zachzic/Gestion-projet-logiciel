package zacharie.gestion_projet_logiciel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import zacharie.gestion_projet_logiciel.dto.ActiviteDTO;
import zacharie.gestion_projet_logiciel.model.Activite;

@Mapper
public interface ActiviteMapper {
    ActiviteMapper INSTANCE = Mappers.getMapper(ActiviteMapper.class);

    ActiviteDTO activiteToActiviteDTO(Activite activite);

    Activite activiteDTOToActivite(ActiviteDTO activiteDTO);
}
