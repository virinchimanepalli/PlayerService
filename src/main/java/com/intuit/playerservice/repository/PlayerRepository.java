package com.intuit.playerservice.repository;

import com.intuit.playerservice.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String>, PlayerRepositoryCustom {
}
