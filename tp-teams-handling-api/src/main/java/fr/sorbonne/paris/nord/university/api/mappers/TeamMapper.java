package fr.sorbonne.paris.nord.university.api.mappers;

import fr.sorbonne.paris.nord.university.api.dtos.TeamDto;
import fr.sorbonne.paris.nord.university.api.entities.TeamEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    TeamDto toDto(TeamEntity teamEntity);

    TeamEntity toEntity(TeamDto teamDto);
}
