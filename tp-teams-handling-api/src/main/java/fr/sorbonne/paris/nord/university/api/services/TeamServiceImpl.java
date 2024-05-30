package fr.sorbonne.paris.nord.university.api.services;

import fr.sorbonne.paris.nord.university.api.dtos.TeamDto;
import fr.sorbonne.paris.nord.university.api.entities.TeamEntity;
import fr.sorbonne.paris.nord.university.api.exceptions.ResourceNotFoundException;
import fr.sorbonne.paris.nord.university.api.mappers.TeamMapper;
import fr.sorbonne.paris.nord.university.api.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    @Override
    public List<TeamDto> getAllTeams() {
        return teamRepository.findAll().stream()
                .map(teamMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TeamDto getTeamById(Long id) {
        TeamEntity teamEntity = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + id));
        return teamMapper.toDto(teamEntity);
    }
    @Override
    public TeamDto saveTeam(TeamDto teamDto) {
        TeamEntity teamEntity = teamMapper.toEntity(teamDto);
        TeamEntity savedTeam = teamRepository.save(teamEntity);
        return teamMapper.toDto(savedTeam);
    }

    @Override
    public void deleteTeam(Long id) {
        TeamEntity teamEntity = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + id));
        teamRepository.delete(teamEntity);
    }


}
