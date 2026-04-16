package com.gamehok.pvp.tournament.service;

import com.gamehok.pvp.tournament.entity.Match;
import com.gamehok.pvp.tournament.entity.Team;
import com.gamehok.pvp.tournament.entity.Tournament;
import com.gamehok.pvp.tournament.enums.MatchStatus;
import com.gamehok.pvp.tournament.repository.MatchRepository;
import com.gamehok.pvp.tournament.repository.TeamRepository;
import com.gamehok.pvp.tournament.repository.TournamentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Service
public class MatchService {

    private TeamRepository teamRepository;
    private MatchRepository matchRepository;
    private TournamentRepository tournamentRepository;

    public void generateBracket(Long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));
        List<Team> teams = teamRepository.findByTournamentId(tournamentId);
        log.info("Tournament {} has teams {}", tournament.getId(), teams);
        if(teams.size() < 2) {
            throw new RuntimeException("Not enough teams to create match");
        }

        int matchNumber = 1;

        Collections.shuffle(teams);

        for(int i = 0; i < teams.size(); i+=2) {
            Team team1 = teams.get(i);
            Team team2 = null;

            if(i + 1 < teams.size()) {
                team2 = teams.get(i + 1);
            }
            Match match = Match.builder()
                    .tournament(tournament)
                    .team1(team1)
                    .team2(team2)
                    .roundNumber(1)
                    .matchNumber(matchNumber++)
                    .status(MatchStatus.SCHEDULED)
                    .build();
            if (team2 == null) {
                match.setWinner(team1);
                match.setStatus(MatchStatus.COMPLETED);
            }
            matchRepository.save(match);
            log.info("Created match {} for round 1", match.getMatchNumber());
        }
    }

    public void generateNextRound(Long tournamentId, int currentRound) {

        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));
        List<Match> completedMatches = matchRepository.findByTournamentIdAndRoundNumberAndStatus(
                tournamentId, currentRound, MatchStatus.COMPLETED
        );
        if(completedMatches.isEmpty()) {
            throw new RuntimeException("No completed matches found for this round");
        }
        ArrayList<Team> winnerTeams = new ArrayList<>();
        for(int i = 0; i < completedMatches.size(); i++) {
            winnerTeams.add(completedMatches.get(i).getWinner());
        }
        if(winnerTeams.size() < 2) {
            throw new RuntimeException("Not enough teams to create next round");
        }
        int matchNumber = 1;

        Collections.shuffle(winnerTeams);
        for(int i = 0; i < winnerTeams.size(); i += 2) {
            Team team1 = winnerTeams.get(i);
            Team team2 = null;

            if(i + 1 < winnerTeams.size()) {
                team2 = winnerTeams.get(i + 1);
            }
            Match match = Match.builder()
                    .tournament(tournament)
                    .team1(team1)
                    .team2(team2)
                    .roundNumber(currentRound + 1)
                    .matchNumber(matchNumber++)
                    .status(MatchStatus.SCHEDULED)
                    .build();
            if (team2 == null) {
                match.setWinner(team1);
                match.setStatus(MatchStatus.COMPLETED);
            }
            matchRepository.save(match);
            log.info("Created match {} for round {}", match.getMatchNumber(), currentRound + 1);
        }
    }

    public void submitMatchResult(Long matchId, Long winnerTeamId) {

        Match match = matchRepository.findById(matchId).orElseThrow(() -> new RuntimeException("Match not found"));

        Team teamWinner = teamRepository.findById(winnerTeamId).orElseThrow(() -> new RuntimeException("Team not found"));

        if (!winnerTeamId.equals(match.getTeam1()) && !winnerTeamId.equals(match.getTeam2())) {
            throw new RuntimeException("Winner must be one of the teams in the match");
        }

        match.setWinner(teamWinner);
        match.setStatus(MatchStatus.COMPLETED);

        matchRepository.save(match);
    }
}
