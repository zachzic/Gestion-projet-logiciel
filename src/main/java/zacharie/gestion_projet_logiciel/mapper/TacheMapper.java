package zacharie.gestion_projet_logiciel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import zacharie.gestion_projet_logiciel.dto.ProjetDTO;
import zacharie.gestion_projet_logiciel.dto.TacheDTO;
import zacharie.gestion_projet_logiciel.model.Projet;
import zacharie.gestion_projet_logiciel.model.Tache;

@Mapper
public interface TacheMapper {
    TacheMapper INSTANCE = Mappers.getMapper(TacheMapper.class);

    @Mapping(source = "projet.id", target = "projet_id")
    @Mapping(source = "statut", target = "statut") // Ajout explicite du mappage pour le champ 'statut'
    TacheDTO tacheToTacheDTO(Tache tache);

    @Mapping(source = "projet_id", target = "projet.id")
    @Mapping(source = "statut", target = "statut") // Ajout explicite du mappage pour le champ 'statut'
    Tache tacheDTOToTache(TacheDTO tacheDTO);
}