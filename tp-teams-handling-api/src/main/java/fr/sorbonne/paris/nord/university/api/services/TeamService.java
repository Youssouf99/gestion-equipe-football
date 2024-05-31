package fr.sorbonne.paris.nord.university.api.services;

import fr.sorbonne.paris.nord.university.api.dtos.TeamDto;
import fr.sorbonne.paris.nord.university.api.entities.TeamEntity;

import java.util.List;

public interface TeamService {


    List<TeamDto> getAllTeams();

    TeamDto getTeamById(Long id);

    TeamDto saveTeam(TeamDto teamDto);

    TeamDto updateTeam(Long id, TeamDto teamDto);

    void deleteTeam(Long id);
}
