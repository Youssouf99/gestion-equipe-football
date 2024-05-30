package fr.sorbonne.paris.nord.university.api;

import fr.sorbonne.paris.nord.university.api.controllers.TeamController;
import fr.sorbonne.paris.nord.university.api.dtos.TeamDto;
import fr.sorbonne.paris.nord.university.api.services.TeamService;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;


@WebMvcTest(TeamController.class)
@AutoConfigureMockMvc
public class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamService teamService;

    @BeforeEach
    public void setUp() {
        standaloneSetup(new TeamController(teamService));
    }

    @Test
    public void testGetAllTeams() throws Exception {
        // Given
        List<TeamDto> expectedTeams = Arrays.asList(
                new TeamDto(1L, "PSG", "Revons plus grand"),
                new TeamDto(2L, "Real Madrid", "Les galactiques"),
                new TeamDto(3L, "Barcelone", "La Masia"),
                new TeamDto(4L, "Bayern", "Les puissants en Allemagne"),
                new TeamDto(5L, "Manchester United", "Les Red Devils")
        );
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/teams")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("$.size()", equalTo(expectedTeams.size()))  // Vérifie la taille de la liste
                .body("[0].id", equalTo(expectedTeams.get(0).getId().intValue()))
                .body("[0].name", equalTo(expectedTeams.get(0).getName()))
                .body("[0].slogan", equalTo(expectedTeams.get(0).getSlogan()))
                .body("[1].id", equalTo(expectedTeams.get(1).getId().intValue()))
                .body("[1].name", equalTo(expectedTeams.get(1).getName()))
                .body("[1].slogan", equalTo(expectedTeams.get(1).getSlogan()))
                .body("[2].id", equalTo(expectedTeams.get(2).getId().intValue()))
                .body("[2].name", equalTo(expectedTeams.get(2).getName()))
                .body("[2].slogan", equalTo(expectedTeams.get(2).getSlogan()))
                .body("[3].id", equalTo(expectedTeams.get(3).getId().intValue()))
                .body("[3].name", equalTo(expectedTeams.get(3).getName()))
                .body("[3].slogan", equalTo(expectedTeams.get(3).getSlogan()))
                .body("[4].id", equalTo(expectedTeams.get(4).getId().intValue()))
                .body("[4].name", equalTo(expectedTeams.get(4).getName()))
                .body("[4].slogan", equalTo(expectedTeams.get(4).getSlogan()));    }


    @Test
    public void testGetTeamById() {
        Long teamId = 1L;
        TeamDto team = new TeamDto(teamId, "PSG", "Revons plus grand");

        Mockito.when(teamService.getTeamById(teamId)).thenReturn(team);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/teams/{id}", teamId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("id", equalTo(team.getId().intValue()))
                .body("name", equalTo(team.getName()))
                .body("slogan", equalTo(team.getSlogan()));
    }

    @Test
    public void testCreateTeam() {
        TeamDto team = new TeamDto(null, "New Team", "New Slogan");
        TeamDto createdTeam = new TeamDto(6L, "New Team", "New Slogan");

        Mockito.when(teamService.saveTeam(any(TeamDto.class))).thenReturn(createdTeam);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(team)
                .when()
                .post("/api/teams")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("id", equalTo(createdTeam.getId().intValue()))
                .body("name", equalTo(createdTeam.getName()))
                .body("slogan", equalTo(createdTeam.getSlogan()));
    }

    @Test
    public void testDeleteTeam() {
        Long teamId = 1L;

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete("/api/teams/{id}", teamId)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }








}
