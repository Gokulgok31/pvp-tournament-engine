package com.gamehok.pvp.tournament.controller;


import com.gamehok.pvp.tournament.dto.MatchResultRequestDto;
import com.gamehok.pvp.tournament.entity.Team;
import com.gamehok.pvp.tournament.service.MatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/matches")
public class MatchController {

    private final MatchService matchService;

    @PostMapping("/{matchId}/result")
    public ResponseEntity<String> submitResult(@PathVariable Long matchId, @RequestBody @Valid MatchResultRequestDto request) {

        log.info("Received matchId {} and winnerTeamId {} for submitting result",
                matchId,
                request.getWinnerTeamId());
        matchService.submitMatchResult(matchId, request.getWinnerTeamId());
        return ResponseEntity.ok("Match result submitted successfully");
    }

    @GetMapping("/{matchId}/winner")
    public ResponseEntity<Team> getMatchWinner(@PathVariable Long matchId) {
        return ResponseEntity.ok(matchService.getMatchWinner(matchId));
    }
}
