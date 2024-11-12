package com.intuit.playerservice.controller;

import com.intuit.playerservice.constant.ResponseMessage;
import com.intuit.playerservice.dto.PlayerResponse;
import com.intuit.playerservice.dto.PlayerSummaryDTO;
import com.intuit.playerservice.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public ResponseEntity<Object> getAllPlayers() {
        List<PlayerSummaryDTO> players = playerService.getAllPlayers();
        PlayerResponse<List<PlayerSummaryDTO>> response = new PlayerResponse<>(ResponseMessage.SUCCESS, players);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{playerID}")
    public ResponseEntity<Object> getPlayerById(@PathVariable String playerID) {
        PlayerSummaryDTO player = playerService.getPlayerById(playerID);
        PlayerResponse<PlayerSummaryDTO> response = new PlayerResponse<>(ResponseMessage.SUCCESS, player);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

