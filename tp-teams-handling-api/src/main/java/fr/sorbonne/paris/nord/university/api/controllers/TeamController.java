package fr.sorbonne.paris.nord.university.api.controllers;

import fr.sorbonne.paris.nord.university.api.dtos.TeamDto;
import fr.sorbonne.paris.nord.university.api.entities.TeamEntity;
import fr.sorbonne.paris.nord.university.api.services.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;


    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }
    @GetMapping("/hello")
    public String getTeams() {
        return "Hello World";
    }

    @GetMapping()
    public List<TeamDto> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDto> getTeamById(@PathVariable Long id) {
        TeamDto team = teamService.getTeamById(id);
        return ResponseEntity.ok(team);
    }



    @PostMapping
    public ResponseEntity<TeamDto> createTeam(@RequestBody TeamDto teamDto) {
        TeamDto savedTeam = teamService.saveTeam(teamDto);
        return ResponseEntity.ok(savedTeam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }






}
