package com.intuit.playerservice.service;

import com.intuit.playerservice.dto.PlayerSummaryDTO;
import com.intuit.playerservice.exception.InvalidInputException;
import com.intuit.playerservice.exception.PlayerNotFoundException;
import com.intuit.playerservice.repository.PlayerRepositoryCustom;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.intuit.playerservice.constant.ResponseMessage;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepositoryCustom playerRepositoryCustom;

    public List<PlayerSummaryDTO> getAllPlayers() {
        List<PlayerSummaryDTO> players = playerRepositoryCustom.findPlayerSummaries();
        if (players.isEmpty()) {
            throw new PlayerNotFoundException(ResponseMessage.PLAYER_NOT_FOUND);
        }
        return players;
    }

    public PlayerSummaryDTO getPlayerById(String playerID) {
        if (playerID == null || playerID.trim().isEmpty()) {
            throw new InvalidInputException(ResponseMessage.INVALID_PLAYER);
        }

        PlayerSummaryDTO player = playerRepositoryCustom.findPlayerById(playerID);
        if (player == null) {
            throw new PlayerNotFoundException("Player ID " + playerID + ": " + ResponseMessage.PLAYER_NOT_FOUND);
        }
        return player;
    }
}
