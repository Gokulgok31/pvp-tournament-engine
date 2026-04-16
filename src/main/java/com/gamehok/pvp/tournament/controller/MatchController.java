package com.gamehok.pvp.tournament.controller;


import com.gamehok.pvp.tournament.service.MatchService;
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
    public ResponseEntity<String> submitResult(@PathVariable Long matchId, @RequestParam Long winnerTeamId) {

        log.info("Received matchId {} and winnerTeamId {} for submitting result", matchId, winnerTeamId);
        matchService.submitMatchResult(matchId, winnerTeamId);
        return ResponseEntity.ok("Match Result Submitted Successfully");
    }


}
