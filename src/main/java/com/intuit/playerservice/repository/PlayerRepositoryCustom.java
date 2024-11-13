package com.intuit.playerservice.repository;

import com.intuit.playerservice.dto.PlayerSummaryDTO;
import com.intuit.playerservice.entity.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepositoryCustom {
    List<PlayerSummaryDTO> findPlayerSummaries();

    PlayerSummaryDTO findPlayerById(String playerID);

    List<PlayerSummaryDTO> findPlayerByName(String nameFirst, String nameLast);

    Player savePlayer(Player player);

    Page<PlayerSummaryDTO> findPlayerList(Pageable pageable);
}
