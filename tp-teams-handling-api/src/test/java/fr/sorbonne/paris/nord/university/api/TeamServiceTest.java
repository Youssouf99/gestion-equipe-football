package fr.sorbonne.paris.nord.university.api;

import fr.sorbonne.paris.nord.university.api.dtos.TeamDto;
import fr.sorbonne.paris.nord.university.api.entities.TeamEntity;
import fr.sorbonne.paris.nord.university.api.exceptions.ResourceNotFoundException;
import fr.sorbonne.paris.nord.university.api.repositories.TeamRepository;
import fr.sorbonne.paris.nord.university.api.services.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class TeamServiceTest {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamRepository teamRepository;


    @BeforeEach
    public void setUp() {
        teamRepository.deleteAll();
        TeamEntity team1 = new TeamEntity();
        team1.setName("PSG");
        team1.setSlogan("Revons plus grand");
        teamRepository.save(team1);

        TeamEntity team2 = new TeamEntity();
        team2.setName("Real Madrid");
        team2.setSlogan("Les galactiques");
        teamRepository.save(team2);
    }

    @Test
    public void shouldGetAllTeams() {
        List<TeamDto> teamDtos = teamService.getAllTeams();
        assertThat(teamDtos).hasSize(2)
                .extracting(TeamDto::getName)
                .containsExactlyInAnyOrder("PSG", "Real Madrid");
    }

    @Test
    public void shouldReturnTheExpectedTeam_whenGetTeamByExistingId() {
        // Given
        List<TeamDto> teamDtos = teamService.getAllTeams();
        Long existingId = teamDtos.get(0).getId();

        // When
        TeamDto teamDto = teamService.getTeamById(existingId);

        // Then
        assertThat(teamDto).isNotNull();
        assertThat(teamDto.getId()).isEqualTo(existingId);
        assertThat(teamDto.getName()).isEqualTo(teamDtos.get(0).getName());
        assertThat(teamDto.getSlogan()).isEqualTo(teamDtos.get(0).getSlogan());
    }

    @Test
    public void givenExistingId_whenGetTeamById_thenExpectedTeamInResult() {
        List<TeamDto> teamDtos = teamService.getAllTeams();
        Long existingId = teamDtos.get(0).getId();

        TeamDto teamDto = teamService.getTeamById(existingId);

        assertThat(teamDto).isNotNull();
        assertThat(teamDto.getId()).isEqualTo(existingId);
        assertThat(teamDto.getName()).isEqualTo(teamDtos.get(0).getName());
        assertThat(teamDto.getSlogan()).isEqualTo(teamDtos.get(0).getSlogan());
    }

    @Test
    public void shouldThrowExceptionWhenTeamNotFoundById() {
        // Given
        Long invalidId = 999L;

        // When & Then
        assertThrows(ResourceNotFoundException.class, () -> {
            teamService.getTeamById(invalidId);
        });
    }


    @Test
    public void shouldSaveTeam() {
        TeamDto newTeamDto = new TeamDto();
        newTeamDto.setName("Barcelona");
        newTeamDto.setSlogan("La Masia");

        TeamDto savedTeamDto = teamService.saveTeam(newTeamDto);

        assertThat(savedTeamDto).isNotNull();
        assertThat(savedTeamDto.getId()).isNotNull();
        assertThat(savedTeamDto.getName()).isEqualTo("Barcelona");
        assertThat(savedTeamDto.getSlogan()).isEqualTo("La Masia");

        List<TeamDto> teams = teamService.getAllTeams();
        assertThat(teams).hasSize(3)
                .extracting(TeamDto::getName)
                .contains("Barcelona");
    }

    @Test
    public void shouldDeleteTeam() {
        List<TeamDto> teamDtos = teamService.getAllTeams();
        Long teamId = teamDtos.get(0).getId();

        teamService.deleteTeam(teamId);

        assertThrows(ResourceNotFoundException.class, () -> {
            teamService.getTeamById(teamId);
        });

        List<TeamDto> remainingTeamDtos = teamService.getAllTeams();
        assertThat(remainingTeamDtos).hasSize(1)
                .extracting(TeamDto::getName)
                .doesNotContain(teamDtos.get(0).getName());
    }



}
