package com.intuit.playerservice.repository;

import com.intuit.playerservice.dto.PlayerSummaryDTO;
import com.intuit.playerservice.exception.PlayerNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PlayerRepositoryImpl implements PlayerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String SELECT_ALL_COLUMNS =
            "SELECT p.\"playerID\", p.\"birthYear\", p.\"birthMonth\", p.\"birthDay\", " +
                    "p.\"birthCountry\", p.\"birthState\", p.\"birthCity\", p.\"deathYear\", " +
                    "p.\"deathMonth\", p.\"deathDay\", p.\"deathCountry\", p.\"deathState\", " +
                    "p.\"deathCity\", p.\"nameFirst\", p.\"nameLast\", p.\"nameGiven\", " +
                    "p.\"weight\", p.\"height\", p.\"bats\", p.\"throws\", p.\"debut\", " +
                    "p.\"finalGame\", p.\"retroID\", p.\"bbrefID\" " +
                    "FROM public.player p";

    @Override
    public List<PlayerSummaryDTO> findPlayerSummaries() {
        Query query = createNativeQuery(SELECT_ALL_COLUMNS);
        return (List<PlayerSummaryDTO>) query.getResultList();
    }

    @Override
    public PlayerSummaryDTO findById(String playerID) {
        try {
            String queryWithCondition = SELECT_ALL_COLUMNS + " WHERE p.\"playerID\" = :playerID";
            Query query = createNativeQuery(queryWithCondition);
            query.setParameter("playerID", playerID);
            return (PlayerSummaryDTO) query.getSingleResult();
        } catch (NoResultException e) {
            throw new PlayerNotFoundException("Player not found with ID: " + playerID);
        }
    }

    private Query createNativeQuery(String sql) {
        return entityManager.createNativeQuery(sql, "PlayerSummaryDTOMapping");
    }
}
