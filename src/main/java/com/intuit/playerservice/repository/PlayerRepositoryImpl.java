package com.intuit.playerservice.repository;

import com.intuit.playerservice.dto.PlayerSummaryDTO;
import com.intuit.playerservice.entity.Player;
import com.intuit.playerservice.exception.PlayerNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
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
    public PlayerSummaryDTO findPlayerById(String playerID) {
        try {
            String queryWithCondition = SELECT_ALL_COLUMNS + " WHERE p.\"playerID\" = :playerID";
            Query query = createNativeQuery(queryWithCondition);
            query.setParameter("playerID", playerID);
            return (PlayerSummaryDTO) query.getSingleResult();
        } catch (NoResultException e) {
            throw new PlayerNotFoundException("Player not found with ID: " + playerID);
        }
    }

    @Override
    public List<PlayerSummaryDTO> findPlayerByName(String nameFirst, String nameLast) {
        Query query = createNativeQuery(SELECT_ALL_COLUMNS + " where p.\"nameFirst\" = :nameFirst and p.\"nameLast\" = :nameLast");
        query.setParameter("nameFirst", nameFirst);
        query.setParameter("nameLast", nameLast);
        return (List<PlayerSummaryDTO>) query.getResultList();
    }

    private Query createNativeQuery(String sql) {
        return entityManager.createNativeQuery(sql, "PlayerSummaryDTOMapping");
    }

    @Transactional
    @Override
    public Player savePlayer(Player player) {
        String sql = "INSERT INTO public.player (\"playerID\", \"birthYear\", \"birthMonth\", \"birthDay\", " +
                "\"birthCountry\", \"birthState\", \"birthCity\", \"deathYear\", " +
                "\"deathMonth\", \"deathDay\", \"deathCountry\", \"deathState\", " +
                "\"deathCity\", \"nameFirst\", \"nameLast\", \"nameGiven\", " +
                "\"weight\", \"height\", \"bats\", \"throws\", \"debut\", " +
                "\"finalGame\", \"retroID\", \"bbrefID\") " +
                "VALUES (:playerID, :birthYear, :birthMonth, :birthDay, " +
                ":birthCountry, :birthState, :birthCity, :deathYear, " +
                ":deathMonth, :deathDay, :deathCountry, :deathState, " +
                ":deathCity, :nameFirst, :nameLast, :nameGiven, " +
                ":weight, :height, :bats, :throws, :debut, " +
                ":finalGame, :retroID, :bbrefID)";

        entityManager.createNativeQuery(sql)
                .setParameter("playerID", player.getPlayerID())
                .setParameter("birthYear", player.getBirthYear())
                .setParameter("birthMonth", player.getBirthMonth())
                .setParameter("birthDay", player.getBirthDay())
                .setParameter("birthCountry", player.getBirthCountry())
                .setParameter("birthState", player.getBirthState())
                .setParameter("birthCity", player.getBirthCity())
                .setParameter("deathYear", player.getDeathYear())
                .setParameter("deathMonth", player.getDeathMonth())
                .setParameter("deathDay", player.getDeathDay())
                .setParameter("deathCountry", player.getDeathCountry())
                .setParameter("deathState", player.getDeathState())
                .setParameter("deathCity", player.getDeathCity())
                .setParameter("nameFirst", player.getNameFirst())
                .setParameter("nameLast", player.getNameLast())
                .setParameter("nameGiven", player.getNameGiven())
                .setParameter("weight", player.getWeight())
                .setParameter("height", player.getHeight())
                .setParameter("bats", player.getBats())
                .setParameter("throws", player.getThrowsHand())
                .setParameter("debut", player.getDebut())
                .setParameter("finalGame", player.getFinalGame())
                .setParameter("retroID", player.getRetroID())
                .setParameter("bbrefID", player.getBbrefID())
                .executeUpdate();

        return player;
    }

    @Override
    public Page<PlayerSummaryDTO> findPlayerList(Pageable pageable) {
        Query query = createNativeQuery(SELECT_ALL_COLUMNS)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize());
        List<PlayerSummaryDTO> results = query.getResultList();

        // Count query for total elements
        Query countQuery = entityManager.createNativeQuery("SELECT COUNT(*) FROM public.player");
        long totalElements = ((Number) countQuery.getSingleResult()).longValue();

        return new PageImpl<>(results, pageable, totalElements);
    }

}
