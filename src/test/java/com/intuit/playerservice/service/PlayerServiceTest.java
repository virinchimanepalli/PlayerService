package com.intuit.playerservice.service;

import com.intuit.playerservice.constant.ResponseMessage;
import com.intuit.playerservice.dto.PlayerSummaryDTO;
import com.intuit.playerservice.exception.PlayerNotFoundException;
import com.intuit.playerservice.repository.PlayerRepositoryCustom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class PlayerServiceTest {

    @Autowired
    private PlayerService playerService;

    @MockBean
    private PlayerRepositoryCustom playerRepositoryCustom;

    private PlayerSummaryDTO player1;
    private PlayerSummaryDTO player2;

    @BeforeEach
    void setUp() {
        player1 = new PlayerSummaryDTO(
                "1", 1990, 5, 20, "USA", "CA", "Los Angeles",
                null, null, null, null, null, null,
                "John", "Doe", "Johnathan", 180, 75, "R", "R",
                LocalDateTime.of(2010, 4, 10, 0, 0), LocalDate.of(2020, 9, 15),
                "RET123", "BBR123"
        );

        player2 = new PlayerSummaryDTO(
                "2", 1992, 8, 15, "Canada", "ON", "Toronto",
                null, null, null, null, null, null,
                "Jane", "Smith", "Janie", 165, 68, "L", "L",
                LocalDateTime.of(2012, 5, 5, 0, 0), LocalDate.of(2022, 7, 20),
                "RET456", "BBR456"
        );
    }

    @Test
    public void testGetAllPlayers() {
        List<PlayerSummaryDTO> mockPlayers = Arrays.asList(player1, player2);
        Mockito.when(playerRepositoryCustom.findPlayerSummaries()).thenReturn(mockPlayers);
        List<PlayerSummaryDTO> players = playerService.getAllPlayers();
        assertNotNull(players);
        assertEquals(2, players.size());
        assertEquals("John", players.get(0).getNameFirst());
    }

    @Test
    public void testGetAllPlayersNotFound() {
        Mockito.when(playerRepositoryCustom.findPlayerSummaries()).thenReturn(Collections.emptyList());
        PlayerNotFoundException exception = assertThrows(PlayerNotFoundException.class, () -> {
            playerService.getAllPlayers();
        });
        assertEquals(ResponseMessage.PLAYER_NOT_FOUND, exception.getMessage());
    }

    @Test
    public void testGetPlayerById() {
        String playerId = "1";
        Mockito.when(playerRepositoryCustom.findById(playerId)).thenReturn(player1);
        PlayerSummaryDTO result = playerService.getPlayerById(playerId);
        assertNotNull(result);
        assertEquals("1", result.getPlayerID());
        assertEquals("John", result.getNameFirst());
    }

    @Test
    public void testGetPlayerByIdNotFound() {
        String playerId = "invalid";
        Mockito.when(playerRepositoryCustom.findById(playerId)).thenReturn(null);
        assertThrows(PlayerNotFoundException.class, () -> {
            playerService.getPlayerById(playerId);
        });
    }
}
