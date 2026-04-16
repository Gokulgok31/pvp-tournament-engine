package com.gamehok.pvp.tournament.entity;

import com.gamehok.pvp.tournament.enums.MatchStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer roundNumber;
    private Integer matchNumber;
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
    @ManyToOne
    @JoinColumn(name = "team1_id")
    private Team team1;
    @ManyToOne
    @JoinColumn(name = "team2_id")
    private Team team2;
    @ManyToOne
    @JoinColumn(name = "winner_team_id")
    private Team winner;
    @Enumerated(EnumType.STRING)
    private MatchStatus status;
}
