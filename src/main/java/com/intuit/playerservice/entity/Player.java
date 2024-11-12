package com.intuit.playerservice.entity;

import com.intuit.playerservice.dto.PlayerSummaryDTO;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "player")
@Data
@SqlResultSetMapping(
        name = "PlayerSummaryDTOMapping",
        classes = {
                @ConstructorResult(
                        targetClass = PlayerSummaryDTO.class,
                        columns = {
                                @ColumnResult(name = "playerID", type = String.class),
                                @ColumnResult(name = "birthYear", type = Integer.class),
                                @ColumnResult(name = "birthMonth", type = Integer.class),
                                @ColumnResult(name = "birthDay", type = Integer.class),
                                @ColumnResult(name = "birthCountry", type = String.class),
                                @ColumnResult(name = "birthState", type = String.class),
                                @ColumnResult(name = "birthCity", type = String.class),
                                @ColumnResult(name = "deathYear", type = Integer.class),
                                @ColumnResult(name = "deathMonth", type = Integer.class),
                                @ColumnResult(name = "deathDay", type = Integer.class),
                                @ColumnResult(name = "deathCountry", type = String.class),
                                @ColumnResult(name = "deathState", type = String.class),
                                @ColumnResult(name = "deathCity", type = String.class),
                                @ColumnResult(name = "nameFirst", type = String.class),
                                @ColumnResult(name = "nameLast", type = String.class),
                                @ColumnResult(name = "nameGiven", type = String.class),
                                @ColumnResult(name = "weight", type = Integer.class),
                                @ColumnResult(name = "height", type = Integer.class),
                                @ColumnResult(name = "bats", type = String.class),
                                @ColumnResult(name = "throws", type = String.class),
                                @ColumnResult(name = "debut", type = LocalDateTime.class),
                                @ColumnResult(name = "finalGame", type = LocalDate.class),
                                @ColumnResult(name = "retroID", type = String.class),
                                @ColumnResult(name = "bbrefID", type = String.class)
                        }
                )
        }
)
public class Player {

    @Id
    @Column(name = "playerID", nullable = false)
    private String playerID;

    @Column(name = "birthYear")
    private Integer birthYear;

    @Column(name = "birthMonth")
    private Integer birthMonth;

    @Column(name = "birthDay")
    private Integer birthDay;

    @Column(name = "birthCountry")
    private String birthCountry;

    @Column(name = "birthState")
    private String birthState;

    @Column(name = "birthCity")
    private String birthCity;

    @Column(name = "deathYear")
    private Integer deathYear;

    @Column(name = "deathMonth")
    private Integer deathMonth;

    @Column(name = "deathDay")
    private Integer deathDay;

    @Column(name = "deathCountry")
    private String deathCountry;

    @Column(name = "deathState")
    private String deathState;

    @Column(name = "deathCity")
    private String deathCity;

    @Column(name = "nameFirst")
    private String nameFirst;

    @Column(name = "nameLast", nullable = false)
    private String nameLast;

    @Column(name = "nameGiven")
    private String nameGiven;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "height")
    private Integer height;

    @Column(name = "bats")
    private String bats;

    @Column(name = "throws")
    private String throwsHand;

    @Column(name = "debut")
    private LocalDateTime debut;

    @Column(name = "finalGame")
    private LocalDate finalGame;

    @Column(name = "retroID")
    private String retroID;

    @Column(name = "bbrefID")
    private String bbrefID;
}

