package zacharie.gestion_projet_logiciel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import zacharie.gestion_projet_logiciel.dto.ActiviteDTO;
import zacharie.gestion_projet_logiciel.dto.TacheDTO;
import zacharie.gestion_projet_logiciel.model.Activite;
import zacharie.gestion_projet_logiciel.model.Tache;

@Mapper
public interface ActiviteMapper {
    ActiviteMapper INSTANCE = Mappers.getMapper(ActiviteMapper.class);

    @Mapping(source = "tache.id", target = "tache_id")
    @Mapping(source = "statut", target = "statut") // Ajout explicite du mappage pour le champ 'statut'
    ActiviteDTO activiteToActiviteDTO(Activite activite);

    @Mapping(source = "tache_id", target = "tache.id")
    @Mapping(source = "statut", target = "statut") // Ajout explicite du mappage pour le champ 'statut'
    Activite activiteDTOToActivite(ActiviteDTO activiteDTO);
}
