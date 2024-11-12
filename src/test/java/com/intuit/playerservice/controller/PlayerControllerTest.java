package com.intuit.playerservice.controller;

import com.intuit.playerservice.constant.ResponseMessage;
import com.intuit.playerservice.dto.PlayerResponse;
import com.intuit.playerservice.dto.PlayerSummaryDTO;
import com.intuit.playerservice.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class PlayerControllerTest {

    @InjectMocks
    private PlayerController playerController;

    @Mock
    private PlayerService playerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPlayers() {
        // Arrange
        PlayerSummaryDTO player = new PlayerSummaryDTO();
        player.setPlayerID("1");
        player.setNameFirst("John");
        player.setNameLast("Doe");

        when(playerService.getAllPlayers()).thenReturn(List.of(player));

        // Act
        ResponseEntity<Object> response = playerController.getAllPlayers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        PlayerResponse<List<PlayerSummaryDTO>> responseBody = (PlayerResponse<List<PlayerSummaryDTO>>) response.getBody();
        assert responseBody != null;
        assertEquals(ResponseMessage.SUCCESS, responseBody.getMessage());
        assertEquals(1, responseBody.getData().size());
        verify(playerService).getAllPlayers();
    }

    @Test
    void testGetPlayerById() {
        // Arrange
        String playerId = "1";
        PlayerSummaryDTO player = new PlayerSummaryDTO();
        player.setPlayerID(playerId);
        player.setNameFirst("John");
        player.setNameLast("Doe");

        when(playerService.getPlayerById(playerId)).thenReturn(player);

        // Act
        ResponseEntity<Object> response = playerController.getPlayerById(playerId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        PlayerResponse<PlayerSummaryDTO> responseBody = (PlayerResponse<PlayerSummaryDTO>) response.getBody();
        assert responseBody != null;
        assertEquals(ResponseMessage.SUCCESS, responseBody.getMessage());
        assertEquals(playerId, responseBody.getData().getPlayerID());
        verify(playerService).getPlayerById(playerId);
    }
}
