package com.gamehok.pvp.tournament.repository;

import com.gamehok.pvp.tournament.entity.Match;
import com.gamehok.pvp.tournament.enums.MatchStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByTournamentIdAndRoundNumberAndStatus(
            Long tournamentId,
            int roundNumber,
            MatchStatus status
    );
    @Query("SELECT MAX(m.roundNumber) FROM Match m WHERE m.tournament.id = :tournamentId")
    Integer findMaxRoundNumber(Long tournamentId);

    List<Match> findByTournamentIdAndRoundNumber(Long tournamentId, int maxRound);

    Optional<Match> findById(Long matchId);
}
