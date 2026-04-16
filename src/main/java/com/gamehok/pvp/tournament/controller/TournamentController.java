package com.gamehok.pvp.tournament.controller;

import com.gamehok.pvp.tournament.entity.Team;
import com.gamehok.pvp.tournament.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tournaments")
@RequiredArgsConstructor
public class TournamentController {

    private final MatchService matchService;

    @PostMapping("/{tournamentId}/generate-bracket")
    public ResponseEntity<String> generateBracket(@PathVariable Long tournamentId) {
        matchService.generateBracket(tournamentId);
        return ResponseEntity.ok("Bracket Generated Successfully");
    }

    @PostMapping("/{tournamentId}/next-round")
    public ResponseEntity<String> generateNextRound(@PathVariable Long tournamentId,
                                                    @RequestParam int currentRound) {
        matchService.generateNextRound(tournamentId, currentRound);
        return ResponseEntity.ok("Next round generated successfully");
    }

    @GetMapping("/{tournamentId}/winner")
    public ResponseEntity<Team> getWinner(@PathVariable Long tournamentId) {
        return ResponseEntity.ok(matchService.getTournamentWinner(tournamentId));
    }

}
