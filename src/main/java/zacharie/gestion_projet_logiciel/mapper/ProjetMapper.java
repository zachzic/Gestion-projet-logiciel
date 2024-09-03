package zacharie.gestion_projet_logiciel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import zacharie.gestion_projet_logiciel.dto.ProjetDTO;
import zacharie.gestion_projet_logiciel.model.Projet;

@Mapper
public interface ProjetMapper {
    ProjetMapper INSTANCE = Mappers.getMapper(ProjetMapper.class);

    @Mapping(source = "statut", target = "statut") // Ajout explicite du mappage pour le champ 'statut'
    @Mapping(source = "responsable", target = "responsable")
    @Mapping(source = "description", target = "description")

    ProjetDTO projetToProjetDTO(Projet projet);

    @Mapping(source = "statut", target = "statut") // Ajout explicite du mappage pour le champ 'statut'
    @Mapping(source = "responsable", target = "responsable")
    @Mapping(source = "description", target = "description")
    Projet projetDTOToProjet(ProjetDTO projetDTO);
}
