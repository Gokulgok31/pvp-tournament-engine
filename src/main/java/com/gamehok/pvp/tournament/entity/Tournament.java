package com.gamehok.pvp.tournament.entity;


import com.gamehok.pvp.tournament.enums.TournamentStatus;
import com.gamehok.pvp.tournament.enums.TournamentType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tournamentName;
    @Enumerated(EnumType.STRING)
    private TournamentType tournamentType;
    private Integer maxTeams;
    @Enumerated(EnumType.STRING)
    private TournamentStatus tournamentStatus;
}
