package com.intuit.playerservice.service;

import com.intuit.playerservice.dto.PlayerSummaryDTO;
import com.intuit.playerservice.entity.Player;
import com.intuit.playerservice.exception.InvalidInputException;
import com.intuit.playerservice.exception.PlayerNotFoundException;
import com.intuit.playerservice.repository.PlayerRepository;
import com.intuit.playerservice.repository.PlayerRepositoryCustom;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.intuit.playerservice.constant.ResponseMessage;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepositoryCustom playerRepositoryCustom;

    @Autowired
    private PlayerRepository playerRepository;

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

    public List<PlayerSummaryDTO> getPlayerByName(String nameFirst, String nameLast){
        if(nameFirst.isEmpty() || nameLast.isEmpty()){
            throw new InvalidInputException(ResponseMessage.INVALID_PLAYER);
        }

        return playerRepositoryCustom.findPlayerByName(nameFirst, nameLast);
    }

    public PlayerSummaryDTO addPlayer(PlayerSummaryDTO player){
        Player playerObj = new Player();
        playerObj.setPlayerID(player.getPlayerID());
        playerObj.setBirthYear(player.getBirthYear());
        playerObj.setBirthMonth(player.getBirthMonth());
        playerObj.setBirthDay(player.getBirthDay());
        playerObj.setBirthCountry(player.getBirthCountry());
        playerObj.setBirthState(player.getBirthState());
        playerObj.setBirthCity(player.getBirthCity());
        playerObj.setDeathYear(player.getDeathYear());
        playerObj.setDeathMonth(player.getDeathMonth());
        playerObj.setDeathDay(player.getDeathDay());
        playerObj.setDeathCountry(player.getDeathCountry());
        playerObj.setDeathState(player.getDeathState());
        playerObj.setDeathCity(player.getDeathCity());
        playerObj.setNameFirst(player.getNameFirst());
        playerObj.setNameLast(player.getNameLast());
        playerObj.setNameGiven(player.getNameGiven());
        playerObj.setWeight(player.getWeight());
        playerObj.setHeight(player.getHeight());
        playerObj.setBats(player.getBats());
        playerObj.setThrowsHand(player.getThrowsHand());
        playerObj.setDebut(player.getDebut());
        playerObj.setFinalGame(player.getFinalGame());
        playerObj.setRetroID(player.getRetroID());
        playerObj.setBbrefID(player.getBbrefID());

        Player savedPlayer = playerRepositoryCustom.savePlayer(playerObj);
        return convertToPlayerSummaryDTO(savedPlayer);
    }

    public PlayerSummaryDTO convertToPlayerSummaryDTO(Player player) {
        PlayerSummaryDTO dto = new PlayerSummaryDTO();
        dto.setPlayerID(player.getPlayerID());
        dto.setBirthYear(player.getBirthYear());
        dto.setBirthMonth(player.getBirthMonth());
        dto.setBirthDay(player.getBirthDay());
        dto.setBirthCountry(player.getBirthCountry());
        dto.setBirthState(player.getBirthState());
        dto.setBirthCity(player.getBirthCity());
        dto.setDeathYear(player.getDeathYear());
        dto.setDeathMonth(player.getDeathMonth());
        dto.setDeathDay(player.getDeathDay());
        dto.setDeathCountry(player.getDeathCountry());
        dto.setDeathState(player.getDeathState());
        dto.setDeathCity(player.getDeathCity());
        dto.setNameFirst(player.getNameFirst());
        dto.setNameLast(player.getNameLast());
        dto.setNameGiven(player.getNameGiven());
        dto.setWeight(player.getWeight());
        dto.setHeight(player.getHeight());
        dto.setBats(player.getBats());
        dto.setThrowsHand(player.getThrowsHand());
        dto.setDebut(player.getDebut());
        dto.setFinalGame(player.getFinalGame());
        dto.setRetroID(player.getRetroID());
        dto.setBbrefID(player.getBbrefID());
        return dto;
    }

    public Page<PlayerSummaryDTO> getAllPlayersList(Pageable pageable){
        return playerRepositoryCustom.findPlayerList(pageable);
    }
}
