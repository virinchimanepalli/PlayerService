package com.intuit.playerservice.repository;

import com.intuit.playerservice.dto.PlayerSummaryDTO;

import java.util.List;

public interface PlayerRepositoryCustom {
    List<PlayerSummaryDTO> findPlayerSummaries();

    PlayerSummaryDTO findById(String playerID);
}
