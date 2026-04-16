package com.gamehok.pvp.tournament.repository;

import com.gamehok.pvp.tournament.entity.Match;
import com.gamehok.pvp.tournament.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findByTournamentId(Long tournamentId);
}
