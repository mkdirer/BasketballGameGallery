package project.basketballgamegallery.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import project.basketballgamegallery.controller.TeamController;
import project.basketballgamegallery.model.Team;
import project.basketballgamegallery.repository.TeamRepository;


import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TeamController.class)
@AutoConfigureMockMvc
@WithMockUser
public class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamRepository teamRepository;


    @Test
    void getAllTeams_NoNameParameter_ReturnsAllTeams() throws Exception {
        // Arrange
        Team team1 = new Team("TeamA", "CountryA", "LeagueA");
        Team team2 = new Team("TeamB", "CountryB", "LeagueB");
        List<Team> teams = Arrays.asList(team1, team2);
        when(teamRepository.findAll()).thenReturn(teams);

        // Act & Assert
        mockMvc.perform(get("/api/teams"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("TeamA")))
                .andExpect(jsonPath("$[0].country", is("CountryA")))
                .andExpect(jsonPath("$[0].league", is("LeagueA")))
                .andExpect(jsonPath("$[1].name", is("TeamB")))
                .andExpect(jsonPath("$[1].country", is("CountryB")))
                .andExpect(jsonPath("$[1].league", is("LeagueB")));

        verify(teamRepository, times(1)).findAll();
    }

    @Test
    void getAllTeams_WithNameParameter_ReturnsTeamsWithNameContainingParameter() throws Exception {
        // Arrange
        Team team1 = new Team("TeamA", "CountryA", "LeagueA");
        Team team2 = new Team("TeamB", "CountryB", "LeagueB");
        List<Team> teams = Arrays.asList(team1, team2);
        when(teamRepository.findByNameContaining("Team")).thenReturn(teams);

        // Act & Assert
        mockMvc.perform(get("/api/teams?name=Team"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("TeamA")))
                .andExpect(jsonPath("$[0].country", is("CountryA")))
                .andExpect(jsonPath("$[0].league", is("LeagueA")))
                .andExpect(jsonPath("$[1].name", is("TeamB")))
                .andExpect(jsonPath("$[1].country", is("CountryB")))
                .andExpect(jsonPath("$[1].league", is("LeagueB")));

        verify(teamRepository, times(1)).findByNameContaining("Team");
    }

    @Test
    void getAllTeams_NoTeamsFound_ReturnsNoContent() throws Exception {
        // Arrange
        when(teamRepository.findAll()).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/teams"))
                .andExpect(status().isNoContent());

        verify(teamRepository, times(1)).findAll();
    }

    @Test
    void getTeamById_ExistingId_ReturnsTeam() throws Exception {
        // Arrange
        long teamId = 1L;
        Team team = new Team("TeamA", "CountryA", "LeagueA");
        when(teamRepository.findById(teamId)).thenReturn(Optional.of(team));

        // Act & Assert
        mockMvc.perform(get("/api/teams/{id}", teamId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("TeamA")))
                .andExpect(jsonPath("$.country", is("CountryA")))
                .andExpect(jsonPath("$.league", is("LeagueA")));

        verify(teamRepository, times(1)).findById(teamId);
    }

    @Test
    void getTeamById_NonExistingId_ReturnsNotFound() throws Exception {
        // Arrange
        long teamId = 1L;
        when(teamRepository.findById(teamId)).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(get("/api/teams/{id}", teamId))
                .andExpect(status().isNotFound());

        verify(teamRepository, times(1)).findById(teamId);
    }

    @Test
    void findByPublished_ValidLeague_ReturnsTeamsWithMatchingLeague() throws Exception {
        // Arrange
        String league = "NBA";
        Team team1 = new Team("TeamA", "CountryA", "NBA");
        Team team2 = new Team("TeamB", "CountryB", "NBA");
        List<Team> teams = Arrays.asList(team1, team2);
        when(teamRepository.findByLeagueContaining(league)).thenReturn(teams);

        // Act & Assert
        mockMvc.perform(get("/api/teams/league")
                        .param("league", league))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("TeamA")))
                .andExpect(jsonPath("$[0].country", is("CountryA")))
                .andExpect(jsonPath("$[0].league", is("NBA")))
                .andExpect(jsonPath("$[1].name", is("TeamB")))
                .andExpect(jsonPath("$[1].country", is("CountryB")))
                .andExpect(jsonPath("$[1].league", is("NBA")));

        verify(teamRepository, times(1)).findByLeagueContaining(league);
    }

    @Test
    void findByPublished_NoTeamsFound_ReturnsNoContent() throws Exception {
        // Arrange
        String league = "NBA";
        when(teamRepository.findByLeagueContaining(league)).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/teams/league")
                        .param("league", league))
                .andExpect(status().isNoContent());

        verify(teamRepository, times(1)).findByLeagueContaining(league);
    }
}
